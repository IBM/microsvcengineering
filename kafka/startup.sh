#!/bin/bash

#export KAFKA_LOG4J_OPTS="-Dlog4j.configuration=file:/config/log4j.properties"
$KAFKA_HOME/bin/kafka-server-start.sh $KAFKA_HOME/config/server.properties
