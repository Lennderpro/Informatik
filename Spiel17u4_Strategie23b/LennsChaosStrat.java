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
        int[] spStaende = holeAlleSpielstaende();
        
        ArrayList<Integer> tempStaende = new ArrayList<Integer>();
        tempStaende.removeIf(e -> e == 0);
        
        ArrayList<Integer> fertigStaende = new ArrayList<Integer>();
        
        boolean tester = false;
        for(Integer sp : tempStaende){
            if(sp >= 100){
                sp -= 100;
                fertigStaende.add(sp);
                tempStaende.remove(sp);
            }
        }
        
        tempStaende.sort((e1,e2) -> -Integer.compare(e1,e2));
        fertigStaende.sort((e1,e2) -> -Integer.compare(e1,e2));
        
        if(aktStand == 21){
            fertig = true;
            return;
        }
        
        if(aktStand < fertigStaende.get(0)){
            return;
        }
        
        if(aktStand < tempStaende.get(0)){
            return;
        }
        
        if(aktStand <= 17){
            return;
        }
    }
}
