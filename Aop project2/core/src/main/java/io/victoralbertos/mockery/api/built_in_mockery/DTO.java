/*
 * Copyright 2016 Victor Albertos
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

package io.victoralbertos.mockery.api.built_in_mockery;

import io.victoralbertos.mockery.api.Mockery;
import io.victoralbertos.mockery.internal.built_in_mockery.DTOMockery;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Retention(RUNTIME)
@Target({METHOD, PARAMETER})
@Mockery(DTOMockery.class)
/**
 * Decorate a method/param with this annotation to supply mocking and validation specs for an specific type.
 * To use it, decorate the method/param with this annotation, supplying a valid {@link DTO.Behaviour} implementation.
 */
public @interface DTO {
  /**
   * The {@code class} of the implementation of {@link Behaviour}
   */
  Class<? extends Behaviour> value();

  /**
   * @param <T> The DTO {@code type}
   */
  interface Behaviour<T> {
    /**
     * Return a legal instance of the DTO {@code type}.
     */
    T legal();

    /**
     * Validate the object and throw an AssertionError in case it does not fulfill the requirements.
     */
    void validate(T candidate) throws AssertionError;
  }

}
