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

import static javax.tools.Diagnostic.Kind.ERROR;

import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.processing.Messager;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.util.ElementFilter;

/**
 * @author esotericman
 */
public class BeanElementResolver {
  public BeanDefinition resolve(
      TypeElement element, List<VariableElement> variableElements, Messager messager) {
    return isConcreteClass(element)
        ? resolveConcreteClass(element, variableElements, messager)
        : failOnInvalidElement(element, messager);
  }

  private boolean isConcreteClass(TypeElement element) {
    return element.getKind().isClass() && !element.getModifiers().contains(Modifier.ABSTRACT);
  }

  private BeanDefinition resolveConcreteClass(
      TypeElement element, List<VariableElement> variableElements, Messager messager) {
    List<ExecutableElement> constructors =
        ElementFilter.constructorsIn(element.getEnclosedElements());
    return constructors.size() == 1
        ? resolveDefinition(element, constructors, variableElements)
        : failOnTooManyConstructors(element, messager, constructors);
  }

  private BeanDefinition resolveDefinition(
      TypeElement element,
      List<ExecutableElement> constructors,
      List<VariableElement> variableElements) {
    ExecutableElement constructor = constructors.get(0);
    return new BeanDefinition(
        element,
        constructor.getParameters().stream()
            .map(VariableElement::asType)
            .collect(Collectors.toList()),
        new BeanDependency(variableElements));
  }

  private BeanDefinition failOnTooManyConstructors(
      TypeElement element, Messager messager, List<ExecutableElement> constructors) {
    String constructorsRep =
        constructors.stream().map(ExecutableElement::toString).collect(Collectors.joining(", "));
    messager.printMessage(
        ERROR,
        String.format("Too many constructors of the class %s (%s)", constructorsRep, element),
        element);
    throw new IllegalStateException("Compilation faced error.");
  }

  private BeanDefinition failOnInvalidElement(TypeElement element, Messager messager) {
    messager.printMessage(ERROR, String.format("Invalid type element: %s", element));
    throw new IllegalStateException("Compilation faced error.");
  }
}
