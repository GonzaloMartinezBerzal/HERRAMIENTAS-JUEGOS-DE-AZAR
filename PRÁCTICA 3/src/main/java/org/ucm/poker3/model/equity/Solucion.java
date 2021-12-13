
package org.ucm.poker3.model.equity;

import java.util.Objects;

public class Solucion implements Comparable<Solucion>{
     private int jugada;
    private int tipo;
    private int tipo2;
    private String mano;

    public Solucion(int jugada, String mano) {
        this.jugada = jugada;
        this.tipo = -1;
        this.mano = mano;
        tipo2 = -1;
        
    }
    public Solucion(int jugada, int tipo, String mano) {
        this.jugada = jugada;
        this.tipo = tipo;
        this.mano = mano;
        tipo2 = -1;
        
    }
    public Solucion(int jugada, int tipo,int tipo2, String mano) {
        this.jugada = jugada;
        this.tipo = tipo;
        this.mano = mano;
        this.tipo2 = tipo2;
        
    }
    public int getJugada() {
        return jugada;
    }

    public int getTipo() {
        return tipo;
    }

    public int getTipo2() {
        return tipo2;
    }
    
    public String getMano() {
        return mano;
    }

    @Override
    public int compareTo(Solucion o) {
        ComparadorSoluciones comp = new ComparadorSoluciones();
        if(this.jugada > o.getJugada()) return 1;
        else if(this.jugada == o.getJugada()){
            if(this.equals(comp.compara(this, o)))
                return -1;
            else
                return 1;
        }
        else
            return -1;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 41 * hash + this.jugada;
        hash = 41 * hash + this.tipo;
        hash = 41 * hash + this.tipo2;
        hash = 41 * hash + Objects.hashCode(this.mano);
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
        final Solucion other = (Solucion) obj;
        if (this.jugada != other.jugada) {
            return false;
        }
        if (this.tipo != other.tipo) {
            return false;
        }
        if (this.tipo2 != other.tipo2) {
            return false;
        }
        if (!Objects.equals(this.mano, other.mano)) {
            return false;
        }
        return true;
    }
    
}
