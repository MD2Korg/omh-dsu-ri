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
