package com.hpin.assistant.bootstrap;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * 
 * @author huaiku
 * @date 2019年1月21日
 * @desc 11.多数据源注册
 *
 */
@Configuration
public class DataSourceRegister {
	
	private static final Logger logger =LoggerFactory.getLogger(DataSourceRegister.class);
	
	@Bean(name = DataSourceKeyStore.NanJingDataSource)
	@ConfigurationProperties(prefix = "spring.nanjing-datasource")
	public DataSource nanJingDataSource() {
		logger.info("register NanJing DataSource...");
		return DataSourceBuilder.create().build();
	}
	
	@Bean
	@Primary
	public DataSource primaryDataSource(
			@Autowired DataSource defaultDataSource,
			@Autowired @Qualifier(DataSourceKeyStore.NanJingDataSource) DataSource nanJingDataSource
			) {
		logger.info("creating routing datasource...");
		Map<Object,Object> sourceMap = new HashMap<Object,Object>();
		sourceMap.put(DataSourceKeyStore.DefaultDataSource, defaultDataSource);
		sourceMap.put(DataSourceKeyStore.NanJingDataSource, nanJingDataSource);
		RoutingDataSource routingDataSource = new RoutingDataSource();
		routingDataSource.setTargetDataSources(sourceMap);
		routingDataSource.setDefaultTargetDataSource(defaultDataSource);
		return routingDataSource;
	}
}
