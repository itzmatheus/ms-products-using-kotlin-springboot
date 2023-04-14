install:
	chmod +x mvnw
	./mvnw clean package -Dmaven.test.skip=true

test:
	./mvnw clean package

run: install
	java -jar target/kotlin-*.jar