/**
 * Cheat-Strategie für Projekt: Spieler17u4_Strategie23WM1
 * 
 * @author David Siekacz, Lenn Stoelzl
 * @version 01.03.2023
 */
public class SLSD2 extends Spieler17u4
{
    public static int depth = 0;
    public int mydepth;
    public Sorter gameMaster = null;
    public static int aktuellerStand = 0;
    /**
     * Konstruktor fÃ¼r Objekte der Klasse SLSD2
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
            System.out.println("\nSPIEL 17u4 Strategievergleich");
        } else {
    
        }
    }
    
    public class Sorter extends Spielleiter{
        public Sorter(){   
        }
        
        @Override
        public int getWurf(){
            int decider = (int) (Math.random()*100000) +1;
            if(decider < 14001){
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
