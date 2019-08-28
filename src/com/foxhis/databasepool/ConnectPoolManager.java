package com.foxhis.databasepool;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * 中间库连接池管理类
 * @author Administrator
 *
 */
public final class ConnectPoolManager {
	
	
	private static ComboPooledDataSource dataSource;
	
	private String dburl;
	private String dbport;
	private String dbname;
	private String dbuser;
	private String dbpwd;
	
	
	private ConnectPoolManager(Builder builder) throws PropertyVetoException
	{
		this.dburl = builder.dburl;
		this.dbport = builder.dbport;
		this.dbname = builder.dbname;
		this.dbuser = builder.dbuser;
		this.dbpwd = builder.dbpwd;
		initDataSource();
	}
	
	private void initDataSource() throws PropertyVetoException
	{
		dataSource = new ComboPooledDataSource();
		dataSource.setUser(dbuser);
	    dataSource.setPassword(dbpwd);
	    //String url = "jdbc:sqlserver://" + dburl+ ":" + dbport + ";DatabaseName=" + dbname;
	    String url = "jdbc:mysql://" + dburl+ ":" + dbport + "/" + dbname;
	    dataSource.setJdbcUrl(url);
	    //dataSource.setDriverClass("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	    dataSource.setDriverClass("com.mysql.jdbc.Driver");
	    dataSource.setInitialPoolSize(2);
	    dataSource.setMinPoolSize(2);
	    dataSource.setMaxPoolSize(3);
	    dataSource.setMaxStatements(50);
	    dataSource.setMaxIdleTime(60);
	    //数据库断了重连
	    dataSource.setTestConnectionOnCheckin(true);
	    dataSource.setTestConnectionOnCheckout(false);
	    dataSource.setIdleConnectionTestPeriod(10);
	    dataSource.setPreferredTestQuery("select 1");
	}


	public Connection getConnection() throws SQLException{
		
		Connection	connection =  dataSource.getConnection();
		
		return connection;
	}
	
	public  void close(Connection conn,Statement pst,ResultSet rs) throws SQLException
	{	

		if(rs!=null)
			rs.close();  

		if(pst!=null)
			pst.close();  

		if(conn!=null)
			conn.close();  

	}

	public static class Builder
	{
		String dburl;
		String dbport;
		String dbname;
		String dbuser;
		String dbpwd;
		
		public Builder setDBUrl(String url)
		{
			this.dburl = url;
			return this;
		}
		public Builder setDBPort(String port)
		{
			this.dbport = port;
			return this;
		}
		public Builder setDBName(String name)
		{
			this.dbname = name;
			return this;
		}
		public Builder setDBUser(String user)
		{
			this.dbuser = user;
			return this;
		}
		public Builder setDBPwd(String pwd)
		{
			this.dbpwd = pwd;
			return this;
		}
		
		public ConnectPoolManager build() throws PropertyVetoException
		{
			return new ConnectPoolManager(this);
		}
		
	}

}
