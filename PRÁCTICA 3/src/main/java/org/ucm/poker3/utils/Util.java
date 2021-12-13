package org.ucm.poker3.utils;

public class Util {

    public static int parseaNumCarta(char c) {
        int ca;
        switch (c) {
            case 'A':
                ca = 14;
                break;
            case 'T':
                ca = 10;
                break;
            case 'J':
                ca = 11;
                break;
            case 'Q':
                ca = 12;
                break;
            case 'K':
                ca = 13;
                break;
            default:
                ca = Character.getNumericValue(c);
                break;
        }
        return ca;
    }

    public static int paloANum(char palo) {
        switch (palo) {
            case 's':
                return 0;
            case 'c':
                return 1;
            case 'd':
                return 2;
            case 'h':
                return 3;
        }
        return -1;
    }

    public static char numAPalo(int palo) {
        switch (palo) {
            case 0:
                return 's';
            case 1:
                return 'c';
            case 2:
                return 'd';
            case 3:
                return 'h';
        }
        return 'f';
    }

    public static String cartaAString(int c) {
        Integer a = c;
        switch (c) {
            case 14:
                return "ace";
            case 11:
                return "jack";
            case 12:
                return "queen";
            case 13:
                return "king";
            default:
                return a.toString();
        }
    }

    public static int getNumJug(int nJug, int cartasEnBoard, boolean modoNormal) {
        if (modoNormal) {
            switch (cartasEnBoard) {
                case 0: {
                    switch (nJug) {
                        case 2:
                            return 1712304;
                        case 3:
                            return 1370754;
                        case 4:
                            return 1086008;
                        case 5:
                            return 850668;
                        case 6:
                            return 658008;
                    }
                }
                case 3: {
                    switch (nJug) {
                        case 2:
                            return 990;
                        case 3:
                            return 903;
                        case 4:
                            return 820;
                        case 5:
                            return 741;
                        case 6:
                            return 666;
                    }
                }
                case 4: {
                    switch (nJug) {
                        case 2:
                            return 44;
                        case 3:
                            return 42;
                        case 4:
                            return 40;
                        case 5:
                            return 38;
                        case 6:
                            return 36;
                    }
                }

            }
        } else {
            switch (cartasEnBoard) {//Falta calcular las de omaha
                case 0: {
                    switch (nJug) {
                        case 2:
                            return 1086008;
                        case 3:
                            return 658008;
                        case 4:
                            return 376992;
                        case 5:
                            return 201376;
                        case 6:
                            return 98280;
                    }
                }
                case 3: {
                    switch (nJug) {
                        case 2:
                            return 820;
                        case 3:
                            return 666;
                        case 4:
                            return 528;
                        case 5:
                            return 406;
                        case 6:
                            return 300;
                    }
                }
                case 4: {
                    switch (nJug) {
                        case 2:
                            return 40;
                        case 3:
                            return 36;
                        case 4:
                            return 32;
                        case 5:
                            return 28;
                        case 6:
                            return 24;
                    }
                }

            }
        }

        return 0;
    }
}
