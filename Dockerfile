FROM java:openjdk-8u111-alpine
RUN apk --no-cache add curl
COPY build/libs/*-all.jar car-rental-system.jar
CMD java ${JAVA_OPTS} -jar car-rental-system.jar