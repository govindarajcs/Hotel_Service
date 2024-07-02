FROM openjdk:8-jdk-alpine
RUN mkdir /root/hotel-naming-server
COPY . /root/hotel-naming-server
EXPOSE 8761
WORKDIR /root/hotel-naming-server
CMD java -jar /root/hotel-naming-server/target/hotel-naming-server-0.0.1.jar