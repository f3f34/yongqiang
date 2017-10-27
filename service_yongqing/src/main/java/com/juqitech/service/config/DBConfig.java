package com.juqitech.service.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.juqitech.service.utils.TicketdashiLog;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.sql.SQLException;

@Configuration
@EnableTransactionManagement
public class DBConfig {

	@Bean
	public DruidDataSource dataSource() {
		return getTomcatPoolingDataSource();
	}

	@Bean
	public JdbcTemplate jdbcTemplate() {
		return new JdbcTemplate(dataSource());
	}

	@Bean
	public PlatformTransactionManager transactionManager() {
		return new DataSourceTransactionManager(dataSource());
	}

	private DruidDataSource getTomcatPoolingDataSource() {
		DruidDataSource dataSource = new DruidDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl(System.getProperty("db_url"));
		dataSource.setUsername(System.getProperty("db_username"));
		dataSource.setPassword(System.getProperty("db_password"));

		dataSource.setInitialSize(5); // 连接池启动时创建的初始化连接数量（默认值为0）
		dataSource.setMaxActive(20); // 连接池中可同时连接的最大的连接数
		dataSource.setMaxIdle(12); // 连接池中最大的空闲的连接数，超过的空闲连接将被释放，如果设置为负数表示不限
		dataSource.setMinIdle(5); // 连接池中最小的空闲的连接数，低于这个数量会被创建新的连接
		dataSource.setMaxWait(60000); // 最大等待时间，当没有可用连接时，连接池等待连接释放的最大时间，超过该时间限制会抛出异常，如果设置-1表示无限等待
		dataSource.setRemoveAbandonedTimeout(180); // 超过时间限制，回收没有用(废弃)的连接
		dataSource.setRemoveAbandoned(true); // 超过removeAbandonedTimeout时间后，是否进
												// 行没用连接（废弃）的回收
		dataSource.setTestOnBorrow(true);
		dataSource.setTestOnReturn(true);
		dataSource.setTestWhileIdle(true);
		dataSource.setValidationQuery("SELECT 1");
		dataSource.setTimeBetweenEvictionRunsMillis(1000 * 5); // 检查无效连接的时间间隔
		try {
			dataSource.setFilters("stat,wall");
		} catch (SQLException e) {
			TicketdashiLog.error(e.getMessage(),e);
		}

		return dataSource;
	}

	private String db_username;
	private String db_password;
	private String db_url;

	public String getDb_username() {
		return db_username;
	}

	public void setDb_username(String db_username) {
		this.db_username = db_username;
	}

	public String getDb_password() {
		return db_password;
	}

	public void setDb_password(String db_password) {
		this.db_password = db_password;
	}

	public String getDb_url() {
		return db_url;
	}

	public void setDb_url(String db_url) {
		this.db_url = db_url;
	}
}
