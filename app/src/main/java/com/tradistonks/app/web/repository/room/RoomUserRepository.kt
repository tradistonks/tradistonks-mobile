package com.tradistonks.app.web.repository.room

import androidx.lifecycle.LiveData
import com.tradistonks.app.models.requests.UserUpdateRequest
import com.tradistonks.app.models.responses.auth.UserResponse

class RoomUserRepository(private val userDatabaseDao: UserDatabaseDao) {
    val readAllData : LiveData<List<UserResponse>> =  userDatabaseDao.getAll()

    suspend fun addUser(user: UserResponse) {
        userDatabaseDao.insert(user)
    }
    suspend fun updateUser(user: UserResponse) {
        userDatabaseDao.update(user)
    }
    suspend fun deleteUser(user: UserResponse) {
        userDatabaseDao.delete(user)
    }
    suspend fun deleteAllUser() {
        userDatabaseDao.deleteAllUsers()
    }
}
