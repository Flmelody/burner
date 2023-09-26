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

import java.util.Collection;
import org.flmelody.burner.exception.BeanException;

/**
 * @author esotericman
 */
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry
    implements BeanFactory {
  @Override
  public <T> T getBean(Class<T> beanType) throws BeanException {
    return null;
  }

  @Override
  public <T> T getBean(String beanName) throws BeanException {
    return null;
  }

  @Override
  public <T> Collection<T> getBeans(Class<T> beanType) throws BeanException {
    return null;
  }

  @Override
  public <T> Collection<T> getBeans(String beanName) throws BeanException {
    return null;
  }
}
