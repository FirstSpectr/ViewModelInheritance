package ru.spectr.viewmodelinheritancetest.ui.base

import androidx.lifecycle.LiveData
import ru.spectr.viewmodelinheritancetest.entities.LiveDataEvent

interface BaseViewModel {
    val singleLiveData: LiveData<LiveDataEvent<String>>
    var isViewDestroyed: Boolean

    fun onForwardClick()
}