FROM gradle:6.5-jdk8 as builder
# To work with gradle in container,we need a root user
USER root
ENV APP_HOME=/usr/app/
WORKDIR $APP_HOME
COPY . .
RUN gradle build
RUN cp build/libs/* ./app.war

FROM tomcat:8-jdk8-openjdk

RUN rm -rf /usr/local/tomcat/webapps/ROOT**

ADD tomcat-users.xml /usr/local/tomcat/conf/
ADD context.xml /usr/local/tomcat/webapps/manager/META-INF/context.xml


EXPOSE 8080
COPY --from=builder ./usr/app/app.war /usr/local/tomcat/webapps/tictactoe.war



ADD https://github.com/ufoscout/docker-compose-wait/releases/download/2.2.1/wait /wait
RUN chmod +x /wait
## Launch the wait tool and then your application
#CMD ["catalina.sh", "run"]
CMD /wait && catalina.sh run
#CMD /wait && java -jar app.war
#CMD ["java","-jar","app.jar"]



