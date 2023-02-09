/**
 * Klasse:  SturSpieler
 * @author  PGW-Theo Heußer 
 * @version 2.2.2023
 */
public class SturSpieler extends Spieler17u4
{
    public SturSpieler()    { 
        super("sturi");    //bzw. Ihr Kürzel oder ähnliches
    }

    public SturSpieler(String neuName)    { 
        super(neuName);
    }
    
    /** entscheidet, wann nicht mehr weiterwuerfelt wird;
     *  das wird der Eigenschaft 'fertig' vermerkt,
     *  der Wert der Variable wird veraendert. */
    public void entscheide()  {
        if (aktStand >17 && anzWuerfe >4) { fertig = true;}
    }
        
}

/* Diese Strategiespieler-Unterklasse hat nun auch zwei Konstruktoren 
 * wie die abstrakte Oberklasse. 
 * 
 */ 