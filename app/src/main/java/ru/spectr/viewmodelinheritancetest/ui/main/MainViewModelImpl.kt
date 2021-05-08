package ru.spectr.viewmodelinheritancetest.ui.main

import androidx.lifecycle.MutableLiveData
import io.reactivex.rxkotlin.addTo
import ru.spectr.viewmodelinheritancetest.data.Repo
import ru.spectr.viewmodelinheritancetest.ui.base.BaseViewModelImpl
import toothpick.InjectConstructor

@InjectConstructor
class MainViewModelImpl(private val repo: Repo) : BaseViewModelImpl(), MainViewModel {
    override val viewModelHash = MutableLiveData<String>()
    override val repoHash = MutableLiveData<String>()

    init {
        viewModelHash.value = "MAIN VM ${this@MainViewModelImpl.hashCode()}"
        repoHash.value = "REPO ${repo.hashCode()}"

        subscribeToFragmentData()
    }

    private fun subscribeToFragmentData() {
        repo.getMainData()
            .subscribe()
            .addTo(compositeDisposable)
    }
}
