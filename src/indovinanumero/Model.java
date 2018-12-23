/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package indovinanumero;

import java.security.InvalidParameterException;

/**
 *
 * @author alegu
 */
public class Model {
    
    private final int NMAX=100;
    private final int TMAX=7;
    private int segreto; //numero pensato dal computer
    private int tentativi; //tentativi già fatti
    private boolean inGame; //se la partita è in corso
    
    public Model()
    {
        this.inGame=false;
    }
    
    /**
     * Avvia una nuova partita generando un nuovo numero segreto 
     */
    public void newGame()
    {
        this.segreto=(int)(Math.random()*NMAX)+1;
        this.tentativi=0;
        this.inGame=true;
    }
    
    
    
    /**
     * Fai un nuovo tentativo di indovinare un numero segreto
     * @param prova valore numerico del tentativo
     * @return 0=indovinato, +1=troppo grande, -1=troppo piccolo
     */
    public int tentativo (int prova)
    {
         if(!inGame)
         {
             throw new IllegalStateException("Partita non attiva!!");
         }
         
         if(!valoreValido(prova))
         {
             throw new InvalidParameterException("Valore tentato fuori range!!");
         }
         this.tentativi++;
         
         if (prova==segreto)
         {
             inGame=false;
             return 0;
         }
         
         if(tentativi==TMAX)
         {
             this.inGame=false;
         }
         
         if (prova<segreto) return -1;
         return 1;
    }
    
    /**
     * Controlla se il tentativo fornito rispetta le regole formali
     * del gioco, cioè è compreso fra [1,NMAX]
     * @param prova=tentativo dell'utente
     * @return true=valore valido,false=valore non valido
     */
    public boolean valoreValido(int prova)
    {
        if (prova<1||prova>NMAX) return false;
        return true;
    }
    
    public boolean isInGame() {
        return inGame;
    }

    public int getTentativi() {
        return tentativi;
    }

    public int getNMAX() {
        return NMAX;
    }

    public int getTMAX() {
        return TMAX;
    }
    
    public int getSegreto()
    {
        return segreto;
    }
    
    
    
    
}
