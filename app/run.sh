#!/bin/sh

red=`tput setaf 1`
green=`tput setaf 2`
reset=`tput sgr0`

case "$1" in
start)
   cd ../infrastructure
   docker-compose -f architecture-components/docker-compose.yml -f app-components/docker-compose.yml up -d
   echo "${red}Please wait 1 min before everything started...${reset}"
   sleep 60
   echo "${green}Started${reset}"
   ;;
start-infrastructure)
   cd ../infrastructure
   docker-compose -f architecture-components/docker-compose.yml up -d
   echo "${red}Please wait 30 sec before everything started...${reset}"
   sleep 30
   echo "${green}Started${reset}"
   ;;
stop)
   cd ../infrastructure
   docker-compose -f architecture-components/docker-compose.yml -f app-components/docker-compose.yml stop
   ;;
remove)
   cd ../infrastructure
   docker-compose -f architecture-components/docker-compose.yml -f app-components/docker-compose.yml down
   ;;
*)
   echo "${red}Usage: $0 ${green}{start|start-infrastructure|stop|remove}${reset}"
esac

exit 0