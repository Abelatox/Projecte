import java.io.IOException;
import java.util.Scanner;

public class Projecte{
	public static void main(String[] args) {
		//Definim la variable de la pantalla actual.
		int pantallaActual=0;
		//Creem les arrays per usuaris
		String[] usuaris= new String[10];
		String[] password=new String[10];
		//Usuaris inicials
		usuaris[0] = "Abel";
		password[0] = "123";
		usuaris[1] = "Xevi";
		password[1] = "456";
		int usuarisActius=0;
		Scanner sc = new Scanner(System.in);
		boolean stop = false;
		String opcio;
		String opcioMenuLogin;
		String usuari="",contrasenya="";
		boolean usuariRepetit = false;
		
		System.out.println("Benvingut al <inserte nombre del programa aqui>");
		while(stop == false)
		{
			if(pantallaActual==0){
				usuari="";
				contrasenya="";
				usuariRepetit=false;
				usuarisActius=0; //Es posa a 0 per quan compti que no surti dels limits
				for(int i=0;i<usuaris.length;i++){
					if(!(usuaris[i] == null))
						usuarisActius++;
				}
				//En cada bucle recompta els usuaris actius
				System.out.println("1: Registrar-se");
				System.out.println("2: Loguejar-se");
				opcio = sc.nextLine();
				
				switch(Integer.parseInt(opcio)){
				case 1: //Registre
					System.out.print("Introdueix el nom d'usuari: ");
					usuari=sc.nextLine();
					//Comprova si l'usuari ja existeix.
					for(int i=0;i<usuarisActius;i++){
						if(usuaris[i].equals(usuari)){
							System.out.println("Usuari repetit");
							usuariRepetit = true;
							break;
						}
					}
					//Si el nom d'usuari es valid et demana contrasenya i el fica en la array.
					if(!usuariRepetit){
						System.out.print("Introdueix la contrasenya: ");
						contrasenya=sc.nextLine();
						usuaris[usuarisActius] = usuari;
						password[usuarisActius] = contrasenya;
					}
					break;
					
				case 2: //Login
					int index=-1; //Variable que guarda en quin index es troba l'usuari.
					System.out.print("Usuari: ");
					usuari=sc.nextLine(); //Llegim el que Introdueix l'usuari.
					System.out.print("Contrassenya: ");
					contrasenya=sc.nextLine(); 
					for(int i=0;i<usuarisActius;i++){ //Aquest for guarda l'index en el que està l'usuari que volem saber.
						if(usuaris[i].equals(usuari)){
							usuariRepetit = true;
							index=i;
							break;
						}
					}
					if(usuariRepetit){ //Si hi ha un usuari repetit
						if(usuari.equals(usuaris[index])){
							if(contrasenya.equals(password[index])){
								pantallaActual=1;
							}
						}
					}
					break;
					
				default:
					System.out.println("Opcio no reconeguda");
				}
			}else if(pantallaActual==1){
				System.out.println("Benvingut "+usuari);
				System.out.println("1- Afegir deute");
				System.out.println("2- Notificacions");
				System.out.println("3- Tornar enrera");
				System.out.print("Tria una opció: ");
				opcioMenuLogin = sc.nextLine();
				switch(Integer.parseInt(opcioMenuLogin)){
				case 1: //Afegir deute
					
					break;
					
				case 2: //Notifiacions / informació
					
					break;
				case 3: //Enrera
					pantallaActual=0;
					break;
					
				default:
					System.out.println("Opcio no reconeguda");
				}				
			}
		}
	}
}
