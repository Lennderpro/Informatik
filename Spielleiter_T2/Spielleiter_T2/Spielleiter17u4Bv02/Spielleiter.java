import java.util.*;  
import javax.swing.*;

/** Spielleiter 17u4Bv02
 *  @author   Theo Heußer
 *  @version  17.1.2023 V.221
 *  fuer Spiel17u4 mit Strategie-Spieler-Klassen, 
 *  Gewinnentscheid bei mehreren Spielern 
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
        maxAnzahl = 7;
        spielBeendet = false;
    }

    /** trage einen Spieler in die TNListe ein. 
     * nur moeglich, wenn  spielerliste  noch nicht voll ist. */
    public boolean eintragen(Spieler17u4 teilnehmer)   {
        if(spielerliste.size() >= maxAnzahl) {  
            melde("Es sind schon genug Teilnehmer.\nKeine Teilnahme mehr m\u00F6glich.",
                "WARNUNG!");
            return false;
        } else {
            return spielerliste.add(teilnehmer);   // add liefert ja/nein zurueck         
        }
    }

    /** prueft, ob Spiel zu Ende ist mit der Antwort: ja/nein
     *  sonst nichts; wird u.a. von den Spielern aufgerufen, 
     *  @return  ob Spiel zu Ende ist; Wert von spielBeendet
     */
    public boolean pruefeEnde()  {
        spielBeendet = true;    
        for (Spieler17u4 sp : spielerliste) {       
            if (sp.getStand()<=21 && !sp.isFertig() ) {
                spielBeendet = false; 
            }            
        }      
        return spielBeendet;       
    }

    /** eine Runde spielen lassen 
     *  fuer jeden angemeldeten Spieler  */
    public void spieleEineRunde() {
        for (Spieler17u4 sp : spielerliste) { 
            sp.spieleRunde(); 
        }      

        if (pruefeEnde()) {
            ermittleSieger();     // und reagieren, wenn Spiel zu Ende:
            zeigeSiegerliste();   // die Siegerliste bestimmen und anzeigen        
        }
    }    

    
    // Hilfsroutinen   
    /** die veraenderlichen Werte zuruecksetzen 
     *  der Reihe nach fuer alle Spieler der  spielerliste  */
    public void neuesSpiel() {
        for (Spieler17u4 sp : spielerliste) {       
            sp.setStartwerte();   
        }
    } 

    /** feststellen, wer Sieger ist. 
     *  Dazu wird die Siegerliste erstellt, d.h. die Variable  siegerliste  gefüllt.
     *  Dies erfolgt in zwei Durchgängen.
     *  1. Suchen des Gewinnwertes (= max.Wert bis 21)
     *  2. Eintragen aller Spieler mit diesem Punktstand in die Siegerliste
     *  
     *  es wird NICHTS zurückgegeben      */
    public void ermittleSieger() {
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
     *  gibt die Siegerliste zurück */
    public ArrayList<Spieler17u4> getSiegerliste()    {
        return siegerliste;
    }

    /** 
     *  gibt die Spielerliste zurück */
    public ArrayList<Spieler17u4> getSpielerliste()    {
        return spielerliste;
    }

    /** gibt die Anzahl der Spieler zurück
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
     *  PktZahl 0 = Spieler, der über 21 hinauskam und daher verloren hat.  
     *  PKtZahl -21 = diesen Spieler gibt es nicht; bei 8 Spielern sind die Spieler 9 - 25 nicht vorhanden,
     *  daher werden also die Ellemente zwi[8]..zwi[24] jeweils die PktZahl -21 haben.
     */
    public int[] getAlleSpielstaende()    {
        int[] zwi = new int[25]; //max. 25 Strategiespieler
        int i=0;
        for (i=0; i<25; i++) { zwi[i]= -21;}
        
        i=0; 
        for (Spieler17u4 sp : spielerliste) {
            if (sp.getStand()>21 ) { zwi[i] = 0; }  //Signal für: 'hat verloren'
            else if( sp.isFertig()) { zwi[i] = 100 + sp.getStand(); }
            else { zwi[i] = sp.getStand(); }
            i++;
        }
        
        return zwi;  //Achtung: Was wird hiermit zurückgegeben???
    }

    
    /** gibt die Anzahl der Sieger zurueck:
     *  Vereinbarung: wenn noch keine Entscheidung: -1;  wenn alle verloren haben: 0
     *    sonst die Anzahl der Sieger
     *  @return   Anzahl der Sieger, also:  -1,0,1,2,3,..
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
     *  Vereinbarung: wenn es (noch) keinen Sieger gibt:  0
     *  @return   Punktzahl des/der Sieger   0 oder ..17,18,19,20,21  
     */
    public int getSiegwert()    {
        if (siegerliste.size()>0) {
            return siegerliste.get(0).getStand();
        } else {
            return 0;
        }
    } 

    /** zeige die Spielerliste in einer Dialogbox auf dem Bildschirm an.
     *  als Ersatz ohne GUI-Anbindungen fuer die Anzeige der Listen
     */
    public void zeigeSpielerliste()   {
        String listenText = "";
        // Lesen Sie die naechste Zeile laut !!
        for (Spieler17u4 sp : spielerliste) {       
            listenText += sp.toString()+"\n"; 
        }
        melde(listenText, "Spielerliste");
    }          

    /** gibt die Siegerliste in Dialogbox auf dem Bildschirm aus
     *  als Ersatz ohne GUI-Anbindungen fuer die Anzeige der Listen   */
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

    // -- Hilfsroutinen
    // -- solange nicht in eine GUI eingebunden.
    /** gibt den uebergebenen String text in Dialogbox auf dem Bildschirm 
     *  unter der Ueberschrift titel aus  */
    protected void melde(String text, String titel) {
        JOptionPane.showMessageDialog(null, text, titel, JOptionPane.INFORMATION_MESSAGE);
    }

}


/* Bemerkungen:
 * 
 * kann auch in GUI eingebaut werden; ist dort das Pendant zu Spielfeld.
 * enthaelt auch melde-Routinen, die unabhaengig von einer GUI laufen.
 * 
 * 
 */
