import java.util.*;  
import javax.swing.*;

/** Spiel17u4_Strategie23b    aus   Spiel17u4_Strategie23a
 *  @author   Theo Heußer
 *  @version  3.2.2023
 *  fuer Spiel17u4 mit Strategie-Spieler-Klassen, 
 *  viele Spiele, Gewinnentscheid bei mehreren Spielern, starre Spielerliste
 */

public class Spielleiter
{
    // --- Objektvariable 
    private ArrayList<Spieler17u4> spielerliste;
    private ArrayList<Spieler17u4> siegerliste;
    private int maxAnzahl;
    private boolean spielBeendet;
    private int anzVglSpiele;

    // --- Konstruktoren ---
    /** Standard-Konstruktor; angelegt auf max. 32 Spieler 
     *  hier aber mit starrer Spielerliste von 7 Spielern  */
    public Spielleiter() {
        spielerliste = new ArrayList<Spieler17u4>();
        siegerliste  = new ArrayList<Spieler17u4>();
        maxAnzahl    = 32;
        spielBeendet = false;
        anzVglSpiele = 55000;       // 20000; 40000; 
        macheSpielerliste();
    }

    /** Anlegen einer starren Spielerliste;  nur als Startpunkt #####
     *  ### dies wird noch verändert!     //<-- 2b.  #######
     */
    private void macheSpielerliste(){
        Spieler17u4 tn;
        spielerliste.clear();
        tn = new DemoSpieler("demo1");   tn.setSpielleiter(this); spielerliste.add(tn);    //<-- 2.
        tn = new Strat2Spieler("Str21"); tn.setSpielleiter(this); this.spielerliste.add(tn);
        tn = new SturSpieler("Stur1");   tn.setSpielleiter(this); this. spielerliste.add(tn);
        tn = new SturSpieler("Stur2");   tn.setSpielleiter(this); spielerliste.add(tn);
        tn = new Stratt17("Str171");     tn.setSpielleiter(this); spielerliste.add(tn);   
        tn = new Strat2Spieler("Str22"); tn.setSpielleiter(this); spielerliste.add(tn);
        tn = new Strat2Spieler("Str23"); tn.setSpielleiter(this); spielerliste.add(tn);        
        tn = new Stratt17("Str172");     tn.setSpielleiter(this); spielerliste.add(tn);        
        tn = new DemoSpieler("demo2");   tn.setSpielleiter(this); spielerliste.add(tn);
        // usw. 
        /*# Genau das wollen wir auf Dauer nicht machen müssen !!!!     */
    }
    // boolean eintragen(Spieler17u4) wird nicht mehr benötigt wegen  macheSpielerliste(); 


    /** prüft und meldet, ob Spiel zu Ende ist, indem der Reihe nach alle Spieler überprüft werden
     *  setzt dabei die Variable spielBeendet
     *  @return  ja/nein, ob Spiel zu Ende ist; als Wert von spielBeendet    */
    public boolean pruefeEnde()  {
        spielBeendet = true;
        for (Spieler17u4 sp : spielerliste) {
            if (sp.getStand()<=21 && !sp.isFertig() ) {  // auch möglich:  !sp.hatVerloren() && ...
                spielBeendet = false; 
            }
        }      
        return spielBeendet;
    }

    /** eine Runde spielen lassen; für jeden Spieler aus  spielerliste
     *  wird z.B. von  einSpiel()  verwendet; kann daher 'private' gesetzt werden.
     */
    private void spieleEineRunde() {
        for (Spieler17u4 sp : spielerliste) { sp.spieleRunde(); }
        //   gleichwertig dazu sind:  [nur als Zusatz]
        // spielerliste.forEach(sp->sp.spieleRunde());
        //   bzw.
        // spielerliste.forEach(Spieler17u4::spieleRunde);
    }    

    /** führt ein Spiel mit den Spielern der Spielerliste durch; 
     *  ermittelt danach den/die Sieger.     */                  // <-- 1.
    public void einSpiel() {
        neuesSpiel();  //ist sinnvoll für viele wiederholte Spiele
        //System.out.println("SPIEL 17u4");
        while (!pruefeEnde())  { spieleEineRunde(); }
        // Nun ist das Spiel beendet
        ermittleSieger();       // und reagieren, wenn Spiel zu Ende:
        //zeigeSiegerliste();   // die Siegerliste bestimmen und anzeigen
    }    

    /** damit die Spieler nicht schummeln können, würfelt der Spielleiter und 
     *  meldet das Wurfergebis z.B. an einen Spieler zurück  */
    public int getWurf() {
        return 1 + (int) (Math.random()*6);
    }  
    
    /** Bei vielen Spielen: Gewinner gemäß der Wettkampfideen protokollieren; 
     *  Ergebnisse merken.   Hier Idee 1: nur die Anzahl der Siege zählen    */
    private void protokolliereSpiel() {         
        for (Spieler17u4 sp : siegerliste) { sp.erhoeheGewinnzahl(); }
        //   gleichwertig  sind:  [nur als Zusatz]
        // siegerliste.forEach(sp->sp.erhoeheGewinnzahl());
        //   bzw.  siegerliste.forEach( ?? :: ??? );
    }  
    
    /*# ##########   vorläufig  nur diese Art der 
     *  ###     Feststellung der besten Strategie    ########  */       
    /** Durchführung von so vielen Spielen, wie  anzVglSpiele  angibt
     *  Gewinnermittlung und Protokollierung der Spielergebnisse
     *  #########   vorläufig nur für obige Idee: "Anzahl der Siege"     ######
     */
    public void aaaStratVergleich(){            //  <-- 4.
        macheSpielerliste();
        for (Spieler17u4 sp : spielerliste) { sp.resetAnzGewinne(); }
        System.out.println("SPIEL 17u4 - Strategievergleich");
        for (int i=0; i<anzVglSpiele; i++) {
            einSpiel();
            ermittleSieger();    // und reagieren, wenn Spiel zu Ende:
            protokolliereSpiel();
            // neuesSpiel();   // ist nun in einSpiel() enthalten.
        }  
        zeigeGewinner();
    }
    
    
    // -----  Hilfsroutinen    -------
    /** die veraenderlichen Werte zuruecksetzen 
     *  der Reihe nach fuer alle Spieler der  spielerliste  */
    public void neuesSpiel() {
        for (Spieler17u4 sp : spielerliste) { sp.setStartwerte();  }
        //   gleichwertig dazu sind:    [nur als Zusatz]
        // spielerliste.forEach(sp->sp.setStartwerte());
        //   bzw.
        // spielerliste.forEach(Spieler17u4::setStartwerte);
    } 

    /** feststellen, wer Sieger ist, d.h.: die Siegerliste erstellen! 
     *  Dazu wird die Variable  siegerliste  gefuellt.
     *  Dies erfolgt in zwei Durchgaengen.
     *  1. Suchen des Gewinnwertes (= max.Wert bis 21)
     *  2. Eintragen aller Spieler mit diesem Punktstand in die Siegerliste
     *  
     *  es wird NICHTS zurueckgegeben      */
    private void ermittleSieger() {
        if (spielBeendet) {
            siegerliste.clear();
            int maxStand = -1; // Hilfsvariable zum Merken des MaxStandes
            // Finde in der Teilnehmerliste den maximalen Spielstand kleiner 22
            for (Spieler17u4 sp : spielerliste) {
                if (sp.getStand()<22 && sp.getStand()> maxStand) { 
                    maxStand = sp.getStand();
                }
            }
            // Schreibe alle Spieler in die Siegerliste, bei denen der Spielstand 
            // mit dem oben gefundenen maxStand übereinstimmt. 
            for (Spieler17u4 sp : spielerliste) {
                if (sp.getStand() == maxStand) { 
                    siegerliste.add(sp);
                }
            }
        }
    }

    /** 
     *  gibt die Siegerliste zurueck */
    public ArrayList<Spieler17u4> getSiegerliste()    {
        return siegerliste;
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

    /** gibt die Spielstände aller Spieler zurück in einer Reihung[] mit Elementtyp  int 
     *  Dies zeigt auch, ob die Spieler noch aktiv sind oder beendet haben.
     *  max. 32 Einträge/Mitspieler
     *  Bsp.:  12 14 118 20 0 18 0 117 -21 -21 -21 ...  
     *  PKtZahl  1 .. 21 = aktiver Spieler mit dieser Punktzahl 
     *  PktZahl 101..121 = Spieler, der aufgehört hat; mit Pktzahl 1xx-100,
     *     also 118 steht für:  Spieler ist fertig und hat 18 Punkte erreicht.
     *  PktZahl 0 = Spieler, der über 21 hinauskam und daher verloren hat. 
     *  PktZahl -21 signalisiert, dass es diesen Spieler im aktuellen Spiel nicht gibt.   */
    public int[] getAlleSpielstaende()    {
        int[] zwi = new int[32]; //max. 32 Strategiespieler
        for (int j=0; j<32; j++) { zwi[j]= -21;}

        int i=0; 
        for (Spieler17u4 sp : spielerliste) {
            if (sp.getStand()>21 )  { zwi[i] = 0; }   //Signal für: 'hat verloren'
            else if( sp.isFertig()) { zwi[i] = 100+sp.getStand(); }
            else { zwi[i] = sp.getStand(); }
            i++;
            if (i>=32) {break;}     //max. 32 Strategiespieler werden beachtet!!
        }

        return zwi;
    }

    /** gibt die Anzahl der Sieger zurueck:
     *  #### Vereinbarung vor allem für die JUnit-Tests der Entwicklungsphase: #### 
     *    liefert -1,  wenn noch keine Entscheidung
     *    liefert 0 ,  wenn alle verloren haben
     *    liefert sonst die Anzahl der Sieger (also 1,2,3,4..)
     *  @return   Anzahl der Sieger
     */
    public int getSiegerzahl()    {
        if (spielBeendet) {
            if (siegerliste==null) {return 0;}
            else { return siegerliste.size();}
        } else {
            return -1;
        }
    }     

    /** gibt die Punktzahl des/der Sieger zurueck:
     *  #### Vereinbarung vor allem für die JUnit-Tests der Entwicklungsphase: #### 
     *    liefert 0 , wenn es (noch) keinen Sieger gibt
     *    sonst die Punktzahl des Siegers bzw. der Sieger
     *  @return   Punktzahl des/der Sieger
     */
    public int getSiegwert()    {
        if (siegerliste.size()>0) {
            return siegerliste.get(0).getStand();
        } else {
            return 0;
        }
    } 

    /** zeige die Spielerliste in einer Dialogbox auf dem Schirm an;
     *  als Ersatz ohne GUI-Anbindungen für die Anzeige der Listen  */
    public void zeigeSpielerliste()   {
        String listenText = "";
        for (Spieler17u4 sp : spielerliste) { listenText += sp.toString()+"\n";  }
        melde(listenText, "Spielerliste");
    }          

    /** gibt die Siegerliste in Dialogbox auf dem Bildschirm aus;
     *  als Ersatz ohne GUI-Anbindungen für die Anzeige der Listen   */
    protected void zeigeSiegerliste() {
        String siegText = "noch niemand";
        if (spielBeendet && siegerliste==null) {siegText= "alle verloren!";}
        else if (spielBeendet) {
            siegText =  "";
            for (Spieler17u4 sp : siegerliste) { siegText += sp.toString()+"\n"; }
        }
        melde(siegText, "Gewonnen haben:");
    }    

    /** zeigt die aktuellen Daten aller Spieler der Reihe nach auf der Konsole;
     *  Eine Alternative zu  zeigeSpielerliste()  */ 
    public void zeigeAlleSpieler() {
        System.out.println("\n akt.Stand:");
        // for (Spieler17u4 sp: spielerliste) { System.out.println(sp.toString() );} 
        // ist ebenso gut, wird hier nur zur Demonstration
        // ersetzt durch den folgenden funktionalen Ausdruck:
        spielerliste.forEach(System.out::println);
    }    

    /** für viele Spiele als Gesamtergebnis;
     *  gibt die Gewinn-Daten aller Spieler auf der Konsole aus    */
    public void zeigeGewinner() {
        System.out.println("\n So oft sind diese Spieler bei  "+anzVglSpiele+" Spielen");
        System.out.println("  unter den Gewinnern:");
        for (Spieler17u4 sp: spielerliste) {
            System.out.printf("%9s : %7d%n", sp.getName(), sp.getAnzGewinne());
        }        
    }
    
    
    // ---------   Hilfsroutinen  ------------------
    // -- solange nicht in eine GUI eingebunden.
    /** gibt den uebergebenen String text in Dialogbox auf dem Bildschirm 
     *  unter der Ueberschrift titel aus  */
    protected void melde(String text, String titel) {
        JOptionPane.showMessageDialog(null, text, titel, JOptionPane.INFORMATION_MESSAGE);
    }

}

/* Bemerkungen:
 * 
 * 
 * 
 * 1.  zur Methode einSpiel() {
       vgl.. auch die Vorgängerversion:  ..Strategie23a
       Sie kann kürzer geschrieben werden 
         System.out.println("SPIEL 17u4");
         while (!pruefeEnde())  { spieleEineRunde(); }
       weil diese Methode ja definiert ist. Oder aber
       man wirft die interne Methode spieleEineRunde() komplett raus und schreibt hier direkt:
         while (!pruefeEnde())  { spielerliste.forEach(Spieler17u4::spieleRunde); }
 * 
 * 
 * 2.     tn.setSpielleiter(this); spielerliste.add(tn);  
 *      reicht aus und ersetzt das  anmeldenBeim(spielleiter)  .
 *      Der zuzufügende Spieler  tn  wird aufgefordert, bei sich den aktuellen Spielleiter 'this' 
 *      als seinen Spielleiter festzusetzen, bei dem er mitspielt, 
 *      also dieses Objekt der Klasse Spielleiter, das gerade aktiv ist, dessen Methode gerade 
 *      abläuft.  Daher das Schlüsselwort  this .
 *      [Zum Zeitpunkt des Erstellens des Quelltextes ist dieses Spielleiter-Objektes und damit 
 *       auch sein Name noch nicht bekannt.]
 *      Dieser aktuelle Spielleiter fügt anschließend den Spieler  tn  seiner Spielerliste hinzu.
 *         vgl. Sequenzdiagramm
 *      Daher kann man die Zeile auch so schreiben: 
 *         tn.setSpielleiter(this); this.spielerliste.add(tn);
 *         
 * 2b.  Auf Dauer wollen wir nicht jeden Spieler einzeln mit Namen im voraus eintragen müssen.
 *      Wir wissen vlt. gar nicht, wer alles mitmachen will. 
 *      Es wäre schön, wenn der Spielleiter sich umsehen könnte und die Spieler dann in seine Spielerliste
 *      eintragen könnte. 'Sich umsehen' könnte heißen, an vorher festgelegten Stellen auf der Festplatte, 
 *      also in festen Verzeichnissen, sucht er nach Unterklassen von Spieler17u4, d.h. nach Dateien 
 *      der Art  superSpieler.class, XYSpieler.class, KV2Spieler.class, ... 
 *      
 *      
 * 3.  Es werden max. 32 Spieler in der Spielerliste beachtet.
 *     
 * 
 * 4.  Der Methodenname ist so komisch:  "aaa...", um sie ganz oben als 1. Methode 
 *     im Methodenaufruffenster zu zeigen. So findet man sie schneller.
 *     
 *     
 */