package com.kaishengit.util;
import java.sql.SQLException;

import com.kaishengit.exception.DataException;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.apache.taglibs.standard.lang.jstl.test.StaticFunctionTests;

public class DpHelper {

	/**
	 * 执行插入语句并获得自动增长的主键
	 */
	public static Long  insert(String sql,Object...params){
		QueryRunner runner = new QueryRunner(ConnectionDriver.getDataSource());
		try {
			return runner.insert(sql,new ScalarHandler<Long>(),params);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataException("数据库异常",e);

		}
	}

	/**
	 * 执行insert update delete语句
	 */
	public static void update(String sql,Object... params) {
		QueryRunner queryRunner = new QueryRunner(ConnectionDriver.getDataSource());
		try {
			queryRunner.update(sql,params);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataException("数据库异常",e);
		}

	}

	/**
	 * 执行查询(select)语句
	 */
	public static <T> T query(String sql,ResultSetHandler<T> handler,Object... params) {
		QueryRunner queryRunner = new QueryRunner(ConnectionDriver.getDataSource());
		try {
			return queryRunner.query(sql,handler,params);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataException("数据库异常",e);
		}
	}


} 