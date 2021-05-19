package com.codingwithmitch.cleannotes.framework.presentation.splash

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.codingwithmitch.cleannotes.R
import com.codingwithmitch.cleannotes.framework.presentation.common.BaseNoteFragment
import com.codingwithmitch.cleannotes.util.cLog
import com.google.firebase.crashlytics.FirebaseCrashlytics

class SplashFragment constructor(
    private val viewModelFactory: ViewModelProvider.Factory
): BaseNoteFragment(R.layout.fragment_splash) {

    val viewModel: SplashViewModel by viewModels {
        viewModelFactory
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        FirebaseCrashlytics.getInstance().log("This is the first log")
    }


    override fun inject() {

    }

}




























