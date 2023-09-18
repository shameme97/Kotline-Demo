package com.example.kotlin.entity

import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey

@DynamoDbBean
data class Feature (
        @get:DynamoDbPartitionKey
        var featureId: String? = null,
        var featureName: String? = null,
        var taskList: List<String>? = null,
)