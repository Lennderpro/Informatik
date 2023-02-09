/**
 * Klasse:  Strat2Spieler
 * @author  PGW-Theo Heußer 
 * @version 2.2.2023
 */
public class Strat2Spieler extends Spieler17u4
{
    public Strat2Spieler()    { 
        super("strat2");    //bzw. Ihr Kürzel oder ähnliches
    }

    public Strat2Spieler(String neuName)    { 
        super(neuName);
    }
    
    /** entscheidet, wann nicht mehr weitergewuerfelt wird.
     */
    public void entscheide()  {
        if (anzWuerfe > 5) { fertig = true;}
    }
    
}

/* Bemerkungen:
 * 
 * Diese Strategiespieler-Unterklasse hat jetzt auch sinnvollerweise zwei Konstruktoren, 
 * genau wie die Oberklase.  
 * 
 */