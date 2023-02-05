
/**
 * Klasse:  SturSpieler
 * @author  PGW-Theo Heußer 
 * @version 27.12.2022
 */
public class SturSpieler extends Spieler17u4
{
    public SturSpieler()  {       //<-- 1.
        super();                    
        name = "sturi";        //bzw. Ihr Kürzel oder ähnliches
    }

    public SturSpieler(String neuName)  { 
        super();                        
        name = neuName;                   //<-- 1.
    }
    
    /** entscheidet, wann nicht mehr weiterwuerfelt wird;
     *  das wird der Eigenschaft 'fertig' vermerkt,
     *  der Wert der Variable wird veraendert. */
    public void entscheide()  {
        if (aktStand >17 && anzWuerfe >4) { fertig = true;}
    }
        
}

/* Bemerkungen:
 * 
 * 1. Die beiden Konstruktoren sind noch nicht optimal.
 *    beachten sie die Unterschiede zu den Kosntruktoren in Spiel17u4_Strategie23b
 *    
 */