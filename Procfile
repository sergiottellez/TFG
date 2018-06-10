web: java $JAVA_OPTS -jar target/dependency/webapp-runner.jar --port $PORT target/*.war
release: java -cp target/spring-boot-sample-flyway-1.0.0.jar:target/dependency/* config.Migrations
