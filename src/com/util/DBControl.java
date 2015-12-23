/**
 * 
 */
package com.util;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;


/**
 * @author tengji
 *	基本数据访问层类
 */
public class DBControl 
{
	private static boolean isDebug = true;
	private static Logger logger = Logger.getLogger(DBControl.class);
	// sql manipulate ------------------------------------------------
	// select 允许没有参数的select dml sql 语句
	public static List<Map<String,Object>> select(Connection connection, String sql, Object[] args) 
			throws Exception
	{
		if (connection == null) 
			throw new Exception("param error   class:DBControl\tmethod:select\t param connection is null\n",new Throwable());
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Map<String,Object>> datas = null;
		
		try {
			pstmt = connection.prepareStatement(sql);
			setSqlArgs(pstmt, args);
			// print if debug
			if (isDebug) {
				logger.info(getSQL(sql, args));
			}
//				System.out.println( getSQL(sql, args) );
			// 
			rs = pstmt.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData(); 
			int columnCount = rsmd.getColumnCount(); 
			datas = new ArrayList<Map<String,Object>>();

			while( rs.next() ) { 
				Map<String,Object> map = new HashMap<String,Object>();  
				for( int i = 1; i <= columnCount; i++ ) {
					// map(string, object)
					map.put( rsmd.getColumnName(i), rs.getObject(i) );
					// System.out.println("rsmd.getColumnName(i)" + rsmd.getColumnName(i));
					// System.out.println(" rs.getObject(i) " + rs.getObject(i));
				}
				datas.add(map);
			}
		}
		catch (SQLException e) {
			logger.error(e.getMessage(),e);
            String msg = " class:DBControl\tmethod:select\t db select execute error，sql is：" + getSQL(sql, args) + "\n";
			throw new Exception(msg);
		}
		finally {
			try {
				if (rs != null) 
					rs.close();
				if (pstmt != null) 
					pstmt.close();
			} catch(Exception e) {
				logger.error(e.getMessage(),e);
	             String msg =" class:DBControl\tmethod:select\t db ResultSet or PreparedStatement close() error\n";
				throw  new Exception(msg);
			}
		}
		// ArrayList<Map>
		return datas;
	}
	
	// insert
	public static Object insert(Connection connection, String sql, Object[] args ,String[] id) throws Exception
	{
		if (connection == null) {
            String msg =" class:DBControl\tmethod:insert\t param connection is null\n";
			throw  new Exception(msg);
		}
		if (sql == null || args == null){
			 String msg ="class:DBControl\tmethod:insert\t insert set args wrong\n"+ getSQL(sql, args) + "\n";
			throw  new Exception(msg);
		}
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Object generatedKey = null;
		
        try {	
			pstmt = connection.prepareStatement(sql,id);
			
        	setSqlArgs(pstmt, args);
        	if (isDebug) {
				logger.info(getSQL(sql, args));
			}
			
			pstmt.executeUpdate();
			// 返回生成主键
			rs = pstmt.getGeneratedKeys();
			if (rs.next() == true) 
				generatedKey = rs.getObject(1);

		} catch (SQLException e) {
        	 String msg = " class:DBControl\tmethod:insert\t db insert execute error，sql is：" + getSQL(sql, args) + "\n";
        	 throw  new Exception(msg);
		} finally {
			try {
				if (rs!=null) 
					rs.close();
				if (pstmt!=null)
					pstmt.close();
			} catch(Exception e) {
				String msg = "class:DBControl\tmethod:insert\t db ResultSet or PreparedStatement close() error\n";
	        	throw  new Exception(msg);
			}
		}       
		return generatedKey;
	}
	
	// execute not use 
	public static void execute(Connection connection, String sql, Object[] args) throws Exception
	{
		if (connection == null) 
			throw new Exception("param error class:DBControl\tmethod:execute\t  param  connection is null\n");
		
		PreparedStatement pstmt = null;
		try {  
			pstmt = connection.prepareStatement(sql);
			setSqlArgs(pstmt, args);
			if (isDebug) {
				logger.info(getSQL(sql, args));
			}
			pstmt.executeUpdate();
		} 
		catch (SQLException e) {
			String msg = " class:DBControl\tmethod:execute\t db  execute execute error ，sql is：" + getSQL(sql, args) + "\n";
			throw  new Exception(msg);
		} 
		finally {
			try {
				pstmt.close();
			} catch(Exception e) {
				String msg = "class:DBControl\tmethod:execute\t db PreparedStatement close() error\n";
				throw  new Exception(msg);
			}
		}
	}

	
	// update
	public static void update(Connection connection, String sql, Object[] args) throws Exception
	{ 
		if (connection == null) 
			throw new Exception("param class:DBControl\tmethod:update\t param connection is null\n");
		if (sql == null || args == null)
			throw new Exception("param class:DBControl\tmethod:update\t update set args wrong\n sql is:"
				+ getSQL(sql, args) + "\n");
		
		PreparedStatement pstmt = null;
		try {   
			pstmt = connection.prepareStatement(sql);
			setSqlArgs(pstmt, args);
			if (isDebug) {
				logger.info(getSQL(sql, args));
			}
			pstmt.executeUpdate();
		} 
		catch (SQLException e) {
        	String msg = " class:DBControl\tmethod:update\t db update execute error，sql is：" + getSQL(sql, args) + "\n";
			throw new  Exception(msg);
		} 
		finally {
			try {
				pstmt.close();
			} catch(Exception e) {
				String msg = " class:DBControl\tmethod:update\t db PreparedStatement close() error\n";
				throw new  Exception(msg);
			}
		}
	}
	
	// delete
	public static void delete(Connection connection, String sql, Object[] args) throws Exception
	{
		if (connection == null) 
			throw new Exception(" class:DBControl\tmethod:delete\t param connection is null\n");
		if (sql == null || args == null)
			throw new Exception( " class:DBControl\tmethod:delete\t delete set args wrong\n"
				+ getSQL(sql, args) + "\n");
		
		PreparedStatement pstmt = null;
		try { 
			pstmt = connection.prepareStatement(sql);
			setSqlArgs(pstmt, args);
			if (isDebug) {
				logger.info(getSQL(sql, args));
			}
			pstmt.executeUpdate();
		} 
		catch (SQLException e) {
			String msg = " class:DBControl\tmethod:delete\t db delete execute error，sql is：" + getSQL(sql, args) + "\n";
			throw new  Exception(msg);
		} 
		finally {
			try {
				pstmt.close();
			} catch(Exception e) {
	            String msg =" class:DBControl\tmethod:delete\t db PreparedStatement close() error\n";
	            throw new  Exception(msg);
			}
		}
	}
	
	
	public static Object[] executeProc(Connection connection, String sql, Object[] argsIn, 
			int[] argsOuttypes, int lastArgsInPos) throws Exception
	{
		if (connection == null) 
			throw new Exception(" class:DBControl\tmethod:executeProc\t param connection is null\n");
		
		CallableStatement cstmt = null;
		Object[] outs = null;
        try {
        	cstmt = connection.prepareCall(sql);
        	// in args set
        	if (argsIn != null && cstmt != null) {	
    			int argc = argsIn.length;
    			for (int i = 0; i < argc; i++) {	
					if (argsIn[i] != null && argsIn[i] instanceof java.lang.String) 
						cstmt.setString(i + 1, (String) argsIn[i]);
					else if (argsIn[i] != null && argsIn[i] instanceof java.lang.Integer)
						cstmt.setInt(i + 1, (Integer) argsIn[i]);
					else
						throw new Exception("class:DBControl\tmethod:executeProc\t in param argsIn only support java.lang.String and  int \n");
    			}
    		}
        	// out args set
        	if (argsOuttypes != null && cstmt != null) {	
    			int argc = argsOuttypes.length;
    			for (int i = 0; i < argc; i++) {
    				// Types.VARCHAR
    				if (argsOuttypes[i] == Types.VARCHAR || argsOuttypes[i] == Types.INTEGER)
						cstmt.registerOutParameter(lastArgsInPos + i + 1, argsOuttypes[i]);
					else
						throw new Exception("class:DBControl\tmethod:executeProc\t out param Outs only support java.lang.Integer and java.lang.String type \n");
    			}
    		}
    		
        	//System.out.println(" executeProc here .... " );
        	// run
        	cstmt.execute();
        	//System.out.println(" executeProc execute wanbi .... " );
        	// 有返回值的
        	if (argsOuttypes != null && cstmt != null) {
        		int argc = argsOuttypes.length;
        		outs = new Object[argc];
        		for (int i = 0; i < argc; i++)
        			outs[i] = cstmt.getObject(lastArgsInPos + i + 1);
        	}
		} catch (SQLException e) {
        	String msg = " class:DBControl\tmethod:executeProc\t db executeProc execute error，Stored procedure is called：" + sql + "\n";
			throw new Exception(msg);
			
		} finally {	
			try {
				cstmt.close();
			} catch(Exception e) {
	            String msg = " class:DBControl\tmethod:executeProc\t db CallableStatement close() error\n";
	            throw new Exception(msg);
			}
		}
		return outs;
	}
	
	
	// tools ------------------------------------------------------------------------	
	public static String getSQL(String sql, Object[] args) 
	{
		if (args == null)
			return sql;
		
		int argsNum = args.length;
		StringBuffer returnSQL = new StringBuffer();
		String[] subSQL = sql.split("\\?");
		
		for (int i = 0; i < argsNum; i++) {
			returnSQL.append(subSQL[i] + " '" +  args[i] + "' ");
		}
		// last one
		if (subSQL.length > args.length) {
			returnSQL.append(subSQL[subSQL.length - 1]);
		}
		
		return returnSQL.toString();
	}
	
	private static void setSqlArgs(PreparedStatement pstmt, Object[] args) throws SQLException
	{
		if (args != null && pstmt != null) {	
			int argc = args.length;
			for (int i = 0; i < argc; i++) {
				if (args[i] != null && args[i] instanceof java.util.Date)
					// setParams 传入参数 没处理java.util.date change to java.sql.date
					pstmt.setObject(i + 1, null);
				else 
					//System.out.println("setSqlArgs 传入参数 args["+ i +"] = " + args[i]);
					pstmt.setObject(i + 1, args[i]);
			}	
		}
	}
	
}
