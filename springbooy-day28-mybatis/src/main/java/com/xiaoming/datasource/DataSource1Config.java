package com.xiaoming.datasource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * 读取 DataSource01 数据源
 */
@Configuration // 注册到springboot容器中
@MapperScan(basePackages = "com.xiaoming.test01", sqlSessionFactoryRef = "test1SqlSessionFactory") //扫包发明围殴
public class DataSource1Config {

	/**
	 * 功能描述:(配置test1数据库)
	 * @return
	 */
	@Bean(name = "test1DataSource") //注入到 spring 容器中
	@ConfigurationProperties(prefix = "spring.datasource.test1")	//以这个开始到配置文件中去读
	public DataSource testDataSource() {
		return DataSourceBuilder.create().build();
	}

	/**
	 * (test1 sql会话工厂)
	 */
	@Bean(name = "test1SqlSessionFactory")
	public SqlSessionFactory testSqlSessionFactory(@Qualifier("test1DataSource") DataSource dataSource)
			throws Exception {
		SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
		bean.setDataSource(dataSource);
		//加载 mapper 配置文件
		// bean.setMapperLocations(
		// new
		// PathMatchingResourcePatternResolver().getResources("classpath:mybatis/mapper/test1/*.xml"));
		return bean.getObject();
	}

	/**
	 * 功能描述:(test1 事物管理)
	 */
	@Bean(name = "test1TransactionManager")
	public DataSourceTransactionManager testTransactionManager(@Qualifier("test1DataSource") DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}

	@Bean(name = "test1SqlSessionTemplate")
	public SqlSessionTemplate testSqlSessionTemplate(
			@Qualifier("test1SqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
		return new SqlSessionTemplate(sqlSessionFactory);
	}

}




