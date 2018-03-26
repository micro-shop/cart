FROM java:8-jre
ADD target/cart-0.0.1-SNAPSHOT.jar .
ENTRYPOINT ["java","-jar","./cart-0.0.1-SNAPSHOT.jar", "--port=80"]