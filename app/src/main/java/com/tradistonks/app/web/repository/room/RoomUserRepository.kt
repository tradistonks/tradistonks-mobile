package com.tradistonks.app.web.repository.room

import androidx.lifecycle.LiveData
import com.tradistonks.app.models.database.UserItem
import com.tradistonks.app.models.requests.UserUpdateRequest
import com.tradistonks.app.models.responses.auth.UserResponse

class RoomUserRepository(private val userDatabaseDao: UserDatabaseDao) {
    val readAllData : LiveData<List<UserItem>> =  userDatabaseDao.getAll()

    suspend fun addUser(user: UserItem) {
        userDatabaseDao.insert(user)
    }
    suspend fun updateUser(user: UserItem) {
        userDatabaseDao.update(user)
    }
    suspend fun deleteUser(user: UserItem) {
        userDatabaseDao.delete(user)
    }
    suspend fun deleteAllUser() {
        userDatabaseDao.deleteAllUsers()
    }
}
