/**
 * Klasse:  DemoSpieler
 * @author  PGW-Theo Heußer 
 * @version 2.2.2023
 */
public class DemoSpieler extends Spieler17u4
{
    public DemoSpieler()    { 
        super("Demo");       //bzw. Ihr Kürzel oder ähnliches 
    }
    
    public DemoSpieler(String neuName)    { 
        super(neuName);
    }

    /** entscheidet, wann nicht mehr weitergewürfelt wird.
     *  Das wird in der Variable fertig vermerkt,
     *  der Wert der Variable wird ggf. verändert. */
    public void entscheide()  {
        if (aktStand >=20) { fertig = true;}
    }
        
}


/* Nehmen Sie diese Klasse z.B. als Vorlage, um Ihre eigene Strategie aufzuschreiben. 
 * 
 * Ändern Sie dazu den Klassen-Kopf und Konstruktoren entsprechend ab.
 * 
 */