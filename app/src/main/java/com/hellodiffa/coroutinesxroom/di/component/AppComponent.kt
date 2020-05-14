package com.hellodiffa.coroutinesxroom.di.component

import com.hellodiffa.coroutinesxroom.TennisPlayerApp
import com.hellodiffa.coroutinesxroom.di.module.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
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
        AndroidInjectionModule::class,
        FragmentModule::class
    ]
)

interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: TennisPlayerApp): Builder

        fun build(): AppComponent
    }


    fun inject(app: TennisPlayerApp)
}