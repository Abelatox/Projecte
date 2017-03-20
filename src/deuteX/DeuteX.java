package deuteX;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class DeuteX extends FuncionsAuxiliars{
	
	//JDBC driver name i base de dades URL
	static final String JDBC_DRIVER="org.postgresql.Driver";
	static final String DB_URL = "jdbc:postgresql://192.168.2.215/DeuteX";
	//static final String DB_URL = "jdbc:postgresql://192.168.1.140/DeuteX";
	static final String DB_USER="postgres";
	static final String DB_PASSWORD="smx";
	
	//Index per les frases de traducció
	static int index=0;
	static final int 
		REGISTRE=index++, 
	    ENTRAR=index++, 
	    ESCULLOPCIO=index++, 
	    INTNOM=index++, 
	    REPETITUSU=index++, 
	    INCONTRASENYA=index++, 
	    USUARI=index++, 
	    CONTRASENYA=index++, 
	    BEN=index++, 
	    ERUSUARI=index++, 
	    ERCONTRASENYA=index++, 
	    OPCIONR=index++, 
	    ADEUTE=index++, 
	    EDEUTE=index++, 
	    IDEUTE=index++,
	    NOESNUM=index++,
	    NODEURES=index++,
	    ENRERA=index++, 
	    OPCIO=index++, 
	    DEUDINERS=index++, 
	    DEUDELIMI=index++, 
	    QUANTITAT=index++, 
	    DEUTOR=index++, 
	    TOTAL=index++; 
	
	static final int 
		ENG = 0, 
		CAST = 1,
		CAT = 2;
	
	public enum Pantalles{
		PRINCIPAL, USUARI, IDIOMES
	}
	
	//Assignació d'idioma
	static String[][] traduccio = new String[index][3]; //[quantitatParaules][quantitatIdiomes];
	
	static int idioma = 0;

	//Moneda
	static String monedaInicial=""; //Dolars
	static String monedaFinal=""; //Euros
	
	static String usuari="",contrasenya="";
	static Pantalles pantallaActual = Pantalles.IDIOMES;
	
	
	/**
	 * Funció principal per el programa DeuteX
	 * Permet crear i entrar amb usuaris, afegir i restar deutes i també mostra una taula de les deutes.
	 * @param args
	 */
	public static void main(String[] args) {
		//Agafem la connexió de la Base de Dades
		Connection conn = FuncionsDatabase.connexioBD(JDBC_DRIVER,DB_URL,DB_USER,DB_PASSWORD);
		
		/*if(args[0] != null){
			//Interpret de comandes
		}*/
		
		Scanner sc = new Scanner(System.in);
		boolean stop = false;
						
		//Carreguem la traducció
		assignarTraducció();
		
		System.out.println("Benvingut al programa");
		
		//Bucle principal
		while(stop == false){
			interpret(conn);
			/*if(pantallaActual==Pantalles.IDIOMES){
				processarPantallaIdiomes();
				
			}else if(pantallaActual==Pantalles.PRINCIPAL){
				processarPantallaPrincipal(conn);
				
			}else if(pantallaActual==Pantalles.USUARI){
				processarPantallaUsuari(conn);				
			}*/
		}
	}
	
	/**
	 * Interpret de comandes
	 */
	private static void interpret(Connection conn){
		Scanner sc = new Scanner(System.in);
		System.out.println("\nIntrodueix ordre:");
		String input = sc.nextLine();
		
		String[] paraules = FuncionsAuxiliars.separaParaules(input);
		if(paraules[0] != null){
			switch(paraules[0]){
			case "registrar":
				System.out.println("Registrant a "+paraules[1]);
				registrar(conn,paraules[1],paraules[2]);
				break;
			case "afegir":
				
				break;
			}
		}
		
	}
	
	/**
	 * Processa la pantalla de selecció d'idioma
	 */
	private static void processarPantallaIdiomes(){
		tIdioma tIdioma = new tIdioma();
		tIdioma = assignaIdioma();
		idioma=tIdioma.idioma;
		monedaInicial=tIdioma.monedaInicial;
		monedaFinal=tIdioma.monedaFinal;
		
		pantallaActual=Pantalles.PRINCIPAL;
	}
	
	/**
	 * Processa la pantalla de registre / login
	 * @param conn
	 */
	private static void processarPantallaPrincipal(Connection conn){
		Scanner sc = new Scanner(System.in);
		String[] mRegistre = {traduccio[REGISTRE][idioma],traduccio[ENTRAR][idioma]};
		String opcio; //Opcio de registre/login (1-2)

		contrasenya="";
		
		printMenu(mRegistre);
		System.out.println(traduccio[ESCULLOPCIO][idioma]+": ");
		opcio = sc.nextLine();
		
		switch(opcio){
		case "1": //Registre
			System.out.print(traduccio[INTNOM][idioma]+":  ");
			String usuari = sc.nextLine();
			System.out.print(traduccio[INCONTRASENYA][idioma]+": ");
			String contrasenya = sc.nextLine();

			registrar(conn,usuari,contrasenya);
			break;
						
		case "2": //Login
			login(conn);
        	break;
           
        default:
            System.out.println(traduccio[OPCIONR][idioma]);
        }
	}
	
	/**
	 * Processa la pantalla de quan l'usuari ha entrat
	 * @param conn
	 */
	private static void processarPantallaUsuari(Connection conn){
		Scanner sc = new Scanner(System.in);
		String opcioMenuLogin; //Opcions d'usuari (1-3)

		String[] mDeute = {traduccio[ADEUTE][idioma],traduccio[EDEUTE][idioma],traduccio[IDEUTE][idioma],traduccio[ENRERA][idioma]}; 

		printMenu(mDeute);
		System.out.println(traduccio[OPCIO][idioma]+": ");
		opcioMenuLogin = sc.nextLine();
		
		switch(opcioMenuLogin){
		case "1": //Afegir deute
			afegirDeute(conn, usuari);
		break;
		 case "2": //Resta deute 
	        restarDeute(conn, usuari);
			break;
			
		case "3": //Notifiacions / informació
			printTaula(conn,usuari,monedaInicial, monedaFinal);
			break;
			
		case "4": //Enrera
			pantallaActual=Pantalles.PRINCIPAL;
			break;
			
		default:
			System.out.println(traduccio[OPCIONR][idioma]);
		}	
		System.out.println();
	}

	/**
	 * Funció per registrar un usuari
	 * @param conn Connexió a la BD
	 */
	private static void registrar(Connection conn, String user, String password){
		Scanner sc = new Scanner(System.in);
		        
		usuari=user;

		if(FuncionsDatabase.existeixUsuari(conn,usuari)){
			System.out.println(traduccio[REPETITUSU][idioma]);
		}else{			
			contrasenya=password;

			
			if(conn != null)
		    {
				try {
					Statement st = conn.createStatement();
					st.execute(" insert into usuaris (nom,pass) values ('"+usuari+"','"+contrasenya+"') ");
					st.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		    }	
		}
	}
	
	/**
	 * Funció per validar login
	 * @param conn Connexió a la BD
	 */
	private static void login(Connection conn){
		Scanner sc = new Scanner(System.in);
		System.out.print(traduccio[USUARI][idioma]+": ");
        usuari=sc.nextLine(); //Llegim el que Introdueix l'usuari.
        System.out.print(traduccio[CONTRASENYA][idioma]+": ");
        contrasenya=sc.nextLine();
    
    	if(FuncionsDatabase.validarLogin(conn, usuari, contrasenya)){ // Comprova si la contrasenya es correcta per l'usuari
			System.out.println(traduccio[BEN][idioma]+" "+usuari);
			System.out.println();
            pantallaActual=Pantalles.USUARI;
        }else{
        	System.out.println(traduccio[ERCONTRASENYA][idioma]);
        }
	}
	
	/**
	 * Afegeix el deute a un usuari
	 * @param conn Connexió a la BD
	 * @param usuari Usuari actual
	 */
	private static void afegirDeute(Connection conn, String usuari){
		Scanner sc = new Scanner(System.in);
		String deutor;
		String inputQuantitat;
		float quantitat;

		System.out.print(traduccio[DEUDINERS][idioma]+": ");
		deutor = sc.nextLine().toUpperCase().trim();
		System.out.print(traduccio[QUANTITAT][idioma]+": ");
		inputQuantitat = sc.nextLine();
		
		if(esNumeroPositiu(inputQuantitat)){
			quantitat=Float.parseFloat(inputQuantitat);
			FuncionsDatabase.afegirDeute(conn, deutor, usuari, quantitat);
		}else{
			System.out.println(traduccio[NOESNUM][idioma]);
		}
	}
	
	/**
	 * Resta el deute a un usuari
	 * @param conn Connexió a la BD
	 * @param usuari Usuari actual
	 */
	private static void restarDeute(Connection conn, String usuari){
		Scanner sc = new Scanner(System.in);
		String deutor;
		String inputQuantitat;
		float quantitat;
		System.out.print(traduccio[DEUDELIMI][idioma]+": "); 
        deutor = sc.nextLine().toUpperCase().trim(); 
     
        System.out.print(traduccio[QUANTITAT][idioma]+": "); 
        inputQuantitat = sc.nextLine(); 
         
        if(esNumeroPositiu(inputQuantitat)){
			quantitat=Float.parseFloat(inputQuantitat);
			FuncionsDatabase.restarDeute(conn, deutor, usuari, quantitat);	
		}else{
			System.out.println(traduccio[NOESNUM][idioma]);
		}
	}
	
	/**
	 * Assigna les traduccions a la array
	 */
	private static void assignarTraducció(){
		//Assignació de la array bidimensional.
		traduccio[REGISTRE][ENG]="Register";					traduccio[REGISTRE][CAST]="Registrarse";							traduccio[REGISTRE][CAT]="Registrar-se";
		traduccio[ENTRAR][ENG]="Login";							traduccio[ENTRAR][CAST]="Entrar";									traduccio[ENTRAR][CAT]="Entrar";
		traduccio[ESCULLOPCIO][ENG]="Choose an option";			traduccio[ESCULLOPCIO][CAST]="Elige una opción";					traduccio[ESCULLOPCIO][CAT]="Escull una opció";
		traduccio[INTNOM][ENG]="Insert username";				traduccio[INTNOM][CAST]="Introduce el nombre de usuario";			traduccio[INTNOM][CAT]="Introdueix el nom d'usuari";
		traduccio[REPETITUSU][ENG]="User already exists";		traduccio[REPETITUSU][CAST]="Usuario repetido";						traduccio[REPETITUSU][CAT]="Usuari repetit";
		traduccio[INCONTRASENYA][ENG]="Insert password";		traduccio[INCONTRASENYA][CAST]="Introduce la contraseña";			traduccio[INCONTRASENYA][CAT]="Introdueix la contrasenya";
		traduccio[USUARI][ENG]="User";							traduccio[USUARI][CAST]="Usuario";									traduccio[USUARI][CAT]="Usuari";
		traduccio[CONTRASENYA][ENG]="Password";					traduccio[CONTRASENYA][CAST]="Contraseña";							traduccio[CONTRASENYA][CAT]="Contrasenya";
		traduccio[BEN][ENG]="Welcome";							traduccio[BEN][CAST]="Bienvenido";									traduccio[BEN][CAT]="Benvingut";
		traduccio[ERUSUARI][ENG]="Invalid user";				traduccio[ERUSUARI][CAST]="Usuario incorrecto";						traduccio[ERUSUARI][CAT]="Usuari incorrecte";
		traduccio[ERCONTRASENYA][ENG]="Invalid password";		traduccio[ERCONTRASENYA][CAST]="Contraseña incorrecta";				traduccio[ERCONTRASENYA][CAT]="Contrasenya incorrecte";
		traduccio[OPCIONR][ENG]="Unrecognized option";			traduccio[OPCIONR][CAST]="Opcion no reconocida";					traduccio[OPCIONR][CAT]="Opcio no reconeguda";
		traduccio[ADEUTE][ENG]="Add/edit debt";         	 	traduccio[ADEUTE][CAST]="Añadir/editar deuda";          			traduccio[ADEUTE][CAT]="Afegir/editar deute"; 
		traduccio[EDEUTE][ENG]="Delete debt";         	 		traduccio[EDEUTE][CAST]="Eliminar deuda";            				traduccio[EDEUTE][CAT]="Eliminar deute"; 
		traduccio[IDEUTE][ENG]="Debts information";				traduccio[IDEUTE][CAST]="Informacion de deudas";					traduccio[IDEUTE][CAT]="Informacio de deutes";
		traduccio[NOESNUM][ENG]="Is not a positive number";		traduccio[NOESNUM][CAST]="No es un número positivo";				traduccio[NOESNUM][CAT]="No és un nombre positiu";
		traduccio[NODEURES][ENG]="does not owe you anything.";	traduccio[NODEURES][CAST]="no te debe nada.";						traduccio[NODEURES][CAT]="no et deu res.";
		traduccio[ENRERA][ENG]="Return";						traduccio[ENRERA][CAST]="Volver atrás";								traduccio[ENRERA][CAT]="Tornar enrera";
		traduccio[OPCIO][ENG]="Choose an option";				traduccio[OPCIO][CAST]="Elige una opción";							traduccio[OPCIO][CAT]="Tria una opcio";
		traduccio[DEUDINERS][ENG]="Insert who owes you money";	traduccio[DEUDINERS][CAST]="Introduce quien te debe dinero";		traduccio[DEUDINERS][CAT]="Introdueix qui et deu diners";
		traduccio[DEUDELIMI][ENG]="Insert who gave you money"; 	traduccio[DEUDELIMI][CAST]="Introduce quien te ha devuelto dinero"; traduccio[DEUDELIMI][CAT]="Introdueix qui t'ha tornat diners"; 
		traduccio[QUANTITAT][ENG]="Insert the amount";			traduccio[QUANTITAT][CAST]="Introduce la cantidad";					traduccio[QUANTITAT][CAT]="Introdueix la quantitat";
		traduccio[DEUTOR][ENG]="Debtor\tAmnt.";					traduccio[DEUTOR][CAST]="Deutor\tCdad.";							traduccio[DEUTOR][CAT]="Deutor\tQtat.";
		traduccio[TOTAL][ENG]="Total";							traduccio[TOTAL][CAST]="Total";										traduccio[TOTAL][CAT]="Total";
	}
}