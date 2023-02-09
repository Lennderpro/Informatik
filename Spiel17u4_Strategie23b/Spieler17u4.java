/** Klasse   Spieler17u4  
 *  Projekt: Spiel17u4_Strategie23b    (V221ff)
 * @author   Theo Heußer 
 * @version  3.2.2023 / 23.1.2023
 */

public abstract class Spieler17u4
{
    // Objektvariable; 
    // sie beschreiben Eigenschaften eines jeden Spielers dieses Spiels
    protected String name;        // merkt sich den Namen des Spielers
    protected int aktStand;       // merkt sich ...
    protected int aktWurf;        // merkt sich ...
    protected int anzWuerfe;      // merkt sich ...
    protected boolean fertig;     // merkt sich, ob ...
    protected boolean verloren;   // merkt sich, ob Spieler verloren hat, weil er über 21 kam
    private Spielleiter chef;     // merkt sich, wer ...
    private int anzGewinne;       // für die WM!! merkt sich, wie oft ... 
    
    
    /** Konstruktor zur Erzeugung einzelner Spieler(=Objekte)
     *  für Spiel17u4 mit Festlegung der Anfangswerte */
    public Spieler17u4(String neuName)    { 
        name = neuName;
        setStartwerte();
        resetAnzGewinne();
        chef = null;
    }

    /** Schnell-Konstruktor für Objekte der Klasse Spieler17u4, der
     *  keine Namenseingabe verlangt. Dies führt auf den allgemeinen 
     *  Konstruktor zurück - ein übliches, sinnvolles Vorgehen */
    public Spieler17u4() {
        this("N.N.");
    }

    // --- Methoden -----    
    /** verbindet den Spieler mit seinem Spielleiter; 
     *  ersetzt das Anmelden beim Spielleiter */
    public final void setSpielleiter(Spielleiter neuChef) {
        chef = neuChef;
    }
    /* ### daher ist boolean anmeldenBeim(Spielleiter schiri)   nicht mehr nötig! ###  */
    
    
    /** den Wert des aktuellen Wurfes nennen
     *  @return  Wert des letzten Wurfes    */
    public final int getWurf() {
        return aktWurf;  
    }

    /** den Wert des aktuellen Standes nennen  
     *  @return  Wert des aktuellen Standes    */
    public final int getStand() {
        return aktStand;  
    }

    /** den Wert des aktuellen Standes nennen  
     *  @return  Wert des aktuellen Standes    */
    public int getAnzWuerfe() {                   // <-- 2.      
        return anzWuerfe;  
    }

    /** der Spieler meldet, ob er von sich aus aufgehört hat
     *  @return  ob das Würfeln beendet wurde oder noch nicht 
     */
    public final boolean isFertig()  {
        return fertig;
    }   

    /** der Spieler meldet, ob er verloren hat, weil er 21 übertraf
     *  @return  ob das Spiel verloren ist 
     */
    public final boolean hatVerloren()  {            // <-- 4.
        return verloren;
    }
    
    /** der Benutzer von außen legt fest, dass er nicht mehr weiterwürfelt, dass er fertig ist!
     *  Das autonome Spieler-Objekt erledigt dies in der  entscheide()-Methode. 
     *  Daher ist  aufhoeren()  für diese Spieler nicht mehr nötig! */
    //public void aufhoeren()  { fertig = true; }
     
    /** Die  >>> entscheidende <<< , abstrakte Methode des Spielers.
     *  Sie >>muss<< von den einzelnen Strategieklassen ausformuliert werden!! 
     *  man sagt: "sie wird dort überschrieben".
     *  Hierin entscheidet der Spieler, ob er weitermacht oder nicht!   */
    public abstract void entscheide();
        /*# ###################
         *  Schlüsselmethode für die Strategien
         *  nur darin unterscheiden sich die verschiedenen Spielertypen!! 
         *  ###################      */      
    

    /** zähle die Gewinne dieses Spielers.
     */ 
    public final void erhoeheGewinnzahl() {
        anzGewinne++;
    }
    /** gib die Anzahl der Gewinne dieses Spielers zurück
     */ 
    public final int getAnzGewinne() {
        return anzGewinne;
    }    
    /**  die Anzahl der Gewinne dieses Spielers zurücksetzen
     */
    public final void resetAnzGewinne() {        
        anzGewinne = 0; 
    }
    
    
    /** holt vom Spielleiter die Spielstände aller Spieler,
     *  um entsprechend reagieren zu können 
     *  Sie werden in einer Reihung/Array geliefert;             // <-- 3.     <<<<<<<<<<<<<
     */
    public final int[] holeAlleSpielstaende()  {
        return chef.getAlleSpielstaende();     
    }

    /** eine Runde spielen; d.h.:  Würfeln mit W6, 
     *  Wurfergebnis vom Spielleiter holen und merken, Spielstand erhöhen und merken, 
     *  vorher kontrollieren, ob noch gespielt werden darf  */
    public final void spieleRunde() {
        if (aktStand > 21) {verloren = true; return;} //Abbruch; nichts mehr!!
        if (!fertig) {      //<-- 1.
            aktWurf  = chef.getWurf();   // Wurfergebnis merken; Spielleiter liefert das Ergebnis
            aktStand += aktWurf;     // aktStand erhöhen um den aktWurf 
            anzWuerfe++;
            entscheide();       // ob man weitermacht oder nicht
        }       
    }   

    /** den Spielernamen zurueckmelden
     *  @return  Spielername       */
    public String getName() {
        return name;  
    }

    /** den Spielernamen neu festsetzen 
     *  Vorsicht: noch ohne Kontrollen !!   */
    public void setName(String neuName) {
        name = neuName;  
    }       

    /**  Startwerte aller veraenderlichen Eigenschaften festsetzen, 
     *   damit nochmal gespielt werden kann */
    public final void setStartwerte() {        
        aktStand = 0; 
        aktWurf  = 0;
        anzWuerfe = 0;
        fertig    = false;
        verloren  = false;
    }

    /** Ausgabe der aktuellen Daten des dieses Spielers
     *  in einen formatierten String
     *  @return  Name, aktueller Stand und Spielstatus
     */
    public final String toString() {        //<-- 5.
        String status = "im Spiel";
        if (aktStand > 21) { 
            status = "verloren";    // ueberkauft im Kartenspiel
        }else if (isFertig()) { 
            status = "fertig"; 
        }
        return String.format("%8s : %3d : %8s", getName(), getStand(), status) ;
    }    

    
}

/* Bemerkungen:
 * 
 * Dies ist immer noch nicht die endgültige Version der Klassen Spieler sowie 
 * Spielleiter für unsere Kurs-Weltmeisterschaft.
 * Evtl. müssen weitere Veränderungen vorgenommen werden, um Fehler auszubessern 
 * oder Schummeleien zu verhindern.
 * 
 * Vorsicht: 
 * im Quelltext heißt es:   isFertig()    und nicht  istFertig(). 
 * Ja, das ist eine Deutsch-englisch Kuddelmuddel wie bei  getStand().
 *
 *
 *
 * 23.1.2023: 
 * Das Würfeln überwacht/übernimmt nun der Spielleiter, so dass der Spieler hier nicht schummeln. 
 *  
 * 
 *
 * 1.-3.  siehe  Spielleiter17u4Bv02
 *    vor allem die Kodierung der Spielstände aller Spieler!!
 *   
 *    
 * 4. Das Spielende kann auf zwei Arten erreicht werden:
 *    selbst bestimmt oder zwangsweise wegen Überschreiten der 21.
 *    Ist das nun durch  istFertig()  sowie  hatVerloren()  korrekt dargestellt??
 *    Hat dies Auswirkungen auf den Spielleiter ?
 *    
 * 
 * 5. optisch etwas aufgebessert gegenüber ..23a.  
 *    Ausgaben erfolgen sauber tabellarisch mit jeweils gleicher Stellenbreite 
 *    mittels:  String.format()...   
 *    aber Achtung: Schriftfamilie! Diese macht evtl. bündige Tabellen kaputt.
 *    
 *    
 */     