package simulador.frontend;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.TextAlignment;
import simulador.backend.ProcesoEDF;
import simulador.backend.ProcesoRMS;

public class ControladorResultado implements Initializable{
	@FXML
	ImageView imagen;
	@FXML
	Label titulo,respuesta,totalCPU,error,planExitoso;
	@FXML
	AnchorPane pane;
	@FXML
	TableView<ProcesoVista> tabla;
	@FXML
	TableColumn<ProcesoVista, String> nombreProceso;
	@FXML
	TableColumn<ProcesoVista, String> periodo;
	@FXML
	TableColumn<ProcesoVista, String> ejecucion;
	@FXML
	TableColumn<ProcesoVista, String> utilizacion;
	@FXML
	TableColumn<ProcesoVista, String> prioridad;
	ObservableList<ProcesoVista> lista;
	String [] plan;
	int nroProcesos=0;
	double totalU;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		nombreProceso.setCellValueFactory(new PropertyValueFactory<>("nombreProceso"));
		utilizacion.setCellValueFactory(new PropertyValueFactory<>("utilizacion"));
		periodo.setCellValueFactory(new PropertyValueFactory<>("periodo"));
		ejecucion.setCellValueFactory(new PropertyValueFactory<>("tiempoEjecucion"));
		prioridad.setCellValueFactory(new PropertyValueFactory<>("prioridad"));
		lista=tabla.getItems();
		imagen.setPreserveRatio(true);
		if(Controlador.procesosedf==null) {
			llenarRms();
		}else {
			llenarEdf();
		}
		totalCPU.setText(totalU+"");
		plan=Controlador.planificacion;
		if(Controlador.error==null) {
			dibujarPlan();
		}else {
			planExitoso.setVisible(false);
			error.setText(Controlador.error);
		}
	}
	private void llenarRms() {
		for (ProcesoRMS p : Controlador.procesosrms) {
			nroProcesos++;
			lista.add(new ProcesoVista(p.getNombreProceso(),0,p.getPeriodo(),p.getTiempo(),p.getUtilizacion(),(100.0/(double)p.getPeriodo())));
			totalU+=p.getUtilizacion();
		}
		respuesta.setText(Controlador.mensaje);
		respuesta.setTextAlignment(TextAlignment.JUSTIFY);
		titulo.setText("ALGORITMO RMS");
		
	}
	private void llenarEdf() {
		
		for (ProcesoEDF p : Controlador.procesosedf) {
			nroProcesos++;
			lista.add(new ProcesoVista(p.getNombreProceso(),p.getTiempoInicio(),p.getPeriodo(),p.getTiempo(),p.getUtilizacion(),0.0));
			totalU+=p.getUtilizacion();
		}
		imagen.setVisible(false);
		respuesta.setVisible(false);
		prioridad.setVisible(false);
		titulo.setText("ALGORITMO EDF");
	}
	private void dibujarPlan() {
		int razon=25;
		int xi= 40;
		int xf = (plan.length*razon)+xi;
		int yf=40;
		int yi=(nroProcesos*razon)+yf;
		pane.setPrefWidth(xf+40);
		pane.setPrefHeight(yi+40);
		ObservableList<Node> contenido=pane.getChildren();
		int aux=yf;
		char p='A';
		Label l;
		for(int i=0;i<=nroProcesos;i++) {
			contenido.add(new Line(xi,aux,xf,aux));
			l = new Label(p+"");
			l.setLayoutX(xi-15);
			l.setLayoutY(aux);
			contenido.add(l);
			p++;
			aux+=razon;
		}
		contenido.remove(contenido.size()-1);
		aux=xi;
		for(int i=0;i<=plan.length;i++) {
			contenido.add(new Line(aux,yi,aux,yf));
			l = new Label(i+"");
			l.setLayoutX(aux);
			l.setLayoutY(yi+10);
			l.setRotate(-90);
			contenido.add(l);
			aux+=razon;
		}
		Rectangle r;
		int x=xi;
		int y;
		for(int i=0;i<plan.length;i++) {
			if(plan[i]==null) {
				x+=razon;
				continue;
			}
			aux=(plan[i].charAt(0))-'A';
			y=yf+(razon*aux);
			r=new Rectangle(x,y,razon,razon);
			r.setFill(Color.DARKORANGE);
			contenido.add(r);
			x+=razon;
			
		}
	}
}
