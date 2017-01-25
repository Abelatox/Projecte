import java.io.IOException;
import java.util.Scanner;

public class Projecte{
	public static void main(String[] args) {
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
		String usuari,contrasenya;
		boolean usuariRepetit = false;
		
		while(stop == false)
		{
			for(int i=0;i<usuaris.length;i++){
				if(!(usuaris[i] == null))
					usuarisActius++;
			}
			//En cada bucle recompta els usuaris actius
			System.out.println("1: Registrar-se");
			System.out.println("2: Loguejar-se");
			opcio = sc.nextLine();
			switch(Integer.parseInt(opcio)){
			case 1:
				System.out.print("Introdueix el nom d'usuari: ");
				usuari=sc.nextLine();
				usuariRepetit=false;
				//Comprova si l'usuari ja existeix.
				for(int i=0;i<usuarisActius;i++){
					if(usuaris[i].equals(usuari)){
						System.out.println("Usuari repetit");
						usuariRepetit = true;
						break;
					}
				}
				if(!usuariRepetit){
					System.out.print("Introdueix la contrasenya: ");
					contrasenya=sc.nextLine();
					usuaris[usuarisActius] = usuari;
					password[usuarisActius] = contrasenya;
				}
				break;
			case 2:
				
				break;
			default:
			}
		}
	}
}
