/*
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 *
 * Copyright 2013-2020 the original author or authors.
 */
package org.assertj.neo4j.api.beta.error;

import org.assertj.neo4j.api.beta.type.DbEntity;

import java.util.List;
import java.util.Objects;

/**
 * @author patouche - 14/11/2020
 */
public class Missing<E extends DbEntity<E>, M> {

    /** The entity. */
    private final E entity;

    /** The missing data. */
    private final List<  M> data;

    public Missing(final E entity, final List<  M> data) {
        this.entity = entity;
        this.data = data;
    }

    public E getEntity() {
        return entity;
    }

    public List<  M> getData() {
        return data;
    }

    public boolean hasMissing() {
        return !data.isEmpty();
    }

    @Override
    public String toString() {
        return "Missing{entity=" + entity + ", data=" + data + '}';
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Missing<?, ?> that = (Missing) o;
        return Objects.equals(entity, that.entity)
               && Objects.equals(data, that.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(entity, data);
    }
}