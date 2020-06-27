FROM java:8

COPY *.jar /app.jar

VOLUME /tmp

ENTRYPOINT ["java","-jar","/app.jar"]
EXPOSE 8088
