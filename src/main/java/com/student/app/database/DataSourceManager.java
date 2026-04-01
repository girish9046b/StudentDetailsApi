package com.student.app.database;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
public class DataSourceManager {
    
		// Code to establish HikariDataSource and JdbcTemplate from properties in application.properties
	 	@Bean
	 	//@Primary
	    @ConfigurationProperties(prefix = "datasource.student")
	    public HikariConfig hikariConfigmysql4() {
	        return new HikariConfig();
	    }

	    @Bean(name = "mysqlDataSource4")
	   // @Primary
	    public HikariDataSource dataSource4() {
	        return new HikariDataSource(hikariConfigmysql4());
	    }
	    @Bean(name = "mysqlTemplate4")
	    //@Primary
		public NamedParameterJdbcTemplate mysqlJdbcTemplate4(@Qualifier("mysqlDataSource4") HikariDataSource dataSource) {
			return new NamedParameterJdbcTemplate(dataSource);
		}
	    
	    @Bean(name = "mysqlcall4")
	    //@Primary
		public SimpleJdbcCall mysqlcall4(@Qualifier("mysqlDataSource4") HikariDataSource dataSource) {
			return  new SimpleJdbcCall(dataSource);
		}
	   
	    
	    
	    
	    
//	   

}
