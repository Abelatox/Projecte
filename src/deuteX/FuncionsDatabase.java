package deuteX;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class FuncionsDatabase {
	
//Comprova si l'usuari ja està creat a la base de dades. 
	public static boolean existeixUsuari(Connection conn, String usuari){
		//Si la connexió no és nul·la
		if(conn != null)
	    {
			try {
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery("select * from usuaris");
				
				while(rs.next())
					//Comprova si l'usuari està a la Base de Dades
				if(usuari.equals(rs.getString("nom"))){
					//Retorna un valor cert perquè pugui continuar.
					return true;
				}
				//Tanquem les connexions a la base de dades.
				rs.close();
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	    }
		//Retorna un valor fals perquè l'usuari no existeix.
		return false;
	}
	
	//Comprova el login d'entrada si l'usuari i la contrasenya estan a la base de dades i són correctes
	public static boolean validarLogin(Connection conn, String usuari, String password){
		//Si la connexió no és nul·la
		if(conn != null)
	    {
			try {
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery("select * from usuaris");
				
				while(rs.next())
				//Si el nom i la contrasenya són correctes.
				if(usuari.equals(rs.getString("nom")) && password.equals(rs.getString("pass"))){
					//Retorna un valor cert.
					return true;
				}
				//Tanquem les connexions a la base de dades.
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
	public static void afegirDeute(Connection conn, String deutor,String prestamista,double quantitat){
		Statement st;
		try{
			int usuariID=FuncionsDatabase.getUserID(conn, prestamista);
			int prestamistaID=-1;
			String dbDeutor="";
			st = conn.createStatement();
			ResultSet rs = st.executeQuery(" select * from usuaris u left join deutes d on u.id_usuari=d.prestamista");
			
			while(rs.next()){
				if(rs.getString("id_usuari").equals(rs.getString("prestamista"))){
					if(rs.getString("deutor").equals(deutor)){
						prestamistaID= Integer.parseInt(rs.getString("prestamista"));
						dbDeutor=rs.getString("deutor");
					}
				}
			}
			//System.out.println("(deutor'"+deutor+"','quantitat"+quantitat+"','usuariID"+usuariID+"')");

			//Comprovem si el deutor ja existeix.
			if(prestamistaID == usuariID && dbDeutor.equals(deutor)){
				st.execute("UPDATE deutes SET quantitat = quantitat + "+quantitat + " where prestamista = '"+usuariID+"' and deutor = '" + deutor+"' " );
			}else{
				st.execute(" INSERT INTO deutes (deutor,quantitat,prestamista) VALUES ('"+deutor+"','"+quantitat+"','"+usuariID+"') ");
			}
			eliminarSaldades(conn);
		}catch (SQLException e){
			e.printStackTrace();
		}
	}
	public static void restarDeute(Connection conn, String deutor,String prestamista,double quantitat){
		Statement st;
		try{
			int usuariID=FuncionsDatabase.getUserID(conn, prestamista);
			int prestamistaID=-1;
			String dbDeutor="";
			st = conn.createStatement();
			ResultSet rs = st.executeQuery(" select * from usuaris u left join deutes d on u.id_usuari=d.prestamista");
			while(rs.next()){
				if(rs.getString("id_usuari").equals(rs.getString("prestamista"))){
					if(rs.getString("deutor").equals(deutor)){
						prestamistaID= Integer.parseInt(rs.getString("prestamista"));
						dbDeutor=rs.getString("deutor");
					}
				}
			}
			//Comprovem si el deutor ja existeix.
			if(prestamistaID == usuariID && dbDeutor.equals(deutor)){
				st.execute("UPDATE deutes SET quantitat = quantitat - "+quantitat + " where prestamista = '"+usuariID+"' and deutor = '" + deutor+"' " );
			}
			eliminarSaldades(conn);
		}catch (SQLException e){
			e.printStackTrace();
		}
	}
}
