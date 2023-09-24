package com.demo.demostramingapp.model

import android.app.Application
import android.content.Context
import com.demo.demostramingapp.viewmodel.MyRepository
import dagger.Module
import dagger.Provides

/** Module used to provide the data required for the class injection. */
@Module
class AppModule(private val application: Application) {

    @Provides
    fun provideApplication(): Application {
        return application
    }

    @Provides
    fun provideContext(application: Application): Context {
        return application.applicationContext
    }

    @Provides
    fun provideMyRepository(context: Context): MyRepository {
        return MyRepository(context)
    }
}
