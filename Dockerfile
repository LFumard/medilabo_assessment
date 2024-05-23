FROM openjdk:17
ADD target/MediLabo_assessment-0.0.1-SNAPSHOT.jar medilabo_assessment.jar
ENTRYPOINT ["java","-jar","/medilabo_assessment.jar"]
EXPOSE 8083