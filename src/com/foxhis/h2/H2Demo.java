package com.foxhis.h2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;

public class H2Demo {

	private static final String locdb_jdbcurl="jdbc:h2:./pack;AUTO_SERVER=TRUE";
	private Connection conn;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DbUtils.loadDriver("org.h2.Driver");
		String sql = H2Utils.buildCreateSql(Pack_Accnt.class);
		H2Demo demo = new H2Demo();
		int re = demo.sqlUpdate(sql, null);
        System.out.println(re);
	}


	public  Connection getConnection() throws SQLException {
		if ((this.conn != null) && (!this.conn.isClosed()))
			return this.conn;
		try {
			String dburl =locdb_jdbcurl;
			this.conn = DriverManager.getConnection(dburl, "foxhis", "foxhis");
			return this.conn;
		} catch (Exception e) {
            System.out.println(e);
		}
		return null;
	}
	
	public int sqlUpdate(String sql,Object[] params) {
		try {
			QueryRunner qr = new QueryRunner();
			Connection connection=getConnection();
			int ret = qr.update(connection, sql, params);
			return ret;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return -1;
	}

}
