# spring-boot-kafka-consumer

Spring Boot application with Kafka is a Kafka Producer application to send messages to the Kafka topic.

## Table of Contents

- [Installation - Kafka](#installation---kafka)
- [Installation - Kafka Producer and Consumer](#installation---kafka-producer-and-consumer)
- [Usage](#usage)
- [Configuration](#configuration)

## Installation - Kafka

**Pre-requisite**: Install Docker Desktop on your local machine.

1. Download the `docker-compose.yaml` file from the project folder or copy the content below to install Kafka and Zookeeper in Docker.

    ```yaml
    version: '3'
    services:
      zookeeper:
        image: wurstmeister/zookeeper:3.4.6
        container_name: local_zookeeper
        ports:
          - "2181:2181"

      kafka:
        image: wurstmeister/kafka:2.13-2.7.0
        container_name: local_kafka
        ports:
          - "9092:9092"
        environment:
          KAFKA_BROKER_ID: 1
          KAFKA_ZOOKEEPER_CONNECT: zookeeper:2181
          KAFKA_LISTENERS: PLAINTEXT://0.0.0.0:9092
          KAFKA_ADVERTISED_LISTENERS: PLAINTEXT://localhost:9092
          KAFKA_LISTENER_SECURITY_PROTOCOL_MAP: PLAINTEXT:PLAINTEXT
          KAFKA_AUTO_CREATE_TOPICS_ENABLE: 'true'
        depends_on:
          - zookeeper
        volumes:
          - /var/run/docker.sock:/var/run/docker.sock
    ```

2. Run Docker Compose to start Zookeeper and Kafka:

    ```bash
    docker-compose up -d
    ```

3. Verify Kafka and Zookeeper:

    ```bash
    docker-compose logs kafka
    docker-compose logs zookeeper
    ```

4. Interacting with Kafka:

    4a. Creating a Topic:

    ```bash
    docker exec -it local_kafka kafka-topics.sh --create --topic my-topic --bootstrap-server localhost:9092 --partitions 1 --replication-factor 1
    ```

    4b. Listing Topics:

    ```bash
    docker exec -it local_kafka kafka-topics.sh --list --bootstrap-server localhost:9092
    ```

    4c. Produce messages:

    ```bash
    docker exec -it local_kafka kafka-console-producer.sh --topic my-topic --bootstrap-server localhost:9092
    ```

    4d. Consuming Messages (Open a new terminal for consumer):

    ```bash
    docker exec -it local_kafka kafka-console-consumer.sh --topic my-topic --from-beginning --bootstrap-server localhost:9092
    ```

5. Stopping and Removing Containers:

    ```bash
    docker-compose down
    ```

## Managing Kafka Partitions

### Check the Number of Partitions Assigned to a Topic (my-topic)

1. Execute a shell inside the Kafka container:

    ```bash
    docker exec -it local_kafka /bin/bash
    ```

2. Describe the topic to verify the number of partitions:

    ```bash
    kafka-topics.sh --describe --bootstrap-server localhost:9092 --topic your_topic_name
    ```

    **Output:**
    ```
    Topic: your_topic_name  PartitionCount: 3  ReplicationFactor: 1  Configs:
      Topic: your_topic_name  Partition: 0  Leader: 1  Replicas: 1  Isr: 1
      Topic: your_topic_name  Partition: 1  Leader: 1  Replicas: 1  Isr: 1
      Topic: your_topic_name  Partition: 2  Leader: 1  Replicas: 1  Isr: 1
    ```

### Update Partitions

1. Execute a shell inside the Kafka container:

    ```bash
    docker exec -it local_kafka /bin/bash
    ```

2. Change the number of partitions:

    ```bash
    kafka-topics.sh --alter --topic my-topic --partitions 5 --bootstrap-server localhost:9092
    ```

3. Describe the topic again to verify the change:

    ```bash
    kafka-topics.sh --describe --topic my-topic --bootstrap-server localhost:9092
    ```

## Installation - Kafka Producer and Consumer

**Pre-requisite**: Run Kafka and Zookeeper (please follow the steps provided above).

### Start Kafka Producer Application

1. Go to your project folder and clone the project to your preferred folder:

    ```bash
    git clone https://github.com/atlasrini88/spring-boot-kafka-producer.git
    ```

2. Import the project to your IDE (e.g., IntelliJ).

3. Start the producer application.

### Start Kafka Consumer Application

1. Go to your project folder and clone the project to your preferred folder:

    ```bash
    git clone https://github.com/atlasrini88/spring-boot-kafka-consumer.git
    ```

2. Import the project to your IDE (e.g., IntelliJ).

3. Start the consumer application.

## Usage

(Provide usage details here.)

## Configuration

(Provide configuration details here.)

