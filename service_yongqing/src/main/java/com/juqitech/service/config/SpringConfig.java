package com.juqitech.service.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.session.ExpiringSession;
import org.springframework.session.data.redis.RedisOperationsSessionRepository;

import com.juqitech.service.cache.TMCacheManager;

@Configuration
@Import(RedisHttpSessionConfig.class)
//@EnableRedisHttpSession
//@EnableCaching
public class SpringConfig {
	private int maxInactiveIntervalInSeconds=Integer.valueOf(System.getProperty("server.session-timeout"));

	@Primary
	@Bean
	public RedisOperationsSessionRepository sessionRepository(RedisTemplate<String, ExpiringSession> sessionRedisTemplate) {
		RedisOperationsSessionRepository sessionRepository = new RedisOperationsSessionRepository(sessionRedisTemplate);
		sessionRepository.setDefaultMaxInactiveInterval(maxInactiveIntervalInSeconds);
		return sessionRepository;
	}

	@Bean
	JedisConnectionFactory jedisConnectionFactory() {
		JedisConnectionFactory factory = new JedisConnectionFactory();
		factory.setHostName(System.getProperty("redis_hostname"));
		factory.setPort(Integer.valueOf(System.getProperty("redis_port")));
		factory.setPassword(System.getProperty("redis_pwd"));
		factory.setUsePool(true);
		return factory;
	}
	
	@Bean(name=TMCacheManager.CACHE_REDIS_TEMPLAT_BEAN)
	RedisTemplate<Object, Object> redisTemplate() {
		RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<Object, Object>();
		redisTemplate.setKeySerializer(new StringRedisSerializer());
		redisTemplate.setValueSerializer(new StringRedisSerializer());
		redisTemplate.setConnectionFactory(jedisConnectionFactory());
		redisTemplate.setEnableTransactionSupport(true);
		return redisTemplate;
	}
}
