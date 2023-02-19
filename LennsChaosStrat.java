import java.util.*;
/**
 *
 * @author lenn Stölzl, David Siekacz
 * @version 09.02.2023
 */
public class LennsChaosStrat extends Spieler17u4
{
    public LennsChaosStrat()    { 
        super("LennsChaosStrat");
    }

    /** Konstruktor zur Erzeugung einzelner Stratt17(=Objekte)
     *  für Spiel17u4 mit Festlegung der Anfangswerte */
    public LennsChaosStrat(String neuName)    { 
        super(neuName);
    }

    /** entscheidet, wann nicht mehr weitergewuerfelt wird;
     *  betrachtet dazu auch die Stände aller Spieler, um passend reagieren zu können  */
    public void entscheide()  {
        int[] spStaende = holeAlleSpielstaende(); //holt alle Spielstände

        ArrayList<Integer> tempStaende = new ArrayList<Integer>();
        for(int i = 0; i < spStaende.length; i++){
            tempStaende.add(spStaende[i]); //füge alle Spielstände in neue Liste
        }
        tempStaende.removeIf(e -> e == 0); //entferne alle Spieler die Verloren haben

        ArrayList<Integer> fertigStaende = new ArrayList<Integer>(); //neue Liste für alle Spieler die aufgehört haben

        boolean tester = false;
        for(int i = 0; i < tempStaende.size(); i++){
            int x = tempStaende.get(i);
            if(x >= 100){ 
                x -= 100;
                fertigStaende.add(x); //fügt alle Spieler die fertig sind der Liste hinzu
                tester = tempStaende.remove(tempStaende.get(i)); //entfernt alle fertigen Spieler aus tempStaende
            }
        }

        tempStaende.sort((e1,e2) -> -Integer.compare(e1,e2)); // sortiert beide Listen absteigend
        fertigStaende.sort((e1,e2) -> -Integer.compare(e1,e2));

        if(aktStand == 21){
            fertig = true;
            return;
        }

        //wenn man weniger hat als der höchste fertige Spieler, mach weiter
        if(fertigStaende.size() != 0){
            if(aktStand < fertigStaende.get(0)){ 
                fertig = false;
                return;
            }
        }

        //wenn man weniger hat als der höchste noch spielende Spieler, mach weiter
        if(aktStand < tempStaende.get(0)){
            fertig = false;
            return;
        }

        //wenn man 18 oder weniger hat, mache weiter, da man zu höchstens 50% verliert
        if(aktStand <= 18){
            fertig = false;
            return;
        }

        fertig = true;
    }
}
