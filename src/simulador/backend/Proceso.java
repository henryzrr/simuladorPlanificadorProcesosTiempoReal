package simulador.backend;

public abstract class Proceso {
	protected int periodo;
	protected int tiempo;
	protected double utilizacion;
	protected String nombreProceso;
	public Proceso(int periodo, int tiempo,String nombre) {
		this.periodo = periodo;
		this.tiempo = tiempo;
		this.nombreProceso=nombre;
		utilizacion = (double)tiempo/(double)periodo;
	}

	public int getPeriodo() {
		return periodo;
	}

	public int getTiempo() {
		return tiempo;
	}

	public double getUtilizacion() {
		return utilizacion;
	}

	public String getNombreProceso() {
		return nombreProceso;
	}
	
	
}
