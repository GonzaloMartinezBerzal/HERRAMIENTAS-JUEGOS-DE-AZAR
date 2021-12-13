
package org.ucm.poker3.model.cartas;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Board {
    private List<Carta> listaCartas;
    public Board(){
        listaCartas = new ArrayList<Carta>();
        
    }
    public void addCarta(Carta carta){
        listaCartas.add(carta);
    }
    public void addCarta(int i,Carta carta){
        if(i == listaCartas.size())
            listaCartas.add(carta);
        else listaCartas.add(i,carta);
    }
    public void reset(){
        listaCartas = new ArrayList<Carta>();
    }
    public int getNumCartas(){
        return listaCartas.size();
    }
    public List<Carta> getListaCartas() {
        return listaCartas;
    }
    public int size(){
        return listaCartas.size();
    }
    public void deleteCarta(int i){
        
        listaCartas.remove(i);
    }

    public void ordenar() {
        Collections.sort(listaCartas);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 11 * hash + Objects.hashCode(this.listaCartas);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Board other = (Board) obj;
        if (!Objects.equals(this.listaCartas, other.listaCartas)) {
            return false;
        }
        return true;
    }
    
    
}
