/**
 * 
 */
package com.util;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.jdbc.datasource.DataSourceUtils;




/**
 * @author tengji
 *
 */
public class DBConnection
{
	private Logger logger = Logger.getLogger(this.getClass());
	private DataSource dataSource;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	

	public Connection getConnection()
	{
		if (dataSource != null) {
			Connection connection = null;
			try {
				 connection = DataSourceUtils.getConnection(dataSource);
			}
			catch (Exception ignored) {
				logger.error("getConnection wrong !!!! ",ignored);
			}
			return connection;	
		}	
		else
			return null;
	}
	
	
	
	public void setAutoCommitTrue(Connection connection)
	{
		if (connection != null) {
			try {
				connection.setAutoCommit(true);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void setAutoCommitFalse(Connection connection)
	{
		if (connection != null) {
			try {
				connection.setAutoCommit(false);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void commit(Connection connection)
	{
		if (connection != null) {
			try {
				connection.commit();
			} 
			catch (Exception ignored) {
				logger.error("connection commit wrong !!!! ",ignored);
			}
		}
	}
	
	public void rollback(Connection connection) 
	{
		if (connection != null) {
			try {
				connection.rollback();
			}
			catch (Exception ignored) {
				logger.error("connection rollback wrong wrong !!!! ",ignored);
			}
		}
	}
	

	public void close(Object connection)
	{
		if (connection != null && connection instanceof Connection) {
			
			try {
				
				DataSourceUtils.releaseConnection( (Connection) connection, dataSource);
				
				//connection.close();
			}
			catch (Exception ignored) {
				logger.error("Connection close wrong !!!! ",ignored);
			}
		}
	}
	
	
}
