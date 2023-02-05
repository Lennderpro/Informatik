TITEL:  Spiel17u4_Strategie23a    aus   Spielleiter17u4Bv02
DATUM:  1.2.2023 / 2.2.22/25.1.2022
AUTOR:  Theo Heußer
ZWECK:  Mehrere Strategien gegeneinander spielen lassen. 
        Vorlage, um eigene Spieler-Klasse einzubauen und zu erproben
        Vergleich verschiedenenr Konstruktoren in den Unterklassen!
        

HINWEISE:
-- Spielleiter so erweitert, dass er ein ganzes Spiel spielen lassen kann 
   in der Methode  demoSpiel() , die bisher aber nur wenige Spieler weniger Strategien 
   vergleicht/miteinbezieht.  
   Der Spielleiter übernimmt/überwacht das Würfeln,
   aber er verhindert bewusst noch nicht Schummelversuche.   

-- Die vier Strategieklassen dienen als Vorlage zur Implementierung eigener Strategien.

-- Die Klasse  Spieler17u4  wurde hier so angepasst, dass
   - verschiedene Strategieklassen als Unterklasse erzeugt und genutzt werden __müssen__
   - kein Objekt der Oberklasse Spieler17u4 erzeugt werden kann, sondern nur aus einer Unterklasse,
     die die Strategie beschreibt.
   Die Unterklasse müssen _nur_ die Methode  entscheide()  ausprogrammieren
   nach ihrer eigenen Idee bzw. Strategie. 

-- Die (Test-)Klasse Speicher wurde entfernt;
   sie diente nur der schnellen Wiederherstellung einer Spielsituation,
   in der 3 Mitspieler vorliegen, die jeweils 4 Runden gespielt haben.
   Das ist nun kaum noch nötig, da demoSpiel() ein Spiel aufbaut und durchführt. 
   
-- Aufgaben bzgl. der Konstruktoren in den Unterklassen  
   in den Bemerkungen zu Strat2Spieler 

    

Beispielabläufe einzelner Spiele: (19.1.2023)

Spielleiter spiellei1 = new Spielleiter();
spiellei1.demoSpiel();
SPIEL 17u4

 akt.Stand:
Sp0: 19: fertig
Sp1: 18: fertig
Sp2: 21: fertig
Sp3: 23: verloren
Sp4: 20: fertig
Sp5: 21: fertig
Test_17: 22: verloren

Gewonnen haben: Sp2, Sp5 mit je 21  wird in Extra-Fenster gemeldet.



spiellei1.demoSpiel();
SPIEL 17u4

 akt.Stand:
Sp0: 18: fertig
Sp1: 19: fertig
Sp2: 19: fertig
Sp3: 21: fertig
Sp4: 19: fertig
Sp5: 26: verloren
Test_17: 23: verloren

Gewonnen haben(?): Sp3 mit 21 Pkt.   wird in Extra-Fenster gemeldet.





--- Hinweise: ---

Vorläufer der Programme:
 
Spiel17u4_Strategie23a
  aus   
Spielleiter17u4Bv02  (erneuert 2023)
  aus 
Spielleiter17u4      ausgelassen
  aus 
Spielleiter17u4_roh  
