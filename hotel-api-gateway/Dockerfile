FROM openjdk:8-jdk-alpine
RUN mkdir /root/hotel-api-gateway
COPY . /root/hotel-api-gateway
WORKDIR /root/hotel-api-gateway
EXPOSE 8765
CMD java -jar /root/hotel-api-gateway/target/hotel-api-gateway-0.0.1.jar