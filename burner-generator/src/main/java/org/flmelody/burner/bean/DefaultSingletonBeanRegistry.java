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

import java.util.HashMap;
import java.util.Map;

/**
 * @author esotericman
 */
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {
  private final Map<String, Object> singletonBeans = new HashMap<>(2 << 4);

  @Override
  public <T> T getSingleton(Class<T> beanType) {
    return getSingleton(beanType.getSimpleName());
  }

  @Override
  public <T> T getSingleton(String beanName) {
    //noinspection unchecked
    return (T) singletonBeans.get(beanName);
  }

  @Override
  public <T> void registerSingleton(T singletonBean) {
    registerSingleton(singletonBean.getClass().getSimpleName(), singletonBean);
  }

  @Override
  public <T> void registerSingleton(String beanName, T singletonBean) {
    singletonBeans.put(beanName, singletonBean);
  }
}
