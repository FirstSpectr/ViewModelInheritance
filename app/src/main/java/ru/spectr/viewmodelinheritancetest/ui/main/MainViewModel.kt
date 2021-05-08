package ru.spectr.viewmodelinheritancetest.ui.main

import androidx.lifecycle.LiveData
import ru.spectr.viewmodelinheritancetest.ui.base.BaseViewModel

interface MainViewModel : BaseViewModel {
    val viewModelHash: LiveData<String>
    val repoHash: LiveData<String>
}