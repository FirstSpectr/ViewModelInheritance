package ru.spectr.viewmodelinheritancetest.ui.second

import androidx.lifecycle.LiveData
import ru.spectr.viewmodelinheritancetest.ui.base.BaseViewModel

interface SecondViewModel : BaseViewModel {
    val secondLiveData: LiveData<String>
    val repoHash: LiveData<String>
}