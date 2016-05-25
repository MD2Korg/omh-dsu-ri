TODO turn this into a proper README

# Logic
1. data point search criteria come in
1. query is issued
1. result set (or entity graph) is mapped
1. data points go out


can we turn the mapping into data? we might be able to do that for most of the mappings,
although it's likely some mappings would just be too complicated

so how do we pick which mapper to use? mapper keys, overriding defaults if not specified.

risk is that something is misconfigured and we get a silent failure; i.e. no data is returned even though
data should be returned. how do we mitigate this?
maybe a simple sanity check? the query is going to get some number of data samples back
 if the data points being returned pre-mapping isn't equal, warn

### query filters depend on the data point search criteria

userId
-> datapoints.datastreams.participants.identifier

schemaNamespace, schemaName
-> datapoints.datastreams.datasources.datasourcetype (with mapping)

creationTimestampRange
->  datapoints.created_at, datapoints.offset

not worrying about schemaVersion for now


### mapping depends on the criteria more than the result set


there are properties that are independent of the criteria

header.id
-> datapoints.id (could be UUID)

header.externalId
-> datapoints.id

header.creationDateTime
-> datapoints.created_at

header.acquisitionProvenance.sourceName
-> contatenation of
- datapoints.datastreams.datasources.identifier
- datapoints.datastreams.datasources.type
- datapoints.datastreams.datasources.mcerebrum*.identifier
- datapoints.datastreams.datasources.mcerebrum*.type

header.userId
-> datapoints.datastreams.participants.identifier

there are other properties that typically depend on the criteria, specifically the schema ID

- header.acquisitionProvenance.modality
- body.descriptiveStatistic
- body.someProperty?
  -   sample index to property
- body.effectiveTimeFrame
  -    for point in time, datapoints.created_at, datapoints.offset
  -   for time intervals, either created_at to updated_at or created_at minus some interval presumably

a data-driven mapping would allow the specification of these properties at runtime

### sample search configuration

```
{
  "searchCriteria": {
    "schemaNamespace": "omh",
    "schemaName": "heart-rate"
  },
  "databaseQueryFilters": "...",
  "mapperSettings": {
    "mapper": "default",
    "schemaNamespace": "omh",
    "schemaName": "heart-rate",
    "schemaVersion": "1.0",
    "propertyMappings": [
      {
        "outputPath": "body.heart_rate.unit",
        "constantValue": "beats/min"
      },
      {
        "outputPath": "body.heart_rate.value",
        "inputField": "sample",
        "inputJsonPath": "$[0]"
      }
    ]
  }
}
```


### upcoming issues

- can we filter on JSON value, e.g. metadata?
- recombining streams, e.g. accel_X, accel_y, accel_Z -> acceleration.json ... but mapping refers to a single data point
  - assume new streams will be built
- count everything wants count distinct on participants and filtering
- seems brittle in the face of future request parameters
  - static ones are added, rest can be programmatically controlled, eg. statistic
- if you specifically want to request RIP stretch median data, is that pre-configured, or dynamic?
  - if preconfigured, then would have to create sections for each statistic (lots of repetition)
  - if dynamic, then wouldn't know what to query for, potentially loading up a ton of results for no reason
    e.g. 20th percentile, when no data has it
      - unless white listed in the search criteria