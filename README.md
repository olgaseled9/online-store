# Home task #21

1. git repo and best practices
2. base on #20
3. add DAO and hibernate tiers with spring integration
4. all configurations in Java code (don't use xml)
5. deploy to servlet container per student
   Note: see example https://github.com/vladislav-sidorovich/web-service-example

# Technologies

1. Java version: 11
2. Postgres 
3. database
4. docker-compose
5. Flyway
6. Hibernate
7. Slf4j+logback Lombok
8. Spring

How to use:

1. Build project: $mvn clean install
2. To run environment: $docker-compose up -d
3. mvn flyway:migrate
4. Copy war file into Tomcat directory (/webapps)
5. Run Tomcat bat file bin/startup.bat


