# MS Products

This little project is a example of a microservice build using Kotlin, Spring boot, Kubernetes and Google cloud following [this article](https://medium.com/rapaduratech/microservices-com-kotlin-spring-boot-e-kubernetes-rodando-na-google-cloud-63056044d8a9).
The purpose of this project is just studying all techs involving.

## Tech Stack

**Server:**
- Language Java 17 and Kotlin 1.7.22
- Framework Spring (Springboot, Spring Data, Spring Web, DevTools), Flyway, H2, Swagger

**DevOps:**
- Google as cloud provider
- Kubernetes (GKE) for cluster services
- Jib for build container image without need dockerfile and send to google registry
- Skaffold to build local image using jib, send to google registry and deploy to K8S automatically. So you can use both localhost and external ip k8s service to get access the api deployed.


## Features

- [x]  CRUD Category
- [x]  CRUD Product


## API Reference

#### Swagger

Just access in your browser: [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

## Run Locally

Clone the project

```bash
  git clone git@github.com:itzmatheus/ms-products-using-kotlin-springboot.git
```

Go to the project directory

```bash
  cd ms-products-using-kotlin-springboot
```

Install dependencies

```bash
  make install
  or 
  chmod +x mvnw /
  ./mvnw clean package -Dmaven.test.skip=true
```

Start the server

```bash
  make run
  or
  chmod +x mvnw /
  ./mvnw clean package -Dmaven.test.skip=true /
  java -jar target/kotlin-*.jar
```


## Running Tests

To run tests, run the following command

```bash
  make test
  or
  chmod +x mvnw /
  ./mvnw clean package -Dmaven.test.skip=true /
  ./mvnw verify
```


## Deployment

To deploy this project run
Before everything install skaffold on your computer: https://skaffold.dev/docs/install/

1 - GCP Auth Login
```bash
  gcloud auth login
```
2 - Assuming you already created the project in you google cloud platform and also kubernetes cluster in gke
```bash
  gcloud config set project YOU_GOOGLE_PROJECT_ID
```
3 - Search and Change YOU_GOOGLE_PROJECT_ID in 3 files:
```bash
k8s/deployment.yaml
pom.xml
skaffold.yaml
```

4 - Build & Push
```bash
  make push-k8s-with-skaffold
  or
  skaffold dev --port-forward
```

## Acknowledgements

- [Microservices com Kotlin, Spring boot e Kubernetes rodando na Google Cloud](https://medium.com/rapaduratech/microservices-com-kotlin-spring-boot-e-kubernetes-rodando-na-google-cloud-63056044d8a9)
- [Google Cloud](https://cloud.google.com/?hl=pt-br)
- [Kubernetes](https://kubernetes.io/pt-br/)
- [ChatGPT](https://openai.com/blog/chatgpt)
- [Jib](https://cloud.google.com/java/getting-started/jib?hl=pt-br)
- [Skaffold](https://skaffold.dev/docs/install/)