
package org.ucm.poker3.model.cartas;

public class MazoCartas {
    private Boolean[][] mazo;
    private int cont;
    public MazoCartas(){
        
        reset();
    }

    public Boolean[][] getMazo() {
        return mazo;
    }
    public int getCont(){
        return cont;
    }
    
    public void meteCarta(int i, int j){
        mazo[i][j] = true;
        cont++;
    }
    public void quitaCarta(int i, int j){
        mazo[i][j] = false;
        cont--;
    }
    public Boolean isCarta(int i, int j){
        return mazo[i][j];
    }
    public void reset(){
        cont = 0;
        mazo = new Boolean[13][4];
        for(int i= 0; i< 13;i++){
            for(int j=0; j<4;j++){
                mazo[i][j] = false;
            }
        }
    }
}
