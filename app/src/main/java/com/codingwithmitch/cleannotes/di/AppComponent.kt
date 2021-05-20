package com.codingwithmitch.cleannotes.di

import com.codingwithmitch.cleannotes.framework.presentation.BaseApplication
import com.codingwithmitch.cleannotes.framework.presentation.MainActivity
import com.codingwithmitch.cleannotes.framework.presentation.notedetail.NoteDetailFragment
import com.codingwithmitch.cleannotes.framework.presentation.notelist.NoteListFragment
import dagger.BindsInstance
import dagger.Component
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import javax.inject.Singleton

@FlowPreview
@ExperimentalCoroutinesApi
@Singleton
@Component(
    modules = [
        AppModule::class,
        ProductionModule::class,
        NoteViewModelModule::class,
        NoteFragmentFactoryModule::class
    ]
)
interface AppComponent{

    @Component.Factory
    interface Factory{
        fun create(@BindsInstance app: BaseApplication): AppComponent
    }

    fun inject(maintActivity: MainActivity)

    fun inject(noteListFragment: NoteListFragment)

    fun inject(noteDetailFragment: NoteDetailFragment)
}