/*
 * Copyright (C) 2023 Flmelody.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.flmelody.burner.bean;

import java.util.List;
import javax.lang.model.element.VariableElement;

/**
 * @author esotericman
 */
public class BeanDependency {
  private final List<VariableElement> variableElements;

  public BeanDependency(List<VariableElement> variableElements) {
    this.variableElements = variableElements;
  }

  public List<VariableElement> getVariableElements() {
    return variableElements;
  }
}
