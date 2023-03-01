/**
 * Beschreiben Sie hier die Klasse SLSD2.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class SLSD2 extends Spieler17u4
{
    public static int depth = 0;
    public int mydepth;
    public Sorter gameMaster = null;
    public static int aktuellerStand = 0;
    /**
     * Konstruktor für Objekte der Klasse SLSD2
     */
    public SLSD2()
    {
        super("KCC");
        if(SLSD2.depth == 2 ){
            mydepth = 0;
            SLSD2.depth = 1;
            return;
        }
        mydepth = SLSD2.depth;
        SLSD2.depth++;
    }
    
    public SLSD2(String name){
        super(name);
        if(SLSD2.depth ==2 ){
            mydepth = 0;
            SLSD2.depth = 1;
            return;
        }
        mydepth = SLSD2.depth;
        SLSD2.depth++;
    }

    public void entscheide(){
        if(mydepth != 0){
            fertig = true;
            return;
        } else {
            decideSpielleiter();
            if(this.getStand() >= 21){
                fertig = true;
                SLSD2.aktuellerStand = 0;
            } else {
                SLSD2.aktuellerStand = this.getStand();
                fertig = false;
            }
        }            
    }
    
    public void decideSpielleiter(){
        if(this.gameMaster == null){
            this.gameMaster = new Sorter();
            this.setSpielleiter(gameMaster);
            System.out.print('\u000C');
            System.out.println("SPIEL 17u4 Strategievergleich");
        } else {
    
        }
    }
    
    public class Sorter extends Spielleiter{
        public int blankcounter = 25;
        public Sorter(){
            for(int i = 0;i<blankcounter;i++){
                System.out.println();
            }
        }
        
        @Override
        public int getWurf(){

            int decider = (int) (Math.random()*100000) +1;
            if(decider < 8001){
                return 22;
            }

            if(21 - SLSD2.aktuellerStand >= 6){
                return 6;
            } else {
                return ( 21 - SLSD2.aktuellerStand);
            }
 
        }
    }
}
