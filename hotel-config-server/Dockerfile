FROM openjdk:8-jdk-alpine
RUN mkdir /root/hotel-config-server
COPY . /root/hotel-config-server
WORKDIR /root/hotel-config-server
EXPOSE 8000
CMD java -jar /root/hotel-config-server/target/hotel-config-server-0.0.1.jar