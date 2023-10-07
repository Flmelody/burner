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

/**
 * @author esotericman
 */
public interface AnnotationConst {
  //  String BEAN = "org.flmelody.burner.annotation.Bean";
  String PROTOTYPE = "org.flmelody.burner.annotation.Prototype";
  //  String CONFIGURATION = "org.flmelody.burner.annotation.Configuration";
  String POST_CONSTRUCT = "org.flmelody.burner.annotation.PostConstruct";
  String PRE_DESTROY = "org.flmelody.burner.annotation.PreDestroy";
  String SINGLETON = "jakarta.inject.Singleton";
  String INJECT = "jakarta.inject.Inject";
}
