
/**
 * Klasse:  DemoSpieler
 * @author  PGW-Theo Heußer 
 * @version 11.12.2021
 */
public class DemoSpieler extends Spieler17u4
{
    public DemoSpieler(String neuName)    { 
        super();
        name = neuName;        //bzw. Ihr Kürzel oder ähnliches 
    }

    /** entscheidet, ob nicht mehr weitergewürfelt wird. 
     *  Das wird in der Variable fertig vermerkt,
     *  der Wert der Variable wird ggf. verändert. */
    public void entscheide()  {
        if (aktStand >=20) { fertig = true;}
    }
        
}


/* Nehmen Sie diese Klasse z.B. als Vorlage, um Ihre eigene Strategie aufzuschreiben. 
 * 
 * Ändern Sie den Klassen-Kopf entsprechend ab.
 * 
 */