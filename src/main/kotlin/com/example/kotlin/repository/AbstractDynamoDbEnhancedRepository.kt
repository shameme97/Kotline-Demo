package com.example.kotlin.repository

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import reactor.core.publisher.Mono
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedAsyncClient
import software.amazon.awssdk.enhanced.dynamodb.Key
import software.amazon.awssdk.enhanced.dynamodb.TableSchema
import java.util.concurrent.CompletableFuture

abstract class AbstractDynamoDbEnhancedRepository<T>(
        dynamoDbEnhancedAsyncClient: DynamoDbEnhancedAsyncClient,
        private val tableNameVal: String,
        tClass: Class<T>
) {

    private val tableSchema = TableSchema.fromBean(tClass)
    private val table = dynamoDbEnhancedAsyncClient.table(tableNameVal, tableSchema)

    private val logger: Logger = LoggerFactory.getLogger(javaClass)


    fun save(t: T): Mono<Boolean> {
        logger.info("AbstractDynamoDbEnhancedRepository:: save:: tableName: {}", tableNameVal)
        return Mono.fromFuture(table.putItem(t)
                .thenApply { true })

    }

    fun getItemWithPartitionKey(partitionKey: String): CompletableFuture<T> {
        logger.info("AbstractDynamoDbEnhancedRepository:: getItemWithPartitionKey:: tableName: {}", tableNameVal)
        val key = Key.builder()
                .partitionValue(partitionKey)
                .build()
        return table.getItem(key)

    }

    fun delete(t: T): CompletableFuture<T> {
        return table.deleteItem(t)
    }
}
