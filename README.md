# Introduction
This Repository contains useful components for engineering MicroServices. It comprises of:
- microsvcframework - which is a collection of reusable components for solving common technical/engineering issues and are built on top of Spring-Boot and Spring-Cloud-Stream. One of the key components it has is for Monitoring. By using the monitoring components - most of the glue code for monitoring microservices can be abstracted out. It uses `Micrometer`, `Prometheus`, `Grafana` and `Spring-Boot-Actuator` for achieving comprehensive monitoring of MicroServices which extend from this framework.
- samplemicrosvc - a sample MicroService which for provides samples of:  
  - RESTful Services
  - Kafka Stream listener
  - Kafka Producer
  - JPA CRUD Repository
  - Ehcache for caching
  - HTTP Client using REST Template
  
## Purpose
The purpose of this repo is to provide examples to demonstrate how to solve some of the engineering issues while doing MicroServices and Event Driven Architecture. It should NOT be used for commercial or production projects. The code in the repo is not production grade.  

# Project Structure
This project has 2 modules - `samplemicrosvc` and `microsvcframework`. Apart from this there are 2 main directories `kafka` and `docker`. 

The `kafka` dir contains a modified kafka shell script and a custom Dockerfile to build a new Kafka Image (plain Kafka + startup parameters for instrumentation and exporting JMX metrics to Prometheus)

The `docker` folder contains:
- `docker-compose.yml` - used for building the docker network and running docker containers
- directories such as `grafana`, `kafka`, `prometheus`, `zookeeper` - which contain configuration for their respective docker containers (some of the config is mounted as persistent volumes on the docker container)
- `setup` directory which contains scripts for building and setting up the deployment
- `grafana` directory contains grafana dashboard jsons which need to be imported into grafana after starting Grafana

*_Note_* - _There are a few manual steps as part of setup and deployment. They are described in detail in the following section._ 

# Prerequisites
To get this repo running - you would need to have:
- A Java IDE to clone and edit the code
- Maven 
- Docker environment with `docker-compose`

_NOTE_: It is assumed that you have an UNIX machine or a vm with bash shell being available. in case its not the case you would need to make changes to `${BASE_DIR}/docker/docker-compose.yml` and create (or run manually the commands in these files) files similar to `${BASE_DIR}/docker/setup/setup_pre.sh` and `${BASE_DIR}/docker/setup/setup_post.sh` in the format compatible with your OS. 

_NOTE_: The docker-compose and custom images built are not hardened for production like environment since the purpose of this repo is to provide guidance and samples. 

# Setup 
- Clone this repo into a `{BASE_DIR}`
- This uses `Docker` and `docker-compose`. There are convenience scripts in `docker/setup` folder for setting up and deploying the `samplemicrosvc`.
- The `docker-compose.yml` is available in `${BASE_DIR}/docker` folder. If required change the network configuration in `docker-compose.yml`.
- Download kafka 2.4.0 and unzip the downloaded file into `${PROJECT_HOME}/kafka` - this is required because we build a custom kafka image to add `jmx_exporter` in Kafka's classpath to enable monitoring Kafka via `Prometheus`.
- Download `jmx_prometheus_javaagent-0.12.0.jar` from [here](https://repo1.maven.org/maven2/io/prometheus/jmx/jmx_prometheus_javaagent/0.12.0/jmx_prometheus_javaagent-0.12.0.jar). Place the file in `${PROJECT_HOME}/kafka` project. 

Only 2 scripts need to be run to setup and deploy the MicroService and other containers. They are
1. docker/setup/setup_pre.sh - builds the project, creates docker images and sets up configuration for Kafka, Zookeeper and Prometheus as volumes.
2. docker/setup/setup_post.sh - creates the required kafka topics

```shell script
cd ${PROJECT_DIR}/docker/setup
./setup_pre.sh
./setup_post.sh
```

# Running
This project spins up following containers: 
- Mariadb
- Zookeeper - requires configuration which is available in `${BASE_DIR}/docker/zookeeper`. As part of setup this configuration is copied to a location in `/var/local` and mounted as a volume on this container.
- Kafka - requires configuration which is available in `${BASE_DIR}/docker/kafka`. As part of setup this configuration is copied to a location in `/var/local` and mounted as a volume on this container.
- Prometheus - requires configuration which is available in `${BASE_DIR}/docker/prometheus`. As part of setup this configuration is copied to a location in `/var/local` and mounted as a volume on this container.
- Grafana - contains dashboard json files which you can readily import into your grafana once all containers are up and running. 
- samplemicrosvc 

Use the following sequence of `docker-compose` commands:
```shell script
cd ${PROJECT_HOME}/docker/setup
docker-compose up -d mariadb prometheus zookeeper
docker-compose up -d kafka grafana
docker-compose up -d samplemicrosvc
```

# Stopping the containers
```shell script
cd ${PROJECT_HOME}/docker/setup
docker-compose stop
```

# Destroying the containers and volumes
```shell script
cd ${PROJECT_HOME}/docker/setup
docker-compose down -v
```

# Grafana Dashboards
There are 3 readymade dashboards available in `docker/grafana` folder. They can be used as is to create:
- MicroServices Platform Overview Dashboard
- MicroServices Metrics Drilldown Dashboard
- Kafka Dashboard

When Grafana is started for the first time - create a new prometheus datasource with name `prometheus` and URL `http://prometheus:9090` (if using the same docker-compose provided in `${BASE_DIR}/docker`). And start importing the dashboard's json files. 
