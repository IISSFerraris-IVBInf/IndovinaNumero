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
    
    private final int NMAX=100;
    private final int TMAX=7;
    private int segreto; //numero pensato dal computer
    private int tentativi; //tentativi già fatti
    private boolean inGame; //se la partita è in corso

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
         
         this.segreto=(int)(Math.random()*NMAX)+1;
         this.tentativi=0;
         this.inGame=true;
         txtLog.clear();
         txtTentativo.setText("");
         btnNuova.setDisable(true);
         boxGioco.setDisable(false);
         txtMax.setText(""+this.TMAX);
         txtCurr.setText(""+this.tentativi);
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
            if (num<1||num>NMAX)
            {
               txtLog.appendText("Devi inserire un numero compreso fra 1 e "+NMAX+"\n");
               return; 
            }
            //A questo punto il numero inseriro è accettabile
            txtTentativo.setText("");
            txtTentativo.requestFocus();
            txtLog.appendText("Num inserito: "+num+" ");
            this.tentativi++;
            txtCurr.setText(""+this.tentativi);
            if (num==segreto)
            {
                txtLog.appendText("Bravo!! Hai indovinato in "+tentativi+" tentativi\nPartita terminata.\n");
                this.inGame=false;
                btnNuova.setDisable(false);
                boxGioco.setDisable(true); 
                return;
            }
            if (num<segreto)
            {
                txtLog.appendText("Il numero inserito è minore di quello segreto.\n");
            }else
            {
                txtLog.appendText("Il numero inserito è maggiore di quello segreto.\n");
            }
            if (tentativi==TMAX)
            {
                txtLog.appendText("Hai perso!! Numero di tentativi esaurito.\nIl numero segreto era "+segreto+"\n");
                this.inGame=false;
                btnNuova.setDisable(false);
                boxGioco.setDisable(true); 
                return;
            }
            
        }catch(NumberFormatException e)
        {
            txtLog.appendText("Il dato inserito non è numerico\n");
            return;
        }
            
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert btnNuova != null : "fx:id=\"btnNuova\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert txtCurr != null : "fx:id=\"txtCurr\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert txtMax != null : "fx:id=\"txtMax\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert boxGioco != null : "fx:id=\"boxGioco\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert txtTentativo != null : "fx:id=\"txtTentativo\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert txtLog != null : "fx:id=\"txtLog\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        
        //mie variabili
        inGame=false;
    }
}
