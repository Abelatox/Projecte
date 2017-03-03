package deuteX;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class FuncionsDatabase {

	public static boolean existeixUsuari(Connection conn, String usuari){
		if(conn != null)
	    {
			try {
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery("select * from usuaris");
				while(rs.next())
				if(usuari.equals(rs.getString("nom"))){
					return true;
				}
				
				rs.close();
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	    }
		return false;
	}
	
	public static boolean validarLogin(Connection conn, String usuari, String password){
		if(conn != null)
	    {
			try {
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery("select * from usuaris");
				while(rs.next())
				if(usuari.equals(rs.getString("nom")) && password.equals(rs.getString("pass"))){
					return true;
				}
				
				rs.close();
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	    }
		return false;
	}
	
	public static int getUserID(Connection conn, String usuari){
		int prestamistaID=-1;
		Statement st;
		try {
			st = conn.createStatement();
			ResultSet rs = st.executeQuery(" select * from usuaris u where u.nom like '"+usuari+"' ");
			while(rs.next())
			{	
				if(rs.getString("id_usuari") != null){
					prestamistaID = Integer.parseInt(rs.getString("id_usuari"));
				}else{
					prestamistaID = -1;
				}
			}
			rs.close();
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return prestamistaID;
	}
	
	public static void eliminarSaldades(Connection conn){
		Statement st;
		try {
			st = conn.createStatement();
			st.execute(" delete from deutes where quantitat = 0 ");
			st.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
