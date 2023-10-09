package com.example.kotlin.repository

import com.example.kotlin.entity.Developer
import lombok.extern.slf4j.Slf4j
import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedAsyncClient

@Slf4j
@Repository
class DeveloperRepository(
        client: DynamoDbEnhancedAsyncClient,
        @Qualifier("developerDetailsTableName") developerTableName: String,
        ) : AbstractDynamoDbEnhancedRepository<Developer>(client, developerTableName, Developer::class.java) {

    private val logger = KotlinLogging.logger {}

    fun saveData(developer: Developer): Mono<Boolean> {
        return save(developer);
    }

    fun getData(id: String): Mono<Developer> {
        return Mono.fromFuture(getItemWithPartitionKey(id)).map {
            logger.info("DeveloperRepository:: getData:: data: {}", it)
            it
        }
    }

    fun removeData(id: String): Mono<Boolean> {
        return getData(id).flatMap {
            logger.info("DeveloperRepository:: removeData:: data: {}", it)
            Mono.fromFuture(delete(it)).thenReturn(true)
        }
    }


}