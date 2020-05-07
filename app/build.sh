#!/bin/sh

cd ../infrastructure
docker-compose -f architecture-components/docker-compose.yml -f app-components/docker-compose.yml down
docker-compose -f architecture-components/docker-compose.yml up -d
sleep 30
cd ..
mvn clean install
cd infrastructure
docker-compose -f architecture-components/docker-compose.yml down