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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author esotericman
 */
public class BeanBuilderFactory extends AbstractBeanFactory {
  private final List<String> packages = new ArrayList<>();

  private BeanBuilderFactory() {}

  public BeanBuilderFactoryBuilder build() {
    return new BeanBuilderFactoryBuilder(new BeanBuilderFactory());
  }

  public List<String> getPackages() {
    return packages;
  }

  static class BeanBuilderFactoryBuilder {
    private final BeanBuilderFactory beanBuilderFactory;

    private BeanBuilderFactoryBuilder(BeanBuilderFactory beanBuilderFactory) {
      this.beanBuilderFactory = beanBuilderFactory;
    }

    public BeanBuilderFactoryBuilder packages(String... packages) {
      this.beanBuilderFactory.packages.addAll(Arrays.asList(packages));
      return this;
    }

    public BeanBuilderFactory builder() {
      return this.beanBuilderFactory;
    }
  }
}
