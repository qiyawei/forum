package com.kaishengit.util;

import com.kaishengit.exception.DataException;
import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;


public class ConnectionDriver {

	private static BasicDataSource dataSource = new BasicDataSource();

	static {

		Properties properties = new Properties();
		try {
			properties.load(ConnectionDriver.class.getClassLoader().getResourceAsStream("config.properties"));

			dataSource.setDriverClassName(properties.getProperty("jdbc.driver"));
			dataSource.setUrl(properties.getProperty("jdbc.url"));
			dataSource.setUsername(properties.getProperty("jdbc.username"));
			dataSource.setPassword(properties.getProperty("jdbc.password"));

			dataSource.setInitialSize(5);
			dataSource.setMaxIdle(20);
			dataSource.setMinIdle(5);
			dataSource.setMaxWaitMillis(5000);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DataException("数据库连接池异常",e);

		}
	}

	public static DataSource getDataSource() {
		return dataSource;
	}

	public static Connection getConnection() {
		try {
			return dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataException("数据库连接池异常",e);
		}
	}
}
