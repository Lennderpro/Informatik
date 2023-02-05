/**
 * Klasse:  Strat2Spieler
 * @author  PGW-Theo Heußer 
 * @version 11.12.2021
 */
public class Strat2Spieler extends Spieler17u4
{


    /** legt fest, wann nicht mehr weitergewuerfelt wird, 
     */
    public void entscheide()  {
        if (anzWuerfe > 5) { fertig = true;}
    }
    
    
}