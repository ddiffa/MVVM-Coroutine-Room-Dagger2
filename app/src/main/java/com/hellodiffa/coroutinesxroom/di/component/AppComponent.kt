package com.hellodiffa.coroutinesxroom.di.component

import com.hellodiffa.coroutinesxroom.TennisPlayerApp
import com.hellodiffa.coroutinesxroom.di.module.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

/*
* created by Diffa
*/

@Singleton
@Component(
    modules = [
        ActivityBuilder::class,
        AppModule::class,
        NetworkModule::class,
        ViewModelModule::class,
        AndroidSupportInjectionModule::class,
        FragmentModule::class
    ]
)

interface AppComponent : AndroidInjector<TennisPlayerApp> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: TennisPlayerApp): Builder

        fun build(): AppComponent
    }

}