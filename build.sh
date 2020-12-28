#!/bin/sh
./gradlew build
docker build -t lashu/food-idea-provider .