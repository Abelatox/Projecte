import java.util.Scanner;

public class Projecte{
	
	public enum Pantalles{
		PRINCIPAL, USUARI, IDIOMES
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
		
		//Definició de la variable de la pantalla actual.
		Pantalles pantallaActual = Pantalles.IDIOMES;
		
		//Usuaris inicials
		usuaris[0].usuari = "Abel";
		usuaris[0].password = "123";
		usuaris[1].usuari = "Xevi";
		usuaris[1].password = "456";
		
		int usuarisActius=0;
		Scanner sc = new Scanner(System.in);
		boolean stop = false;
		
		//Opcions de menú
		String opcioIdioma; //OpciÃ³ d'idioma (1-3)
		String opcio; //Opcio de registre/login (1-2)
		String opcioMenuLogin; //Opcions d'usuari (1-3)
		
		String usuari="",contrasenya="";
		
		String deutor=""; //Qui deu diners
		String inputQuantitat; //String del preu per comprovar si Ã©s un nÃºmero
		float quantitat=0F; //Quantitat ja en float
		float total=0; //Total de diners
		boolean usuariRepetit = false;
		
		//Variables TraducciÃ³
		int 
		iRegistre=0,
		iEntrar=1,
		iEscullOpcio=2,
		iIntNom=3,
		iRepetitUsu=4,
		iInContrasenya=5,
		iUsuari=6,
		iContrasenya=7,
		iBen=8,
		iErUsuari=9,
		iErContrasenya=10,
		iOpcioNr=11,
		iEDeute=12,
		iIDeute=13,
		iEnrera=14,
		iOpcio=15,
		iDeuDiners=16,
		iQuantitat=17,
		iDeutor=18,
		iTotal=19;
		
		int eng=0, cast = 1, cat = 2;
		
		//Assignació d'idioma
		String[][] traduccio = new String[20][3]; //[numParaules][numIdiomes]
		traduccio[iRegistre][eng]="Register";					traduccio[iRegistre][cast]="Registrarse";						traduccio[iRegistre][cat]="Registrar-se";
		traduccio[iEntrar][eng]="Login";						traduccio[iEntrar][cast]="Entrar";								traduccio[iEntrar][cat]="Entrar";
		traduccio[iEscullOpcio][eng]="Choose an option";		traduccio[iEscullOpcio][cast]="Elige una opcion";				traduccio[iEscullOpcio][cat]="Escull una opcio";
		traduccio[iIntNom][eng]="Insert username";				traduccio[iIntNom][cast]="Introduce el nombre de usuario";		traduccio[iIntNom][cat]="Introdueix el nom d'usuari";
		traduccio[iRepetitUsu][eng]="User already exists";		traduccio[iRepetitUsu][cast]="Usuario repetido";				traduccio[iRepetitUsu][cat]="Usuari repetit";
		traduccio[iInContrasenya][eng]="Insert password";		traduccio[iInContrasenya][cast]="Introduce la contraseña";		traduccio[iInContrasenya][cat]="Introdueix la contrasenya";
		traduccio[iUsuari][eng]="User";							traduccio[iUsuari][cast]="Usuario";								traduccio[iUsuari][cat]="Usuari";
		traduccio[iContrasenya][eng]="Password";				traduccio[iContrasenya][cast]="Contraseña";						traduccio[iContrasenya][cat]="Contrasenya";
		traduccio[iBen][eng]="Welcome";							traduccio[iBen][cast]="Bienvenido";								traduccio[iBen][cat]="Benvingut";
		traduccio[iErUsuari][eng]="Invalid user";				traduccio[iErUsuari][cast]="Usuario incorrecto";				traduccio[iErUsuari][cat]="Usuari incorrecte";
		traduccio[iErContrasenya][eng]="Invalid password";		traduccio[iErContrasenya][cast]="Contraseña incorrecta";		traduccio[iErContrasenya][cat]="Contrasenya incorrecte";
		traduccio[iOpcioNr][eng]="Unrecognized option";			traduccio[iOpcioNr][cast]="Opcion no reconocida";				traduccio[iOpcioNr][cat]="Opcio no reconeguda";
		traduccio[iEDeute][eng]="Add/edit debt";				traduccio[iEDeute][cast]="Añadir/editar deuda";					traduccio[iEDeute][cat]="Afegir/editar deute";
		traduccio[iIDeute][eng]="Debts information";			traduccio[iIDeute][cast]="Informacion de deudas";				traduccio[iIDeute][cat]="Informacio de deutes";
		traduccio[iEnrera][eng]="Return";						traduccio[iEnrera][cast]="Volver atras";						traduccio[iEnrera][cat]="Tornar enrera";
		traduccio[iOpcio][eng]="Choose an option";				traduccio[iOpcio][cast]="Elige una opcion";						traduccio[iOpcio][cat]="Tria una opcio";
		traduccio[iDeuDiners][eng]="Insert who owes you money";	traduccio[iDeuDiners][cast]="Introduce quien te debe dinero";	traduccio[iDeuDiners][cat]="Introdueix qui et deu diners";
		traduccio[iQuantitat][eng]="Insert the amount";			traduccio[iQuantitat][cast]="Introduce la cantidad";			traduccio[iQuantitat][cat]="Introdueix la quantitat";
		traduccio[iDeutor][eng]="Debtor\tAmnt.";				traduccio[iDeutor][cast]="Deutor\tCdad.";						traduccio[iDeutor][cat]="Deutor\tQtat.";
		traduccio[iTotal][eng]="Total";							traduccio[iTotal][cast]="Total";								traduccio[iTotal][cat]="Total";
		
		int idioma = 0;
		
		//Moneda
		String monedaInicial=""; //Dolars
		String monedaFinal=""; //Euros
		
		System.out.println("Benvingut al programa");
		
		while(stop == false)
		{		
			if(pantallaActual==Pantalles.IDIOMES){
				
				do{
					System.out.println(
						"\n1- English\n"+
						"2- Castellano\n"+
						"3- Catala\n"+
						"Escull el teu idioma: "
					);
					opcioIdioma=sc.nextLine();
				
					switch (opcioIdioma){
					case "1": //ENGLISH
						
						idioma = eng;
						
						monedaInicial="$";
						monedaFinal="";
						break;
					
					case "2": //CASTELLANO
						
						idioma = cast;

						monedaInicial="";
						monedaFinal="€";
						break;
					
					case "3": //CATALA
						
						idioma = cat;

						monedaInicial="";
						monedaFinal="€";
						break;
					
					default: 
						opcioIdioma="0";
						System.out.println("Idioma incorrecte\n");
					}
					
				}while(opcioIdioma.equals("0")); //Quan l'idioma ï¿½s 1, 2 o 3 passa a la pantalla principal, sino segueix fent bucle
				
				pantallaActual=Pantalles.PRINCIPAL;
				
			}else if(pantallaActual==Pantalles.PRINCIPAL){
				usuari="";
				contrasenya="";
				usuariRepetit=false;
				usuarisActius=0; //Es posa a 0 per quan compti que no surti dels limits
				
				for(int i=0;i<usuaris.length;i++){
					if(!(usuaris[i].usuari == null))
						usuarisActius++;
				}
				
				//En cada bucle recompta els usuaris actius
				System.out.println("\n1- "+traduccio[iRegistre][idioma]);
				System.out.println("2- "+traduccio[iEntrar][idioma]);
				System.out.print(traduccio[iEscullOpcio][idioma]+": ");
				opcio = sc.nextLine();
				
				switch(opcio){
				case "1": //Registre
					
					System.out.print(traduccio[iIntNom][idioma]+":  ");
					usuari=sc.nextLine();
					
					//Comprova si l'usuari ja existeix.
					for(int i=0;i<usuarisActius;i++){
						if(usuaris[i].usuari!=null && usuaris[i].usuari.equals(usuari)){
							System.out.println(traduccio[iRepetitUsu][idioma]);
							usuariRepetit = true;
							break;
						}
					}
					
					//Si el nom d'usuari es valid et demana contrasenya i el fica en la array.
					if(!usuariRepetit){
						System.out.print(traduccio[iInContrasenya][idioma]+": ");
						contrasenya=sc.nextLine();
						usuaris[usuarisActius].usuari = usuari;
						usuaris[usuarisActius].password = contrasenya;
					}
					
					break;
					
				case "2": //Login
					
                    int index=-1; //Variable que guarda en quin index es troba l'usuari.
                    System.out.print(traduccio[iUsuari][idioma]+": ");
                    usuari=sc.nextLine(); //Llegim el que Introdueix l'usuari.
                    System.out.print(traduccio[iContrasenya][idioma]+": ");
                    contrasenya=sc.nextLine();
                    
                    for(int i=0;i<usuarisActius;i++){ //Aquest for guarda l'index en el que estÃ  l'usuari que volem saber i si existeix.
                        if(usuaris[i].usuari.equals(usuari)){
                            usuariRepetit = true;
                            index=i;
                            break;
                        }
                    }
                    
                    if(usuariRepetit){ //Si existeix l'usuari
                    	if(contrasenya.equals(usuaris[index].password)){ // Comprova si la contrasenya es correcta per l'usuari
            				System.out.println(traduccio[iBen][idioma]+" "+usuari);
        					System.out.println();
                            pantallaActual=Pantalles.USUARI;
                        }else{
                        	System.out.println(traduccio[iErContrasenya][idioma]);
                        }
                    }else{
                        System.out.println(traduccio[iErUsuari][idioma]);
                    }
                    break;
                   
                default:
                    System.out.println(traduccio[iOpcioNr][idioma]);
                }

			}else if(pantallaActual==Pantalles.USUARI){
				System.out.println("1- "+traduccio[iEDeute][idioma]);
				System.out.println("2- "+traduccio[iIDeute][idioma]);
				System.out.println("3- "+traduccio[iEnrera][idioma]);
				System.out.print(traduccio[iOpcio][idioma]+": ");
				opcioMenuLogin = sc.nextLine();
				
				switch(opcioMenuLogin){
				case "1": //Afegir deute
					System.out.print(traduccio[iDeuDiners][idioma]+": ");
					deutor = sc.nextLine().toUpperCase().trim();
					System.out.print(traduccio[iQuantitat][idioma]+": ");
					inputQuantitat = sc.nextLine();
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
						
						for(int i = 0; i<dades.length;i++){
							if(dades[i].quantitat==0){
								dades[i].deutor=null;
								dades[i].prestamista=null;
							}
						}
					}else{
						System.out.println("*No es un numero*");
					}
				break;
					
				case "2": //Notifiacions / informaciÃ³
					System.out.println("|---------------|");
					System.out.println("|\t"+usuari+"\t|");
					System.out.println("|---------------|");
					System.out.println("|"+traduccio[iDeutor][idioma]+"\t|");
					System.out.println("|---------------|");
					total=0;
					
					for(int i=0;i<dades.length;i++){
						if(dades[i].prestamista!= null && dades[i].prestamista.equals(usuari)){
							total=total+dades[i].quantitat;
							System.out.println("|"+dades[i].deutor+"\t"+monedaInicial+dades[i].quantitat+monedaFinal+"\t|");
							System.out.println("|---------------|");
						}
					}
					
					System.out.println("|---------------|");
					System.out.println("|"+traduccio[iTotal][idioma]+"\t"+monedaInicial+total+monedaFinal+"\t|");
					System.out.println("|---------------|");
					
					break;
				case "3": //Enrera
					
					pantallaActual=Pantalles.PRINCIPAL;
					break;
					
				default:
					System.out.println(traduccio[iOpcioNr][idioma]);
				}	
				System.out.println();
			}
		}
	}
}