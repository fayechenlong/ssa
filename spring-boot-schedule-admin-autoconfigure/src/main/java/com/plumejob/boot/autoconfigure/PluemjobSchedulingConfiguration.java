/*
 * Copyright 2002-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.plumejob.boot.autoconfigure;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Role;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.annotation.ScheduledAnnotationBeanPostProcessor;


@Configuration
@ComponentScan("com.plumejob.boot.autoconfigure")
@Role(BeanDefinition.ROLE_INFRASTRUCTURE)
public class PluemjobSchedulingConfiguration {

	@ConditionalOnMissingBean(ScheduledAnnotationBeanPostProcessor.class)
	@Bean
	@Role(BeanDefinition.ROLE_INFRASTRUCTURE)
	public PlumejobScheduledAnnotationBeanPostProcessor scheduledAnnotationProcessor() {
		return new PlumejobScheduledAnnotationBeanPostProcessor();
	}

}
