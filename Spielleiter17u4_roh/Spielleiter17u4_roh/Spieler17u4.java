/** Klasse   Spieler17u4  
 *  Projekt: Spielleiter_roh
 * @author  Theo Heußer
 * @version 17.4.16ff
 */

public class Spieler17u4
{
    // Objektvariable; 
    // sie beschreiben Eigenschaften eines jeden Spielers dieses Spiels
    private String name;        // merkt sich den Namen des Spielers
    private int aktStand;       // merkt sich ...
    private int aktWurf;        // merkt sich ...
    private boolean fertig;     // merkt sich, ob ...
    private Spielleiter chef;   // merkt sich, wer ...
    
    
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
        chef = schiri; 
        chef.eintragen(this);  // dem Spielleiter die eigenen Daten uebermitteln
        return (chef != null);
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
    
    /** der Spieler meldet, ob er aufgehört hat 
     *  @return  ob das Würfeln beendet wurde oder noch nicht
     */
    public boolean isFertig()  {
        return fertig;
    }   
  
    
    /** der Spieler legt fest, dass er nicht mehr weiterwürfelt, sondern
     *  schon aufgehört hat; das wird in einer Eigenschaft vermerkt,
     *  der Wert einer Variable wird verändert. */
    public void aufhoeren()  {
        fertig = true;
        chef.pruefeEnde();   // Spielleiter auffordern, Spielende zu pruefen
                             // besser wie bei spieleRunde() .   Auslagern?
    }
 
    /** eine Runde spielen; d.h.:  Würfeln mit 6-Wuerfel, 
     *  Wurfergebnis merken, Spielstand erhoehen und merken, 
     *  vorher kontrollieren, ob noch gespielt werden darf  */
    public void spieleRunde() {
       if ( aktStand <= 21 && !fertig) {
           aktWurf  =  wuerfle();   // Wurfergebnis unter aktWurf merken
           aktStand += aktWurf;     // aktStand erhoehen um den aktWurf 
       }
       // zwangsweise den Spielleiter auffordern, das Spielende zu pruefen
       if (chef.pruefeEnde()==true) {
           // und reagieren, wenn Spiel zu Ende:
           chef.ermittleSieger();
           // die Siegerliste bestimmen und anzeigen
           //chef.zeigeSiegerliste();           
       }
    }    

    /** interne Routine: das Wurfergebnis eines 
     *  Wurfes mit einem normalen W6 zurückmelden */
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
        fertig = false;
    }
    
    /** Ausgabe der Daten in einen String
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
    
    /**  nicht im Spiel noetig, nur zum Testen! 
     *   Das ist gefaehrlich, kann ausgenutzt werden.
     *   Gesamtstand gewaltsam von Testrahmen festsetzen  */
    public void setStand(int wert) {
        aktStand = wert;
    }
   
}

/* Bemerkungen:
 * 0.  Es ist abzuwägen, ob der Spielleiter bereits im Konstruktor angeben werden muss!
 * 1.  nur wenn der Stand bisher nicht über 21 ist UND wenn der 
 *     Spieler nicht beendet hat, kann er weiter würfeln.  
 *     
 */     