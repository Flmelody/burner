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

package org.flmelody.burner.processor;

import com.google.auto.service.AutoService;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.Name;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.util.ElementFilter;
import org.flmelody.burner.bean.BeanDefinition;
import org.flmelody.burner.bean.BeanElementResolver;

/**
 * @author esotericman
 */
@SupportedAnnotationTypes({
  AnnotationConst.BEAN,
  AnnotationConst.CONFIGURATION,
  AnnotationConst.PROTOTYPE,
  AnnotationConst.POST_CONSTRUCT,
  AnnotationConst.PRE_DESTROY,
  AnnotationConst.SINGLETON,
  AnnotationConst.INJECT
})
@AutoService(Processor.class)
public class FactoryProcessor extends AbstractProcessor {

  @Override
  public SourceVersion getSupportedSourceVersion() {
    return SourceVersion.latestSupported();
  }

  @Override
  public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
    Set<? extends Element> elementsAnnotatedWithSingleton =
        roundEnv.getElementsAnnotatedWith(Singleton.class);
    Set<TypeElement> typeElements = ElementFilter.typesIn(elementsAnnotatedWithSingleton);

    Set<? extends Element> elementsAnnotatedWithInject =
        roundEnv.getElementsAnnotatedWith(Inject.class);
    Set<VariableElement> variableElements = ElementFilter.fieldsIn(elementsAnnotatedWithInject);
    Map<Name, List<VariableElement>> fieldsDependency =
        variableElements.stream()
            .collect(
                Collectors.groupingBy(
                    variableElement -> variableElement.getEnclosingElement().getSimpleName()));

    BeanElementResolver beanElementResolver = new BeanElementResolver();
    for (TypeElement typeElement : typeElements) {
      BeanDefinition beanDefinition =
          beanElementResolver.resolve(
              typeElement,
              fieldsDependency.get(typeElement.getSimpleName()),
              processingEnv.getMessager());
    }

    return false;
  }
}
