#!/bin/bash
cd src
javac cards/*.java
javac videopoker/*.java
javac players/*.java
javac ourvideopoker/*.java
jar cfm ../mygame.jar manif.txt cards videopoker players images ourvideopoker
rm cards/*.class
rm videopoker/*.class
rm players/*.class
rm ourvideopoker/*.class
