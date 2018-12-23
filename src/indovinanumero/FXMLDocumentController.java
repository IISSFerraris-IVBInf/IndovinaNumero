/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Sample Skeleton for 'FXMLDocument.fxml' Controller Class
 */

package indovinanumero;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class FXMLDocumentController {
    
    private Model model;
    
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="btnNuova"
    private Button btnNuova; // Value injected by FXMLLoader

    @FXML // fx:id="txtCurr"
    private TextField txtCurr; // Value injected by FXMLLoader

    @FXML // fx:id="txtMax"
    private TextField txtMax; // Value injected by FXMLLoader

    @FXML // fx:id="boxGioco"
    private HBox boxGioco; // Value injected by FXMLLoader

    @FXML // fx:id="txtTentativo"
    private TextField txtTentativo; // Value injected by FXMLLoader

    @FXML // fx:id="txtLog"
    private TextArea txtLog; // Value injected by FXMLLoader

    @FXML
    void handleNuova(ActionEvent event) {
        //Nuova partita
         
        model.newGame(); 
        
         txtLog.clear();
         txtTentativo.setText("");
         btnNuova.setDisable(true);
         boxGioco.setDisable(false);
         txtMax.setText(""+model.getTMAX());
         txtCurr.setText(""+model.getTentativi());
         txtLog.setText("Indovina un numero compreso fra 1 e "+model.getNMAX());
         
    }

    @FXML
    void handleProva(ActionEvent event) {
        //Pulsante di prova del numero
        String numS=txtTentativo.getText();
        if (numS.length()==0) 
        { 
            txtLog.appendText("Devi inserire un numero\n");
            return;
        }
        try
        {
            int num=Integer.parseInt(numS);
            //Numero intero
            if (!model.valoreValido(num))
            {
               txtLog.appendText("Devi inserire un numero compreso fra 1 e "+model.getNMAX()+"\n");
               return; 
            }
            //A questo punto il numero inseriro è accettabile
            txtTentativo.setText("");
            txtTentativo.requestFocus();
            txtLog.appendText("Num inserito: "+num+" ");
            
            
            
            int esito=model.tentativo(num);
            txtCurr.setText(""+model.getTentativi());
            
            if (esito==0)
            {
                txtLog.appendText("\nBravo!! Hai indovinato in "+model.getTentativi()+" tentativi\nPartita terminata.\n");
                chiudiPartita();
                return;
            }
            if (esito==-1)
            {
                txtLog.appendText("\nIl numero inserito è minore di quello segreto.\n");
            }else
            {
                txtLog.appendText("\nIl numero inserito è maggiore di quello segreto.\n");
            }
            if (!model.isInGame())
            {
                //La partita è finita (vittoria o sconfitta)
                if (esito!=0)
                    txtLog.appendText("\nHai perso!! Numero di tentativi esaurito.\nIl numero segreto era "+model.getSegreto()+"\n");
                chiudiPartita();
                return;
            }
            
        }catch(NumberFormatException e)
        {
            txtLog.appendText("Il dato inserito non è numerico\n");
            return;
        }
            
    }
    /**
     * Abilita e disabilita i pulsanti giusti
     */
    private void chiudiPartita()
    {
        //Chiudo la partita
        btnNuova.setDisable(false);
        boxGioco.setDisable(true); 
    }
    
    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert btnNuova != null : "fx:id=\"btnNuova\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert txtCurr != null : "fx:id=\"txtCurr\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert txtMax != null : "fx:id=\"txtMax\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert boxGioco != null : "fx:id=\"boxGioco\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert txtTentativo != null : "fx:id=\"txtTentativo\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert txtLog != null : "fx:id=\"txtLog\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        
        
    }

    public void setModel(Model model) {
        this.model = model;
    }
    
    
}
