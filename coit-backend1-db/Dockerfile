FROM openjdk:8-jdk-alpine
# Environment Variable that defines the endpoint of sentiment-analysis python api.
ENV SA_LOGIC_API_URL http://localhost:5050
EXPOSE 8080
CMD ["java", "-jar", "sentiment-analysis-persistance-web-0.0.2-SNAPSHOT.jar", "--sa.logic.api.url=${SA_LOGIC_API_URL}"]
ADD target/sentiment-analysis-persistance-web-0.0.2-SNAPSHOT.jar /
