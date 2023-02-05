/**
 * Klasse:  Strat17
 * @author  PGW-TheoHeußer 
 * @version 17.1.2023 / 17.12.21
 */

public class Stratt17 extends Spieler17u4
{
    public Stratt17()    { 
        super("Test_17");
    }

    /** entscheidet, wann nicht mehr weitergewuerfelt wird;
     *  betrachtet dazu auch die Stände aller Spieler, um passend reagieren zu können */
    public void entscheide()  {                             //<-- 2.                
        int[] spStaende = holeAlleSpielstaende();
        int anzF19 = 0;  // Anzahl d. Spieler, die mind. 19 Pkt haben und fertig sind
        int anzM18 = 0;  // Anzahl d. Spieler, die mind. 18 Pkt haben und noch spielen
        int anz=0;
        while(spStaende[anz] > -1) {                        //<-- 1. 
            if (spStaende[anz] >= 119) {anzF19++;}
            else if (spStaende[anz] >= 18) {anzM18++;}
            anz++;
        }
        if (aktStand == 21) { fertig = true;}
        else if ( aktStand > 18 && (anzF19 <=2 || anzM18 <=2) )  { fertig = true;}
        // sonst eben bleibt fertig wie es war, meist auf false !! 
    }
    
}

/* Bemerkungen:
 * 
 * 1. Beachte die Kodierung der Spielstände, wie sie vom Spielleiter gemeldet werden. 
 *    
 *     public int[] getAlleSpielstaende()  des Spielleiters   
 *     gibt die Spielstände aller Spieler zurück in einer Reihung[] mit Elementtyp  int 
 *     und auch ob die Spieler noch aktiv sind oder beendet haben.
 *     max. 25 Einträge/Mitspieler:  Bsp.:  12 14 118 20 0 18 0 117 -21 -21 -21 ...  
 *     PKtZahl von 1..21 = aktiver, noch weiter-würfelnder Spieler mit eben dieser Punktzahl 
 *     PktZahl 101..121  = Spieler, der aufgehört hat; mit Pktzahl 1xx-100,
 *        also 118 steht für:  Spieler ist fertig und hat 18 Punkte erreicht.
 *     PktZahl 0 = Spieler, der über 21 hinauskam und daher verloren hat. 
 *     PktZahl -21 signalisiert, dass es diesen Spieler im aktuellen Spiel nicht gibt. 
 *     
 *     Im Beispiel sind also 8 Spieler beteiligt, davon sind 2 schon fertig, 2 über das 
 *     Ziel hinausgeschossen und haben verloren und vier noch aktiv am Würfeln.
 *     
 * 
 * 2. Halten Sie die Strategie für risikoreich? 
 *    Beschreiben Sie die Strategie in Worten.
 * 
 * 
 */ 