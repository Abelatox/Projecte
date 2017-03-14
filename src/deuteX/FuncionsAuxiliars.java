package deuteX;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class FuncionsAuxiliars {

	public static void printTaula(Connection conn,String usuari,String monedaInicial, String monedaFinal){
		float total=0;
		System.out.println(
				"|---------------|\n"+
				"|\t"+usuari+"\t|\n"+
				"|---------------|\n"+
				"|"+DeuteX.traduccio[DeuteX.DEUTOR][DeuteX.idioma]+"\t|\n"+
				"|---------------|"
		);
		total=0;
		try{
			Statement st = conn.createStatement();
			int idUsuari=FuncionsDatabase.getUserID(conn, usuari);
			ResultSet rs = st.executeQuery("select * from deutes where prestamista = '"+idUsuari+"' ");
			while (rs.next()){
					total=total+Float.parseFloat(rs.getString("quantitat"));
					System.out.println("|"+rs.getString("deutor")+"\t"+DeuteX.monedaInicial+rs.getString("quantitat")+DeuteX.monedaFinal+"\t|");
					System.out.println("|---------------|");
			}
			
		}catch(SQLException e){
			e.printStackTrace();
		}
				
		
		System.out.print("|---------------|\n"+
			"|"+DeuteX.traduccio[DeuteX.TOTAL][DeuteX.idioma]+":\t"+monedaInicial+total+monedaFinal+"\t|\n"+
			"|---------------|\n"
		);
	}
	
	public static void printMenu(String[] array){
		System.out.println();
		for(int i=0;i<array.length;i++){
			System.out.println((i+1)+"- "+array[i]);
		}
	}
	
	public static boolean esNumeroPositiu(String num){
		if(!num.equals(""))
		{
			for(int i=0;i<num.length();i++){
				if((num.charAt(i)<48 || num.charAt(i)>57) && num.charAt(i) != '.'){
					return false;
				}
			}
			return true;
		}
		return false;
	}
	
}
