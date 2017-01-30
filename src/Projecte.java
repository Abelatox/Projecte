import java.util.Scanner;

public class Projecte{
	public static void main(String[] args) {
		//Definim la variable de la pantalla actual.
		int pantallaActual=0;
		//Creem les arrays per usuaris
		String[] usuaris= new String[10];
		String[] password=new String[10];
		
		String[] deutors=new String[10];
		float[] quantitats=new float[10];
		String[] prestamistes=new String[10];
		
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
		
		String deutor=""; //Qui deu diners
		float quantitat=0F;
		float total=0;
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
				System.out.println("2: Entrar");
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
                    System.out.print("Contrasenya: ");
                    contrasenya=sc.nextLine();
                    for(int i=0;i<usuarisActius;i++){ //Aquest for guarda l'index en el que està l'usuari que volem saber i si existeix.
                        if(usuaris[i].equals(usuari)){
                            usuariRepetit = true;
                            index=i;
                            break;
                        }
                    }
                    if(usuariRepetit){ //Si existeix l'usuari
                            if(contrasenya.equals(password[index])){ // Comprova si la contrassenya és correta per l'usuari
                				System.out.println("Benvingut "+usuari);
            					System.out.println();
                                pantallaActual=1;
                            }else{
                                    System.out.println("contrasenya erronia!!");
                                }
                    }else{
                        System.out.println("usuari erroni!!");
                    }
                    break;
                    
                default:
                    System.out.println("Opcio no reconeguda");
                }

			}else if(pantallaActual==1){
				System.out.println("1- Editar deute");
				System.out.println("2- Informacio de deutes");
				System.out.println("3- Tornar enrera");
				System.out.print("Tria una opcio: ");
				opcioMenuLogin = sc.nextLine();
				
				switch(Integer.parseInt(opcioMenuLogin)){
				case 1: //Afegir deute
					System.out.print("Introdueix qui et deu diners: ");
					deutor = sc.nextLine().toUpperCase().trim();
					System.out.print("Introdueix la quantitat: ");
					quantitat=Float.parseFloat(sc.nextLine());
										
					//Afegim les dades a les arrays
					for(int i=0;i<deutors.length;i++){
						if(deutors[i]==null){
							deutors[i]=deutor;
							quantitats[i]=quantitat;
							prestamistes[i]=usuari;
							break;
						}else{ //Si el usuari �s el mateix es suma / resta a la quantitat que deu
							if(deutors[i].equals(deutor)){
								quantitats[i]=quantitats[i]+quantitat;
								break;
							}
						}
					}
				break;
					
				case 2: //Notifiacions / informació
					System.out.println("|---------------|");
					System.out.println("|Deutor\tQtat."+"\t|");
					System.out.println("|---------------|");
					total=0;
					for(int i=0;i<deutors.length;i++){
						if(prestamistes[i]==usuari){
							total=total+quantitats[i];
							System.out.println("|"+deutors[i]+"\t"+quantitats[i]+"\t|");
							System.out.println("|---------------|");
						}
					}
					System.out.println("|---------------|");
					System.out.println("|Total\t"+total+"\t|");
					System.out.println("|---------------|");
					
					break;
				case 3: //Enrera
					pantallaActual=0;
					break;
					
				default:
					System.out.println("Opcio no reconeguda");
				}	
				System.out.println();
			}
		}
	}
}
