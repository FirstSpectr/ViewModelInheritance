package ru.spectr.viewmodelinheritancetest.ui.second

import androidx.lifecycle.MutableLiveData
import io.reactivex.rxkotlin.addTo
import ru.spectr.viewmodelinheritancetest.data.Repo
import ru.spectr.viewmodelinheritancetest.ui.base.BaseViewModelImpl
import toothpick.InjectConstructor

@InjectConstructor
class SecondViewModelImpl(private val repo: Repo) : BaseViewModelImpl(), SecondViewModel {
    override val secondLiveData = MutableLiveData<String>()
    override val repoHash = MutableLiveData<String>()

    init {
        secondLiveData.value = "SECOND VM ${this@SecondViewModelImpl.hashCode()}"
        repoHash.value = "REPO ${repo.hashCode()}"

        subscribeToFragmentData()
    }

    private fun subscribeToFragmentData() {
        repo.getSecondData()
            .subscribe()
            .addTo(compositeDisposable)
    }
}