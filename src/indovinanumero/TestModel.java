/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package indovinanumero;

/**
 *
 * @author alegu
 */
public class TestModel {
    
    public static void main(String argv[])
    {
        Model model=new Model();
        int t,esito,min=1,numt=0;
        int max=model.getNMAX();
        model.newGame();
        while(model.isInGame())
        {
           numt++;
           t=(min+max)/2;
           esito=model.tentativo(t);
           System.out.format("Provo fra %d e %d con il numero %d\n",min,max,t);
           if(esito>0) 
           {
               max=t-1;
               System.out.format("Troppo alto\n");
           }
           else if (esito<0) 
                {
                    min=t+1;
                    System.out.format("Troppo basso\n");
                }
           else System.out.format("Bravo!! Hai indovinato il numero in %d tentativi\n",numt);
        }
    }
}
