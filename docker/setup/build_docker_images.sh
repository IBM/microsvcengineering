#!/bin/bash
set -e

# Absolute path to this script, e.g. /home/user/bin/foo.sh
SCRIPT=$(readlink -f "$0")
# Absolute path this script is in, thus /home/user/bin
basedir=$(dirname "$SCRIPT")

cd $basedir/../../kafka
docker build -t kafka:2.4.0 .

cd $basedir/../../samplemicrosvc
echo Building ${pwd}
docker build -t samplemicrosvc .

cd $basedir

