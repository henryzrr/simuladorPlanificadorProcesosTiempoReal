package simulador.backend;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class AlgoritmosAuxiliares {
	public static int encontrarMinimoComunMultiplo(List<Integer>numeros) {
		int i=0;
		int tamLista = numeros.size();
		boolean impar=false;
		if((tamLista&1)==1) {
			impar=true;
			tamLista--;
		}
		List<Integer> nuevaLista = new ArrayList<>();
		int auxMCD;
		for(;i<tamLista;i+=2) {
			auxMCD=algoritmoEuclides(numeros.get(i), numeros.get(i+1));
			nuevaLista.add((numeros.get(i)*numeros.get(i+1))/auxMCD);
		}
		if(impar) nuevaLista.add(numeros.get(i));
		Collections.sort(nuevaLista,Collections.reverseOrder());
		if(nuevaLista.size()==1)
			return nuevaLista.get(0);
		else
			return encontrarMinimoComunMultiplo(nuevaLista);
	}
	
	private static int algoritmoEuclides(int numA, int numB) {
		if(numB==0) {
			return numA;
		}else {
			return algoritmoEuclides(numB, numA%numB);
		}
	}
	
}
