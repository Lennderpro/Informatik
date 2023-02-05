TITEL:  Spielleiter17u4Bv02   
DATUM:  20.1.2023/12.1.2022/17.12.2021
AUTOR:  Theo Heußer
ZWECK:  Siegerermittlung in der Klasse Spielleiter V.220ff
        Klasse - Unterklasse
        Einbinden von abgeleiteten Strategie-Spielerklassen
        Lösungsbeispiel zur Roh-Vorlage Spielleiter17u4_roh; Siegerermittlung
        
        
HINWEISE:
-- Spielleiter so erweitert, dass er auch mitteilt, welche Spieler noch aktiv sind. 

-- Die Strategieklassen dienen als Vorlage

-- Die Klasse  Spieler17u4  wurde hier so angepasst, dass
   verschiedene Strategieklassen als Unterklasse erzeugt und genutzt werden können.
   Sie müssen _nur_  die Methode  entscheide()  ausprogrammieren, 
   die entscheidet, ob der Spieler aufhoert oder nicht.

-- Die Klasse Speicher dient der schnellen Wiederherstellung einer Testsituation,
   in der 3 Mitspieler vorliegen, die jeweils 4 Runden gespielt haben.

-- Noch würfeln die Spieler selbst. 
   Das wird bald vom Spielleiter übernommen/kontrolliert.



Hinweis zur Methode:   
  public int[] getAlleSpielstaende()    des Spielleiters:
     Sie gibt die Spielstände aller Spieler zurück,
     einzeln abgelegt in einer Reihung[] mit Elementtyp  int 
     und auch, ob die Spieler noch aktiv sind oder beendet haben.
     Sie liefert immer 25 Einträge/Mitspieler:     (eine kleine Schwäche!!)
     Bsp.:  12 14 118 20 0 18 0 117 -21 -21 -21 ...  
     bedeutet, dass 8 Spieler teilnehmen, 
     2 davon haben die 21 überschossen und sind damit Verlierer
        (das wird angezeigt durch 0)
     2 davon haben bereits aufgehört zu würfeln 
        (das wird angezeigt durch 118, 117; sie haben 18 bzw. 17 erreicht)
     4 sind noch am Würfeln und haben derzeit 12,14,20,18 Punkte.
     Allgemein gilt:
     PKtzahl von 1..21 = aktiver Spieler mit dieser Punktzahl 
     PktZahl 101..121  = Spieler, der aufgehört hat; mit Pktzahl 1xx-100,
        also 118 steht für: der Spieler ist fertig und hat 18 Punkte erreicht.
     PktZahl 0  = Spieler ist über 21 hinaus gekommen und hat verloren.
     PktZahl -21 = diesen Spieler gibt es nicht im Spiel.   



  
--- Bemerkungen: ---
V222: Anstehende Änderungen verdeutlicht:
     (Ober-)Klasse Spieler ist noch nicht abstrakt
     Noch würfeln die Spieler selbst. Das wird bald vom Spielleiter übernommen/kontrolliert.


V221: erweitert um 
      spieleEineRunde()
      sowie:  int[] getAlleSpielstaende()    Details dazu  s.o. 
      spieleGesSpiel()  fehlt noch
    
