package com.swtracker.winrate_sw.entity

import org.springframework.data.annotation.*
//import org.springframework.data.dynamodb.core.mapping.Document
import java.util.*

@DynamoDbBean
data class User(
    @get:DynamoDbPartitionKey
    var id: String = UUID.randomUUID().toString(),

    var username: String = "",

    var email: String = "",

    var password: String = "",

)