/** Klasse   Spieler17u4  
 *  Projekt: Spielleiter 17u4Bv02  (V220ff)
 * @author   Theo Heußer 
 * @version  19.1.2023 / 15.12.21 /18.12.18
 */

public class Spieler17u4
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

    
    /** Konstruktor zur Erzeugung einzelner Spieler fuer 17u4 
     *  Festlegung der Anfangswerte */
    public Spieler17u4(String neuName)    { 
        name = neuName;
        setStartwerte();
        chef = null;
    }

    /** Schnell-Konstruktor fuer Objekte der Klasse Spieler17u4,
     *  der keine Namenseingabe verlangt. */
    public Spieler17u4() {
        this("N.N.");
    }

    // --- Methoden -----    
    /** bei einem Spielleiter anmelden. 
     *  @param schiri    der Spielleiter, bei dem man sich anmeldet 
     *  @return  ob es geklappt hat */
    public boolean anmeldenBeim(Spielleiter schiri) {
        boolean hatGeklappt = false;
        hatGeklappt = schiri.eintragen(this);// dem Spielleiter die eigenen Daten uebermitteln
        if (hatGeklappt) {
            chef = schiri;
        }
        return (hatGeklappt);
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
    public boolean hatVerloren()  {                 // <-- 4.
        return fertig;
    }
    
    /** der Spieler legt fest, dass er nicht mehr weiterwuerfelt, sondern
     *  schon aufgehoert hat; das wird in einer Eigenschaft vermerkt,
     *  der Wert einer Variable wird veraendert. */
    public void aufhoeren()  {
        fertig = true;
    }

    /** Die  >>> entscheidende <<<  Methode des Spielers
     *  wird von den einzelnen Strategieklassen ausformuliert, man sagt: "überschrieben".
     *  Hier macht sie gar nichts, hoert also von sich aus nie auf zu würfeln!!  */
    public void entscheide()  {                             // <-- 5.     <<<<
        /*# ###################
         *  Schlüsselmethode für die Strategien;
         *  nur darin unterscheiden sich die verschiedenen Spielertypen!! 
         *  ###################      */      
    }

    /** holt vom Spielleiter die Spielstände aller Spieler,
     *  um entsprechend reagieren zu können 
     *  Sie werden in einer Reihung/Array geliefert;             // <-- 3.     <<<<<<<<<<<<<
     */
    public int[] holeAlleSpielstaende()  {
        return chef.getAlleSpielstaende();     
    }

    /** eine Runde spielen; d.h.:  
     *  a) kontrollieren, ob noch gespielt werden darf 
     *  b) Wuerfeln mit 6-Wuerfel, Wurfergebnis merken, Spielstand erhoehen und merken,
     *  c) entscheiden, ob weitergemacht wird oder nicht  */
    public void spieleRunde() {
        if (aktStand > 21) {verloren = true; return;} //Abbruch; nichts mehr!!
        if (!fertig) {      //<-- 1.
            aktWurf  =  wuerfle();   // Wurfergebnis unter aktWurf merken
            aktStand += aktWurf;     // aktStand erhoehen um den aktWurf 
            anzWuerfe++;
        }
        entscheide();             // ?? hier ??
    }

    /** interne Routine: das Wurfergebnis eines 
     *  Wurfes mit einem normalen W6 zurueckmelden */
    private int wuerfle() {
        return 1 + (int) (Math.random()*6);
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
    public String toString() {        
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
 * 
 * 0.  Vorsicht: isFertig() und hatVerloren()  sind noch nicht endgültig. 
 *    
 *    
 * 1.  -- Zuerst die verwendete Strategie, also die Entscheidungen des Strategie-Spielers 
 *     StratSpieler  erarbeiten durch den Quelltext der Klasse StratSpieler.
 *  
 * 
 * 2.  Die Klasse  Spieler17u4  wurde erweitert um die Eigenschaft  anzWuerfe , 
 *     weil das vllt. einige für ihre Strategie verwenden wollen.
 * 
 * 
 *
 * 3.  Der Spielleiter liefert Angaben zum Spielstand aller teilnehmenden Spieler
 *     und sagt dabei auch, ob die Spieler noch aktiv sind oder beendet oder verloren haben.
 *     dies sieht z.B. so aus:   15 16 118 20 0 18 0 117 -21 -21 -21 .... -21
 *     Er liefert immer 25 Werte, kann also höchstens 25 Spieler betreuen.
 *     Solange die Werte positiv sind inkl. Null, gibt es diese Spieler.
 *     Im Beispiel nehmen also 8 Spieler teil.
 *     PKtZahl von 1..21 = aktiver, noch weiter-würfelnder Spieler mit eben dieser Punktzahl 
 *     PktZahl 101..121  = Spieler, der aufgehört hat; mit Pktzahl 1xx-100,
 *        also 118 steht für:  Spieler ist fertig und hat 18 Punkte erreicht.
 *     PktZahl 0 = Spieler, der über 21 hinauskam und daher verloren hat.  
 *              (Sollte man dafür 100 als Kodierung verwenden?)
 *     PktZahl -21 signalisiert, dass es diesen Spieler im aktuellen Spiel nicht gibt. 
 *     Jeder am Spiel beteiligte Spieler hat einen Stand/Eintrag von 0..21 oder 101..121.
 *     
 *     Die Spielstände werden in einer Reihung[] mit Elementtyp  int  geliefert.
 *     In der Strategieklasse  Stratt17  wird dies beispielhaft verwendet.
 *   
 *       
 * 4.  Hier ist Arbeit für Sie! 
 *       isFertig() sowie hatVerloren()  dieser Oberklasse Spieler17u4 sind noch nicht korrekt; 
 *     und damit auch das Spielende.
 *     Da passt einiges noch nicht. Korrigieren Sie!
 *     Hat dies Auswirkungen auf den Spielleiter??
 *    
 *    
 * 5.  Die Methode  entscheide()  brauchen wir hier gar nicht. Wir haben KEINE Strategie 
 *     festgelegt. Wir wollen ja gar nicht, dass so ein Spieler spielt. 
 *     Das werden wir ändern, indem wir dafür sorgen, dass KEIN Objekt dieser (Ober-)Klasse 
 *     Spieler17u4 erzeugt werden kann.
 *     vgl. Folgeprojekt:  Spiel17u4_Strategie23a
 *     
 */     