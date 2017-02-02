import java.util.Scanner;

public class Projecte{
	
	public enum Pantalles{
		PRINCIPAL, USUARI
	}

	public static void main(String[] args) {
		
		class Usuaris{
			String usuari;
			String password;
		}
		
		class Dades{
			String deutor;
			float quantitat;
			String prestamista;
		}
		
		Usuaris[] usuaris = new Usuaris[10];
		Dades[] dades = new Dades[10];
		
		for(int i=0;i<10;i++){
			usuaris[i] = new Usuaris();
			dades[i] = new Dades();
		}
		
		//Definim la variable de la pantalla actual.
		Pantalles pantallaActual = Pantalles.PRINCIPAL;
		
	/*	//Creem les arrays per usuaris
		String[] usuaris= new String[10];
		String[] password=new String[10];*/
		
		//Usuaris inicials
		usuaris[0].usuari = "Abel";
		usuaris[0].password = "123";
		usuaris[1].usuari = "Xevi";
		usuaris[1].password = "456";
		
		int usuarisActius=0;
		Scanner sc = new Scanner(System.in);
		boolean stop = false;
		
		String opcioIdioma; //Opció d'idioma (1-3)
		String opcio; //Opcio de registre/login (1-2)
		String opcioMenuLogin; //Opcions d'usuari (1-3)
		
		String usuari="",contrasenya="";
		
		String deutor=""; //Qui deu diners
		float quantitat=0F; //Quantitat ja en float
		float total=0; //Total de diners
		boolean usuariRepetit = false;
		
		//Variables Traducció
		String 
		sRegistre="",
		sEntrar="",
		sIntNom="",
		sRepetitUsu="",
		sInContrasenya="",
		sUsuari="",
		sContrasenya="",
		sBen="",
		sErUsuari="",
		sErContrasenya="",
		sOpcioNr="",
		sEDeute="",
		sIDeute="",
		sEnrera="",
		sOpcio="",
		sDeuDiners="",
		sQuantitat="",
		sDeutor="",
		sTotal="";
		
		//Moneda
		String monedaInicial=""; //Pounds i dolars
		String monedaFinal=""; //Euros
		
		System.out.println("Benvingut al programa");
		
		do{
			System.out.println("1- English");
			System.out.println("2- Castellano");
			System.out.println("3- Catala");
			System.out.print("Escull el teu idioma: ");
			
			opcioIdioma=sc.nextLine();
			
			switch (opcioIdioma){
			case "1": //ENGLISH
				sRegistre="Sign up";
				sEntrar="Login";
				sIntNom="Insert username";
				sRepetitUsu="User already exists";
				sInContrasenya="Insert password";
				sUsuari="User";
				sContrasenya="Password";
				sBen="Welcome";
				sErUsuari="Invalid user";
				sErContrasenya="Invalid password";
				sOpcioNr="Unrecognized option";
				sEDeute="Add/edit debt";
				sIDeute="Debts information";
				sEnrera="Return";
				sOpcio="Choose an option";
				sDeuDiners="Insert who owes you money";
				sQuantitat="Insert the amount";
				sDeutor="Debtor\tAmnt.";
				sTotal="Total";
				monedaInicial="$";
				monedaFinal="";
				break;
			
			case "2": //CASTELLANO
				sRegistre="Registrarse";
				sEntrar="Entrar";
				sIntNom="Introduce el nombre de usuario";
				sRepetitUsu="Usuario repetido";
				sInContrasenya="Introduce la contraseña";
				sUsuari="Usuario";
				sContrasenya="Contraseña";
				sBen="Bienvenido";
				sErUsuari="Usuario incorrecto";
				sErContrasenya="Contraseña incorrecta";
				sOpcioNr="Opcion no reconocida";
				sEDeute="Añadir/editar deuda";
				sIDeute="Informacion de deudas";
				sEnrera="Volver atras";
				sOpcio="Elige una opcion";
				sDeuDiners="Introduce quien te debe dinero";
				sQuantitat="Introduce la cantidad";
				sDeutor="Deutor\tCdad.";
				sTotal="Total";
				monedaInicial="";
				monedaFinal="�";
				break;
			
			case "3": //CATALA
				sRegistre="Registrar-se";
				sEntrar="Entra";
				sIntNom="Introdueix el nom d'usuari";
				sRepetitUsu="Usuari repetit";
				sInContrasenya="Introdueix la contrasenya";
				sUsuari="Usuari";
				sContrasenya="Contrasenya";
				sBen="Benvingut";
				sErUsuari="usuari incorrecte";
				sErContrasenya="contrasenya incorrecte";
				sOpcioNr="Opcio no reconeguda";
				sEDeute="Afegir/editar deute";
				sIDeute="Informacio de deutes";
				sEnrera="Tornar enrera";
				sOpcio="Tria una opcio";
				sDeuDiners="Introdueix qui et deu diners";
				sQuantitat="Introdueix la quantitat";
				sDeutor="Deutor\tQtat.";
				sTotal="Total";
				monedaInicial="";
				monedaFinal="�";
				break;
			
			default: 
				opcioIdioma="0";
				System.out.println();
			}
			
		}while(opcioIdioma.equals("0"));
		
		while(stop == false)
		{
			if(pantallaActual==Pantalles.PRINCIPAL){
				usuari="";
				contrasenya="";
				usuariRepetit=false;
				usuarisActius=0; //Es posa a 0 per quan compti que no surti dels limits
				
				for(int i=0;i<usuaris.length;i++){
					if(!(usuaris[i].usuari == null))
						usuarisActius++;
				}
				
				//En cada bucle recompta els usuaris actius
				System.out.println("1: "+sRegistre);
				System.out.println("2: "+sEntrar);
				opcio = sc.nextLine();
				
				switch(opcio){
				case "1": //Registre
					System.out.print(sIntNom+":  ");
					usuari=sc.nextLine();
					
					//Comprova si l'usuari ja existeix.
					for(int i=0;i<usuarisActius;i++){
						if(usuaris[i].usuari!=null && usuaris[i].usuari.equals(usuari)){
							System.out.println(sRepetitUsu);
							usuariRepetit = true;
							break;
						}
					}
					
					//Si el nom d'usuari es valid et demana contrasenya i el fica en la array.
					if(!usuariRepetit){
						System.out.print(sInContrasenya+": ");
						contrasenya=sc.nextLine();
						usuaris[usuarisActius].usuari = usuari;
						usuaris[usuarisActius].password = contrasenya;
					}
					
					break;
					
				case "2": //Login
                    int index=-1; //Variable que guarda en quin index es troba l'usuari.
                    System.out.print(sUsuari+": ");
                    usuari=sc.nextLine(); //Llegim el que Introdueix l'usuari.
                    System.out.print(sContrasenya+": ");
                    contrasenya=sc.nextLine();
                    
                    for(int i=0;i<usuarisActius;i++){ //Aquest for guarda l'index en el que està l'usuari que volem saber i si existeix.
                        if(usuaris[i].usuari.equals(usuari)){
                            usuariRepetit = true;
                            index=i;
                            break;
                        }
                    }
                    
                    if(usuariRepetit){ //Si existeix l'usuari
                        if(contrasenya.equals(usuaris[index].password)){ // Comprova si la contrasenya es correcta per l'usuari
            				System.out.println(sBen+" "+usuari);
        					System.out.println();
                            pantallaActual=Pantalles.USUARI;
                        }else{
                        	System.out.println(sErContrasenya+"\n");
                        }
                    }else{
                        System.out.println(sErUsuari+"\n");
                    }
                    break;
                    
                default:
                    System.out.println(sOpcioNr);
                }

			}else if(pantallaActual==Pantalles.USUARI){
				System.out.println("1- "+sEDeute);
				System.out.println("2- "+sIDeute);
				System.out.println("3- "+sEnrera);
				System.out.print(sOpcio+": ");
				opcioMenuLogin = sc.nextLine();
				
				switch(opcioMenuLogin){
				case "1": //Afegir deute
					System.out.print(sDeuDiners+": ");
					deutor = sc.nextLine().toUpperCase().trim();
					System.out.print(sQuantitat+": ");
					String inputQuantitat = sc.nextLine(); //TODO String definici� a dalt
					
					boolean quantitatEsNumero=true;
					for(int i=0;i<inputQuantitat.length();i++){
						if(inputQuantitat.charAt(i) != '-' && (inputQuantitat.charAt(i)<48 || inputQuantitat.charAt(i)>57)){
							quantitatEsNumero=false;
						}
					}
					if(quantitatEsNumero){
							
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
					}else{
						System.out.println("No es un numero");
					}
				break;
					
				case "2": //Notifiacions / informació
					System.out.println("|---------------|");
					System.out.println("|"+sDeutor+"\t|");
					System.out.println("|---------------|");
					total=0;
					
					for(int i=0;i<dades.length;i++){
						if(dades[i].prestamista==usuari){
							total=total+dades[i].quantitat;
							if(dades[i].quantitat>0){
								System.out.println("|"+dades[i].deutor+"\t"+monedaInicial+dades[i].quantitat+monedaFinal+"\t|");
							}else{
								System.err.println("|"+dades[i].deutor+"\t"+monedaInicial+dades[i].quantitat+monedaFinal+"\t|");
							}
							System.out.println("|---------------|");
						}
					}
					
					System.out.println("|---------------|");
					System.out.println("|"+sTotal+"\t"+monedaInicial+total+monedaFinal+"\t|");
					System.out.println("|---------------|");
					
					break;
				case "3": //Enrera
					pantallaActual=Pantalles.PRINCIPAL;
					break;
					
				default:
					System.out.println(sOpcioNr);
				}	
				System.out.println();
			}
		}
	}
}