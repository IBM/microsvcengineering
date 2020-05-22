FROM java
MAINTAINER tanmay ambre
COPY ./target/samplemicrosvc-1.0-SNAPSHOT.jar /usr/local/samplemicrosvc/
RUN ls -al /usr/local/samplemicrosvc
EXPOSE 23011
VOLUME /logs
WORKDIR /usr/local/samplemicrosvc
ENTRYPOINT java -Dspring.profiles.active=${ENV} -jar samplemicrosvc-1.0-SNAPSHOT.jar
