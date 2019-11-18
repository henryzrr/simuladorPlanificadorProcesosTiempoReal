package simulador.backend;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

public class Edf {
	private String error;

	private List<ProcesoEDF> procesos;
	private PriorityQueue<ProcesoEDF> colaPrioridad;
	private List<ProcesoEDF> enEspera;
	public Edf(List<ProcesoEDF> procesos) {
		this.procesos=procesos;
		colaPrioridad = new PriorityQueue<>();
		enEspera = new LinkedList<>();
		iniciarCola();
	}
	
	private void iniciarCola() {
		for (ProcesoEDF proceso : procesos) {
			if(proceso.getTiempoInicio()==0) {
				colaPrioridad.add(proceso);
			}else{
				enEspera.add(proceso);
			}
		}
	}
	public String [] iniciarPlanificacion() {
		int mcm = AlgoritmosAuxiliares.encontrarMinimoComunMultiplo(getPeriodos());
		String [] tablero = new String[mcm];
		int i=0;
		int aux;
		while(i<mcm) {
			ProcesoEDF p = colaPrioridad.poll();
			if(p==null) {
				i++;
				verificarNuevosProcesos(i);
				continue;
			}
			if(p.getTiempoEjecutado()<p.tiempo) {
				tablero[i]=p.getNombreProceso();
				p.setTiempoEjecutado(p.getTiempoEjecutado()+1);
				i++;
				verificarNuevosProcesos(i);
				colaPrioridad.add(p);
			}else if(p.getTiempoEjecutado()==p.tiempo){
				aux=p.getDeadLine();
				p.setTiempoEjecutado(0);
				p.setDeadLine(aux+p.getPeriodo());
				if(aux>i) {
					p.setTiempoInicio(aux);
					enEspera.add(p);
					
				}else {
					colaPrioridad.add(p);
				}
			}
		}
		if(!colaPrioridad.isEmpty()&& i<mcm)
			error="Los procesos son implanificables, estos superan el uso del 100% de la CPU";
		return tablero;
	}
	private  List<Integer> getPeriodos(){
		List<Integer>numeros = new ArrayList<>();
		for (ProcesoEDF proceso : procesos) {
			numeros.add(proceso.getPeriodo());
		}
		Collections.sort(numeros,Collections.reverseOrder());
		return numeros;
	}
	private void verificarNuevosProcesos(int tiempoActual) {
		Iterator<ProcesoEDF> it = enEspera.iterator();
		while(it.hasNext()) {
			ProcesoEDF p = it.next();
			if(p.getTiempoInicio()==tiempoActual) {
				colaPrioridad.add(p);
				it.remove();
			}
		}
	}
	public String getError() {
		return error;
	}
}
