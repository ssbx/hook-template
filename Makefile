all:
	./gradlew war
	sudo cp build/libs/hook-template-1.0-SNAPSHOT.war /var/lib/tomcat7/webapps/
