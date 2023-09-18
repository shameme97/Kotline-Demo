package com.example.kotlin.repository

import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedAsyncClient

abstract class AbstractDynamoDbEnhancedRepository(
        private val dynamoDbEnhancedAsyncClient: DynamoDbEnhancedAsyncClient,
        private val tableName: String,
        ) {
}