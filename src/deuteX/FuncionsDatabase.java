package deuteX;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class FuncionsDatabase {
	/**
	 * Connexio a la Base de Dades
	 * @param jdbcDriver Ubicació del driver
	 * @param dbUrl URL de la Base de Dades
	 * @param dbUser Nom d'usuari de la Base de Dades
	 * @param dbPassword Contrassenya de l'usuari de la Base de Dades
	 * @return (Connection) Connexio a la Base de Dades
	 */
	public static Connection connexioBD(String jdbcDriver, String dbUrl, String dbUser, String dbPassword) {
		Connection conn=null;
		
		try {
			Class.forName(jdbcDriver);
			conn = DriverManager.getConnection(dbUrl,dbUser,dbPassword);
		}catch(SQLException sqle){
			sqle.printStackTrace();
		}catch(ClassNotFoundException cnfe){
			System.out.println("No s'ha trobat el driver");
		}
		return conn;
	}
	
	/**
	 * Comprova si l'usuari existeix a la base de dades. 
	 * @param conn Connexio a la BD
	 * @param usuari Usuari a cercar a la BD
	 * @return (boolean) true si l'usuari existeix
	 */
	public static boolean existeixUsuari(Connection conn, String usuari){
		//Si la connexio no es nula
		if(conn != null){
			
			try {
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery("select * from usuaris");
				
				while(rs.next()){
					//Comprova si l'usuari està a la Base de Dades
					if(usuari.equals(rs.getString("nom"))){
						//Retorna un valor cert perquè pugui continuar.
						return true;
					}
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
	
	/**
	 * Comprova el login d'entrada si l'usuari i la contrasenya estan a la base de dades i son correctes
	 * @param conn Connexio a la BD
	 * @param usuari Nom d'usuari
	 * @param password Contrasenya de l'usuari
	 * @return (boolean) true si les dades son correctes
	 */
	public static boolean validarLogin(Connection conn, String usuari, String password){
		//Si la connexio no es nula
		if(conn != null){
			
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
	
	/**
	 * Obte la ID de l'usuari de la BD
	 * @param conn Connexio a la BD
	 * @param usuari Usuari de qui retornar la ID
	 * @return (Int) ID de l'usuari
	 */
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
	
	/**
	 * Elimina totes les deudes saldades
	 * @param conn Connexio a la BD
	 */
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
	
	/**
	 * Afegeix un deute o suma si n'existeix
	 * @param conn Connexió a la BD
	 * @param deutor Persona que deu diners a l'usuari
	 * @param prestamista Usuari actual del programa
	 * @param quantitat Quantitat de diners
	 */
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
	
	/**
	 * Resta part d'un deute
	 * @param conn Connexió a la BD
	 * @param deutor Persona que deu diners a l'usuari
	 * @param prestamista Usuari actual del programa
	 * @param quantitat Quantitat de diners
	 */
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
