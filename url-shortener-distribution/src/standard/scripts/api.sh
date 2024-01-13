#!/bin/bash
export JAVA_HOME=/opt/java

cd /opt/api/
./bin/url-shortener-distribution server var/conf/server.yml
