import java.util.*;  
import javax.swing.*;

/** Klasse:  Spielleiter 
 *  Projekt: Spielleiter17u4_roh
 *  @author  Theo Heußer
 *  2.12.2022 / 16.10.16 / 7.9.13
 *  nur als Roh-Vorlage u.a. zur Siegerermittlung
 */

public class Spielleiter
{

    // --- Objektvariable 
    private ArrayList<Spieler17u4> spielerliste;
    private ArrayList<Spieler17u4> siegerListe;
    private int maxAnzahl;
    private boolean spielBeendet;

    // --- Konstruktor ---
    /** Standard-Konstruktor, so dass danach max. sieben Spieler per Hand zugefuegt 
     *  werden koennen. */
    public Spielleiter() {
        spielerliste = new ArrayList<Spieler17u4>();  
        maxAnzahl = 7;
        spielBeendet = false;
    }

    /** trage einen Spieler in die TNListe ein. 
     * nur moeglich, wenn  spielerliste  noch nicht voll ist. */
    public void eintragen(Spieler17u4 teilnehmer)   {
        if(spielerliste.size() >= maxAnzahl) {  
            melde("Es sind schon genug Teilnehmer.\nKeine Teilnahme mehr m\u00F6glich.",
                "WARNUNG!");
        }
        else {
            spielerliste.add(teilnehmer);
        }
    }

    /** prueft, ob das Spiel zu Ende ist und meldet die Antwort: ja/nein
     *  diese Methode wird u.a. von den Spielern aufgerufen, 
     *  @return  spielBeendet;   dessen Wert gibt an, ob Spiel zu Ende ist
     */
    public boolean pruefeEnde()  {
        spielBeendet = true;     // <-- 2.
        for (Spieler17u4 sp : spielerliste) {       
            if (sp.getStand()<=21 && !sp.isFertig()) {
                spielBeendet = false; 
            }            
        }      
        return spielBeendet;       
    }

    // Hilfsroutinen   
    /** die veraenderlichen Werte zuruecksetzen 
     *  der Reihe nach fuer alle Spieler aus der  spielerliste  */
    public void neuesSpiel() {      // <-- 1.
        for(Spieler17u4 sp : spielerliste){
            sp.setStartwerte();
            spielBeendet = false;
        }
    } 

    /** Verfahren, um Sieger festzustellen und irgendwie zu merken/notieren.
     *  es wird NICHTS als Antwort zurueckgegeben
     */
    public void ermittleSieger()    {
        siegerListe = new ArrayList<Spieler17u4>();
        
        if(!pruefeEnde()) {
            Spieler17u4 testSpieler = new Spieler17u4();
            testSpieler.setStand(-1);
            siegerListe.add(testSpieler);
            return;
        }
        int max = -1;

        for(Spieler17u4 sp : spielerliste){
            if(sp.getStand() > max && sp.getStand() <= 21){
                max = sp.getStand();
            }
        }

        if(max == -1) {
            Spieler17u4 testSpieler = new Spieler17u4();
            testSpieler.setStand(0);
            siegerListe.add(testSpieler);
            return;
        }

        for(Spieler17u4 sp : spielerliste){
            if(sp.getStand() == max){
                siegerListe.add(sp);
            }
        }
    }

    /** 
     *  gibt die Spielerliste zurueck */
    public ArrayList<Spieler17u4> getSpielerliste()    {
        return spielerliste;
    }

    /** gibt die Anzahl der Spieler zurueck
     *  @return   Anzahl der Spieler
     */
    public int getSpielerzahl()    {
        return spielerliste.size();
    }

    /** gibt die Anzahl der Sieger zurueck:
     *  Vereinbarung: wenn noch keine Entscheidung: -1;  wenn alle verloren haben: 0
     *    sonst die Anzahl der Sieger
     *  @return   Anzahl der Sieger
     */
    public int getSiegerzahl()    {
        ermittleSieger();

        if(siegerListe.get(0).getStand() <= 0){
            return siegerListe.get(0).getStand();
        }

        return siegerListe.size();
    }     

    /** gibt die Punktzahl des/der Sieger zurueck:
     *  Vereinbarung: wenn es (noch) keinen Sieger gibt:  0
     *  @return   Punktezahl des/der Sieger
     */
    public int getSiegwert()    {
        ermittleSieger();

        if(siegerListe.get(0).getStand() <= 0){
            return siegerListe.get(0).getStand();
        }

        return siegerListe.get(0).getStand();
    }

    /** zeige die Spielerliste in einer Dialogbox auf dem Schirm an.
     *  als Ersatz fuer GUI zur Anzeige */
    public void zeigeSpielerliste()   {
        String listenText = "";
        for (Spieler17u4 sp : spielerliste) {       
            listenText += sp.toString()+"\n"; 
        }
        melde(listenText, "Spielerliste");
    }
    
    public void zeigeSieger()   {
        ermittleSieger();
        
        String listenText = "";
        for (Spieler17u4 sp : siegerListe) {       
            listenText += sp.toString()+"\n"; 
        }
        melde(listenText, "Spielerliste");
    }    

    // -- Hilfsroutinen
    // -- als Ersatz fuer eine GUI     
    /** gibt den uebergebenen String text in Dialogbox auf dem Bildschirm 
     *  unter der Ueberschrift titel aus  */
    protected void melde(String text, String titel) {
        JOptionPane.showMessageDialog(null, text, titel, JOptionPane.INFORMATION_MESSAGE);
    }

}

/** Bemerkungen: vgl. Arbeitsblatt AB3; Aufg. 1, 2 und 3
 * 
 *  1. Erstellen Sie diese Methode.
 *  
 *  2. Wozu macht die Methode pruefeEnde() dies als erstes?
 *  
 *  3. Um zu pruefen, wie weit Ihre Siegerfeststellung korrekt ist, können Sie den 
 *     JUnit-Test 'SpielTest' verwenden.
 *  
 *  4. Die wichtige Aufgabe:
 *     Erstellen Sie am besten schrittweise eine Methode zur Siegerfeststellung.
 *     Dies ist erst sinnvoll, nachdem Sie Ihre Ideen gemäß dem Arbeitsblatt AB3
 *     "Spielleiter für 17u4: Lesen, Entwerfen, Testen, Implementieren eines Programmes"
 *     notiert haben. Beachte Aufg. 2 und 3 darin.
 *     Beginnen Sie dann z.B. damit zunächst einen typischen Fall zu lösen.
 *    
 */
