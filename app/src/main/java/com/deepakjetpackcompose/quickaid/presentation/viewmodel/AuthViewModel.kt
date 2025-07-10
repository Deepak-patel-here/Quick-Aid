package com.deepakjetpackcompose.quickaid.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.deepakjetpackcompose.quickaid.domain.consatant.USER
import com.deepakjetpackcompose.quickaid.domain.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(private val auth: FirebaseAuth,private val firestore: FirebaseFirestore): ViewModel() {
    private val _authState = MutableStateFlow<AuthState>(AuthState.Idle)
    val authState: StateFlow<AuthState> = _authState.asStateFlow()

    fun registerUser(name:String,email:String,password:String,onResult:(Boolean,String)->Unit){
        _authState.value= AuthState.Loading
        try {
            auth.createUserWithEmailAndPassword(email,password)
                .addOnSuccessListener {
                    firestore.collection(USER).document(it.user?.uid?:"").set(User(name = name, email = email))
                        .addOnSuccessListener {
                            onResult(true,"Registration Success")
                        }
                        .addOnFailureListener {
                            onResult(false,it.localizedMessage?:"Unable to Register User")
                        }
                    _authState.value=AuthState.Success
                }
                .addOnFailureListener {
                    onResult(false,it.localizedMessage?:"Unable to Register User")
                    _authState.value=AuthState.Error(it.localizedMessage?:"Unable to Register User")
                    _authState.value= AuthState.Idle

                }
        }catch (e: Exception){
            onResult(false,e.localizedMessage?:"Something went wrong")
            _authState.value=AuthState.Error(e.localizedMessage?:"Something went wrong")
        }
    }

    fun loginUser(email:String,password:String,onResult:(Boolean,String)->Unit){
        _authState.value= AuthState.Loading
        try{
            auth.signInWithEmailAndPassword(email,password)
                .addOnSuccessListener {
                    onResult(true,"Registration Success")
                    _authState.value=AuthState.Success
                }
                .addOnFailureListener {
                    onResult(false,it.localizedMessage?:"Unable to Register User")
                    _authState.value=AuthState.Error(it.localizedMessage?:"Unable to Register User")
                }
        }catch (e: Exception){
            onResult(false,e.localizedMessage?:"Something went wrong")
            _authState.value=AuthState.Error(e.localizedMessage?:"Something went wrong")
            _authState.value= AuthState.Idle

        }
    }
}

sealed class AuthState{
    object Loading : AuthState()
    object Success: AuthState()
    object Idle: AuthState()
    data class Error(val message: String): AuthState()
}