package com.doosan.web.ctx;

import javax.sql.DataSource;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@MapperScan(basePackages = {"com.doosan.web"})
@ComponentScan(basePackages ={"com.doosan.web"})

public class RootContext {
	@Bean
	public DataSource dataSource() {
		HikariConfig hikarConfig = new HikariConfig();
		hikarConfig.setDriverClassName("org.mariadb.jdbc.Driver");
		hikarConfig.setJdbcUrl("jdbc:mariadb://172.168.0.150:3306/mypet");
		hikarConfig.setUsername("catdog");
		hikarConfig.setPassword("catdog");
		HikariDataSource dataSource = new HikariDataSource(hikarConfig);
		return dataSource;
	}
	@Bean
	public DataSourceTransactionManager txManger() {
		return new DataSourceTransactionManager(dataSource());
	}
}