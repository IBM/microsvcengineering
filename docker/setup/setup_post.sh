#!/bin/bash

# Absolute path to this script, e.g. /home/user/bin/foo.sh
SCRIPT=$(readlink -f "$0")
# Absolute path this script is in, thus /home/user/bin
basedir=$(dirname "$SCRIPT")


cd $basedir/..

echo 'starting prometheus'
docker-compose up -d prometheus
sleep 20

echo 'starting grafana'
docker-compose up -d grafana

echo 'Starting required docker containers'
docker-compose up -d zookeeper
echo 'sleeping...'
sleep 10

docker-compose up -d kafka
echo 'sleeping...'
sleep 10

cd $basedir

echo 'creating kafka topics'
$basedir/create_kafka_topics.sh

cd $basedir/..
echo 'stopping docker containers'
docker-compose stop

cd $basedir
