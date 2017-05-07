FROM java:8-alpine
MAINTAINER Your Name <you@example.com>

ADD target/uberjar/old-school-library.jar /old-school-library/app.jar

EXPOSE 3000

CMD ["java", "-jar", "/old-school-library/app.jar"]
