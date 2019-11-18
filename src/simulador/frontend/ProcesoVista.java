package simulador.frontend;

public class ProcesoVista {
	private String nombreProceso;
	private String tiempoInicio;
	private String  periodo;
	private String tiempoEjecucion;
	private String utilizacion;
	private String prioridad;
	public ProcesoVista(String nombre,int tiempoInicio, int periodo, int tiempoEjecucion) {
		nombreProceso=nombre;
		this.tiempoEjecucion=tiempoEjecucion+"";
		this.tiempoInicio=tiempoInicio+"";
		this.periodo=periodo+"";
	}
	public ProcesoVista(String nombre,int tiempoInicio, int periodo, int tiempoEjecucion,double utilizacion, double prioridad) {
		nombreProceso=nombre;
		this.tiempoEjecucion=tiempoEjecucion+"";
		this.tiempoInicio=tiempoInicio+"";
		this.periodo=periodo+"";
		this.utilizacion=utilizacion+"";
		this.prioridad=prioridad+"";
	}
	public void setPrioridad(String prioridad) {
		this.prioridad = prioridad;
	}
	public String getPrioridad() {
		return prioridad;
	}
	public void setUtilizacion(String utilizacion) {
		this.utilizacion = utilizacion;
	}
	public String getUtilizacion() {
		return utilizacion;
	}
	public String getNombreProceso() {
		return nombreProceso;
	}

	public void setNombreProceso(String nombreProceso) {
		this.nombreProceso = nombreProceso;
	}

	public String getTiempoInicio() {
		return tiempoInicio;
	}

	public void setTiempoInicio(String tiempoInicio) {
		this.tiempoInicio = tiempoInicio;
	}

	public String getPeriodo() {
		return periodo;
	}

	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}

	public String getTiempoEjecucion() {
		return tiempoEjecucion;
	}

	public void setTiempoEjecucion(String tiempoEjecucion) {
		this.tiempoEjecucion = tiempoEjecucion;
	}

}
