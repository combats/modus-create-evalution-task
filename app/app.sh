#!/bin/sh

red=`tput setaf 1`
green=`tput setaf 2`
yellow=`tput setaf 3`
blue=`tput setaf 4`
magenta=`tput setaf 5`
reset=`tput sgr0`

help_flag=''
register_flag=''
login_flag=''
password_flag=''
authorization_flag=''
transaction_flag=''
jwt_flag=''
monetary_flag=''
income_flag=''
expense_flag=''
balance_flag=''
url_flag='http://localhost:8010'


while getopts 'hrl:p:atj:m:iebu:' flag; do
  case "${flag}" in
    r) register_flag='true'
    echo "${red}You will be register user via: ${yellow}base url: ${green}${url_flag} with ${yellow}login: ${green}${login_flag} ${yellow}password: ${green}${password_flag}${reset}"

    curl -X POST --header "Content-Type: application/json" -d "{\"login\":\"${login_flag}\",\"password\":\"${password_flag}\"}" ${url_flag}/auth/register
    ;;
    l) login_flag="${OPTARG}"
    ;;
    p) password_flag="${OPTARG}"
    ;;
    a) authorization_flag='true'
    echo "${red}You will register user via: ${yellow}base url: ${green}${url_flag} with ${yellow}login: ${green}${login_flag} ${yellow}password: ${green}${password_flag}${reset}"
    echo "${red}Please copy jwt token after response${reset}"

    curl -X POST --header "Content-Type: application/json" -d "{\"login\":\"${login_flag}\",\"password\":\"${password_flag}\"}" ${url_flag}/auth/login
    ;;
    t) transaction_flag='true'
    transaction_type='INCOME'
    if [[ ${expense_flag} != '' ]]
    then
      transaction_type='EXPENSE'
    fi
    echo "${red}You will make transaction via: ${yellow}base url: ${green}${url_flag} with ${yellow}token: ${green}${jwt_flag} ${yellow}transaction type: ${green}${transaction_type} ${yellow}monetary value: ${green}${monetary_flag}${reset}"

    curl -X POST --header "Content-Type: application/json" -H "Authorization: ${jwt_flag}" -d "{\"monetaryValue\":\"${monetary_flag}\",\"type\":\"${transaction_type}\"}" ${url_flag}/transaction
    ;;
    j) jwt_flag="${OPTARG}"
    ;;
    m) monetary_flag="${OPTARG}"
    ;;
    i) income_flag='true'
    ;;
    e) expense_flag='true'
    ;;
    b) balance_flag='true'
    echo "${red}You will get balance via: ${yellow}base url: ${green}${url_flag} with ${yellow}token: ${green}${jwt_flag}${reset}"

    curl -X GET -H "Accept: application/json" -H "Authorization: ${jwt_flag}" ${url_flag}/balance
    ;;
    m) url_flag="${OPTARG}"
    ;;
    h)
    echo "${red}Usage: ...${reset}"
    echo "${red}Get help: ${green}./app.sh h${reset}"
    echo "${red}Register: ${green}./app.sh -l ${blue}{wanted login}${green} -p ${blue}{wanted password} ${green}-r${reset}"
    echo "${red}Login/Authorize: ${green}./app.sh -l ${blue}{your login}${green} -p ${blue}{your password} ${green}-a${reset}"
    echo "${red}Make transaction: ${green}./app.sh -j ${blue}{your jwt token from auth}${green} -m ${blue}{wanted monetary INT value}${green} -i ${magenta}if you want add INCOME or ${green} -e ${magenta}if you want add EXPENSE ${green}-t${reset}"
    echo "${red}Get balance: ${green}./app.sh -j ${blue}{your jwt token from auth} ${green}-b${reset}"
    echo "${red}Custom URL: ${green}./app.sh -u ${blue}{your custom URL} ${magenta}if you need to define custom url in format: http://localhost:8010 with any other command${reset}"
    exit 0
    ;;
    *)
    exit 1
    ;;
  esac
done