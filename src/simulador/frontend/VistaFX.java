package simulador.frontend;

import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class VistaFX extends Application {
	private Stage stage;
	public VistaFX() {
		
	}
	public void iniciar(String [] args) {
		Application.launch(args);
	}
	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader loader = new FXMLLoader();
		URL fxml = getClass().getClassLoader()
  				.getResource("simulador/frontend/vista.fxml");
        loader.setLocation(fxml);
        Scene scene = new Scene(loader.load());
        primaryStage.setScene(scene);
        primaryStage.show();
        stage = primaryStage;
	}
	
}
