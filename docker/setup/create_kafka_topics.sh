#!/bin/bash
set -e

# Absolute path to this script, e.g. /home/user/bin/foo.sh
SCRIPT=$(readlink -f "$0")
# Absolute path this script is in, thus /home/user/bin
basedir=$(dirname "$SCRIPT")

$basedir/../../kafka/bin/kafka-topics.sh --create --zookeeper 10.13.0.3:2181 --replication-factor 1 --partitions 4 --topic audit.persist