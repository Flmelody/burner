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

import com.squareup.javapoet.CodeBlock;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterSpec;
import com.squareup.javapoet.TypeSpec;
import org.flmelody.burner.bean.BeanDefinition;
import org.flmelody.burner.bean.BeanFactory;

import javax.lang.model.type.TypeMirror;
import java.util.List;

import static javax.lang.model.element.Modifier.PUBLIC;
import static javax.lang.model.element.Modifier.STATIC;

/**
 * @author esotericman
 */
public class BeanProxyWriter {
    private final BeanDefinition beanDefinition;

    public BeanProxyWriter(BeanDefinition beanDefinition) {
        this.beanDefinition = beanDefinition;
    }


    public JavaFile generateFile() {
        var definitionSpec = TypeSpec.classBuilder("$%s$Burner$Proxy".formatted(beanDefinition.getBeanClassName().simpleName()))
                .addModifiers(PUBLIC)
                .addMethod(generateMethod())
                .build();
        return JavaFile.builder(beanDefinition.getBeanClassName().packageName(), definitionSpec).build();
    }

    private MethodSpec generateMethod() {
        List<TypeMirror> constructorParameterTypes = beanDefinition.getConstructorParameterTypes();
        //
        return MethodSpec.methodBuilder("create")
                .addModifiers(PUBLIC, STATIC)
                .addParameter(ParameterSpec.builder(BeanFactory.class, "beanFactory").build())
                .addStatement(CodeBlock.builder()
                        .add("return")
                        .add(" new")
                        .add(" $T", beanDefinition.getBeanClassName())
                        .add("()")
                        .build())
                .returns(beanDefinition.getBeanClassName())
                .build();
    }
}
