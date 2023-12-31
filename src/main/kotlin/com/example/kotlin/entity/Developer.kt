package com.example.kotlin.entity

import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey


@DynamoDbBean
data class Developer(
        @get:DynamoDbPartitionKey var employeeId: String? = null,
){
    lateinit var team: String
    var taskList: List<String> = listOf()
}
