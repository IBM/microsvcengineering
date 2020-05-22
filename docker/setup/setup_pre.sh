#!/bin/bash
set -e

# Absolute path to this script, e.g. /home/user/bin/foo.sh
SCRIPT=$(readlink -f "$0")
# Absolute path this script is in, thus /home/user/bin
basedir=$(dirname "$SCRIPT")

cd $basedir

cd ../..

echo "building modules"
mvn clean install -DskipTests

echo "building docker images"
$basedir/./build_docker_images.sh

cd $basedir

echo "copying zookeeper configuration"
mkdir -p /var/local/docker/msengg/volumes/zookeeper/config
cp ../zookeeper/zoo.cfg /var/local/docker/msengg/volumes/zookeeper/config/.
chmod -R 777 /var/local/docker/msengg/volumes/zookeeper/config

echo "copying kafka configuration"
mkdir -p /var/local/docker/msengg/volumes/kafka/config
cp ../kafka/config/* /var/local/docker/msengg/volumes/kafka/config/.
chmod -R 777 /var/local/docker/msengg/volumes/kafka/config

echo "copying prometheus configuration"
mkdir -p /var/local/docker/msengg/volumes/prometheus
cp ../prometheus/prometheus.yml /var/local/docker/msengg/volumes/prometheus/.
chmod -R 777 /var/local/docker/msengg/volumes/prometheus/
