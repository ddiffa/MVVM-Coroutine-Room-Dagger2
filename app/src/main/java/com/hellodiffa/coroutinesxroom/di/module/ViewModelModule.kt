package com.hellodiffa.coroutinesxroom.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.hellodiffa.coroutinesxroom.di.factory.ViewModelFactory
import com.hellodiffa.coroutinesxroom.ui.detail.DetailViewModel
import com.hellodiffa.coroutinesxroom.ui.main.MainViewModel
import com.hellodiffa.coroutinesxroom.utils.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/*
* created by Diffa
*/

@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    internal abstract fun providesPlayerViewModel(viewModel: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DetailViewModel::class)
    internal abstract fun providesDetailViewModel(viewModel: DetailViewModel): ViewModel
}