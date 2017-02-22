package deuteX;

import java.util.Scanner;

public class Project extends FuncionsAuxiliars{
	
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
	
	static int usuarisActius=0; //Es posa a 0 per quan compti que no surti dels limits

	//Assignació d'idioma
	static String[][] traduccio = new String[index][3]; //[numParaules][numIdiomes];
	
	static int idioma = 0;

	
	
	
	
	
	public static void main(String[] args) {
		
		//Inicialització de les class
		Usuaris[] usuaris = new Usuaris[10];
		Dades[] dades = new Dades[10];
		
		for(int i=0;i<10;i++){
			usuaris[i] = new Usuaris();
			dades[i] = new Dades();
		}
		
		//Definició de la variable de la pantalla actual.
		Pantalles pantallaActual = Pantalles.IDIOMES;
		
		//Usuaris inicials
		usuaris[0].usuari = "Abel";
		usuaris[0].password = "123";
		usuaris[1].usuari = "Xevi";
		usuaris[1].password = "456";
		
		Scanner sc = new Scanner(System.in);
		boolean stop = false;
		
		//Opcions de menú
		String opcioIdioma; //Opció d'idioma (1-3)
		String opcio; //Opcio de registre/login (1-2)
		String opcioMenuLogin; //Opcions d'usuari (1-3)
		
		String usuari="",contrasenya="";
		
		String deutor=""; //Qui deu diners
		String inputQuantitat; //String del preu per comprovar si és un número
		float quantitat=0F; //Quantitat ja en float
		float total=0; //Total de diners
		boolean usuariRepetit = false;
		
		//Assignació del vector bidimensional.
		traduccio[REGISTRE][ENG]="Register";					traduccio[REGISTRE][CAST]="Registrarse";							traduccio[REGISTRE][CAT]="Registrar-se";
		traduccio[ENTRAR][ENG]="Login";							traduccio[ENTRAR][CAST]="Entrar";									traduccio[ENTRAR][CAT]="Entrar";
		traduccio[ESCULLOPCIO][ENG]="Choose an option";			traduccio[ESCULLOPCIO][CAST]="Elige una opción";					traduccio[ESCULLOPCIO][CAT]="Escull una opcio";
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
		traduccio[ENRERA][ENG]="Return";						traduccio[ENRERA][CAST]="Volver atrás";								traduccio[ENRERA][CAT]="Tornar enrera";
		traduccio[OPCIO][ENG]="Choose an option";				traduccio[OPCIO][CAST]="Elige una opción";							traduccio[OPCIO][CAT]="Tria una opcio";
		traduccio[DEUDINERS][ENG]="Insert who owes you money";	traduccio[DEUDINERS][CAST]="Introduce quien te debe dinero";		traduccio[DEUDINERS][CAT]="Introdueix qui et deu diners";
		traduccio[DEUDELIMI][ENG]="Insert who gave you money"; 	traduccio[DEUDELIMI][CAST]="Introduce quien te ha devuelto dinero"; traduccio[DEUDELIMI][CAT]="Introdueix qui t'ha tornat diners"; 
		traduccio[QUANTITAT][ENG]="Insert the amount";			traduccio[QUANTITAT][CAST]="Introduce la cantidad";					traduccio[QUANTITAT][CAT]="Introdueix la quantitat";
		traduccio[DEUTOR][ENG]="Debtor\tAmnt.";					traduccio[DEUTOR][CAST]="Deutor\tCdad.";							traduccio[DEUTOR][CAT]="Deutor\tQtat.";
		traduccio[TOTAL][ENG]="Total";							traduccio[TOTAL][CAST]="Total";										traduccio[TOTAL][CAT]="Total";
		
		
		//Moneda
		String monedaInicial=""; //Dolars
		String monedaFinal=""; //Euros
		
		//Menus
		String[] mIdioma = {"English","Castellano","Català"};
		System.out.println("Benvingut al programa");
		
		while(stop == false)
		{		
			String[] mRegistre = {traduccio[REGISTRE][idioma],traduccio[ENTRAR][idioma]};
		    String[] mDeute = {traduccio[ADEUTE][idioma],traduccio[EDEUTE][idioma],traduccio[IDEUTE][idioma],traduccio[ENRERA][idioma]}; 

			if(pantallaActual==Pantalles.IDIOMES){
				
				do{
					printMenu(mIdioma);
					System.out.print("Escull el teu idioma: ");

					opcioIdioma=sc.nextLine();
				
					switch (opcioIdioma){
					case "1": //ENGLISH
						
						idioma = ENG;
						monedaInicial="$";
						monedaFinal="";
						break;
					
					case "2": //CASTELLANO
						
						idioma = CAST;
						monedaInicial="";
						monedaFinal="€";
						break;
					
					case "3": //CATALA
						
						idioma = CAT;
						monedaInicial="";
						monedaFinal="€";
						break;
					
					default: 
						opcioIdioma="0";
						System.out.println("Idioma incorrecte\n");
					}
					
				}while(opcioIdioma.equals("0")); //Quan l'idioma �s 1, 2 o 3 passa a la pantalla principal, sino segueix fent bucle
				
				pantallaActual=Pantalles.PRINCIPAL;
				
			}else if(pantallaActual==Pantalles.PRINCIPAL){
				usuari="";
				contrasenya="";
				usuariRepetit=false;
				
				for(int i=0;i<usuaris.length;i++){
					if(!(usuaris[i].usuari == null))
						usuarisActius++;
				}
				
				//En cada bucle recompta els usuaris actius
				printMenu(mRegistre);
				System.out.println(traduccio[ESCULLOPCIO][idioma]+": ");
				opcio = sc.nextLine();
				
				switch(opcio){
				case "1": //Registre
					
					System.out.print(traduccio[INTNOM][idioma]+":  ");
					usuari=sc.nextLine();
					
					//Comprova si l'usuari ja existeix.
					for(int i=0;i<usuarisActius;i++){
						if(usuaris[i].usuari!=null && usuaris[i].usuari.equals(usuari)){
							System.out.println(traduccio[REPETITUSU][idioma]);
							usuariRepetit = true;
							break;
						}
					}
					
					//Si el nom d'usuari es valid et demana contrasenya i el fica en la array.
					if(!usuariRepetit){
						System.out.print(traduccio[INCONTRASENYA][idioma]+": ");
						contrasenya=sc.nextLine();
						usuaris[usuarisActius].usuari = usuari;
						usuaris[usuarisActius].password = contrasenya;
					}
					
					break;
								
				case "2": //Login
					
                    int index=-1; //Variable que guarda en quin index es troba l'usuari.
                    System.out.print(traduccio[USUARI][idioma]+": ");
                    usuari=sc.nextLine(); //Llegim el que Introdueix l'usuari.
                    System.out.print(traduccio[CONTRASENYA][idioma]+": ");
                    contrasenya=sc.nextLine();
                   
                    index=usuariRepetit(usuaris, usuari);
                  
                    if(index!=-1){ //Si existeix l'usuari
                    	if(contrasenya.equals(usuaris[index].password)){ // Comprova si la contrasenya es correcta per l'usuari
            				System.out.println(traduccio[BEN][idioma]+" "+usuari);
                            pantallaActual=Pantalles.USUARI;
                        }else{
                        	System.out.println(traduccio[ERCONTRASENYA][idioma]);
                        }
                    }else{
                        System.out.println(traduccio[ERUSUARI][idioma]);
                    }
                    break;
                   
                default:
                    System.out.println(traduccio[OPCIONR][idioma]);
                }

			}else if(pantallaActual==Pantalles.USUARI){
				printMenu(mDeute);
				System.out.println(traduccio[OPCIO][idioma]+": ");
				opcioMenuLogin = sc.nextLine();
				
				switch(opcioMenuLogin){
				case "1": //Afegir deute
					System.out.print(traduccio[DEUDINERS][idioma]+": ");
					deutor = sc.nextLine().toUpperCase().trim();
					System.out.print(traduccio[QUANTITAT][idioma]+": ");
					inputQuantitat = sc.nextLine();
					
					if(esNumero(inputQuantitat)){
							
						quantitat=Float.parseFloat(inputQuantitat);
						
						//Afegim les dades a la class Dades
						for(int i=0;i<dades.length;i++){
							
							if(dades[i].deutor==null){
								dades[i].deutor=deutor;
								dades[i].quantitat=quantitat;
								dades[i].prestamista=usuari;
								break;
							}else{ //Si el usuari es el mateix es suma / resta a la quantitat que deu
								if(dades[i].deutor.equals(deutor)){
									dades[i].quantitat=dades[i].quantitat+quantitat;
									break;
								}
							}
						}
						saldarDeute(dades);
					}else{
						System.out.println(traduccio[NOESNUM][idioma]);
					}
				break;
				
				 case "2": //Resta deute 
			          System.out.print(traduccio[DEUDELIMI][idioma]+": "); 
			          deutor = sc.nextLine().toUpperCase().trim(); 
			          System.out.print(traduccio[QUANTITAT][idioma]+": "); 
			          inputQuantitat = sc.nextLine(); 
			          
			          if(esNumero(inputQuantitat)){
							quantitat=Float.parseFloat(inputQuantitat);
							for(int i=0;i<dades.length;i++){
								
								if(dades[i].deutor==null){
									dades[i].deutor=deutor;
									dades[i].quantitat=quantitat;
									dades[i].prestamista=usuari;
									break;
								}else{ //Si el deutor es el mateix es suma / resta a la quantitat que deu
									if(dades[i].deutor.equals(deutor)){
										dades[i].quantitat=dades[i].quantitat-quantitat;
										break;
									}
								}
							}
							saldarDeute(dades);
						}else{
							System.out.println(traduccio[NOESNUM][idioma]);
						}
					break;
				case "3": //Notifiacions / informació
					
					printTaula(usuari, dades, monedaInicial, monedaFinal);
					break;
					
				case "4": //Enrera
					
					pantallaActual=Pantalles.PRINCIPAL;
					break;
					
				default:
					System.out.println(traduccio[OPCIONR][idioma]);
				}	
				System.out.println();
			}
		}
	}
	
	
}