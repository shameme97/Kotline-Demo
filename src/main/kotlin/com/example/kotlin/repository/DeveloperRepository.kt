package com.example.kotlin.repository

import com.example.kotlin.entity.Developer
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedAsyncClient
import software.amazon.awssdk.enhanced.dynamodb.Key
import software.amazon.awssdk.enhanced.dynamodb.TableSchema

@Repository
class DeveloperRepository(
        private val client: DynamoDbEnhancedAsyncClient,

        ) {

    companion object {
        private val tableSchema = TableSchema.fromBean(Developer::class.java)
        private val tableName: String = "dev-developerDetails"
    }


    private val logger: Logger = LoggerFactory.getLogger(javaClass)
    private val table = client.table(tableName, tableSchema)

    fun saveData(developer: Developer): Mono<Unit> {
        logger.info("Table name: {}", tableName)
        return Mono.fromFuture(table.putItem(developer))
                .map { Unit }
                .doOnError { logger.error("Exception while saving data - $it") }
    }

    fun getData(id: String): Mono<Developer> {
        val key = Key.builder().partitionValue(id).build()
        logger.info("Table name: {}", tableName)
        return Mono.fromFuture(table.getItem(key))
                .doOnError { logger.error("Exception while retrieving data - $it") }
    }


}