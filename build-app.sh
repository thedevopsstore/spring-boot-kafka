#!/bin/bash

./gradlew clean build

cp app/front/build/libs/*.jar app/front/src/main/docker/

cp app/back/build/libs/*.jar app/back/src/main/docker/

cp app/reader/build/libs/*.jar app/reader/src/main/docker/
