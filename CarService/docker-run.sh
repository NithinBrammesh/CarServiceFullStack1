#!/bin/bash

set -xe
docker build --network host -t  car-service-app .
docker rm car-service-app || true
#docker run --name car-service-app --env SERVER_PORT=9090 -p 9090:9090 -p 3306:3306 car-service-app
docker run --name car-service-app --env SERVER_PORT=9090 --network host car-service-app