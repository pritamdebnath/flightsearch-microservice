# flightsearch-microservice
A flight search using microservices

# How to deploy in AWS

- Create folder for each microservice(except flightsearchclient) project in AWS AMI root folder(e.g "eureka")
- Copy the corresponding jar and the dockerfile in that folder (e.g "eurekaserver-0.0.1-SNAPSHOT.jar" and "Dockerfile" )
- Go to that directory from root and run docker build command to create docker image("cd eureka","sudo docker build -t eureka .")
- Go to root directory and run docker compose command("sudo docker-compose up")
- To stop run "sudo docker-compose down"


    ----Created by team "Micromakers"
