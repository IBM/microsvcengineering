FROM java

LABEL name="kafka" version="2.4.0"

ENV KAFKA_HOME /usr/local/kafka_2.12-2.4.0

RUN java -version
RUN mkdir -p $KAFKA_HOME/bin && mkdir -p $KAFKA_HOME/libs && mkdir -p $KAFKA_HOME/config && mkdir -p $KAFKA_HOME/logs && mkdir -p $KAFKA_HOME/data

ADD Kafka-LICENSE $KAFKA_HOME
ADD Kafka-NOTICE $KAFKA_HOME
ADD bin/* $KAFKA_HOME/bin/
ADD libs/* $KAFKA_HOME/libs/
ADD startup.sh $KAFKA_HOME/bin
ADD kafka-run-class.sh $KAFKA_HOME/bin
ADD jmx_prometheus_javaagent-0.12.0.jar $KAFKA_HOME/libs/

RUN ls -al $KAFKA_HOME/bin
RUN ls -al $KAFKA_HOME/libs

VOLUME $KAFKA_HOME/config
VOLUME $KAFKA_HOME/logs
VOLUME $KAFKA_HOME/data

EXPOSE 9092
EXPOSE 9999
EXPOSE 9308

WORKDIR $KAFKA_HOME/bin
ENTRYPOINT ["./startup.sh"]

