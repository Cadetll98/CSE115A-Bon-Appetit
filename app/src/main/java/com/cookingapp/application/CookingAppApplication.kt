package com.cookingapp.application

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

// we use this in order for hilt to generate hilt components attached to the
// life cycle of the application and it will provide dependencies.
// the Application() class is the parent class so child classes can access dependencies that this
// class provides
@HiltAndroidApp
class CookingAppApplication: Application() // CookingAppApplication inherits from the Application() class
