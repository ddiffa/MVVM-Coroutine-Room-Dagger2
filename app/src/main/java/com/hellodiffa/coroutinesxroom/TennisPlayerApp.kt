package com.hellodiffa.coroutinesxroom

import android.app.Application
import com.hellodiffa.coroutinesxroom.di.component.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

/*
* created by Diffa
*/

class TennisPlayerApp : Application(), HasAndroidInjector {

    @Inject
    lateinit var inject: DispatchingAndroidInjector<Any>

    override fun androidInjector(): AndroidInjector<Any> = inject

    override fun onCreate() {
        super.onCreate()
        DaggerAppComponent.builder()
            .application(this)
            .build()
            .inject(this)
    }



}