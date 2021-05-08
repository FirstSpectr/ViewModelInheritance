package ru.spectr.viewmodelinheritancetest.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ru.spectr.viewmodelinheritancetest.data.Repo
import toothpick.InjectConstructor

@InjectConstructor
class AppViewModelImpl(private val repo: Repo) : ViewModel() {

    fun onTriggerClick() {
        repo.triggerCommonEvent()
    }

    fun onDelayClick() {
        viewModelScope.launch {
            delay(5_000)
            repo.triggerCommonEvent()
        }
    }
}