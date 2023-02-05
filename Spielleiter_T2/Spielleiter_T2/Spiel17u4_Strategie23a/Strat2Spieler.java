/**
 * Klasse:  Strat2Spieler
 * @author  PGW-Theo Heußer 
 * @version 1.2.2023
 */
public class Strat2Spieler extends Spieler17u4
{

    /** entscheidet, wann nicht mehr weitergewuerfelt wird.
     */
    public void entscheide()  {
        if (anzWuerfe > 5) { fertig = true;}
    }
    
}

/* Bemerkungen:
 * 
 * Erläutern Sie diese Spiel-Strategie.
 * Warum ist sie nicht sehr sinnvoll bzw. gewinnversprechend?
 * 
 * Diese Strategiespieler-Unterklasse hat keinen Konstruktor!! 
 * wie geht das? Was passiert dadurch?
 * DemoSpieler und Stratt17 haben hat einen Konstrukor mit Parameter
 * SturSpieler hat zwei Konstruktoren wie auch die abstrakte Oberklasse. 
 * 
 * Erläutern Sie!
 */