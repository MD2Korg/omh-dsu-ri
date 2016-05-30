package org.openmhealth.dsu.controller;

import org.md2k.dsu.configuration.DataPointSearchConfiguration;
import org.md2k.dsu.repository.DataPointSearchConfigurationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.openmhealth.dsu.controller.DataPointSearchController.*;
import static org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder.fromMethodCall;
import static org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder.on;

/**
 * CRUD support for DataPointSearchConfiguration
 * Created by wwadge on 26/05/2016.
 */
@ApiController
public class DataPointSearchConfigurationController {

    @Autowired
    DataPointSearchConfigurationRepository dataPointSearchConfigurationRepository;

    private static final String TOP_LEVEL = "/dataPointSearchConfigurations";


    /**
     * @return
     */
    @RequestMapping(value = TOP_LEVEL, method = RequestMethod.GET)
    public ResponseEntity<?> listAllConfigurations(@RequestParam(value = RESULT_OFFSET_PARAMETER, defaultValue = "0") final int offset,
                                                   @RequestParam(value = RESULT_LIMIT_PARAMETER, defaultValue = DEFAULT_RESULT_LIMIT) final int limit
    ) {


        Page<DataPointSearchConfiguration> configurations = dataPointSearchConfigurationRepository.findAll(new PageRequestToOffset(offset, limit));

        if (configurations == null || configurations.getTotalElements() == 0) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(configurations.getContent());
    }


    @RequestMapping(value = TOP_LEVEL + "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DataPointSearchConfiguration> getDataPointSearchConfiguration(@PathVariable("id") String id) {

        DataPointSearchConfiguration dataPointSearchConfiguration = dataPointSearchConfigurationRepository.findOne(id);

        if (dataPointSearchConfiguration == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(dataPointSearchConfiguration);
    }

    @RequestMapping(value = TOP_LEVEL, method = RequestMethod.POST)
    public ResponseEntity<Void> createDataPointSearchConfiguration(@RequestBody @Valid DataPointSearchConfiguration dataPointSearchConfiguration) {


        if (dataPointSearchConfiguration.getId() != null && dataPointSearchConfigurationRepository.exists(dataPointSearchConfiguration.getId())) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        dataPointSearchConfigurationRepository.save(dataPointSearchConfiguration);

        return ResponseEntity.created(fromMethodCall(on(DataPointSearchConfigurationController.class).getDataPointSearchConfiguration(dataPointSearchConfiguration.getId())).build().toUri()).build();
    }


    @RequestMapping(value = TOP_LEVEL + "/{id}", method = {RequestMethod.PUT, RequestMethod.POST})
    public ResponseEntity<DataPointSearchConfiguration> updateDataPointSearchConfiguration(@PathVariable("id") String id, @RequestBody @Valid DataPointSearchConfiguration dataPointSearchConfiguration) {

        DataPointSearchConfiguration currentDataPointSearchConfiguration = dataPointSearchConfigurationRepository.findOne(id);

        if (currentDataPointSearchConfiguration == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        currentDataPointSearchConfiguration.setDatabaseQueryFilters(dataPointSearchConfiguration.getDatabaseQueryFilters());
        currentDataPointSearchConfiguration.setSearchCriteria(dataPointSearchConfiguration.getSearchCriteria());
        currentDataPointSearchConfiguration.setMapperSettings(dataPointSearchConfiguration.getMapperSettings());

        dataPointSearchConfigurationRepository.save(currentDataPointSearchConfiguration);
        return ResponseEntity.ok(currentDataPointSearchConfiguration);
    }


    @RequestMapping(value = TOP_LEVEL + "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteDataPointSearchConfiguration(@PathVariable("id") String id) {

        DataPointSearchConfiguration dataPointSearchConfiguration = dataPointSearchConfigurationRepository.findOne(id);

        if (dataPointSearchConfiguration == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        dataPointSearchConfigurationRepository.delete(id);
        return ResponseEntity.accepted().build();
    }


    class PageRequestToOffset implements Pageable {

        private final int offset;
        private final int limit;

        public PageRequestToOffset(int offset, int limit) {
            this.offset = offset;
            this.limit = limit;
        }

        @Override
        public int getPageNumber() {
            return 0;
        }

        @Override
        public int getPageSize() {
            return limit;
        }

        @Override
        public int getOffset() {
            return offset;
        }

        @Override
        public Sort getSort() {
            return null;
        }

        @Override
        public Pageable next() {
            return null;
        }

        @Override
        public Pageable previousOrFirst() {
            return this;
        }

        @Override
        public Pageable first() {
            return this;
        }

        @Override
        public boolean hasPrevious() {
            return false;
        }
    }
}
