#!/bin/sh
red=`tput setaf 1`
reset=`tput sgr0`

cd ../infrastructure
docker-compose -f architecture-components/docker-compose.yml -f app-components/docker-compose.yml down
docker-compose -f architecture-components/docker-compose.yml up -d
echo "${red}Please wait 30 sec before architecture components started...${reset}"
sleep 30
cd ..
mvn clean install
cd infrastructure
docker-compose -f architecture-components/docker-compose.yml down