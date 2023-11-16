package com.petofy.androidarchdemoprojects.dagger.cheezyCode

import dagger.Component

@Component
interface UserRegistrationComponent {
    fun getUserRegistrationService(): UserRegistrationService

}