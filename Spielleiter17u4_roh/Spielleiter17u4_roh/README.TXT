TITEL:  Spielleiter17u4_roh 
        Version V21x 
DATUM:  2.12.2022 /  2021,2017,2015
ZWECK:  Tests zum Spielleiter (JUnit)
        nutzt JUnit 4 ; ab BlueJ 3.0.5

        
Die Testklasse benoetigt Methoden von Spieler bzw. Spielleiter:
Die Testklasse legt einen Spielleiter an und vier Mitspieler und erprobt, ob die Siegerermittlung
für einige gegebene Beispiele korrekt ist.
Dabei wird natürlich speziell die Methode ermittleSieger() des Spielleiters verwendet,
die Sie ausarbeiten müssen.


Spieler17u4:
  - Konstruktor(String ..) 
  boolean anmeldenBeim(Spielleiter ..)    
  void setStand(int ..)
  void aufhoeren()
  
Spielleiter:  
  - Konstruktor()
  boolean pruefeEnde()
  void ermittleSieger()
  int getSpielerzahl()
  int getSiegerzahl()
  int getSiegwert()
  
Die Klassen Spieler17u4 sowie Spielleiter muessen diese Methoden bieten, 
damit sie vom vorhandenen JUnit-Test getestet werden koennen. 
Der JUnit-Test macht die Vorgaben, er legt die Schnittstellen fest.

