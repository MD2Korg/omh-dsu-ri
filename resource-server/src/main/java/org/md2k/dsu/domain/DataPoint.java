package org.md2k.dsu.domain;

/**
 * A data point in a {@link DataStream}. This marker interface is an attempt to disambiguate the {@link
 * org.openmhealth.schema.domain.omh.DataPoint} class from the MD2K entity of the same name. The MD2K entity and
 * corresponding classes use the {@link DataSample} nomenclature instead.
 *
 * @author Emerson Farrugia
 * @see {@link DataSample}
 */
public interface DataPoint {

}
