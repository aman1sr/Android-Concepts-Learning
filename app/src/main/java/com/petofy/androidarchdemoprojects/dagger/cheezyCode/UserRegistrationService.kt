package com.petofy.androidarchdemoprojects.dagger.cheezyCode

import javax.inject.Inject

class UserRegistrationService @Inject constructor(
    private val userRepository: UserRepository,
    private val emailService: EmailService
){
    fun registerUser(email: String, password: String) {
        userRepository.saveUser(email, password)
        emailService.send(email,"cheezy-code@gmail.com","User password")
    }
}