/*
 * Copyright 2016 Open mHealth
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

package org.md2k.dsu.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.md2k.dsu.configuration.DataPointSearchConfiguration;
import org.md2k.dsu.configuration.RepositoryConfiguration;
import org.md2k.dsu.domain.DataSample;
import org.openmhealth.dsu.configuration.IntegrationTestConfiguration;
import org.openmhealth.dsu.domain.DataPointSearchCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZoneOffset;
import java.util.List;

import static java.time.temporal.ChronoUnit.DAYS;
import static org.junit.Assert.assertTrue;
import static org.md2k.dsu.domain.DataSampleFactory.newDataSample;


/**
 * @author Emerson Farrugia
 * @author Wallace Wadge
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {
        IntegrationTestConfiguration.class,
        RepositoryConfiguration.class
})
public class JpaDataSampleRepositoryIntegrationTests extends DataSampleRepositoryIntegrationTests {

    @Autowired
    MCerebrumApplicationRepository mCerebrumApplicationRepository;

    @Autowired
    MCerebrumPlatformRepository mCerebrumPlatformRepository;

    @Autowired
    MCerebrumPlatformAppRepository mCerebrumPlatformAppRepository;

    @Autowired
    DataSourceRepository dataSourceRepository;

    @Autowired
    DataStreamRepository dataStreamRepository;

    @Autowired
    ParticipantRepository participantRepository;

    @Test
    @Transactional
    public void shouldFindJustCreatedEntries() {

        DataSample dataSample = setupData();

        DataPointSearchConfiguration dpsc = new DataPointSearchConfiguration();
        DataPointSearchCriteria criteria = new DataPointSearchCriteria();
        DataPointSearchCriteria dataPointSearchCriteria = new DataPointSearchCriteria();
        dpsc.setSearchCriteria(dataPointSearchCriteria);
        dataPointSearchCriteria.setEffectiveBefore(dataSample.getEffectiveTimestamp().atOffset(ZoneOffset.UTC));
        dataPointSearchCriteria.setEffectiveOnOrAfter(dataSample.getEffectiveTimestamp().atOffset(ZoneOffset.UTC));


        List<DataSample> result = dataSampleRepository.findBySearchConfigurationAndSearchCriteria(dpsc, criteria, 0, Long.MAX_VALUE);
        assertTrue(result.iterator().next().getId().equals(dataSample.getId()));
    }

    @Test
    @Transactional
    public void shouldFindNoEntriesMatchingFilter() {

        DataSample dataSample = setupData();

        DataPointSearchConfiguration dpsc = new DataPointSearchConfiguration();
        DataPointSearchCriteria criteria = new DataPointSearchCriteria();
        DataPointSearchCriteria dataPointSearchCriteria = new DataPointSearchCriteria();
        dpsc.setSearchCriteria(dataPointSearchCriteria);
        dpsc.setDatabaseQueryFilters("datasources.identifier=='garbage'");
        dataPointSearchCriteria.setEffectiveBefore(dataSample.getEffectiveTimestamp().atOffset(ZoneOffset.UTC));
        dataPointSearchCriteria.setEffectiveOnOrAfter(dataSample.getEffectiveTimestamp().atOffset(ZoneOffset.UTC));


        List<DataSample> result = dataSampleRepository.findBySearchConfigurationAndSearchCriteria(dpsc, criteria, 0, Long.MAX_VALUE);
        assertTrue(result.isEmpty());
    }

    @Test
    @Transactional
    public void shouldFindNoEntriesOutsideOfRange() {

        DataSample dataSample = setupData();

        DataPointSearchConfiguration dpsc = new DataPointSearchConfiguration();
        DataPointSearchCriteria criteria = new DataPointSearchCriteria();
        DataPointSearchCriteria dataPointSearchCriteria = new DataPointSearchCriteria();
        dpsc.setSearchCriteria(dataPointSearchCriteria);
        dataPointSearchCriteria.setEffectiveBefore(dataSample.getEffectiveTimestamp().atOffset(ZoneOffset.UTC).minus(1, DAYS));
        dataPointSearchCriteria.setEffectiveOnOrAfter(dataSample.getEffectiveTimestamp().atOffset(ZoneOffset.UTC));


        List<DataSample> result = dataSampleRepository.findBySearchConfigurationAndSearchCriteria(dpsc, criteria, 0, Long.MAX_VALUE);
        assertTrue(result.isEmpty());
    }

    private DataSample setupData() {
        DataSample dataSample = newDataSample();
        dataSample.getDataStream().getDataSource().getPlatformApp().setMetadata("{}");
        mCerebrumPlatformAppRepository.save(dataSample.getDataStream().getDataSource().getPlatformApp());
        mCerebrumPlatformRepository.save(dataSample.getDataStream().getDataSource().getPlatform());
        mCerebrumApplicationRepository.save(dataSample.getDataStream().getDataSource().getApplication());
        dataSourceRepository.save(dataSample.getDataStream().getDataSource());
        participantRepository.save(dataSample.getDataStream().getParticipant());
        dataStreamRepository.save(dataSample.getDataStream());
        dataSampleRepository.save(dataSample);


        return dataSample;

    }


}
