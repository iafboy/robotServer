FROM java:8
COPY ./jssecacerts /usr/lib/jvm/java-8-openjdk-amd64/jre/lib/security/
VOLUME /tmp
ADD config-1.0.jar app.jar
RUN bash -c 'touch /app.jar'
EXPOSE 8888
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
