FROM payara/micro
MAINTAINER Marcus Fihlon, fihlon.ch
ENV DEPLOYMENT_DIR /opt/payara/deployments
COPY build/libs/sportchef-ping.war ${DEPLOYMENT_DIR}
ENTRYPOINT java -jar /opt/payara/payara-micro.jar --deploymentDir ${DEPLOYMENT_DIR}
HEALTHCHECK --interval=5s --timeout=3s --retries=3 CMD curl --fail http://localhost:8080/ping/pong || exit 1
