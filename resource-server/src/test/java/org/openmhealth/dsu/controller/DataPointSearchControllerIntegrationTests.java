/*
 * Copyright 2014 Open mHealth
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.openmhealth.dsu.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import com.querydsl.core.types.Predicate;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.md2k.dsu.configuration.DataPointSearchConfiguration;
import org.md2k.dsu.configuration.DataPointSearchConfigurationFactory;
import org.md2k.dsu.repository.DataPointSearchConfigurationRepository;
import org.mockito.Mockito;
import org.mockito.stubbing.Answer;
import org.openmhealth.dsu.configuration.Application;
import org.openmhealth.dsu.configuration.TestConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.Mockito.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder.fromMethodCall;
import static org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder.on;


/**
 * A suite of integration tests for the data point controller.
 *
 * @author Emerson Farrugia
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@SpringApplicationConfiguration(classes = {
        Application.class,
        DataPointSearchControllerIntegrationTests.Configuration.class
}
)
public class DataPointSearchControllerIntegrationTests {


    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    DataPointSearchConfigurationRepository repository;

    @TestConfiguration
    static class Configuration {

        @Bean
        @Primary
        public DataPointSearchConfigurationRepository dataPointService() {
            DataPointSearchConfigurationRepository repo = mock(DataPointSearchConfigurationRepository.class);
            when(repo.findAll(Mockito.any(Pageable.class))).then((Answer<Page<DataPointSearchConfiguration>>) invocationOnMock -> new PageImpl<>(Lists.newArrayList()));
            return repo;
        }
    }

    @After
    public void resetRepoMock() {
        reset(repository);
    }

    @Autowired
    private WebApplicationContext applicationContext;

    private MockMvc mockMvc;

    @Before
    public void initialiseClientMock() {
        mockMvc = MockMvcBuilders.webAppContextSetup(this.applicationContext).build();
    }

    @Test
    public void shouldCreateNewDataPointSearchConfiguration() throws Exception {

        mockMvc.perform(post(fromMethodCall(on(DataPointSearchConfigurationController.class).createDataPointSearchConfiguration(new DataPointSearchConfiguration())).build().toUri())
                .accept(APPLICATION_JSON)
                .contentType(APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(DataPointSearchConfigurationFactory.newDataPointSearchConfiguration())))
                .andExpect(status().isCreated());


        verify(repository, times(1)).save(any(DataPointSearchConfiguration.class));
    }

    @Test
    public void shouldNotClobberExistingDataPointSearchConfiguration() throws Exception {

        when(repository.exists(Mockito.any(String.class))).thenReturn(true);

        DataPointSearchConfiguration config = DataPointSearchConfigurationFactory.newDataPointSearchConfiguration();
        config.setId("123");
        mockMvc.perform(post(fromMethodCall(on(DataPointSearchConfigurationController.class).createDataPointSearchConfiguration(new DataPointSearchConfiguration())).build().toUri())
                .accept(APPLICATION_JSON)
                .contentType(APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(config)))
                .andExpect(status().isConflict());


        verify(repository, times(0)).save(any(DataPointSearchConfiguration.class));
    }


    @Test
    public void shouldDeleteDataPointSearchConfiguration() throws Exception {

        when(repository.findOne(any(String.class))).thenReturn(new DataPointSearchConfiguration());

        mockMvc.perform(delete(fromMethodCall(on(DataPointSearchConfigurationController.class).deleteDataPointSearchConfiguration("1")).queryParam("id", "1").build().toUri())
                .accept(APPLICATION_JSON)
                .contentType(APPLICATION_JSON))
                .andExpect(status().isAccepted());


        verify(repository, times(1)).delete("1");
    }

    @Test
    public void shouldReturnErrorOnTryingToDeleteNonExistingDataPointSearchConfiguration() throws Exception {

        when(repository.findOne(any(String.class))).thenReturn(null);

        mockMvc.perform(delete(fromMethodCall(on(DataPointSearchConfigurationController.class).deleteDataPointSearchConfiguration("1")).queryParam("id", "1").build().toUri())
                .accept(APPLICATION_JSON)
                .contentType(APPLICATION_JSON))
                .andExpect(status().isNotFound());


    }


    @Test
    public void shouldGetExistingEntry() throws Exception {

        when(repository.findOne(any(String.class))).thenReturn(new DataPointSearchConfiguration());
        mockMvc.perform(get(fromMethodCall(on(DataPointSearchConfigurationController.class).getDataPointSearchConfiguration("123")).build().toUri())
                .accept(APPLICATION_JSON)
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk());


        verify(repository, times(1)).findOne("123");
    }

    @Test
    public void shouldGetAllEntriesWithNoData() throws Exception {

        mockMvc.perform(get(fromMethodCall(on(DataPointSearchConfigurationController.class).listAllConfigurations(0, 20)).build().toUri())
                .accept(APPLICATION_JSON)
                .contentType(APPLICATION_JSON))
                .andExpect(status().isNoContent());


        verify(repository, times(1)).findAll(any(Predicate.class), any(PageRequest.class));
    }

    @Test
    public void shouldGetAllEntriesWithSomeData() throws Exception {

        when(repository.findAll(any(Predicate.class), any(Pageable.class))).thenReturn(new PageImpl<>(Lists.newArrayList(new DataPointSearchConfiguration())));
        mockMvc.perform(get(fromMethodCall(on(DataPointSearchConfigurationController.class).listAllConfigurations(0, 10)).build().toUri())
                .accept(APPLICATION_JSON)
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk());


        verify(repository, times(1)).findAll(any(Predicate.class), any(PageRequest.class));
    }

}
