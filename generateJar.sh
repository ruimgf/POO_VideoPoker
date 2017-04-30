#!/bin/bash
cd src
javac cards/*.java
javac videopoker/*.java
javac doublebonus10_7/*.java
javac players/*.java
jar cfm ../mygame.jar manif.txt cards doublebonus10_7 videopoker players
rm cards/*.class
rm videopoker/*.class
rm doublebonus10_7/*.class
rm players/*.class
