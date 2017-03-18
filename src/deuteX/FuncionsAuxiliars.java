package deuteX;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Locale;
import java.util.Scanner;

public class FuncionsAuxiliars {
	/**
	 * Imprimeix la taula dels deutes
	 * @param conn Connexió a la BD
	 * @param usuari Usuari actual
	 * @param monedaInicial Moneda abans del número
	 * @param monedaFinal Moneda després del número
	 */
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
	/**
	 * Assigna l'idioma al programa
	 * @return (tIdioma) Idioma
	 */
	public static tIdioma assignaIdioma(){
		int 
		ENG = 0, 
		CAST = 1,
		CAT = 2;
		Scanner sc = new Scanner(System.in);
		String opcioIdioma;
		String[] mIdioma = {"English","Castellano","CatalÃ "};

		tIdioma idioma = new tIdioma();
		do{
			printMenu(mIdioma);
			System.out.print("Escull el teu idioma: ");

			opcioIdioma=sc.nextLine();
		
			switch (opcioIdioma){
			case "1": //ENGLISH
				idioma.idioma = ENG; idioma.monedaInicial="$"; idioma.monedaFinal="";
				break;
			
			case "2": //CASTELLANO
				idioma.idioma = CAST; idioma.monedaInicial=""; idioma.monedaFinal="â‚¬";
				break;
			
			case "3": //CATALA
				idioma.idioma = CAT; idioma.monedaInicial=""; idioma.monedaFinal="â‚¬";
				break;
			
			default: 
				opcioIdioma="0";
				System.out.println("Idioma incorrecte\n");
			}
			
		}while(opcioIdioma.equals("0")); //Quan l'idioma ï¿½s 1, 2 o 3 passa a la pantalla principal, sino segueix fent bucle
		return idioma;
	}
	
	/**
	 * Imprimeix els menus
	 * @param array Array que conté els valors del menú
	 */
	public static void printMenu(String[] array){
		System.out.println();
		for(int i=0;i<array.length;i++){
			System.out.println((i+1)+"- "+array[i]);
		}
	}
	
	/**
	 * Comprova si un número és positiu
	 * @param num Número a comprovar en String
	 * @return (boolean) true si el número és positiu
	 */
	//TODO provar de forma més simple amb un try catch
	public static boolean esNumeroPositiu(String num){
		try{
			Float.parseFloat(num);
		}catch(Exception e){
			return false;
		}
		return true;
	}
}
