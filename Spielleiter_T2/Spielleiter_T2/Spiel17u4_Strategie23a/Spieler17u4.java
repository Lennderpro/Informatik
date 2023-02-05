/** Klasse   Spieler17u4  
 *  Projekt: Spiel17u4_Strategie23    (V221ff)
 * @author   Theo Heußer 
 * @version  23.1.2023 / 12.1.22/15.12.21 / 17.12.16 
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

    
    /** Konstruktor zur Erzeugung einzelner Spieler(=Objekte)
     *  für Spiel17u4 mit Festlegung der Anfangswerte */
    public Spieler17u4(String neuName)    { 
        name = neuName;
        setStartwerte();
        chef = null;
    }

    /** Schnell-Konstruktor für Objekte der Klasse Spieler17u4, der
     *  keine Namenseingabe verlangt. Dies führt auf den allgemeinen 
     *  Konstruktor zurück - ein übliches, sinnvolles Vorgehen */
    public Spieler17u4() {
        this("N.N.");
    }

    // --- Methoden -----    
    /** bei einem Spielleiter anmelden. 
     *  @param schiri    der Spielleiter, bei dem man sich anmeldet 
     *  @return   ob es geklappt hat   */
    public boolean anmeldenBeim(Spielleiter schiri) {
        boolean hatGeklappt = false;
        hatGeklappt = schiri.eintragen(this);// dem Spielleiter die eigenen Daten uebermitteln
        if (hatGeklappt) {
            chef = schiri;
        }
        return hatGeklappt;
    }

    /** den Wert des aktuellen Wurfes nennen
     *  @return  Wert des letzten Wurfes    */
    public int getWurf() {
        return aktWurf;  
    }

    /** den Wert des aktuellen Standes nennen  
     *  @return  Wert des aktuellen Standes    */
    public int getStand() {
        return aktStand;  
    }

    /** den Wert des aktuellen Standes nennen  
     *  @return  Wert des aktuellen Standes    */
    public int getAnzWuerfe() {                   // <-- 2.      
        return anzWuerfe;  
    }

    /** der Spieler meldet, ob er von sich aus aufgehoert hat 
     *  @return  ob das Wuerfeln beendet wurde oder noch nicht 
     */
    public boolean isFertig()  {
        return fertig;
    }   

    /** der Spieler meldet, ob er verloren hat, weil er 21 übertraf
     *  @return  ob das Spiel verloren ist 
     */
    public boolean hatVerloren()  {            // <-- 4.
        return verloren;
    }
    
    /** der Benutzer von außen legt fest, dass er nicht mehr weiterwuerfelt, dass er fertig ist!
     *  Das autonome Spieler-Objekt erledigt dies in der  entscheide()-Methode. 
     *  Daher ist  aufhoeren()  für diese Spieler nicht mehr noetig! */
    /*public void aufhoeren()  {
        fertig = true;
    }*/
    /** Die  >>> entscheidende <<< , abstrakte Methode des Spielers.
     *  Sie >> muss << von den einzelnen Strategieklassen ausformuliert werden!! 
     *  Hier wird nur ihr Name und Rückgabetyp festgelegt. Es gibt KEINEN Methodenkörper.
     *  Man sagt: "sie wird in den Unterklassen überschrieben".       */
    public abstract void entscheide();
        /*# ###################
         *  Schlüsselmethode für die Strategien
         *  nur darin unterscheiden sich die verschiedenen Spielertypen!! 
         *  ###################      */      
    

    /** holt vom Spielleiter die Spielstände aller Spieler,
     *  um entsprechend reagieren zu können 
     *  Sie werden in einer Reihung/Array geliefert;             // <-- 3.     <<<<<<<<<<<<<
     */
    public int[] holeAlleSpielstaende()  {
        return chef.getAlleSpielstaende();     
    }

    /** eine Runde spielen; d.h.:  Wuerfeln mit 6-Wuerfel, 
     *  Wurfergebnis merken, Spielstand erhoehen und merken, 
     *  vorher kontrollieren, ob noch gespielt werden darf  */
    public void spieleRunde() {
        if (aktStand > 21) {verloren = true; return;} //Abbruch; nichts mehr!!
        if (!fertig) {      //<-- 1.
            aktWurf  =  wuerfle();   // Wurfergebnis unter aktWurf merken
            aktStand += aktWurf;     // aktStand erhoehen um den aktWurf 
            anzWuerfe++;
        }
        entscheide();
    }    

    /** interne Routine: das aktuelle Wurfergebnis des W6 zurückmelden;
     *  der Spielleiter liefert dieses Ergebnis !! */
    private int wuerfle() {
        return chef.getWurf();
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
    public void setStartwerte() {        
        aktStand = 0; 
        aktWurf  = 0;
        anzWuerfe = 0;
        fertig    = false;
        verloren  = false;
    }

    /** Ausgabe der aktuellen Daten des dieses Spielers in einen String
     *  @return  Name, aktueller Stand und Spielstatus
     */
    public String toString() {               // <-- 5.
        String status = "im Spiel";
        if (aktStand > 21) { 
            status = "verloren";    // ueberkauft im Kartenspiel
        }else if (isFertig()) { 
            status = "fertig"; 
        }
        return getName()+": "+getStand()+": "+status ;
    }    

}

/* Bemerkungen:
 * 
 * Dies ist nicht die endgültige Version der Klassen Spieler sowie Spielleiter 
 * für unsere Kurs-Weltmeisterschaft. 
 * Evtl. müssen ja noch Veränderungen vorgenommen werden, um Fehler auszubessern 
 * oder Schummeleien zu verhindern.
 * 
 * Vorsicht: 
 * im Quelltext heißt es:   isFertig()    und nicht  istFertig(). 
 * Ja, das ist eine Deutsch-englisch Kuddelmuddel wie bei  getStand().
 *
 *
 *
 * 23.1.2023: 
 * Das Würfeln überwacht/übernimmt nun der Spielleiter, so dass die Spieler hier nicht schummeln. 
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
 * 5. Das sollte optisch etwas aufgebessert werden.
 *    Informieren Sie sich dazu über  String.format("%....",...)  
 *    Ausgaben erfolgen so sauber tabellarisch mit jeweils gleicher Stellenbreite 
 *    aber Achtung: Schriftfamilie!
 *    
 */     