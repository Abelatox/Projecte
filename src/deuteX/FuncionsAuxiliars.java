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
					System.out.println("|"+rs.getString("deutor")+"\t"+rs.getString("quantitat")+"\t|");
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
	
	public static boolean esNumero(String num){
		if(!num.equals(""))
		{
			for(int i=0;i<num.length();i++){
				if(num.charAt(i)<48 || num.charAt(i)>57){
					return false;
				}
			}
			return true;
		}
		return false;
	}
	
	public static void saldarDeute(Dades[] dades){
		for(int i = 0; i<dades.length;i++){
			if(dades[i].quantitat==0){
				dades[i].deutor=null;
				dades[i].prestamista=null;
			}
		}
	}
	
	public static int usuariRepetit(Usuaris[] usuaris, String usuari){
		for(int i=0;i<DeuteX.usuarisActius;i++){ //Aquest for guarda l'index en el que està l'usuari que volem saber i si existeix.
            if(usuaris[i].usuari.equals(usuari)){
                return i;
            }
        }
		return -1;
	}
	
	public static boolean existeixDeutor(Dades[] dades, String deutor, String usuari){
		for(int i=0; i<dades.length;i++){
			if(dades[i].deutor != null){
				if(dades[i].deutor.equals(deutor) && dades[i].prestamista.equals(usuari)){
					return true;
				}
      	  	}
        }
		return false;
	}
}
