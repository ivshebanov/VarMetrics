#!/bin/sh

java \
-Dspring.profiles.active=PROM,COMMON \
-Xms1024m -Xmx3800m \
-jar usr/src/varmetrics/varmetrics.jar