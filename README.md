# Home task #20

1. new git repo
2. all best practices (.gitignore, readme etc)
3. base on modules home task #18
4. add web module
5. add spring dispatcher servlet
6. add spring context
7. deploy to servlet container per student

# Technologies

Java version: 11 Postgres database docker-compose Flyway Hibernate Slf4j+logback Lombok

How to use:

1. Build project: $mvn clean install
2. To run environment: $docker-compose up -d
3. mvn flyway:migrate
4. Copy war file into Tomcat directory (/webapps)
5. Run Tomcat bat file bin/startup.bat


