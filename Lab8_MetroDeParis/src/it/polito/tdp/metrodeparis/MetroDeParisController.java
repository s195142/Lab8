/**
 * Sample Skeleton for 'MetroDeParis.fxml' Controller Class
 */

package it.polito.tdp.metrodeparis;

import java.net.URL;
import java.util.ResourceBundle;

import it.polito.tdp.metrodeparis.model.Fermata;
import it.polito.tdp.metrodeparis.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;

public class MetroDeParisController {
	
	Model model;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="partenza"
    private ComboBox<Fermata> partenza; // Value injected by FXMLLoader

    @FXML // fx:id="arrivo"
    private ComboBox<Fermata> arrivo; // Value injected by FXMLLoader

    @FXML // fx:id="btnPercorso"
    private Button btnPercorso; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    @FXML
    void doPercorso(ActionEvent event) {
    	
    	Fermata p = partenza.getValue();
    	Fermata a = arrivo.getValue();
    	
    	if(p!=null) {
    		if(a!=null) {
    			if(!p.equals(a)) { // !!!
        			String risultato = model.calcolaPercorso(p, a); //parametri da sol
        			txtResult.setText(risultato);
    			}else {
    				showMessage("Errore: selezionare una fermata di arrivo diversa da quella di partenza");
    			}
    		}else {
        		showMessage("Errore: selezionare una fermata di arrivo");
    		}
    	}else {
    		showMessage("Errore: selezionare una fermata di partenza");
    	}
    	

    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert partenza != null : "fx:id=\"partenza\" was not injected: check your FXML file 'MetroDeParis.fxml'.";
        assert arrivo != null : "fx:id=\"arrivo\" was not injected: check your FXML file 'MetroDeParis.fxml'.";
        assert btnPercorso != null : "fx:id=\"btnPercorso\" was not injected: check your FXML file 'MetroDeParis.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'MetroDeParis.fxml'.";
        
        partenza.getItems().addAll(Model.getAllFermate());
        arrivo.getItems().addAll(Model.getAllFermate());


    }

	public void setModel(Model model) {
		this.model = model;
	}
	
	
	private void showMessage(String message) {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setContentText(message);
		alert.show();
	}
	
}
