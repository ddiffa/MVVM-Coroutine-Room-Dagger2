package com.hellodiffa.coroutinesxroom

import com.hellodiffa.coroutinesxroom.di.component.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

/*
* created by Diffa
*/

class TennisPlayerApp : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication>  =
        DaggerAppComponent.builder().application(this).build()


}