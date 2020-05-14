package com.hellodiffa.coroutinesxroom.di.module

import com.hellodiffa.coroutinesxroom.ui.detail.DetailFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

/*
* created by Diffa
*/

@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract fun contributesDetailFragment(): DetailFragment

}