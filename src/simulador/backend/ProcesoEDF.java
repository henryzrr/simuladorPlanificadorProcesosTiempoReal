package simulador.backend;

public class ProcesoEDF extends Proceso implements Comparable<ProcesoEDF>{
	private int tiempoInicio;
	private int deadLine;
	private int tiempoEjecutado;
	public ProcesoEDF(int periodo, int tiempo, String nombre, int tiempoInicio) {
		super(periodo, tiempo, nombre);
		this.tiempoInicio=tiempoInicio;
		deadLine=periodo+tiempoInicio;
		tiempoEjecutado=0;
	}
	@Override
	public int compareTo(ProcesoEDF p) {
		if(deadLine>p.getDeadLine()) {
			return 1;
		}else if (deadLine<p.getDeadLine()) {
			return -1;
		}else return 0;
	}
	public int getDeadLine() {
		return deadLine;
	}
	public void setDeadLine(int deadLine) {
		this.deadLine = deadLine;
	}
	public int getTiempoInicio() {
		return tiempoInicio;
	}
	public void setTiempoEjecutado(int tiempoEjecutado) {
		this.tiempoEjecutado = tiempoEjecutado;
	}
	public int getTiempoEjecutado() {
		return tiempoEjecutado;
	}
	public void setTiempoInicio(int tiempoInicio) {
		this.tiempoInicio = tiempoInicio;
	}
}
