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

import com.squareup.javapoet.ClassName;
import java.util.List;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.TypeMirror;

/**
 * @author esotericman
 */
public class BeanDefinition {
  private final TypeElement beanElement;
  private final Class<?> beanClass;
  private final ClassName beanClassName;
  private final List<TypeMirror> constructorParameterTypes;
  private final BeanDependency beanDependency;

  public BeanDefinition(
      TypeElement beanElement,
      List<TypeMirror> constructorParameterTypes,
      BeanDependency beanDependency) {
    this.beanElement = beanElement;
    this.beanClass = beanElement.getClass();
    this.beanClassName = ClassName.get(beanElement);
    this.constructorParameterTypes = constructorParameterTypes;
    this.beanDependency = beanDependency;
  }

  public TypeElement getBeanElement() {
    return beanElement;
  }

  public Class<?> getBeanClass() {
    return beanClass;
  }

  public ClassName getBeanClassName() {
    return beanClassName;
  }

  public List<TypeMirror> getConstructorParameterTypes() {
    return constructorParameterTypes;
  }

  public BeanDependency getBeanDependency() {
    return beanDependency;
  }
}
