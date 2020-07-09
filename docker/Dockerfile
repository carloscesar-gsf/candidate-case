FROM openjdk:jre-alpine

COPY candidate-case-api.jar /candidate-case-api.jar

EXPOSE 8080

ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Duser.timezone=America/Sao_Paulo -Djava.security.egd=file:/dsv/./urandom -jar /candidate-case-api.jar" ]
