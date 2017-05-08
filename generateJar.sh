#!/bin/bash
cd src
javac cards/*.java
javac videopoker/*.java
javac players/*.java
jar cfm ../mygame.jar manif.txt cards videopoker players images
rm cards/*.class
rm videopoker/*.class
rm players/*.class
