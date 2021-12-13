/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.ucm.poker02p.model;

import java.util.ArrayList;
import java.util.List;
/*
RANKING Sklansky-Chubukov sacado de:
https://www.pokermatematicas.com/rankings/sklansky_chubukov.txt
*/

public class Ranking {
    ArrayList<Mano> rankList;
    public Ranking() {
        rankList = new ArrayList();
        generaRankList();
    }
    public List<Mano> getRankListActual(double porcentaje){
        Double posD = (porcentaje/100) * 169;
        int pos = posD.intValue();
        if(pos >= rankList.size())
            pos = rankList.size() - 1;
        List<Mano> aux = new ArrayList();
        for(int i = 0; i <= pos; i++){
            aux.add(rankList.get(i));
        }
        return aux;
    }
    
    
    private void generaRankList(){//en los o se pone el pequeño en la izq
        rankList.add(new Mano(14, 14, 'p'));//AA
        rankList.add(new Mano(13, 13, 'p'));//KK
        rankList.add(new Mano(14, 13, 's'));//AKs
        rankList.add(new Mano(12, 12, 'p'));//QQ
        rankList.add(new Mano(13, 14, 'o'));//AKo
        rankList.add(new Mano(11, 11, 'p'));//JJ
        rankList.add(new Mano(14, 12, 's'));//AQs
        rankList.add(new Mano(10, 10, 'p'));//TT
        rankList.add(new Mano(12, 14, 'o'));//AQo
        rankList.add(new Mano(9, 9, 'p'));//99
        rankList.add(new Mano(14, 11, 's'));//AJs
        rankList.add(new Mano(8, 8, 'p'));//88
        rankList.add(new Mano(14, 10, 's'));//ATs
        rankList.add(new Mano(11, 14, 'o'));//AJo
        rankList.add(new Mano(7, 7, 'p'));//77
        rankList.add(new Mano(6, 6, 'p'));//66
        rankList.add(new Mano(10, 14, 'o'));//ATo
        rankList.add(new Mano(14, 9, 's'));//A9s
        rankList.add(new Mano(5, 5, 'p'));//55
        rankList.add(new Mano(14, 8, 's'));//A8s
        rankList.add(new Mano(13, 12, 's'));//KQs
        rankList.add(new Mano(4, 4, 'p'));//44
        rankList.add(new Mano(9, 14, 'o'));//A9o
        rankList.add(new Mano(14, 7, 's'));//A7s
        rankList.add(new Mano(13, 11, 's'));//KJs
        rankList.add(new Mano(14, 5, 's'));//A5s
        rankList.add(new Mano(8, 14, 'o'));//A8o
        rankList.add(new Mano(14, 6, 's'));//A6s
        rankList.add(new Mano(14, 4, 's'));//A4s
        rankList.add(new Mano(3, 3, 'p'));//33
        rankList.add(new Mano(13, 10, 's'));//KTs
        rankList.add(new Mano(7, 14, 'o'));//A7o
        rankList.add(new Mano(14, 3, 's'));//A3s
        rankList.add(new Mano(12, 13, 'o'));//KQo
        rankList.add(new Mano(14, 2, 's'));//A2s
        rankList.add(new Mano(5, 14, 'o'));//A5o
        rankList.add(new Mano(6, 14, 'o'));//A6o
        rankList.add(new Mano(4, 14, 'o'));//A4o
        rankList.add(new Mano(11, 13, 'o'));//KJo
        rankList.add(new Mano(12, 11, 's'));//QJs
        rankList.add(new Mano(3, 14, 'o'));//A3o
        rankList.add(new Mano(2, 2, 'p'));//22
        rankList.add(new Mano(13, 9, 's'));//K9s
        rankList.add(new Mano(2, 14, 'o'));//A2o
        rankList.add(new Mano(10, 13, 'o'));//KTo
        rankList.add(new Mano(12, 10, 's'));//QTs
        rankList.add(new Mano(13, 8, 's'));//K8s
        rankList.add(new Mano(13, 7, 's'));//K7s
        rankList.add(new Mano(11, 10, 's'));//JTs
        rankList.add(new Mano(9, 13, 'o'));//K9o
        rankList.add(new Mano(13, 6, 's'));//K6s
        rankList.add(new Mano(11, 12, 'o'));//QJo
        rankList.add(new Mano(12, 9, 's'));//Q9s
        rankList.add(new Mano(13, 5, 's'));//K5s
        rankList.add(new Mano(8, 13, 'o'));//K8o
        rankList.add(new Mano(13, 4, 's'));//K4s
        rankList.add(new Mano(10, 12, 'o'));//QTo
        rankList.add(new Mano(7, 13, 'o'));//K7o
        rankList.add(new Mano(13, 3, 's'));//K3s
        rankList.add(new Mano(13, 2, 's'));//K2s
        rankList.add(new Mano(12, 8, 's'));//Q8s
        rankList.add(new Mano(6, 13, 'o'));//K6o
        rankList.add(new Mano(11, 9, 's'));//J9s
        rankList.add(new Mano(5, 13, 'o'));//K5o
        rankList.add(new Mano(9, 12, 'o'));//Q9o
        rankList.add(new Mano(10, 11, 'o'));//JTo
        rankList.add(new Mano(4, 13, 'o'));//K4o
        rankList.add(new Mano(12, 7, 's'));//Q7s
        rankList.add(new Mano(10, 9, 's'));//T9s
        rankList.add(new Mano(12, 6, 's'));//Q6s
        rankList.add(new Mano(3, 13, 'o'));//K3o
        rankList.add(new Mano(11, 8, 's'));//J8s
        rankList.add(new Mano(12, 5, 's'));//Q5s
        rankList.add(new Mano(2, 13, 'o'));//K2o
        rankList.add(new Mano(8, 12, 'o'));//Q8o
        
        rankList.add(new Mano(12, 4, 's'));//Q4s
        rankList.add(new Mano(9, 11, 'o'));//J9
        rankList.add(new Mano(12, 3, 's'));//Q3s
        rankList.add(new Mano(10, 8, 's'));//T8s
        rankList.add(new Mano(11, 7, 's'));//J7s
        rankList.add(new Mano(7, 12, 'o'));//Q7
        rankList.add(new Mano(12, 2, 's'));//Q2s
        rankList.add(new Mano(6, 12, 'o'));//Q6
        rankList.add(new Mano(9, 8, 's'));//98s
        rankList.add(new Mano(5, 12, 'o'));//Q5
        rankList.add(new Mano(8, 11, 'o'));//J8
        rankList.add(new Mano(9, 10, 'o'));//T9
        rankList.add(new Mano(11, 6, 's'));//J6s
        rankList.add(new Mano(10, 7, 's'));//T7s
        rankList.add(new Mano(11, 5, 's'));//J5s
        rankList.add(new Mano(4, 12, 'o'));//Q4
        rankList.add(new Mano(11, 4, 's'));//J4s
        rankList.add(new Mano(7, 11, 'o'));//J7
        rankList.add(new Mano(3, 12, 'o'));//Q3
        rankList.add(new Mano(9, 7, 's'));//97s
        rankList.add(new Mano(8, 10, 'o'));//T8
        rankList.add(new Mano(11, 3, 's'));//J3s
        rankList.add(new Mano(10, 6, 's'));//T6s
        rankList.add(new Mano(2, 12, 'o'));//Q2
        rankList.add(new Mano(11, 2, 's'));//J2s
        rankList.add(new Mano(8, 7, 's'));//87s
        rankList.add(new Mano(6, 11, 'o'));//J6
        rankList.add(new Mano(8, 9, 'o'));//98
        rankList.add(new Mano(7, 10, 'o'));//T7
        rankList.add(new Mano(9, 6, 's'));//96s
        rankList.add(new Mano(5, 11, 'o'));//J5
        rankList.add(new Mano(10, 5, 's'));//T5s
        rankList.add(new Mano(10, 4, 's'));//T4s
        rankList.add(new Mano(8, 6, 's'));//86s
        rankList.add(new Mano(4, 11, 'o'));//J4
        rankList.add(new Mano(6, 10, 'o'));//T6
        rankList.add(new Mano(7, 9, 'o'));//97
        rankList.add(new Mano(10, 3, 's'));//T3s
        rankList.add(new Mano(7, 6, 's'));//76s
        rankList.add(new Mano(9, 5, 's'));//95s
        rankList.add(new Mano(3, 11, 's'));//J3
        rankList.add(new Mano(10, 2, 's'));//T2s
        rankList.add(new Mano(7, 8, 'o'));//87
        rankList.add(new Mano(8, 5, 's'));//85s
        rankList.add(new Mano(6, 9, 'o'));//96
        rankList.add(new Mano(5, 10, 'o'));//T5
        rankList.add(new Mano(2, 11, 'o'));//J2
        rankList.add(new Mano(7, 5, 's'));//75s
        rankList.add(new Mano(9, 4, 's'));//94s
        rankList.add(new Mano(4, 10, 'o'));//T4
        rankList.add(new Mano(6, 5, 's'));//65s
        rankList.add(new Mano(6, 8, 'o'));//86
        rankList.add(new Mano(9, 3, 's'));//93s
        rankList.add(new Mano(8, 4, 's'));//84s
        rankList.add(new Mano(5, 9, 'o'));//95
        rankList.add(new Mano(3, 10, 'o'));//T3
        rankList.add(new Mano(6, 7, 'o'));//76
        rankList.add(new Mano(9, 2, 's'));//92s
        rankList.add(new Mano(7, 4, 's'));//74s
        rankList.add(new Mano(5, 4, 's'));//54s
        rankList.add(new Mano(2, 10, 'o'));//T2
        rankList.add(new Mano(5, 8, 'o'));//85
        rankList.add(new Mano(6, 4, 's'));//64s
        rankList.add(new Mano(8, 3, 's'));//83s
        rankList.add(new Mano(4, 9, 'o'));//94
        rankList.add(new Mano(5, 7, 'o'));//75
        rankList.add(new Mano(8, 2, 's'));//82s
        rankList.add(new Mano(7, 3, 's'));//73s
        rankList.add(new Mano(3, 9, 'o'));//93
        rankList.add(new Mano(5, 6, 'o'));//65
        rankList.add(new Mano(5, 3, 's'));//53s
        rankList.add(new Mano(6, 3, 's'));//63s
        rankList.add(new Mano(4, 8, 'o'));//84
        rankList.add(new Mano(2, 9, 'o'));//92
        rankList.add(new Mano(4, 3, 's'));//43s
        rankList.add(new Mano(4, 7, 'o'));//74
        rankList.add(new Mano(7, 2, 's'));//72s
        rankList.add(new Mano(4, 5, 'o'));//54
        rankList.add(new Mano(4, 6, 'o'));//64
        rankList.add(new Mano(5, 2, 's'));//52s
        rankList.add(new Mano(6, 2, 's'));//62s
        rankList.add(new Mano(3, 8, 'o'));//83
        rankList.add(new Mano(4, 2, 's'));//42s
        rankList.add(new Mano(2, 8, 'o'));//82
        rankList.add(new Mano(3, 7, 'o'));//73
        rankList.add(new Mano(3, 5, 'o'));//53
        rankList.add(new Mano(3, 6, 'o'));//63
        rankList.add(new Mano(3, 2, 's'));//32s
        rankList.add(new Mano(3, 4, 'o'));//43
        rankList.add(new Mano(2, 7, 'o'));//72
        rankList.add(new Mano(2, 5, 'o'));//52
        rankList.add(new Mano(2, 6, 'o'));//62
        rankList.add(new Mano(2, 4, 'o'));//42
        rankList.add(new Mano(2, 3, 'o'));//32
    }

}
