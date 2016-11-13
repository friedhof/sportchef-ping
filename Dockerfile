FROM airhacks/payara-micro
MAINTAINER Marcus Fihlon, fihlon.ch
COPY build/libs/sportchef-ping.war ${DEPLOYMENT_DIR}
ENTRYPOINT java -jar ${PAYARA_ARCHIVE}.jar --deploy ${ARCHIVE_NAME} --deploy sportchef-ping.war
HEALTHCHECK --interval=15s CMD curl --fail http://localhost:8080/ping/pong || exit 1
