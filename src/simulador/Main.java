package simulador;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import javafx.application.Application;
import javafx.stage.Stage;
import simulador.backend.Edf;
import simulador.backend.ProcesoEDF;
import simulador.frontend.VistaFX;


public class Main{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		/*List<ProcesoRMS> procesos = new LinkedList<>();
		
		/*procesos.add(new ProcesoRMS(5,1,"A"));
		procesos.add(new ProcesoRMS(10,3,"B"));
		procesos.add(new ProcesoRMS(15,2,"C"));
		procesos.add(new ProcesoRMS(10,2,"D"));
		Rms rms = new Rms(procesos);
		rms.IniciarPlanificacion();*/
		//List<ProcesoEDF> procesos = new LinkedList<>();
		//procesos.add(new ProcesoEDF(3,1,"A",0));
		//procesos.add(new ProcesoEDF(2,1,"B",0));
		//procesos.add(new ProcesoEDF(10,2,"C",0));
		//procesos.add(new ProcesoEDF(10,2,"D",2));
		//Edf edf = new Edf(procesos);
		//System.out.println(Arrays.toString(edf.iniciarPlanificacion()));
		VistaFX vista = new VistaFX();
		vista.iniciar(args);
	}

}
