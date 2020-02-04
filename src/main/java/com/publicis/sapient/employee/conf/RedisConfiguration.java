package com.publicis.sapient.employee.conf;

import com.publicis.sapient.employee.exception.NoRecordFoundException;
import com.publicis.sapient.employee.model.Employee;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import redis.embedded.RedisServer;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.IOException;

@Configuration
@EnableRedisRepositories
public class RedisConfiguration {
	private RedisServer redisServer;

	public RedisConfiguration(org.springframework.boot.autoconfigure.data.redis.RedisProperties redisProperties) {
		this.redisServer = new RedisServer(redisProperties.getPort());
	}

	@PostConstruct
	public void init() throws IOException, NoRecordFoundException {
		redisServer.start();
	}

	@PreDestroy
	public void destory() {
		redisServer.stop();
	}

	@Bean
	public LettuceConnectionFactory redisConnectionFactory(RedisProperties redisProperties) {
		return new LettuceConnectionFactory(redisProperties.getRedisHost(), redisProperties.getRedisPort());
	}

	@Bean
	public RedisTemplate<String, Employee> redisTemplate(LettuceConnectionFactory connectionFactory) {
		RedisTemplate<String, Employee> template = new RedisTemplate<>();
		template.setConnectionFactory(connectionFactory);
		return template;
	}
}
