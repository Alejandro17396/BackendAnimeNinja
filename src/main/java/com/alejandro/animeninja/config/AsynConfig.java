package com.alejandro.animeninja.config;

import java.util.concurrent.Executor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@EnableAsync
public class AsynConfig {

	@Bean(name ="asyncExecutor")
	public Executor asynExevutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(4);
		executor.setCorePoolSize(8);
		executor.setQueueCapacity(16);
		executor.setThreadNamePrefix("AsyncThread-");
		executor.initialize();
		return executor;
	}
	
}
