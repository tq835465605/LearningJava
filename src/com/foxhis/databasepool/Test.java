package com.foxhis.databasepool;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;





public class Test {
	
	public static void main(String[] args) throws PropertyVetoException {
		
		ConnectPoolManager connectpool = new ConnectPoolManager.Builder()
				.setDBUrl("127.0.0.1")
				.setDBPort("3306")
				.setDBName("xmsitf")
				.setDBUser("root")
				.setDBPwd("foxhis").build();
		Test dTest=new Test();
		
		for(;;)
		{
			List<BWCarCOM> ls=null;
			try {
				ls = dTest.selectInterDB(connectpool);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println(e);
			}
			System.out.println(ls);
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
	
	public  List<BWCarCOM> selectInterDB(ConnectPoolManager connectpool) throws SQLException{
		// TODO Auto-generated method stub
		Connection connection = connectpool.getConnection();
		System.out.println(connection);
		//checkConnection(connection);
		
		List<BWCarCOM> carlists = new ArrayList<BWCarCOM>();
		String sql  = "select ID,C_Accno,C_RoomNo,C_CardNo,Dt_CheckOut,C_Status,Dt_Dt,C_Sync from t_bwcarcom";
		Statement statement = connection.createStatement();
		ResultSet result = statement.executeQuery(sql);
		while(result.next())
		{
			BWCarCOM car = new BWCarCOM();
			car.setId(result.getInt("ID"));
			car.setC_accno(result.getString("C_Accno"));
			car.setC_roomno(result.getString("C_RoomNo"));
			car.setC_cardno(result.getString("C_CardNo"));
			car.setDt_checkout(result.getDate("Dt_CheckOut"));
			car.setC_status(result.getString("C_Status"));
			car.setDt_dt(result.getDate("Dt_Dt"));
			car.setC_sync(result.getString("C_Sync"));
			carlists.add(car);
		}
		//connectpool.close(connection, statement, result);
		return carlists;
	}

}
