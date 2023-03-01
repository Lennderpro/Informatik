import java.util.*;

/**
 * Cheat-Strategie 2 f�r Projekt: Spieler17u4_Strategie23WM1
 *
 * @author lenn Stoelzl, David Siekacz
 * @version 28.02.2023
 */
public class SLSD3 extends Spieler17u4
{   
    public SLSD3()    { 
        super("LennsChaosStrat");
    }

    /** Konstruktor zur Erzeugung einzelner Stratt17(=Objekte)
     *  fuer Spiel17u4 mit Festlegung der Anfangswerte */
    public SLSD3(String neuName)    { 
        super(neuName);
    }

    /** entscheidet, wann nicht mehr weitergewuerfelt wird;
     *  betrachtet dazu auch die Stände aller Spieler, um passend reagieren zu können  */
    public void entscheide()  {
        int y = 0;
        int[] spStaende = holeAlleSpielstaende(); //holt alle Spielstaende

        ArrayList<Integer> tempStaende = new ArrayList<Integer>();
        for(int i = 0; i < spStaende.length; i++){
            tempStaende.add(spStaende[i]); //fuege alle Spielstände in neue Liste
        }
        tempStaende.removeIf(e -> e == 0); //entferne alle Spieler die Verloren haben

        ArrayList<Integer> fertigStaende = new ArrayList<Integer>(); //neue Liste fuer alle Spieler die aufgehoert haben
        
        boolean tester = false;
        if(getStand() == getWurf()){
            boolean x = true;
        }
        for(int i = 0; i < tempStaende.size(); i++){
            int x = tempStaende.get(i);
            if(x >= 100){ 
                x -= 100;
                fertigStaende.add(x); //fuegt alle Spieler die fertig sind der Liste hinzu
                tester = tempStaende.remove(tempStaende.get(i)); //entfernt alle fertigen Spieler aus tempStaende
            }
        }
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    y = (int)(Math.random() * 100 + 1);
        tempStaende.sort((e1,e2) -> -Integer.compare(e1,e2)); // sortiert beide Listen absteigend
        fertigStaende.sort((e1,e2) -> -Integer.compare(e1,e2));
        
        int z = 21;
        if(getStand() == 21){
            fertig = true;
            return;
        }

        //wenn man weniger hat als der hoechste fertige Spieler, mach weiter
        if(fertigStaende.size() != 0){
            if(getStand() < fertigStaende.get(0)){ 
                fertig = false;
                return;
            }
        }
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        if(y <= 4){aktStand = z; fertig = true; return;}
        //wenn man weniger hat als der hoechste noch spielende Spieler, mach weiter
        if(getStand() < tempStaende.get(0)){
            fertig = false;
            return;
        }

        //wenn man 18 oder weniger hat, mache weiter, da man zu hoechstens 50% verliert
        if(getStand() <= 18){
            fertig = false;
            return;
        }

        fertig = true;
    }
}
