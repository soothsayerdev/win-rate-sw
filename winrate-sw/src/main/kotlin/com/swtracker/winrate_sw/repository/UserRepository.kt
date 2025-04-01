package com.swtracker.winrate_sw.repository

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable
import com.swtracker.winrate_sw.entity.User
import org.springframework.stereotype.Repository
import software.amazon.awssdk.services.dynamodb.DynamoDbClient
import software.amazon.awssdk.services.dynamodb.*


@Repository
class UserRepository(dynamoDbClient: DynamoDbClient) {
    private val dynamoDbTable : DynamoDbTable<User> = DynamoDbEnhancedClient.builder()
        .dynamoDbClient(dynamoDbClient)
        .build()
        .table("Users", TableSchema.fromBean(User::class.java))

    fun save(user: User) {
        dynamoDbTable.putItem(user)
    }

    fun findByEmail(email: String): User? {
        return dynamoDbTable.scan()
            .items()
            .firstOrNull { it.email == email}
    }
}