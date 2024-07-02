FROM openjdk:8-jdk-alpine
RUN mkdir /root/inventory
COPY . /root/inventory 
EXPOSE 9000
WORKDIR /root/inventory
CMD java -jar /root/inventory/target/inventory-0.0.1.jar