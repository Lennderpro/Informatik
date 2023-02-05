// Darstellung ab BJ 3.0.5 bzgl. der Test-Annotationen

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Die JUnit-Testklasse: SpielTest
 *
 * @author  Theo Heußer
 * @version 16.1.2023/ 2018,17,15,13,11  BJ V. >= 3.0.5
 */
public class SpielTest
{
    private Spielleiter chef;
    private Spieler17u4 sp1;
    private Spieler17u4 sp2;
    private Spieler17u4 sp3;
    private Spieler17u4 sp4;    
    
    /**
     * Standard-Konstruktor fuer die Testklasse: SpielTest
     */
    public SpielTest() {
    }

    /**
     * Herstellen des Testgeruestes
     * Wird vor jeder Testmethode aufgerufen 
     */
    @Before
    public void setUp() {
        chef = new Spielleiter();
        sp1 = new Spieler17u4("a");
        sp2 = new Spieler17u4("bb");
        sp3 = new Spieler17u4("ccc");
        sp1.anmeldenBeim(chef);
        sp2.anmeldenBeim(chef);
        sp3.anmeldenBeim(chef);
        sp4 = new Spieler17u4("Dulli");
        sp4.anmeldenBeim(chef);
        sp1.setStand(18);
        sp2.setStand(19);
        sp3.setStand(22);
        sp4.setStand(19);
        sp4.aufhoeren();
    }

    /**
     * Aufräumarbeiten nach Testende
     * Hier nichts notwendig.
     */
    @After
    public void tearDown() {
    }
    
    
    // --- einzelne Tests ---
    /** prueft, ob ein weiterer Eintrag die Anzahl der Spieler
     *  in der Variable  spielerliste  um Eins erhoeht.
     *  (in der durch  setUp()  gegebenen Situation!)
     */
    @Test
    public void spielerEintragen()  {
        int n = chef.getSpielerzahl();
        Spieler17u4 neu = new Spieler17u4();
        assertEquals(true, neu.anmeldenBeim(chef));
        int m = chef.getSpielerzahl();
        assertEquals(1, m-n);
    }   
    
    /** prueft, dass nicht bereits nach setUp() 
     *  das Spielende erreicht ist */
    @Test
    public void nochKeinEnde() {
        assertEquals(false, chef.pruefeEnde());
        assertTrue(chef.getSiegerzahl()<0);  // statt:  == -1
    }
    
    // --- mehrere Situationen fuer Spielende-Pruefung ---
    /** 
     *  prueft einen Fall mit einem Sieger */
    @Test
    public void einSieger()  {
        sp2.setStand(18);
        sp1.aufhoeren();
        sp2.aufhoeren(); // damit sind alle zu Ende !!
        //18-18-22-19
        assertEquals(true, chef.pruefeEnde());
        chef.ermittleSieger();
        //assertTrue(chef.getSiegerzahl()>0);  //unnoetig, denn naechste Anw. ist genauer
        assertEquals(1, chef.getSiegerzahl());
        assertEquals(19, chef.getSiegwert());
    }
    
    /** 
     *  prueft einen weiteren Fall mit einem Sieger */
    @Test
    public void pruefeEinSieger()  {
        sp1.setStand(21);
        sp1.aufhoeren();
        sp2.aufhoeren(); // damit sind alle zu Ende !!
        //21-19-22-19
        assertEquals(true, chef.pruefeEnde());
        chef.ermittleSieger();
        assertEquals(1, chef.getSiegerzahl());
        assertEquals(21, chef.getSiegwert());
    }
    
    /** prueft einen Fall, in dem 
     *  noch nicht alle beendet haben oder ueberkauft sind */
    @Test
    public void nichtBeendet(){
        sp1.aufhoeren(); // sp2 noch nicht zu Ende !!
        //18-19-22-19
        assertEquals(false, chef.pruefeEnde());
        chef.ermittleSieger(); 
        assertTrue(chef.getSiegerzahl()== -1); 
    }
    
    /** 
     *  prueft einen Fall mit zwei Siegern */
    @Test
    public void zweiSieger() {
        sp1.aufhoeren();
        sp2.aufhoeren(); // damit sind alle zu Ende bzw. ueber 21 !!
        //18-19-22-19
        assertEquals(true, chef.pruefeEnde());
        chef.ermittleSieger();
        assertEquals(2, chef.getSiegerzahl());  // Es muss zwei Sieger geben: 19-19
        assertEquals(19, chef.getSiegwert());   // Als Siegwert muss 19 gemeldet werden.   
    }
    
    /** 
     *  prueft einen weiteren Fall mit zwei Siegern */
    @Test
    public void zweiSieger2() {
        sp1.setStand(23);
        sp2.aufhoeren(); // damit sind alle zu Ende !!
        //23-19-22-19
        assertEquals(true, chef.pruefeEnde());
        chef.ermittleSieger();
        assertEquals(2, chef.getSiegerzahl());
        assertEquals(19, chef.getSiegwert());       
    }
    
    /** 
     *  prueft einen Fall mit drei Siegern */
    @Test
    public void dreiSieger() {
        sp1.setStand(19);
        sp1.aufhoeren();
        sp2.aufhoeren(); // damit sind alle zu Ende !!
        //19-19-22-19
        assertEquals(true, chef.pruefeEnde());
        chef.ermittleSieger();
        assertEquals(3, chef.getSiegerzahl());
        assertEquals(19, chef.getSiegwert());    
    }
    
    /** 
     *  prueft den Fall, dass alle ueber 21 haben */
    @Test
    public void keinSieger() {
        sp1.setStand(23);
        sp2.setStand(22); 
        sp3.setStand(25);
        sp4.setStand(25); // damit sind alle zu Ende !!
        //23-22-25-25
        assertEquals(true, chef.pruefeEnde());
        chef.ermittleSieger();
        assertTrue(chef.getSiegerzahl()==0);
    } 

    /** 
     *  prueft einen weiteren Fall, in dem alle ueber 21 haben  */
    @Test
    public void keinSieger2() {
        sp1.aufhoeren();   // sp1 in einen unerlaubten Fall zwingen
        sp1.setStand(23);  // und auch dies testen
        sp2.setStand(25);
        sp4.setStand(25);  // damit sind alle zu Ende !!
        //23-25-22-25
        assertEquals(true, chef.pruefeEnde());
        chef.ermittleSieger();
        assertTrue(chef.getSiegerzahl()==0);
    }
    
    /** prueft den Fall: ein TN bis 21, alle anderen darueber;
     *  und der eine bis 21 hat beendet */
    @Test
    public void einSieger3() {
        sp1.setStand(23);
        sp2.setStand(22); 
        sp3.setStand(25);
        sp4.setStand(21);  // damit sind alle zu Ende !!
        //23-22-25-21
        assertEquals(true, chef.pruefeEnde());
        chef.ermittleSieger();
        assertTrue(chef.getSiegerzahl()==1);
    }  
    
    /** prueft den Fall: ein TN bis 21, alle anderen darueber;
     *  und der eine bis 21 spielt noch */
    @Test
    public void keinSieger3() {
        sp1.setStand(21);  // noch nicht zu Ende !!
        sp2.setStand(22); 
        sp3.setStand(25);
        sp4.setStand(23);  
        //21-22-25-23
        assertEquals(false, chef.pruefeEnde());
        chef.ermittleSieger();
        assertTrue(chef.getSiegerzahl()==-1);
    } 
    
}

