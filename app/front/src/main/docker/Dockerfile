ARG BASE_IMAGE_REGISTRY=docker.io
ARG BASE_IMAGE_NAME=openjdk
ARG BASE_IMAGE_TAG=11-slim

FROM ${BASE_IMAGE_REGISTRY}/${BASE_IMAGE_NAME}:${BASE_IMAGE_TAG}

ARG JAR_VERSION=0.1.0
ARG JAR_FILE=./front-${JAR_VERSION}.jar

COPY ${JAR_FILE} /app.jar

ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app.jar", "--spring.config.location=/opt/"]
