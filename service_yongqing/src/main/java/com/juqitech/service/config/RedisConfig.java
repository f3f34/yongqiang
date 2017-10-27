package com.juqitech.service.config;

import com.juqitech.service.cache.TMCacheManager;
import com.juqitech.service.utils.StringUtil;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.session.ExpiringSession;
import org.springframework.session.data.redis.RedisOperationsSessionRepository;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
@Import(RedisHttpSessionConfig.class)
//@EnableRedisHttpSession
public class RedisConfig {

	private int maxInactiveIntervalInSeconds = Integer
			.valueOf(System.getProperty("server.session-timeout"));
	
	@Primary
	@Bean
	public RedisOperationsSessionRepository sessionRepository(
			RedisTemplate<String, ExpiringSession> sessionRedisTemplate) {
		RedisOperationsSessionRepository sessionRepository = new RedisOperationsSessionRepository(
				sessionRedisTemplate);
		sessionRepository
				.setDefaultMaxInactiveInterval(maxInactiveIntervalInSeconds);
		return sessionRepository;
	}
	
	@Bean
	public JedisConnectionFactory jedisConnectionFactory() {
		JedisConnectionFactory factory = new JedisConnectionFactory();
		factory.setHostName(System.getProperty("redis_hostname"));
		factory.setPort(Integer.valueOf(System.getProperty("redis_port")));
		factory.setPassword(System.getProperty("redis_pwd"));
		factory.setUsePool(true);
		factory.setTimeout(1000);
		JedisPoolConfig poolConfig = new JedisPoolConfig();
		poolConfig.setTestOnBorrow(true);
		poolConfig.setTestOnCreate(true);
		poolConfig.setTestWhileIdle(true);
		poolConfig.setTestOnReturn(true);
		poolConfig.setTimeBetweenEvictionRunsMillis(1000);
		poolConfig.setSoftMinEvictableIdleTimeMillis(60000);
		if(StringUtil.isNotEmpty(System.getProperty("redis_maxTotal"))){
			poolConfig.setMaxTotal(Integer.valueOf(System.getProperty("redis_maxTotal")));
		}else{
			poolConfig.setMaxTotal(150);
		}
		if(StringUtil.isNotEmpty(System.getProperty("redis_maxIdle"))){
			poolConfig.setMaxIdle(Integer.valueOf(System.getProperty("redis_maxIdle")));
		}else{
			poolConfig.setMaxIdle(30);
		}
		if(StringUtil.isNotEmpty(System.getProperty("redis_minIdle"))){
			poolConfig.setMinIdle(Integer.valueOf(System.getProperty("redis_minIdle")));
		}else{
			poolConfig.setMinIdle(10);
		}
		if(StringUtil.isNotEmpty(System.getProperty("redis_maxWaitMillis"))){
			poolConfig.setMaxWaitMillis(Integer.valueOf(System.getProperty("redis_maxWaitMillis")));
		}else{
			poolConfig.setMaxWaitMillis(3*1000);
		}
		factory.setPoolConfig(poolConfig); 
		return factory;
	}
	
	@Bean(name=TMCacheManager.CACHE_REDIS_TEMPLAT_BEAN)
	public RedisTemplate<Object, Object> redisTemplate() {
		RedisTemplate<Object, Object> template = new RedisTemplate<Object, Object>();
		template.setKeySerializer(new StringRedisSerializer());
		template.setValueSerializer(new StringRedisSerializer());
		template.setConnectionFactory(jedisConnectionFactory());
		template.setEnableTransactionSupport(true);
		return template;
	}
	
	@NotEmpty
	protected String hostname;
	protected int port;

	public String getHostname() {
		return hostname;
	}

	public void setHostname(String hostname) {
		this.hostname = hostname;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}
}
