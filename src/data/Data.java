package data;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Data {
	Statement st;
	Connection con;
	PreparedStatement ps;
	ResultSet rs;
	
	private void connect(){
		try {
			con = DriverManager.getConnection(
					"jdbc:postgresql://localhost/simulation", "postgres", "password");
			st = con.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void insertTemp(double temp){
		connect();
		
		try {
			ps = con.prepareStatement("INSERT INTO temp VALUES(?,?)");
			ps.setInt(1, getRows());
			ps.setDouble(2, temp);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void insertCO2(double co2) {
		connect();
		try {
			ps = con.prepareStatement("INSERT INTO co2_level VALUES(?,?)");
			ps.setInt(1, getRows());
			ps.setDouble(2, co2);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	public void insertFertilize(int fertalizer_getCount, String format) {
		connect();
		try {
			ps = con.prepareStatement("INSERT INTO fertilizedata VALUES(?,?,?)");
			ps.setInt(1, getRows());
			ps.setInt(2, fertalizer_getCount);
			ps.setString(3, format);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void insertLight(int red_level, int blue_level) {
		connect();
		
		try {
			ps = con.prepareStatement("INSERT INTO lightdata VALUES(?,?,?)");
			ps.setInt(1, getRows());
			ps.setInt(2, red_level);
			ps.setInt(3,blue_level);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void insertWater(double logTemp) {
		connect();
		
		try {
			ps = con.prepareStatement("INSERT INTO waterlevel VALUES(?,?)");
			ps.setInt(1, getRows());
			ps.setDouble(2, logTemp);
			ps.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public int getRows(){
		connect();
		int rows = 0;
		try {
			rs = st.executeQuery("SELECT COUNT(*) AS rows FROM co2_level INNER JOIN temp ON co2_level.count = temp.count INNER JOIN fertilizedata ON fertilizedata.count = co2_level.count INNER JOIN lightdata ON lightdata.count = co2_level.count INNER JOIN waterlevel ON waterlevel.count=co2_level.count");
			rs.next();
			rows = Integer.valueOf(rs.getString("rows"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rows;
	}
	
	public String[] getReadings() throws SQLException{
		connect();
		String co2_level = null;
		String fertilizeCount = null;
		String fertilizeDate = null;
		String red_light = null;
		String blue_light = null;
		String waterlevel = null;
		String temperature = null;
		int rows = getRows()-1;

		
			System.out.println(rows);

//			rs = st.executeQuery("SELECT lightdata.count,co2_level.co2_level,temp.temp,fertilizedata.fertilizecount,fertilizedata.fertalizedate,lightdata.red_level,lightdata.blue_level,waterlevel.waterlevel FROM co2_level INNER JOIN temp ON co2_level.count = temp.count INNER JOIN fertilizedata ON fertilizedata.count = co2_level.count INNER JOIN lightdata ON lightdata.count = co2_level.count INNER JOIN waterlevel ON waterlevel.count=co2_level.count WHERE lightdata.count = '+"+ (rows-1) +"+';");
			
			ps = con.prepareStatement("SELECT lightdata.count,co2_level.co2_level,temp.temp,fertilizedata.fertilizecount,fertilizedata.fertalizedate,lightdata.red_level,lightdata.blue_level,waterlevel.waterlevel FROM co2_level INNER JOIN temp ON co2_level.count = temp.count INNER JOIN fertilizedata ON fertilizedata.count = co2_level.count INNER JOIN lightdata ON lightdata.count = co2_level.count INNER JOIN waterlevel ON waterlevel.count=co2_level.count WHERE lightdata.count =?;");
			ps.setInt(1, rows);
			rs = ps.executeQuery();
			
			System.out.println("has done a query");		
			rs.next();
			System.out.println("called a next");
			
			co2_level = String.valueOf(rs.getDouble("co2_level"));
			System.out.println("loaded co2");
			
			fertilizeCount = String.valueOf(rs.getInt("fertilizecount"));
			System.out.println("loaded ferta_count");
			fertilizeDate = rs.getString("fertalizedate");
			System.out.println("loaded ferta_date");
			red_light = String.valueOf(rs.getInt("red_level"));
			System.out.println("loaded red");
			blue_light = String.valueOf(rs.getInt("blue_level"));
			System.out.println("loaded blue");
			temperature = String.valueOf(rs.getDouble("temp"));
			waterlevel = String.valueOf(rs.getInt("waterlevel"));
			System.out.println("loaded water");
			System.out.println("has loaded strings");
			
			
			String[] readings = {co2_level,fertilizeCount,fertilizeDate,red_light,blue_light,temperature,waterlevel};
		return readings;
		
		
		
		//return readings;
		
	}


}
