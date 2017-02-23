package deuteX;

public class FuncionsAuxiliars {

	public static void printTaula(String usuari, Dades[] dades, String monedaInicial, String monedaFinal){
		float total=0;
		System.out.println(
				"|---------------|\n"+
				"|\t"+usuari+"\t|\n"+
				"|---------------|\n"+
				"|"+DeuteX.traduccio[DeuteX.DEUTOR][DeuteX.idioma]+"\t|\n"+
				"|---------------|"
		);
		total=0;
		
		for(int i=0;i<dades.length;i++){
			if(dades[i].prestamista!= null && dades[i].prestamista.equals(usuari)){
				total=total+dades[i].quantitat;
				System.out.println("|"+dades[i].deutor+"\t"+monedaInicial+dades[i].quantitat+monedaFinal+"\t|");
				System.out.println("|---------------|");
			}
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
