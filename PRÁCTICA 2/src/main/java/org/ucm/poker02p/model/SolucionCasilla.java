/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.ucm.poker02p.model;

import java.util.ArrayList;

public class SolucionCasilla {
	private Mano mano;
	private Board board;
	private Solucion solucion;

	public SolucionCasilla(Mano mano, Board board) {
		this.mano = mano;
		this.board = board;

		switch (mano.getTipo()) {
		case 'p':
			solucion = parejaSolucion();
			break;
		case 's':
			solucion = suitedSolucion();
			break;
		case 'o':
			solucion = offSuitedSolucion();
			break;
		}

	}

	public Solucion getSolucion() {
		return solucion;
	}
	/*
	 * 0. Escalera real 1. Escalera de color 2. Poker 3. FullHouse 4. Color 5.
	 * Escalera 6. Trio 7. Doble Pareja 8. OverPair (pareja en mano mejor que la
	 * carta mas alta del board) 9. TopPair (pareja con la carta mas alta del board)
	 * 10. pocket pair below top pair(pareja en mano con cartas menores que la mas
	 * alta del board pero que no es débil) 11. MiddlePair (pareja con la segunda
	 * carta mas alta del board) 12. WeakPair (otras parejas) 13. Proyecto color 14.
	 * proyecto escalera open-ended 15. proyecto escalera gutshot
	 * 
	 * 16. Ace high 17. No made hand
	 * 
	 */
	/*
	 * new Solucion(5, 4, mano.toString()); el primer parametro es cual es de lo de
	 * arriba, por ej el 6 es trio
	 * 
	 * el segundo es el num combinaciones, por ejemplo AA sin ninguna A en el board
	 * es 6(en el pdf explica mas)
	 * 
	 * el tercero poned mano.toString()
	 * 
	 * podeis usar mano.toString() si lo quereis como se escribe en vez de con
	 * numeros el board es un ArrayList de tipo Carta.java for(Carta c: board){//asi
	 * se usa el for de ArrayList
	 * 
	 * }
	 * 
	 * en el github lo he dejado en la version 1.3.3 seguid por la .4
	 */

	// No estoy seguro de como tienen q ser los returns preguntar a JUANMA
	private Solucion parejaSolucion() {// aqui ya sabeis si es pareja
		Solucion sol = new Solucion(12, 6, mano.toString());
		int solucionActual = 12;
		// Para las escaleras y el color se necesitan más de tres cartas pq con una
		// pareja no hay opción de conseguirlo con menos

		// Comprobamos posible escalera real
		if (mano.getCarta1() >= 10
				&& (board.getListaOrdenada().get(0).getNum() >= 10 || board.getListaOrdenada().get(1).getNum() >= 10)) {

			if (board.getColC() >= 4 || board.getColS() >= 4 || board.getColH() >= 4 || board.getColD() >= 4) {
				// Escalera Real con 4 cartas en board
				sol = resuelveEscaleras();
				if (sol.getJugada() == 5) { // Por defecto va a devolver que la jugada va a ser 5
					sol = new Solucion(0, 3, mano.toString());
					solucionActual = 0;
				}

			}
		}

		// Escalera de Color
		if (solucionActual > 0) {
			if (board.getColC() >= 4 || board.getColS() >= 4 || board.getColH() >= 4 || board.getColD() >= 4) {
				sol = resuelveEscaleras();
				if (sol.getJugada() == 5) {
					sol = new Solucion(1, 3, mano.toString());
					solucionActual = 1;
				}
			}
		}

		// Poker
		if (solucionActual > 1) {
			if (board.getPareja1() != 0 || board.getPareja2() != 0) {
				if (board.getPareja1() == mano.getCarta1() || board.getPareja2() == mano.getCarta1()) {
					sol = new Solucion(2, 6, mano.toString());
					solucionActual = 2;
				}
			}
		}

		// Full House
		if (solucionActual > 2) {
			if (board.getPareja1() != 0 || board.getTrio() != 0) { // Si en el board no hay un trio o una pareja no
																	// puede haber full
				if (board.getTrio() != 0) {
					sol = new Solucion(3, 6, mano.toString());
					solucionActual = 3;
				}
				if (board.getPareja1() != 0 && board.getRepeticiones().get(mano.getCarta1()) != null) {
					sol = new Solucion(3, 6, mano.toString());
					solucionActual = 3;
				}
			}
		}

		// Color
		if (solucionActual > 3) {
			if (board.getColC() == 4 || board.getColS() == 4 || board.getColH() == 4 || board.getColD() == 4) {
				sol = new Solucion(4, 3, mano.toString());
				solucionActual = 4;
			}
		}

		// Escalera
		if (board.getNumCart() > 3) {
			if (solucionActual > 4) {
				sol = resuelveEscaleras();
				if (sol.getJugada() == 5) {
					solucionActual = 5;
				}
			}
		}

		// Trio
		if (solucionActual > 5) {
			if (board.getRepeticiones().get(mano.getCarta1()) != null) {
				sol = new Solucion(6, 6, mano.toString());
				solucionActual = 6;
			}
		}

		// Doble Pareja
		if (solucionActual > 6) {
			if (board.getPareja1() != 0) { // Basta con que tenga una aunque la otra pueda ser mejor
				sol = new Solucion(7, 6, mano.toString());
				solucionActual = 7;
			}
		}

		// OverPair
		if (solucionActual > 7) {
			if (board.getPareja1() == 0
					&& mano.getCarta1() > board.getListaOrdenada().get(board.getNumCart() - 1).getNum()) {
				sol = new Solucion(8, 6, mano.toString());
				solucionActual = 8;
			}
		}

		// TopPair
		// No puede tener Top Pair pq al ser una pareja no puede hacer una pareja con
		// una carta del board

		// Pocket pair below top pair(pareja en mano con cartas menores que la mas alta
		// del board pero que no es débil)
		if (solucionActual > 9) {
			if (board.getPareja1() == 0
					&& mano.getCarta1() > board.getListaOrdenada().get(board.getNumCart() - 2).getNum()) {
				sol = new Solucion(10, 6, mano.toString());
				solucionActual = 10;
			}
		}

		// MiddlePair
		// No puede tener Middle Pair pq al ser una pareja no puede hacer una pareja con
		// una carta del board
		// 12. WeakPair (otras parejas)

		if (solucionActual > 11) {
			if (board.getPareja1() == 0
					&& mano.getCarta1() < board.getListaOrdenada().get(board.getNumCart() - 2).getNum()) {
				sol = new Solucion(12, 6, mano.toString());
				solucionActual = 12;
			}
		}

		// No hace falta hacer más comprobaciones porque como mínimo tiene pareja
		// siempre
		return sol;
	}

	private Solucion suitedSolucion() { // aqui ya sabeis si es suited
		Solucion sol = new Solucion(14, 6, mano.toString());
		int solucionActual = 17;

		if (mano.getCarta1() >= 10
				&& (board.getListaOrdenada().get(0).getNum() >= 10 || board.getListaOrdenada().get(1).getNum() >= 10)
				|| mano.getCarta1() >= 10 && (board.getListaOrdenada().get(0).getNum() >= 10
						|| board.getListaOrdenada().get(1).getNum() >= 10)) {

			if (board.getColC() >= 3 || board.getColS() >= 3 || board.getColH() >= 3 || board.getColD() >= 3) {
				// Escalera Real con 4 cartas en board
				sol = resuelveEscaleraRealyColorSuited();
				if (sol.getJugada() == 5) { // Por defecto va a devolver que la jugada va a ser 5
					sol = new Solucion(0, 3, mano.toString());
					solucionActual = 0;
				}

			}
		}

		// Escalera de color
		if (solucionActual > 1) {
			if (board.getColC() >= 3 || board.getColS() >= 3 || board.getColH() >= 3 || board.getColD() >= 3) {
				// Escalera Real con 4 cartas en board
				sol = resuelveEscaleraRealyColorSuited();
				if (sol.getJugada() == 5) { // Por defecto va a devolver que la jugada va a ser 5
					sol = new Solucion(1, 3, mano.toString());
					solucionActual = 1;
				}

			}
		}

		// Poker
		if (solucionActual > 2) {
			if (board.getTrio() == mano.getCarta1() || board.getTrio() == mano.getCarta2()) {
				sol = new Solucion(2, 1, mano.toString());
				solucionActual = 2;
			}
		}

		// FullHouse
		if (solucionActual > 3) {
			if (board.getTrio() != 0) {
				if (board.getNRepeticiones().get(mano.getCarta1() - 2) != 0
						|| board.getNRepeticiones().get(mano.getCarta2() - 2) != 0) {
					sol = new Solucion(3, 3, mano.toString());
					solucionActual = 3;
					if (board.getNRepeticiones().get(mano.getCarta1() - 2) != 0
							&& board.getNRepeticiones().get(mano.getCarta2() - 2) != 0) {
						sol = new Solucion(3, 6, mano.toString());
					}
				}
			}
			if (board.getPareja1() != 0) {

				if (mano.getCarta1() == board.getPareja1() && board.getNRepeticiones().get(mano.getCarta2() - 2) == 1) {
					sol = new Solucion(3, 6, mano.toString());
					solucionActual = 3;
				}
				if (mano.getCarta2() == board.getPareja1() && board.getNRepeticiones().get(mano.getCarta1() - 2) == 1) {
					sol = new Solucion(3, 6, mano.toString());
					solucionActual = 3;
				}
				if (board.getPareja2() != 0) {
					if (mano.getCarta1() == board.getPareja1() || mano.getCarta1() == board.getPareja2()) {
						sol = new Solucion(3, 3, mano.toString());
						if (mano.getCarta2() == board.getPareja1() || mano.getCarta2() == board.getPareja2()) {
							sol = new Solucion(3, 3, mano.toString());
						}
						solucionActual = 3;
					}

					if (mano.getCarta2() == board.getPareja1() || mano.getCarta2() == board.getPareja2()) {
						sol = new Solucion(3, 3, mano.toString());
						if (mano.getCarta1() == board.getPareja1() || mano.getCarta1() == board.getPareja2()) {
							sol = new Solucion(3, 3, mano.toString());
						}
						solucionActual = 3;
					}
				}
			}
		}

		// Color
		if (solucionActual > 4) {
			if (board.getColC() == 4 || board.getColS() == 4 || board.getColH() == 4 || board.getColD() == 4) {
				sol = new Solucion(4, 1, mano.toString());
				solucionActual = 4;
			}
			if (board.getColC() == 3 || board.getColS() == 3 || board.getColH() == 3 || board.getColD() == 3) {
				sol = new Solucion(4, 1, mano.toString());
				solucionActual = 4;
			}
		}

		// Escalera
		if (board.getNumCart() >= 3) {
			if (solucionActual > 5) {
				sol = resuelveEscaleras();
				if (sol.getJugada() == 5) {
					solucionActual = 5;
				}
			}
		}

		// Trio
		if (solucionActual > 6) {
			if (board.getPareja1() == mano.getCarta1() || board.getPareja1() == mano.getCarta2()
					|| board.getPareja2() == mano.getCarta1() || board.getPareja2() == mano.getCarta2()) {
				sol = new Solucion(6, 3, mano.toString());
				solucionActual = 6;
			}
		}

		// Doble Pareja
		if (solucionActual > 7) {
			if (board.getPareja1() != 0) {
				if (board.getNRepeticiones().get(mano.getCarta1() - 2) == 1
						|| board.getNRepeticiones().get(mano.getCarta2() - 2) == 1) {
					sol = new Solucion(7, 6, mano.toString());
					solucionActual = 7;
				}
			}
			if (board.getNRepeticiones().get(mano.getCarta1() - 2) == 1
					&& board.getNRepeticiones().get(mano.getCarta2() - 2) == 1) {
				sol = new Solucion(7, 6, mano.toString());
				solucionActual = 7;
			}
		}

		// OverPair (pareja en mano mejor que la carta mas alta del board)
		// No puede haber over pair pq no tienes una pareja en mano

		// TopPair (pareja con la carta mas alta del board)
		if (solucionActual > 9) {
			if (board.getNumCart() == 3) {
				if (mano.getCarta1() == board.getListaOrdenada().get(2).getNum()
						|| mano.getCarta2() == board.getListaOrdenada().get(2).getNum()) {
					sol = new Solucion(9, 6, mano.toString());
					solucionActual = 9;
				}
				if (board.getNumCart() == 4) {
					if (mano.getCarta1() == board.getListaOrdenada().get(3).getNum()
							|| mano.getCarta2() == board.getListaOrdenada().get(3).getNum()) {
						sol = new Solucion(9, 6, mano.toString());
						solucionActual = 9;
					}
					if (board.getNumCart() == 5) {
						if (mano.getCarta1() == board.getListaOrdenada().get(4).getNum()
								|| mano.getCarta2() == board.getListaOrdenada().get(4).getNum()) {
							sol = new Solucion(9, 6, mano.toString());
							solucionActual = 9;
						}
					}
				}
			}
		}

		// pocket pair below top pair(pareja en mano con cartas menores que la mas alta
		// del board pero que no es débil)
		// No puede haber pocket pair pq no tienes pareja en mano

		// MiddlePair (pareja con la segunda carta mas alta del board)
		if (solucionActual > 11) {
			if (board.getNumCart() == 3) {
				if (mano.getCarta1() == board.getListaOrdenada().get(1).getNum()
						|| mano.getCarta2() == board.getListaOrdenada().get(1).getNum()) {
					sol = new Solucion(11, 6, mano.toString());
					solucionActual = 11;
				}
				if (board.getNumCart() == 4) {
					if (mano.getCarta1() == board.getListaOrdenada().get(2).getNum()
							|| mano.getCarta2() == board.getListaOrdenada().get(2).getNum()) {
						sol = new Solucion(11, 6, mano.toString());
						solucionActual = 11;
					}
					if (board.getNumCart() == 5) {
						if (mano.getCarta1() == board.getListaOrdenada().get(3).getNum()
								|| mano.getCarta2() == board.getListaOrdenada().get(3).getNum()) {
							sol = new Solucion(11, 6, mano.toString());
							solucionActual = 11;
						}
					}
				}
			}
		}

		// WeakPair (otras parejas)
		if (solucionActual > 12) {
			if (board.getNumCart() == 3) {
				if (mano.getCarta1() == board.getListaOrdenada().get(0).getNum()
						|| mano.getCarta2() == board.getListaOrdenada().get(0).getNum()) {
					sol = new Solucion(12, 6, mano.toString());
					solucionActual = 12;
				}
				if (board.getNumCart() == 4) {
					if (mano.getCarta1() == board.getListaOrdenada().get(1).getNum()
							|| mano.getCarta2() == board.getListaOrdenada().get(1).getNum()) {
						sol = new Solucion(12, 6, mano.toString());
						solucionActual = 12;
					}
					if (board.getNumCart() == 5) {
						if (mano.getCarta1() == board.getListaOrdenada().get(2).getNum()
								|| mano.getCarta2() == board.getListaOrdenada().get(2).getNum()) {
							sol = new Solucion(12, 6, mano.toString());
							solucionActual = 12;
						}
					}
				}
			}

		}

		// Proyecto color

		if (solucionActual > 13) {
			if (board.getColC() == 3 || board.getColS() == 3 || board.getColH() == 3 || board.getColD() == 3) {
				sol = new Solucion(13, 1, mano.toString());
				solucionActual = 13;
			}
			if (board.getColC() == 2 || board.getColS() == 2 || board.getColH() == 2 || board.getColD() == 2) {
				sol = new Solucion(13, 1, mano.toString());
				solucionActual = 13;
			}
		}

		// proyecto escalera open-ended
		if (solucionActual > 14) {
			boolean[] vb = new boolean[15];
			Integer[] vi = new Integer[15];
			for (int i = 0; i < board.getNumCart(); i++) {
				if (vb[board.getListaOrdenada().get(i).getNum()]) {
					vi[board.getListaOrdenada().get(i).getNum()]++;
				} else {
					vb[board.getListaOrdenada().get(i).getNum()] = true;
					vi[board.getListaOrdenada().get(i).getNum()] = 1;

				}
			}

			if (vb[mano.getCarta1()]) {
				vi[mano.getCarta1()]++;
			} else {
				vb[mano.getCarta1()] = true;
				vi[mano.getCarta1()] = 1;

			}
			if (vb[mano.getCarta2()]) {
				vi[mano.getCarta2()]++;
			} else {
				vb[mano.getCarta2()] = true;
				vi[mano.getCarta2()] = 1;

			}
			int cont = 0;
			int inicio = 0;
			// Miramos manualmente para la posible escalera usando el as como 1
			if (vb[14] && vb[2]) {
				cont = 1;
				inicio = 1;
			}
			for (int i = 2 + inicio; i < 15; i++) {
				if (vb[i]) {
					cont++;
					if (cont == 4) {
						sol = new Solucion(14, (4 - (vi[mano.getCarta1()] - 1)) * (4 - (vi[mano.getCarta2()] - 1)),
								mano.toString());
						solucionActual = 14;
					}
				} else {
					cont = 0;
				}

			}

		}
		// proyecto escalera gutshot
		if (solucionActual > 15) {
			boolean[] vb = new boolean[15];
			Integer[] vi = new Integer[15];
			for (int i = 0; i < board.getNumCart(); i++) {
				if (vb[board.getListaOrdenada().get(i).getNum()]) {
					vi[board.getListaOrdenada().get(i).getNum()]++;
				}
				vb[board.getListaOrdenada().get(i).getNum()] = true;
				vi[board.getListaOrdenada().get(i).getNum()] = 1;

			}

			if (vb[mano.getCarta1()]) {
				vi[mano.getCarta1()]++;
			} else {
				vb[mano.getCarta1()] = true;
				vi[mano.getCarta1()] = 1;
			}
			if (vb[mano.getCarta2()]) {
				vi[mano.getCarta2()]++;
			} else {
				vb[mano.getCarta2()] = true;
				vi[mano.getCarta2()] = 1;
			}
			int cont = 0;
			int inicio = 0;
			boolean hueco = false;
			// Miramos manualmente para la posible escalera usando el as como 1
			if (vb[14]) {
				cont = 2;
				inicio = 1;
				if (vb[2]) {
					cont++;
					hueco = true;
				}
			}
			for (int i = 2 + inicio; i < 15; i++) {
				if (vb[i]) {
					if (vb[i - 1]) {
						cont++;
					} else
						cont = 1;
					if (cont == 5) {
						sol = new Solucion(15, (4 - vi[mano.getCarta1()] - 1) * (4 - vi[mano.getCarta2()] - 1),
								mano.toString());
						solucionActual = 15;
					}
				} else if (!hueco) {
					cont = 0;
				} else {
					cont++;
					hueco = true;
				}

			}

		}

		// Ace high
		if (solucionActual > 16) {
			if (mano.getCarta1() > board.getListaOrdenada().get(board.getNumCart() - 1).getNum()) {
				sol = new Solucion(16, 6, mano.toString());
				solucionActual = 16;
			}
		}

		return sol;
	}

	private Solucion offSuitedSolucion() {/// y aqui offsuited
		Solucion sol = new Solucion(14, 6, mano.toString());
		int solucionActual = 14;

		// Escalera real

		if (mano.getCarta1() >= 10
				&& (board.getListaOrdenada().get(0).getNum() >= 10 || board.getListaOrdenada().get(1).getNum() >= 10)
				|| mano.getCarta1() >= 10 && (board.getListaOrdenada().get(0).getNum() >= 10
						|| board.getListaOrdenada().get(1).getNum() >= 10)) {

			if (board.getColC() >= 4 || board.getColS() >= 4 || board.getColH() >= 4 || board.getColD() >= 4) {
				// Escalera Real con 4 cartas en board
				sol = resuelveEscaleras();
				if (sol.getJugada() == 5) { // Por defecto va a devolver que la jugada va a ser 5
					sol = new Solucion(0, 3, mano.toString());
					solucionActual = 0;
				}

			}
		}

		// Escalera de color
		if (solucionActual > 1) {
			if (board.getColC() >= 4 || board.getColS() >= 4 || board.getColH() >= 4 || board.getColD() >= 4) {
				// Escalera Real con 4 cartas en board
				sol = resuelveEscaleras();
				if (sol.getJugada() == 5) { // Por defecto va a devolver que la jugada va a ser 5
					sol = new Solucion(0, 3, mano.toString());
					solucionActual = 0;
				}

			}
		}

		// Poker
		if (solucionActual > 2) {
			if (board.getTrio() == mano.getCarta1() || board.getTrio() == mano.getCarta2()) {
				sol = new Solucion(2, 1, mano.toString());
				solucionActual = 2;
			}
		}

		// FullHouse
		if (solucionActual > 3) {
			if (board.getTrio() != 0) {
				if (board.getNRepeticiones().get(mano.getCarta1() - 2) != 0
						|| board.getNRepeticiones().get(mano.getCarta2() - 2) != 0) {
					sol = new Solucion(3, 3, mano.toString());
					solucionActual = 3;
					if (board.getNRepeticiones().get(mano.getCarta1() - 2) != 0
							&& board.getNRepeticiones().get(mano.getCarta2() - 2) != 0) {
						sol = new Solucion(3, 6, mano.toString());
					}
				}
			}
			if (board.getPareja1() != 0) {

				if (mano.getCarta1() == board.getPareja1() && board.getNRepeticiones().get(mano.getCarta2() - 2) == 1) {
					sol = new Solucion(3, 6, mano.toString());
					solucionActual = 3;
				}
				if (mano.getCarta2() == board.getPareja1() && board.getNRepeticiones().get(mano.getCarta1() - 2) == 1) {
					sol = new Solucion(3, 6, mano.toString());
					solucionActual = 3;
				}
				if (board.getPareja2() != 0) {
					if (mano.getCarta1() == board.getPareja1() || mano.getCarta1() == board.getPareja2()) {
						sol = new Solucion(3, 3, mano.toString());
						if (mano.getCarta2() == board.getPareja1() || mano.getCarta2() == board.getPareja2()) {
							sol = new Solucion(3, 3, mano.toString());
						}
						solucionActual = 3;
					}

					if (mano.getCarta2() == board.getPareja1() || mano.getCarta2() == board.getPareja2()) {
						sol = new Solucion(3, 3, mano.toString());
						if (mano.getCarta1() == board.getPareja1() || mano.getCarta1() == board.getPareja2()) {
							sol = new Solucion(3, 3, mano.toString());
						}
						solucionActual = 3;
					}
				}
			}
		}

		// Color
		if (solucionActual > 4) {
			if (board.getColC() >= 4 || board.getColS() >= 4 || board.getColH() >= 4 || board.getColD() >= 4) {
				sol = new Solucion(4, 4, mano.toString());
				solucionActual = 4;
			}
		}

		// Escalera
		if (board.getNumCart() >= 3) {
			if (solucionActual > 5) {
				sol = resuelveEscaleras();
				if (sol.getJugada() == 5) {
					solucionActual = 5;
				}
			}
		}

		// Trio
		if (solucionActual > 6) {
			if (board.getPareja1() == mano.getCarta1() || board.getPareja1() == mano.getCarta2()
					|| board.getPareja2() == mano.getCarta1() || board.getPareja2() == mano.getCarta2()) {
				sol = new Solucion(6, 3, mano.toString());
				solucionActual = 6;
			}
		}

		// Doble Pareja
		if (solucionActual > 7) {
			if (board.getPareja1() != 0) {
				if (board.getNRepeticiones().get(mano.getCarta1() - 2) == 1
						|| board.getNRepeticiones().get(mano.getCarta2() - 2) == 1) {
					sol = new Solucion(7, 6, mano.toString());
					solucionActual = 7;
				}
			}
			if (board.getNRepeticiones().get(mano.getCarta1() - 2) == 1
					&& board.getNRepeticiones().get(mano.getCarta2() - 2) == 1) {
				sol = new Solucion(7, 6, mano.toString());
				solucionActual = 7;
			}
		}

		// OverPair (pareja en mano mejor que la carta mas alta del board)
		// No puede haber over pair pq no tienes una pareja en mano

		// TopPair (pareja con la carta mas alta del board)
		if (solucionActual > 9) {
			if (board.getNumCart() == 3) {
				if (mano.getCarta1() == board.getListaOrdenada().get(2).getNum()
						|| mano.getCarta2() == board.getListaOrdenada().get(2).getNum()) {
					sol = new Solucion(9, 6, mano.toString());
					solucionActual = 9;
				}
				if (board.getNumCart() == 4) {
					if (mano.getCarta1() == board.getListaOrdenada().get(3).getNum()
							|| mano.getCarta2() == board.getListaOrdenada().get(3).getNum()) {
						sol = new Solucion(9, 6, mano.toString());
						solucionActual = 9;
					}
					if (board.getNumCart() == 5) {
						if (mano.getCarta1() == board.getListaOrdenada().get(4).getNum()
								|| mano.getCarta2() == board.getListaOrdenada().get(4).getNum()) {
							sol = new Solucion(9, 6, mano.toString());
							solucionActual = 9;
						}
					}
				}
			}
		}

		// pocket pair below top pair(pareja en mano con cartas menores que la mas alta
		// del board pero que no es débil)
		// No puede haber pocket pair pq no tienes pareja en mano

		// MiddlePair (pareja con la segunda carta mas alta del board)
		if (solucionActual > 11) {
			if (board.getNumCart() == 3) {
				if (mano.getCarta1() == board.getListaOrdenada().get(1).getNum()
						|| mano.getCarta2() == board.getListaOrdenada().get(1).getNum()) {
					sol = new Solucion(11, 6, mano.toString());
					solucionActual = 11;
				}
				if (board.getNumCart() == 4) {
					if (mano.getCarta1() == board.getListaOrdenada().get(2).getNum()
							|| mano.getCarta2() == board.getListaOrdenada().get(2).getNum()) {
						sol = new Solucion(11, 6, mano.toString());
						solucionActual = 11;
					}
					if (board.getNumCart() == 5) {
						if (mano.getCarta1() == board.getListaOrdenada().get(3).getNum()
								|| mano.getCarta2() == board.getListaOrdenada().get(3).getNum()) {
							sol = new Solucion(11, 6, mano.toString());
							solucionActual = 11;
						}
					}
				}
			}
		}

		// WeakPair (otras parejas)
		if (solucionActual > 12) {
			if (board.getNumCart() == 3) {
				if (mano.getCarta1() == board.getListaOrdenada().get(0).getNum()
						|| mano.getCarta2() == board.getListaOrdenada().get(0).getNum()) {
					sol = new Solucion(12, 6, mano.toString());
					solucionActual = 12;
				}
				if (board.getNumCart() == 4) {
					if (mano.getCarta1() == board.getListaOrdenada().get(1).getNum()
							|| mano.getCarta2() == board.getListaOrdenada().get(1).getNum()) {
						sol = new Solucion(12, 6, mano.toString());
						solucionActual = 12;
					}
					if (board.getNumCart() == 5) {
						if (mano.getCarta1() == board.getListaOrdenada().get(2).getNum()
								|| mano.getCarta2() == board.getListaOrdenada().get(2).getNum()) {
							sol = new Solucion(12, 6, mano.toString());
							solucionActual = 12;
						}
					}
				}
			}

		}

		// Proyecto color
		if (solucionActual > 13) {
			if (board.getColC() >= 3 || board.getColS() >= 3 || board.getColH() >= 3 || board.getColD() >= 3) {
				sol = new Solucion(13, 4, mano.toString());
				solucionActual = 13;
			}
		}

		// proyecto escalera open-ended
		if (solucionActual > 14) {
			boolean[] vb = new boolean[15];
			Integer[] vi = new Integer[15];
			for (int i = 0; i < board.getNumCart(); i++) {
				if (vb[board.getListaOrdenada().get(i).getNum()]) {
					vi[board.getListaOrdenada().get(i).getNum()]++;
				} else {
					vb[board.getListaOrdenada().get(i).getNum()] = true;
					vi[board.getListaOrdenada().get(i).getNum()] = 1;

				}
			}
			if (vb[mano.getCarta1()]) {
				vi[mano.getCarta1()]++;
			} else {
				vb[mano.getCarta1()] = true;
				vi[mano.getCarta1()] = 1;

			}
			if (vb[mano.getCarta2()]) {
				vi[mano.getCarta2()]++;
			} else {
				vb[mano.getCarta2()] = true;
				vi[mano.getCarta2()] = 1;

			}
			int cont = 0;
			int inicio = 0;
			// Miramos manualmente para la posible escalera usando el as como 1
			if (vb[14] && vb[2]) {
				cont = 1;
				inicio = 1;
			}
			for (int i = 2 + inicio; i < 15; i++) {
				if (vb[i]) {
					if (vb[i - 1]) {
						cont++;
					} else
						cont = 1;
					if (cont == 4) {
						sol = new Solucion(14, (4 - (vi[mano.getCarta1()] - 1)) * (4 - (vi[mano.getCarta2()] - 1)),
								mano.toString());
						solucionActual = 14;
					}
				} else {
					cont = 0;
				}

			}

		}

		// proyecto escalera gutshot
		if (solucionActual > 15) {
			boolean[] vb = new boolean[15];
			Integer[] vi = new Integer[15];
			for (int i = 0; i < board.getNumCart(); i++) {
				if (vb[board.getListaOrdenada().get(i).getNum()]) {
					vi[board.getListaOrdenada().get(i).getNum()]++;
				}
				vb[board.getListaOrdenada().get(i).getNum()] = true;
				vi[board.getListaOrdenada().get(i).getNum()] = 1;

			}

			if (vb[mano.getCarta1()]) {
				vi[mano.getCarta1()]++;
			} else {
				vb[mano.getCarta1()] = true;
				vi[mano.getCarta1()] = 1;
			}
			if (vb[mano.getCarta2()]) {
				vi[mano.getCarta2()]++;
			} else {
				vb[mano.getCarta2()] = true;
				vi[mano.getCarta2()] = 1;
			}
			int cont = 0;
			int inicio = 0;
			boolean hueco = false;
			// Miramos manualmente para la posible escalera usando el as como 1
			if (vb[14]) {
				cont = 2;
				inicio = 1;
				if (vb[2]) {
					cont++;
					hueco = true;
				}
			}
			for (int i = 2 + inicio; i < 15; i++) {
				if (vb[i]) {
					if (vb[i - 1]) {
						cont++;
					} else
						cont = 1;
					if (cont == 5) {
						sol = new Solucion(15, (4 - vi[mano.getCarta1()] - 1) * (4 - vi[mano.getCarta2()] - 1),
								mano.toString());
						solucionActual = 15;
					}
				} else if (!hueco) {
					cont = 0;
				} else {
					cont++;
					hueco = true;
				}

			}
		}
		// Ace high
		if (solucionActual > 16) {
			if (mano.getCarta1() > board.getListaOrdenada().get(board.getNumCart() - 1).getNum()) {
				sol = new Solucion(16, 6, mano.toString());
				solucionActual = 16;
			}
		}

		return sol;
	}

	public Solucion resuelveEscaleraRealyColorSuited() {
		Solucion sol = new Solucion(14, 6, mano.toString());

		// Con la llamada a este método hace una llamada a resolver pareja para resolver
		// la primera carta
		ArrayList<Integer> esta = new ArrayList<>();
		int aux = 0;
		// Rellenamos un array con el numero de repeticiones que hay juntando la mano y
		// el board
		for (int i = 0; i < 13; i++) {
			aux = 0;

			if (mano.getCarta1() - 2 == i) {
				aux++;
			}
			if (mano.getCarta2() - 2 == i) {
				aux++;
			}
			for (int j = 0; j < board.getNumCart(); j++) {
				if (board.getListaOrdenada().get(j).getNum() - 2 == i) {
					aux++;
				}
			}
			esta.add(aux);
		}
		// Comprobamos si hay 5 seguidos
		int numeroDeCartasQueQuedanPorMirar = board.getNumCart() + 2;
		int numCartasManoUsadas = 0;
		int numCartasBoardUsadas = 0;
		for (int i = 0; i < 9; i++) {
			if (numeroDeCartasQueQuedanPorMirar >= 5 && esta.get(i) != 0) {
				if (esta.get(i) != 0 && esta.get(i + 1) != 0 && esta.get(i + 2) != 0 && esta.get(i + 3) != 0
						&& esta.get(i + 4) != 0) {
					if (i == mano.getCarta1() - 2 || i + 1 == mano.getCarta1() - 2 || i + 2 == mano.getCarta1() - 2
							|| i + 3 == mano.getCarta1() - 2 || i + 4 == mano.getCarta1() - 2) {
						numCartasManoUsadas++;
					}
					if (i == mano.getCarta2() - 2 || i + 1 == mano.getCarta2() - 2 || i + 2 == mano.getCarta2() - 2
							|| i + 3 == mano.getCarta2() - 2 || i + 4 == mano.getCarta2() - 2) {
						numCartasManoUsadas++;
					}
					int aux2 = 0;
					for (int x = numCartasBoardUsadas; x < board.getNumCart(); x++) {
						if (board.getListaOrdenada().get(x).getPalo() == board.getListaOrdenada()
								.get(numCartasBoardUsadas).getPalo()) {
							aux2++;
						}
					}
					if (aux2 + numCartasManoUsadas == 5) {
						sol = new Solucion(5, 6, mano.toString());

					}
				}
				if (i == mano.getCarta1() - 2 || i + 1 == mano.getCarta1() - 2 || i + 2 == mano.getCarta1() - 2
						|| i + 3 == mano.getCarta1() - 2 || i + 4 == mano.getCarta1() - 2) {
					numCartasManoUsadas++;
				} else if (i == mano.getCarta2() - 2 || i + 1 == mano.getCarta2() - 2 || i + 2 == mano.getCarta2() - 2
						|| i + 3 == mano.getCarta2() - 2 || i + 4 == mano.getCarta2() - 2) {
					numCartasManoUsadas++;
				} else {
					numCartasBoardUsadas++;
				}
				numeroDeCartasQueQuedanPorMirar -= esta.get(i);
				numCartasManoUsadas = 0;
			}
		}

		return sol;
	}

	public Solucion resuelveEscaleras() {
		Solucion sol = new Solucion(14, 6, mano.toString());

		// Con la llamada a este método hace una llamada a resolver pareja para resolver
		// la primera carta

		// Mira si hay escalera con las dos cartas
		if (sol.getJugada() != 5) {
			ArrayList<Integer> esta = new ArrayList<>();
			int aux = 0;
			// Rellenamos un array con el numero de repeticiones que hay juntando la mano y
			// el board
			for (int i = 0; i < 13; i++) {
				aux = 0;

				if (mano.getCarta1() - 2 == i) {
					aux++;
				}
				if (mano.getCarta2() - 2 == i) {
					aux++;
				}
				for (int j = 0; j < board.getNumCart(); j++) {
					if (board.getListaOrdenada().get(j).getNum() - 2 == i) {
						aux++;
					}
				}
				esta.add(aux);
			}
			// Comprobamos si hay 5 seguidos
			int numeroDeCartasQueQuedanPorMirar = board.getNumCart() + 2;
			for (int i = 0; i < 9; i++) {
				if (numeroDeCartasQueQuedanPorMirar >= 5 && esta.get(i) != 0) {
					if (esta.get(i) != 0 && esta.get(i + 1) != 0 && esta.get(i + 2) != 0 && esta.get(i + 3) != 0
							&& esta.get(i + 4) != 0) {
						sol = new Solucion(5, 6, mano.toString());
					}
					numeroDeCartasQueQuedanPorMirar -= esta.get(i);
				}
			}

		}

		return sol;
	}
}
