package simulador.frontend;

import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;


import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import simulador.backend.Edf;
import simulador.backend.ProcesoEDF;
import simulador.backend.ProcesoRMS;
import simulador.backend.Rms;

public class Controlador implements Initializable{
	@FXML
	private TableView<ProcesoVista> procesos;
	@FXML
	private TableColumn<ProcesoVista,String> nombreProceso;
	@FXML
	private TableColumn<ProcesoVista,String> tiempoInicio ;
	@FXML
	private TableColumn< ProcesoVista,String> periodo;
	@FXML
	private TableColumn<ProcesoVista,String> tiempoEjecucion;
	
	@FXML 
	private TextField tiempoI,per,tiempoEj;
	
	@FXML
	private RadioButton rms,edf,algoritmoSeleccionado;
	ObservableList<ProcesoVista> lista;
	private char procesoActual;
	public static List<ProcesoRMS> procesosrms;
	public static List<ProcesoEDF> procesosedf;
	public static String [] planificacion;
	public static String mensaje;
	public static String error;
	public Controlador() {
		procesoActual='A';
		
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		nombreProceso.setCellValueFactory(new PropertyValueFactory<>("nombreProceso"));
		tiempoInicio.setCellValueFactory(new PropertyValueFactory<>("tiempoInicio"));
		periodo.setCellValueFactory(new PropertyValueFactory<>("periodo"));
		tiempoEjecucion.setCellValueFactory(new PropertyValueFactory<>("tiempoEjecucion"));
		
		/*tiempoInicio.setCellFactory(TextFieldTableCell.forTableColumn());
		periodo.setCellFactory(TextFieldTableCell.forTableColumn());
		tiempoEjecucion.setCellFactory(TextFieldTableCell.forTableColumn());*/
		procesos.setPlaceholder(new Label("SIN PROCESOS CARGADOS"));
		lista=procesos.getItems();
		tiempoI.setText("0");
		//per.setText("0");
		//tiempoEj.setText("0");
		tiempoI.setDisable(true);
		algoritmoSeleccionado=rms;
	}
	@FXML
	public void cargarProcesos() {
		try {
			ProcesoVista p = new ProcesoVista(procesoActual+"", Integer.parseInt(tiempoI.getText()), 
					Integer.parseInt(per.getText()), Integer.parseInt(tiempoEj.getText()));
			lista.add(p);
			tiempoI.setText("0");
			per.clear();
			tiempoEj.clear();
			//per.setText("0");
			//tiempoEj.setText("0");
			procesoActual++;
		}catch(Exception e) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("ERROR!");
			alert.setHeaderText(null);
			alert.setContentText("Los campos TIEMPO INICIO, PERIODO, Y TIEMPO EJECUCION DEBEN SER NUMERICOS!");
			alert.showAndWait();
		}
		
	}
	@FXML
	public void eliminarProcesos() {
		lista.clear();
		procesoActual='A';
	}
	@FXML
	public void seleccionAlgoritmo(Event e) {
		if(e.getSource().equals(edf)) {
			rms.setSelected(false);
			algoritmoSeleccionado=edf;
			habilitarEDF();
		}else {
			edf.setSelected(false);
			algoritmoSeleccionado=rms;
			habilitarRMS();
		}
	}
	@FXML
	public void ejecutarAlgoritmo() {
		if(algoritmoSeleccionado.equals(edf)) {
			procesosedf = new LinkedList<>();
			for (ProcesoVista proceso : lista) {
				ProcesoEDF pedf = new ProcesoEDF(Integer.parseInt(proceso.getPeriodo()),Integer.parseInt(proceso.getTiempoEjecucion()),
						proceso.getNombreProceso(), Integer.parseInt(proceso.getTiempoInicio()));
				procesosedf.add(pedf);
			}
			iniciarAlgoritmoEdf();
		}else {
			procesosrms = new LinkedList<>();
			for (ProcesoVista proceso : lista) {
				ProcesoRMS prms = new ProcesoRMS(Integer.parseInt(proceso.getPeriodo()),Integer.parseInt(proceso.getTiempoEjecucion()),
						proceso.getNombreProceso());
				procesosrms.add(prms);
			}
			iniciarAlgoritmoRms();
		}
	}
	private void habilitarEDF() {
		tiempoI.setDisable(false);;
	}
	private void habilitarRMS() {
		tiempoI.setDisable(true);
	}
	private void iniciarAlgoritmoEdf( ) {
		Edf edf = new Edf(procesosedf);
		planificacion = edf.iniciarPlanificacion();
		procesosrms=null;
		error=edf.getError();
		crearVentana();
	}
	private void iniciarAlgoritmoRms( ) {
		Rms rms = new Rms(procesosrms);
		mensaje=rms.realizarTestDePlanificabilidad();
		planificacion = rms.IniciarPlanificacion();
		procesosedf=null;
		error=rms.getError();
		crearVentana();
	}
	private void crearVentana() {
		try {
		Stage primaryStage = new Stage();
		FXMLLoader loader = new FXMLLoader();
		URL fxml = getClass().getClassLoader()
  				.getResource("simulador/frontend/ResultadoAlgoritmo.fxml");
        loader.setLocation(fxml);
        Scene scene = new Scene(loader.load());
        primaryStage.setScene(scene);
        primaryStage.setTitle("Simulacion");
        primaryStage.show();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
