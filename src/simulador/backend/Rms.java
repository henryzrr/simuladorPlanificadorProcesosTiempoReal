
package simulador.backend;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;



public class Rms {
	private String error;
	private List<ProcesoRMS> procesos;
	public Rms(List<ProcesoRMS>procesos) {
		this.procesos=procesos;
	}
	
	public String realizarTestDePlanificabilidad() {
		int numeroProcesos = procesos.size();
		double ecuacion1 = numeroProcesos*(Math.pow(2, (1.0/(double)numeroProcesos))-1);
		double ecuacion2=0;
		for (ProcesoRMS proceso : procesos) {
			ecuacion2+=proceso.getUtilizacion();
		}
		if(ecuacion2>ecuacion1) {
			return "Los datos ingresados pueden no ser planificables debido que no cumplen el test de planificabilidad con la ecuacion mostrada";
		}
		return "El conjunto de procesos es planificable mediante EDF";
	}
	public String [] IniciarPlanificacion() {
		int mcm = AlgoritmosAuxiliares.encontrarMinimoComunMultiplo(getPeriodos());
		Collections.sort(procesos);
		String [] tablero = new String[mcm];
		boolean implanificable=false;
		for (ProcesoRMS proceso : procesos) {
			int tiempoCompletado=0;
			int periodoFin=proceso.getPeriodo();
			int contadorArreglo=0;
			while(contadorArreglo<mcm) {
				if(tablero[contadorArreglo]==null) {
					tablero[contadorArreglo]=proceso.getNombreProceso();
					tiempoCompletado++;
				}
				if(tiempoCompletado==proceso.getTiempo()) {
					tiempoCompletado=0;
					contadorArreglo=periodoFin-1;
					periodoFin+=proceso.getPeriodo();
				}else if(periodoFin==contadorArreglo+1 && tiempoCompletado<proceso.getTiempo()) {
					error="Los procesos son implanificables, estos superan el uso del 100% de la CPU";
					implanificable=true;
					break;
				}
				contadorArreglo++;
			}
			if(implanificable) {
				break;
			}
		}
		return tablero;
	}
	private List<Integer> getPeriodos(){
		List<Integer>numeros = new ArrayList<>();
		for (ProcesoRMS proceso : procesos) {
			numeros.add(proceso.getPeriodo());
		}
		Collections.sort(numeros,Collections.reverseOrder());
		return numeros;
	}
	public String getError() {
		return error;
	}
}
