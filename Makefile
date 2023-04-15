install:
	chmod +x mvnw
	./mvnw clean package -Dmaven.test.skip=true

test:
	./mvnw clean package

run: install
	java -jar target/kotlin-*.jar

create-and-send-to-google-cloud-registry:
	./mvnw compile jib:build

push-k8s-with-skaffold:
	skaffold dev --port-forward