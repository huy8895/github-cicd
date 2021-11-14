FROM openjdk:11
EXPOSE 8080
ADD target/springboot-cicd-github.jar springboot-cicd-github.jar
ENTRYPOINT ["java","-jar","/springboot-cicd-github.jar"]