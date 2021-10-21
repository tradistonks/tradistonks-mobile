package com.tradistonks.app.web.repository.room

import com.tradistonks.app.models.Strategy
import com.tradistonks.app.models.database.UserItem
import com.tradistonks.app.models.requests.UserUpdateRequest
import com.tradistonks.app.models.responses.auth.UserResponse

class RoomUserRepository(private val userDatabaseDao: UserDatabaseDao) {
    suspend fun getAllUsers() : List<UserItem>? = userDatabaseDao.getAll()

    suspend fun addUser(user: UserResponse) {
        userDatabaseDao.insert(UserItem(user))
    }

    suspend fun getUserById(id: String): UserResponse? {
        val item = userDatabaseDao.getById(id)
        return if(item != null) UserItem.toUserResponse(item) else null
    }

    suspend fun updateUser(user: UserResponse) {
        userDatabaseDao.update(UserItem(user))
    }
    suspend fun deleteUser(user: UserResponse) {
        userDatabaseDao.delete(UserItem(user))
    }
    suspend fun deleteAllUser() {
        userDatabaseDao.deleteAllUsers()
    }
}
