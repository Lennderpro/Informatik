
/**
 * Klasse:  StratSpieler
 * @author  PGW - Theo Heußer 
 * @version 17.1.2023
 */
public class StratSpieler extends Spieler17u4
{
    public StratSpieler()    { 
        super();
        name = "strat1";
    }

    /** entscheidet, wann nicht mehr weitergewürfelt wird.
     *  Das wird in der Eigenschaft 'fertig' vermerkt,
     *  der Wert dieser Variable also verändert. */
    public void entscheide()  {
        if (aktStand >=20) { fertig = true;}
    }
        
}

/* Dies ist nur eine einfache und starre Strategie zur Demonstration.
 * Ist sie gut?
 * 
 */