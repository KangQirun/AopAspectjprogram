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

package io.victoralbertos.mockery.internal;

import io.victoralbertos.mockery.api.built_in_mockery.DTO;
import java.util.List;

public class Mockeries {
  public static class MockModel implements DTO.Behaviour<List<Model>> {

    @Override public List<Model> legal() {
      return null;
    }

    @Override public void validate(List<Model> candidate) throws AssertionError {
      assert candidate != null : "List of models can not be null";
      assert !candidate.isEmpty() : "List of models can not be empty";
    }
  }
}
