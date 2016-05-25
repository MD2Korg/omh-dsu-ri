package org.md2k.dsu.configuration;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.Predicate;
import org.md2k.dsu.domain.QDataSample;

import java.util.Map;

/**
 * RSQL Mappings.
 * Created by wwadge on 24/05/2016.
 */
public class DatabaseQueryFilterAliasConfiguration {

    public static Map<String, Path> searchMappings = ImmutableMap.<String, Path>builder()
            .put("datapoints.created_at", QDataSample.dataSample.creationTimestamp)
            .put("datapoints.updated_at", QDataSample.dataSample.modificationTimestamp)
            .put("datapoints.timestamp", QDataSample.dataSample.effectiveTimestamp)

            .put("participants.identifier", QDataSample.dataSample.dataStream.participant.identifier)

            .put("datasources.identifier", QDataSample.dataSample.dataStream.dataSource.identifier)
            .put("datasources.datasourcetype", QDataSample.dataSample.dataStream.dataSource.type)

            .put("m_cerebrum_platforms.platformtype", QDataSample.dataSample.dataStream.dataSource.platform.type)
            .put("m_cerebrum_platforms.identifier", QDataSample.dataSample.dataStream.dataSource.platform.identifier)
            .put("m_cerebrum_platforms.created_at", QDataSample.dataSample.dataStream.dataSource.platform.creationTimestamp)
            .put("m_cerebrum_platforms.updated_at", QDataSample.dataSample.dataStream.dataSource.platform.modificationTimestamp)

            .put("m_cerebrum_platform_apps.identifier", QDataSample.dataSample.dataStream.dataSource.platformApp.identifier)
            .put("m_cerebrum_platform_apps.type", QDataSample.dataSample.dataStream.dataSource.platformApp.type)
            .put("m_cerebrum_platform_apps.created_at", QDataSample.dataSample.dataStream.dataSource.platformApp.creationTimestamp)
            .put("m_cerebrum_platform_apps.updated_at", QDataSample.dataSample.dataStream.dataSource.platformApp.modificationTimestamp)

            .put("m_cerebrum_applications.identifier", QDataSample.dataSample.dataStream.dataSource.application.identifier)
            .put("m_cerebrum_applications.type", QDataSample.dataSample.dataStream.dataSource.application.type)
            .put("m_cerebrum_applications.created_at", QDataSample.dataSample.dataStream.dataSource.application.creationTimestamp)
            .put("m_cerebrum_applications.updated_at", QDataSample.dataSample.dataStream.dataSource.application.modificationTimestamp)
            .build();

    public static Map<String, Predicate> predicateCache = Maps.newConcurrentMap();
}
