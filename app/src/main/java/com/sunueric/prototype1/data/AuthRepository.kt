package com.sunueric.prototype1.data

import com.google.firebase.auth.FirebaseUser
import com.sunueric.testingapicall2.data.Resource

interface AuthRepository {
    val currentUser: FirebaseUser?
    suspend fun login(email: String, password: String): Resource<FirebaseUser>
    suspend fun signUp(name: String, email: String, password: String): Resource<FirebaseUser>
    fun logout()
}