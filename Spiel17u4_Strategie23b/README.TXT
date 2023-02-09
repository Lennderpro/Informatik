TITEL:  Spiel17u4_Strategie23b    aus   Spiel17u4_Strategie23a
DATUM:  3.2.2023 ; 23.1.2023
AUTOR:  Theo Heußer
ZWECK:  Mehrere Strategien gegeneinander spielen lassen. 
        Zwischenschritt zum Projekt, um den Kursweltmeister zu bestimmen.
        

Änderungen:
-- Spielleiter etwas verändert, so dass er
   - ein ganzes Spiel spielen lassen kann 
   - bei Erzeugung eine starre Spielerliste anlegt
   - das Würfeln jedes Spielers übernimmt/überwacht 
   - Spiele gemäß der Wettkampfideen protokollieren kann
   - vielen Spiele durchführen und die Gesamtsieger auf eine Art protokollieren kann

   
-- Die abstrakte Klasse  Spieler17u4  wurde so verändert, 
   - dass fast alle Methoden _final_ sind, also unveränderbar in den Unterklassen, 
     z.B. um Schummelversuche zu verhindern
   - dass sie eine Var.  anzGewinne hat, in der die Gewinne bei vielen Spielen gezählt werden
   - sowie zugehörige Methoden erhoeheGewinnzahl(), getAnzGewinne(), resetAnzGewinne()
   - dass toString() optisch etwas verbessert wurde
   - der Spieler nicht mehr selbst würfeln kann
   - in spieleRunde() das Entscheiden nur noch erfolgt, wenn es sinnvoll ist
   
   
weiterhin:   
   Die Unterklassen müssen _nur_ die Methode  entscheide()  ausprogrammieren
   nach der gewünschten eigenen Idee bzw. Strategie.


   
   

Beispielabläufe einzelner Spiele: (3.2.2023)

Spielleiter spiellei1 = new Spielleiter();
spiellei1.aaaStratVergleich();
SPIEL 17u4 - Strategievergleich

 So oft sind diese Spieler bei  55000 Spielen
  unter den Gewinnern:
    demo1 :   17150
    Str21 :    6314
    Stur1 :   11173
    Stur2 :   10772
   Str171 :   14433
    Str22 :    6211
    Str23 :    6434
   Str172 :   14538
    demo2 :   17043




--- Entwicklung der Programme: ---

Spiel17u4_Strategie23b
  aus
Spiel17u4_Strategie23a
  aus   
Spielleiter17u4Bv02  (erneuert 2023)
  aus 
Spielleiter17u4      ausgelassen
  aus 
Spielleiter17u4_roh  
