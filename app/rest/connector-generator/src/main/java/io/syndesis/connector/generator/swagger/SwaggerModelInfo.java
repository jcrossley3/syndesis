/*
 * Copyright (C) 2016 Red Hat, Inc.
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
package io.syndesis.connector.generator.swagger;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.swagger.models.Swagger;
import io.syndesis.model.Violation;
import org.immutables.value.Value;

import java.util.Collections;
import java.util.List;

/**
 * Class holding information about a swagger model and related validations.
 */
@Value.Immutable
@JsonDeserialize(builder = SwaggerModelInfo.Builder.class)
public interface SwaggerModelInfo {

    Swagger getModel();

    @Value.Default
    default List<Violation> getErrors() {
        return Collections.emptyList();
    }

    @Value.Default
    default List<Violation> getWarnings() {
        return Collections.emptyList();
    }

    default SwaggerModelInfo withError(Violation violation) {
        return new Builder()
            .createFrom(this)
            .addError(violation)
            .build();
    }

    default SwaggerModelInfo withWarning(Violation violation) {
        return new Builder()
            .createFrom(this)
            .addWarning(violation)
            .build();
    }

    class Builder extends ImmutableSwaggerModelInfo.Builder {}

}
