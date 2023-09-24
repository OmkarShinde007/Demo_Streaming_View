package com.demo.demostramingapp.model

import com.demo.demostramingapp.view.HomeMovieListFragment
import com.demo.demostramingapp.view.MainActivity
import dagger.Component

/** Component used to inject the class into the activity and fragment. */
@Component(modules = [AppModule::class])
interface AppComponent {
    fun inject(activity: HomeMovieListFragment)
    fun inject(activity: MainActivity)
}