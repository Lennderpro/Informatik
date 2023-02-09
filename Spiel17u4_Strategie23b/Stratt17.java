/**
 * Klasse:  Stratt17    angepasst an Strategie23b ff
 * @author  PGW-TheoHeußer 
 * @version 2.2.2023 / 23.1.23
 */

public class Stratt17 extends Spieler17u4
{
    public Stratt17()    { 
        super("Test_17");
    }

    /** Konstruktor zur Erzeugung einzelner Stratt17(=Objekte)
     *  für Spiel17u4 mit Festlegung der Anfangswerte */
    public Stratt17(String neuName)    { 
        super(neuName);
    }
    
    /** entscheidet, wann nicht mehr weitergewuerfelt wird;
     *  betrachtet dazu auch die Stände aller Spieler, um passend reagieren zu können  */
    public void entscheide()  {
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
 * 1. Hinweise zur Kodierung der Spielstände, wie sie vom Spielleiter gemeldet werden.
 *    Die Methode  holeAlleSpielstaende() der abstrakten Oberklasse Spieler17u4  erhält  
 *    alle Spielstände vom Spielleiter, indem sie ihn auffordert, sie auszuliefern.
 *    
 *     public int[] getAlleSpielstaende()    des Spielleiters
 *     
 *     gibt die Spielstände aller Spieler zurück in einer Reihung[] mit Elementtyp  int 
 *     Dies zeigt auch, ob die Spieler noch aktiv sind oder beendet haben.
 *     EINSCHRÄNKUNG:  Dabei werden max. 32 Einträge/Mitspieler verwaltet !!
 *     Bsp.:  12 14 118 20 0 18 0 117 -21 -21 -21 ...  
 *     PKtZahl  1 .. 21 = aktiver, noch weiter-würfelnder Spieler mit eben dieser Punktzahl 
 *     PktZahl 101..121 = Spieler, der aufgehört hat; mit Pktzahl 1xx-100,
 *        also 118 steht für:  Spieler ist fertig/hat aufgehört und 18 Punkte erreicht.
 *     PktZahl 0  =  Spieler, der über 21 hinauskam und daher verloren hat. 
 *     PktZahl -21 signalisiert, dass es diesen Spieler im aktuellen Spiel nicht gibt. 
 *     
 *     Im Beispiel sind also 8 Spieler beteiligt, davon sind zwei schon fertig (118, 117), 
 *     zwei über das Ziel hinausgeschossen und haben verloren 
 *     und vier noch aktiv am Würfeln (12,14,20,18) 
 *     
 *     
 */ 