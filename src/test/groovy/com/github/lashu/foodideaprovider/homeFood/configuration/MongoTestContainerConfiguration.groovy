package com.github.lashu.foodideaprovider.homeFood.configuration

import com.mongodb.client.MongoClient
import com.mongodb.client.MongoClients
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.testcontainers.containers.MongoDBContainer

@Configuration
class MongoTestContainerConfiguration {

    private static final String MONGO_DB_DOCKER_IMAGE_NAME = "mongo:3.6"
    private static final int MONGO_PORT = 27017

    @Bean(initMethod = "start", destroyMethod = "stop")
    MongoDBContainer mongoDBContainer() {
        return new MongoDBContainer(MONGO_DB_DOCKER_IMAGE_NAME)
                .withExposedPorts(MONGO_PORT)
    }

    @Bean
    MongoClient mongoClient(MongoDBContainer mongoDBContainer) {
        return MongoClients.create(mongoDBContainer.replicaSetUrl)
    }

}

