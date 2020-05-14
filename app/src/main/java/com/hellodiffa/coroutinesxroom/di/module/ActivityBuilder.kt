package com.hellodiffa.coroutinesxroom.di.module

import com.hellodiffa.coroutinesxroom.ui.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/*
* created by Diffa
*/

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector
    abstract fun contributesMainActivity(): MainActivity

}