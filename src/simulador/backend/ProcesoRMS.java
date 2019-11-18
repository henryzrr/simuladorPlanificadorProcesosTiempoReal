package simulador.backend;

public class ProcesoRMS extends Proceso implements Comparable<ProcesoRMS>{

	public ProcesoRMS(int periodo, int tiempo, String nombre) {
		super(periodo, tiempo, nombre);
	}
	@Override
	public int compareTo(ProcesoRMS p) {
		if(periodo>p.getPeriodo())return 1;
		else if (periodo<p.getPeriodo())return -1;
		else return 0;
	}
}
