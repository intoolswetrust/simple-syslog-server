FROM gcr.io/distroless/java:8
MAINTAINER Josef (kwart) Cacek <josef.cacek@gmail.com>

COPY target/simple-syslog-server.jar /simple-syslog-server.jar
ENTRYPOINT ["/usr/bin/java", "-jar", "/simple-syslog-server.jar"]
EXPOSE 9898
CMD ["tcp" ]
