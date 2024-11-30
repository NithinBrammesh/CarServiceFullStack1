#!/bin/bash 

set -xe

docker-compose down || true
docker-compose build
docker-compose up -d