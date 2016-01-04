#!/bin/bash

CLASS=$1;

if [ -z "$CLASS" ]; then
    echo "Usage: $0 THE_CLASS_FILE" 
    exit; 
fi

MAVEN=`which mvn`;
JAVA=`which java`


$MAVEN exec:java -Dexec.mainClass=$CLASS

# Or you can run it with java
#$JAVA -cp target/*.jar $CLASS
