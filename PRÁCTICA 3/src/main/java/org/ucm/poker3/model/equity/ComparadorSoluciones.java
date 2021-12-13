package org.ucm.poker3.model.equity;

import org.ucm.poker3.utils.Util;

public class ComparadorSoluciones {

    public ComparadorSoluciones() {
    }

    public Solucion compara(Solucion sol1, Solucion sol2) {
        if (sol2 == null) {
            return sol1;
        }
        if (sol1.getJugada() > sol2.getJugada()) {
            return sol2;
        } else if (sol1.getJugada() == sol2.getJugada()) {
            switch (sol1.getJugada()) {
                case 1: {
                    if (escaleraReal(sol1, sol2)) {
                        return sol1;
                    } else {
                        return sol2;
                    }
                }

                case 2: {
                    if (escaleraColor(sol1, sol2)) {
                        return sol1;
                    } else {
                        return sol2;
                    }
                }

                case 3: {
                    if (Poker(sol1, sol2)) {
                        return sol1;
                    } else {
                        return sol2;
                    }
                }

                case 4: {
                    if (fullHouse(sol1, sol2)) {
                        return sol1;
                    } else {
                        return sol2;
                    }
                }

                case 5: {
                    if (Color(sol1, sol2)) {
                        return sol1;
                    } else {
                        return sol2;
                    }
                }

                case 6: {
                    if (Escalera(sol1, sol2)) {
                        return sol1;
                    } else {
                        return sol2;
                    }
                }

                case 7: {
                    if (Trio(sol1, sol2)) {
                        return sol1;
                    } else {
                        return sol2;
                    }
                }

                case 8: {
                    if (DoblePareja(sol1, sol2)) {
                        return sol1;
                    } else {
                        return sol2;
                    }
                }

                case 9: {
                    if (Pareja(sol1, sol2)) {
                        return sol1;
                    } else {
                        return sol2;
                    }
                }

                case 10: {
                    if (CartaAlta(sol1, sol2)) {
                        return sol1;
                    } else {
                        return sol2;
                    }
                }

            }
            return sol1;
        } else {
            return sol1;
        }
    }

    /*
        1. Escalera real de color (cartas 10, J, Q, K y A del mismo palo)
        2. Escalera de color
        3. Poker
        4. Full house
        5. Color
        6. Escalera
        7. Trio
        8. Dobles parejas
        9. Parejas
        10. Carta alta
     */
    private boolean escaleraReal(Solucion sol1, Solucion sol2) {
        return true;
    }

    private boolean escaleraColor(Solucion sol1, Solucion sol2) {
        if (sol1.getMano().charAt(0) > sol2.getMano().charAt(0)) {
            return true;
        } else {
            return false;
        }
    }

    private boolean Poker(Solucion sol1, Solucion sol2) {
        if (sol1.getTipo() > sol2.getTipo()) {
            return true;
        } else if (sol1.getTipo() == sol2.getTipo()) {
            return CartaAlta(sol1, sol2);
        } else {
            return false;
        }
    }

    private boolean fullHouse(Solucion sol1, Solucion sol2) {
        if (sol1.getTipo2() == sol2.getTipo2()) {
            return sol1.getTipo() > sol2.getTipo();
        } else {
            return sol1.getTipo2() > sol2.getTipo2();
        }

    }

    private boolean Color(Solucion sol1, Solucion sol2) {
        return CartaAlta(sol1, sol2);
    }

    private boolean Escalera(Solucion sol1, Solucion sol2) {
        return CartaAlta(sol1, sol2);
    }

    private boolean Trio(Solucion sol1, Solucion sol2) {
        if (sol1.getTipo() == sol2.getTipo()) {
            return CartaAlta(sol1, sol2);
        } else {
            return (sol1.getTipo() > sol2.getTipo());
        }
    }

    private boolean DoblePareja(Solucion sol1, Solucion sol2) {
        if (sol1.getTipo() == sol2.getTipo()) {
            if (sol1.getTipo2() == sol2.getTipo2()) {
                return CartaAlta(sol1, sol2);
            } else {
                return sol1.getTipo2() > sol2.getTipo2();
            }
        } else {
            return sol1.getTipo() > sol2.getTipo();
        }
    }

    private boolean Pareja(Solucion sol1, Solucion sol2) {
        if (sol1.getTipo() == sol2.getTipo()) {
            return CartaAlta(sol1, sol2);
        } else {
            return (sol1.getTipo() > sol2.getTipo());
        }
    }

    private boolean CartaAlta(Solucion sol1, Solucion sol2) {
        if (sol1.getMano().length() > sol2.getMano().length()) {
            return true;
        } else if (sol1.getMano().length() == sol2.getMano().length()) {
            for (int i = sol1.getMano().length() - 1; i >= 0; i--) {
                if (sol1.getMano().charAt(i) != 'c'
                        && sol1.getMano().charAt(i) != 'd'
                        && sol1.getMano().charAt(i) != 'h'
                        && sol1.getMano().charAt(i) != 's') {
                    if (sol1.getMano().charAt(i) > sol2.getMano().charAt(i)) {
                        return true;
                    }
                    else if(sol1.getMano().charAt(i) < sol2.getMano().charAt(i)){
                        return false;
                    }
                }
            }
            return false;
        } else {
            return false;
        }
    }

    public boolean sonIguales(Solucion sol1, Solucion sol2) {
        if (sol1.getJugada() == sol2.getJugada()) {
            if (sol1.getMano().length() == sol2.getMano().length()) {
                for (int i = sol1.getMano().length() - 1; i >= 0; i--) {
                    if (sol1.getMano().charAt(i) != 'c'
                            && sol1.getMano().charAt(i) != 'd'
                            && sol1.getMano().charAt(i) != 'h'
                            && sol1.getMano().charAt(i) != 's') {
                        if (sol1.getMano().charAt(i) != sol2.getMano().charAt(i)) {
                            return false;
                        }
                    }
                }
                return true;
            }
        }
        return false;
    }
}
