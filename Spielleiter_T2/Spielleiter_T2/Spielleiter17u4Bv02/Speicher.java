import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Die Test-Klasse Speicher
 *  @author   PGW Theo Heußer
 *  @version  17.12.2021
 */

public class Speicher
{
    private Stratt17 strat17;
    private Strat2Spieler strat2Sp1;
    private StratSpieler st1;
    private Spielleiter chef1;

    /**
     * Konstruktor fuer die Test-Klasse Speicher
     */
    public Speicher() {
    }

    /**
     *  Setzt das Testgeruest fuer den Test.
     *  Wird vor jeder Testfall-Methode aufgerufen.
     */
    @Before
    public void setUp() {
        strat17   = new Stratt17();
        strat2Sp1 = new Strat2Spieler();
        st1   = new StratSpieler();
        chef1 = new Spielleiter();
        strat17.anmeldenBeim(chef1);
        strat2Sp1.anmeldenBeim(chef1);
        st1.anmeldenBeim(chef1);
        chef1.spieleEineRunde();
        chef1.spieleEineRunde();
        chef1.spieleEineRunde();
        chef1.spieleEineRunde();
    }

    /**
     * Gibt das Testgeruest wieder frei.
     * Wird nach jeder Testfall-Methode aufgerufen.
     */
    @After
    public void tearDown()  {
    }
}

/* HINWEIS:
 * 
 * dient nur als Speicher zur Wiederherstellung, 
 * dient  NICHT  zum Testen!!
 * 
 */ 