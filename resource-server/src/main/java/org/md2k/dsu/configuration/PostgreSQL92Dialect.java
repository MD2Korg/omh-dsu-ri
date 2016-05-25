package org.md2k.dsu.configuration;

/**
 * Created by wwadge on 24/05/2016.
 */

import org.hibernate.dialect.PostgreSQL9Dialect;

import java.sql.Types;

/**
 * This class is in Hibernate v5+. It only exists here until we migrate to v5.
 */
public class PostgreSQL92Dialect extends PostgreSQL9Dialect {

    /**
     * Constructs a PostgreSQL92Dialect
     */
    public PostgreSQL92Dialect() {
        super();
        registerColumnType(Types.JAVA_OBJECT, "json");
        registerColumnType(Types.JAVA_OBJECT, "jsonb");
    }
}