package com.sunueric.prototype1.data

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.sunueric.prototype1.data.util.await
import com.sunueric.testingapicall2.data.Resource
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) : AuthRepository {
    override val currentUser: FirebaseUser?
        get() = firebaseAuth.currentUser

    override suspend fun login(email: String, password: String): Resource<FirebaseUser> {
       return try {
           val result = firebaseAuth.signInWithEmailAndPassword(email, password).await()
           Resource.Success(result.user!!)
       } catch (e: Exception) {
           Resource.Error(e)
       }
    }

    override suspend fun signUp(
        name: String,
        email: String,
        password: String
    ): Resource<FirebaseUser> {
        return try {
            val result = firebaseAuth.createUserWithEmailAndPassword(email, password).await()
            result?.user?.updateProfile(userProfileChangeRequest { displayName = name })?.await()
            Resource.Success(result.user!!)
        } catch (e: Exception) {
            Resource.Error(e)
        }
    }

    override fun logout() {
        firebaseAuth.signOut()
    }
}