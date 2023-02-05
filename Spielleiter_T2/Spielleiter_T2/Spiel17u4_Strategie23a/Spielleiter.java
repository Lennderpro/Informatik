import java.util.*;  
import javax.swing.*;

/** Spiel17u4_Strategie23    aus   Spielleiter17u4Bv02
 *  @author   Theo Heußer
 *  @version  19.1.2023 /2.2.22/25.1.2022
 *  fuer Spiel17u4 mit Strategie-Spieler-Klassen, 
 *  Ganze Spiele, Gewinnentscheid bei mehreren Spielern 
 */

public class Spielleiter
{
    // --- Objektvariable 
    private ArrayList<Spieler17u4> spielerliste; 
    private ArrayList<Spieler17u4> siegerliste;
    private int maxAnzahl;
    private boolean spielBeendet;

    // --- Konstruktoren ---
    /** Standard-Konstruktor, so dass danach max. sieben Spieler per Hand zugefuegt 
     *  werden koennen. */
    public Spielleiter() {
        spielerliste = new ArrayList<Spieler17u4>();
        siegerliste  = new ArrayList<Spieler17u4>();
        maxAnzahl = 17;
        spielBeendet = false;
    }

    /** trage einen Spieler in die TNListe ein.
     *  nur moeglich, wenn  spielerliste  noch nicht voll ist. */
    public boolean eintragen(Spieler17u4 teilnehmer)   {
        if(spielerliste.size() >= maxAnzahl) {  
            melde("Es sind schon genug Teilnehmer.\nKeine Teilnahme mehr m\u00F6glich.",
                "WARNUNG!");
            return false;
        } else {
            return spielerliste.add(teilnehmer);   // add liefert ja/nein zurueck
        }
    }

    /** prüft, ob Spiel zu Ende ist; sonst nichts; 
     *  indem der Reihe nach alle Spieler überprüft werden
     *  @return  ja/nein, ob Spiel zu Ende ist; Wert von spielBeendet
     */
    public boolean pruefeEnde()  {
        spielBeendet = true;
        for (Spieler17u4 sp : spielerliste) {
            if (sp.getStand()<=21 && !sp.isFertig() ) {  // auch möglich:  !sp.hatVerloren() && ...
                spielBeendet = false; 
            }
        }      
        return spielBeendet;
    }

    /** eine Runde spielen lassen 
     *  fuer jeden angemeldeten Spieler
     *  wird z.B. von  demoSpiel()  verwendet; kann daher 'private' gesetzt werden.
     */
    public void spieleEineRunde() {
        for (Spieler17u4 sp : spielerliste) { 
            sp.spieleRunde(); 
        }      
        /* die folgende Prüfung muss raus! Sie gehört zum Gesamtspiel, also z.B. in  demoSpiel(). */
        /*if (pruefeEnde()) {
            ermittleSieger();     // und reagieren, wenn Spiel zu Ende:
            zeigeSiegerliste();   // die Siegerliste bestimmen und anzeigen        
        }*/
    }    

    /** führt ein Spiel mit etlichen Spielern der Unterklasse SturSpieler und einem
     *  Stratt17-Spieler durch;
     */
    public void demoSpiel() {
        spielerliste.clear();    // Anfangszustand herstellen !!!
        for (int i=0; i<maxAnzahl-1; i++)  {                        // <-- 2.
            Spieler17u4 sp = new SturSpieler("Sp"+i);
            sp.anmeldenBeim(this);                                  // <-- 2.           
        }         
        Spieler17u4 sp2 = new Stratt17();
        sp2.anmeldenBeim(this); 
        
        //neuesSpiel();  //ist nicht noetig, weil alle Spieler oben neu erzeugt wurden!!
        System.out.println("SPIEL 17u4");
        
        while (!pruefeEnde())  {                                        // <-- 1.
            for (Spieler17u4 sp: spielerliste) { sp.spieleRunde();}
        }
        // Nun ist das Spiel beendet        
        zeigeAlleSpieler();        
        ermittleSieger();     // und reagieren, wenn Spiel zu Ende:
        zeigeSiegerliste();   // die Siegerliste bestimmen und anzeigen 
    }    

    /** das Wurfergebnis eines Wurfes mit einem normalen W6 
     *  z.B. an einen Spieler zurueckmelden */
    public int getWurf() {
        return 1 + (int) (Math.random()*6);
    }  
    
    // -----  Hilfsroutinen    -------
    /** die veraenderlichen Werte zuruecksetzen 
     *  der Reihe nach fuer alle Spieler der  spielerliste  */
    public void neuesSpiel() {
        for (Spieler17u4 sp : spielerliste) {       
            sp.setStartwerte();   
        }
        siegerliste = null;
    } 

    /** feststellen, wer Sieger ist. 
     *  Dazu wird die Siegerliste erstellt, d.h. die Variable  siegerliste  gefuellt.
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
    
    public void getWinners(){
        siegerliste = new ArrayList<Spieler17u4>();
        ArrayList<Spieler17u4> spielertmp = (ArrayList<Spieler17u4>) spielerliste.clone();
        spielertmp.removeIf(e -> e.getStand()>21);
        spielertmp.sort((e1,e2) -> -Integer.compare(e1.getStand(),e2.getStand()));
        
        if(spielertmp.size() >0){
            siegerliste = null;
            return;
        }
        
        siegerliste.add(spielertmp.get(0));
        for(int i = 1; i > spielertmp.size(); i++){
            if(spielertmp.get(i).getStand() == spielertmp.get(i-1).getStand()){
                siegerliste.add(spielertmp.get(i));
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
     *  und auch ob die Spieler noch aktiv sind oder beendet haben.
     *  max. 25 Einträge/Mitspieler:  Bsp.:  12 14 118 20 0 18 0 117 -21 -21 -21 ...  
     *  PKtZahl von 1..21 = aktiver Spieler mit dieser Punktzahl 
     *  PktZahl 101..121  = Spieler, der aufgehört hat; mit Pktzahl 1xx-100,
     *     also 118 steht für:  Spieler ist fertig und hat 18 Punkte erreicht.
     *  PktZahl 0 = Spieler, der über 21 hinauskam und daher verloren hat.  */
    public int[] getAlleSpielstaende()    {
        int[] zwi = new int[25]; //max. 25 Strategiespieler
        for (int j=0; j<25; j++) { zwi[j]= -21;}

        int i=0; 
        for (Spieler17u4 sp : spielerliste) {
            if (sp.getStand()>21 )  { zwi[i] = 0; }  //Signal für: 'hat verloren'
            else if( sp.isFertig()) { zwi[i] = 100+sp.getStand(); }
            else { zwi[i] = sp.getStand(); }
            i++;
        }

        return zwi;
    }

    /** gibt die Anzahl der Sieger zurueck:
     *  #### Vereinbarung vor allem für die JUnit-Tests: #### 
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
     *  #### Vereinbarung vor allem für die JUnit-Tests: #### 
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
        for (Spieler17u4 sp : spielerliste) { 
            listenText += sp.toString()+"\n"; 
        }
        melde(listenText, "Spielerliste");
    }          

    /** gibt die Siegerliste in Dialogbox auf dem Bildschirm aus;
     *  als Ersatz ohne GUI-Anbindungen für die Anzeige der Listen   */
    protected void zeigeSiegerliste() {
        String siegText = "noch niemand";
        if (spielBeendet && siegerliste==null) {siegText= "alle verloren!";}
        else if (spielBeendet) {
            siegText =  "";
            for (Spieler17u4 sp : siegerliste) { 
                siegText += sp.toString()+"\n"; 
            }
        }
        melde(siegText, "Gewonnen haben:");
    }    

    /** zeigt die aktuellen Daten aller Spieler der Reihe nach auf der Konsole;
     *  Eine Alternative zu  zeigeSpielerliste()  */ 
    public void zeigeAlleSpieler() {
        System.out.println("\n akt.Stand:");
        for (Spieler17u4 sp: spielerliste) {
            System.out.println(sp.toString() );
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
 * kann auch in eine GUI eingebaut werden; 
 * ist dort das Pendant zu Spielfeld.
 * enthält auch melde-Routinen und Konsolen-Ausgaben, die unabhängig von einer GUI laufen.
 * 
 * 
 * 
 * 1.  zur Prüfung in der Methode demoSpiel() {
       
        ...
        System.out.println("SPIEL 17u4");
        while (!pruefeEnde())  { 
            for (Spieler17u4 sp: spielerliste) {
                if (!(sp.isFertig()|| sp.hatVerloren() )) { sp.spieleRunde();}
            }
        }
        // darin ist die Abfrage für jedem Spieler nicht nötig!! 
        // sondern es reicht: 
        while (!pruefeEnde())  { 
            for (Spieler17u4 sp: spielerliste) { sp.spieleRunde();}
        }
        // weil pruefeEnde() ....
       
 * 
 * 
 * 2.   spielerliste.add(sp)  statt  sp.anmeldenBeim(this)    reicht an der Stelle nur aus, 
 *      wenn der zuzufügende Spieler sp  nichts von seinem Spielleiter braucht.
 *      Aber bei Strategien, die die Spielstände der anderen Spieler kennen wollen/müssen, 
 *      muss der Spieler diese Spielstände vom Spielleiter erfragen; dazu muss er ihn kennen.
 *      Daher muss dieser Spieler bei seinem Spielleiter angemeldet werden.
 *        sp.anmeldenBeim(this);
 *      Zu dieser Anweisung im Detail: 
 *      Wir sind in der Klasse Spielleiter. 
 *      Dieser aktuelle Spielleiter gibt dem Spieler mit Namen sp den Befehl, dass er sich anmeldet;
 *      und zwar bei ihm, bei diesem Spielleiter 'this', der gerade den Befehl gibt.
 *      Daher das Schlüsselwort  this .
 *      Der Spielleiter beauftragt also den Spieler sp, dass er sich bei ihm, dem Spielleiter selbst, 
 *      anmelden soll.       
 *      
 *      Wie/Wo wird dem Spieler  sp  beim Anmelden sein Spielleiter bekannt gemacht? 
 *      Wo merkt sich der Spieler seinen Spielleiter?
 *      
 *      Die Methode anmeldenBeim() des Spielers umfasst auch das Eintragen in die Spielerliste 
 *      des Spielleiters, also: spielerliste.add(sp) 
 *      und schafft zusätzlich die Verknüpfung von Spieler zu Spielleiter.
 *        vgl. Sequenzdiagramm
 *      
 *      
 */
