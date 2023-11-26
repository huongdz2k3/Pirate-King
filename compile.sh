#!/bin/bash 
(
	cd src
	javac -d ../bin $(find . -name *.java)
)
 
