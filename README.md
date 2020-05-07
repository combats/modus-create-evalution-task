# Evaluation task for Modus Create

Backend/server made as Evaluation task for Modus Create.
 - application allow to manage your budget 
 - make monetary transactions asincome or an expense
 - allow to consult the balance resulting from your financial transactions
 - application support multiple users

## Getting Started


### Prerequisites

What things you need to install the software and how to install them

* [Docker](https://www.docker.com/community-edition#/download) - Container Management. Version: 19.03+ and Docker Compose  Version: 1.25+
* [Maven](https://maven.apache.org/) - Dependency Management
* [Java](http://openjdk.java.net/install/) - OpenJDK 8 or similar OracleJDK 8

### Installing

A step by step series of examples that tell you how to get a env running

####Go to:

```bash
cd %project_parent_directory%/app
```

####Execute

```bash
./build.sh
```
It will build project, run all tests

### Running
####Go to:

```bash
cd %project_parent_directory%/app
```

####Execute

####To start app:
```bash
./run.sh start
```

It will run all needed environment

####To start infrastructure-only:
```bash
./run.sh start-infrastructure
```

Now you can play with code, run microservices from IDE, build tests etc.

####To stop app:

```bash
./run.sh stop
```
####To remove/cleanup app run

```bash
./run.sh remove
```

### Using

####Go to:

```bash
cd %project_parent_directory%/app
```
#### Usage commands:

##### Get help

```bash
./app.sh -h
```
##### Register new user

```bash
./app.sh -l {wanted login} -p {wanted password} -r
```

##### Login/Authorize (you will receive JWT token that needed for making transactions and getting balance)
```bash
./app.sh -l {your login} -p {your password} -a
```

##### Make income transaction (-i key) (value for -m parameter should be positive INTEGER).
```bash
./app.sh -j {your jwt token from auth} -m {wanted monetary INT value} -i -t
```

##### Make expense transaction (-e key) (value for -m parameter should be positive INTEGER). You can have negative balance if you expense too much ;)
```bash
./app.sh -j {your jwt token from auth} -m {wanted monetary INT value} -e -t
```

##### Check your balance
```bash
./app.sh -j {your jwt token from auth} -b
```
hrl:p:atj:m:iebu:
#### List of parameters:
* ```-h```    help flag. Without value
* ```-r```    register flag. Allow you to register. Without value. Should goes in the end of command
* ```-l```    login flag. Should goes with your login
* ```-p```    password flag. Should goes with your password
* ```-a```    authorization flag. Allow you to get jwt token. Without value. Should goes in the end of command
* ```-t```    transaction flag. Allow you to make income/expense transaction. Without value. Should goes in the end of command
* ```-j```    jwt flag. Should goes with your token (received via login)
* ```-m```    monetary flag. Should goes with Integer value (whether you want to add income/expense to your account)
* ```-i```    income flag. Define your transaction as income. Without value
* ```-e```    expense  flag. Define your transaction as expense. Without value
* ```-b```    balance flag. Allow you to take a look at your balance. Without value. Should goes in the end of command
* ```-u```    url flag. if you need to define custom url in format: http://localhost:8010 (without slash in the end) with any other command

##Architecture
Application Architecture implements Microservice choreography pattern and CQRS pattern

Application consist from 4 microservices:
* gateway - responsible for RestAPI and route queries to other services
* users - registration/login
* financial-transactions - keep financial transactions historically
* financial-balance - calculate balance, based on transactions and allow to retrieve it

To prevent additional AWS configuration, and be more atomic in running and vendor-free, following 3rd party (architecture) components was chosen
####DB
PostgreSQL
####Message Broker
RabbitMQ
####Containerization
Docker (with docker-compose)

##Known Technical Debt
* Way more code should be covered with unit and integration tests
* Service inside gateway should not notify different queues for making single transaction. Some sort of topic (when all consumers read single message) should be implemented
* for real clustering - docker compose can't be applicable as solution. Docker swarm or Kubernetes should be chosen instead  

## Authors

* **Ihor Tsinko** - *Architect* - [email](igor.tsinko@gmail.com)

## License

This project is licensed under MIT License - all details in LICENSE.md file