package ru.spectr.viewmodelinheritancetest.ui.base

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import ru.spectr.viewmodelinheritancetest.data.Repo
import ru.spectr.viewmodelinheritancetest.entities.LiveDataEvent
import ru.spectr.viewmodelinheritancetest.entities.Router
import toothpick.ktp.KTP
import toothpick.ktp.delegate.inject


abstract class BaseViewModelImpl : ViewModel(), BaseViewModel {
    override val singleLiveData = MutableLiveData<LiveDataEvent<String>>()
    override var isViewDestroyed: Boolean = true

    protected val compositeDisposable = CompositeDisposable()

    private val repo by inject<Repo>()
    private val router by inject<Router>()

    init {
        Log.i("MY_TAG_DEBUG", "init ${this::class.simpleName} ${this.hashCode()}")

        KTP.openRootScope().inject(this) //Warning: Leaking 'this' in constructor of non-final class BaseViewModelImpl

        subscribeToCommonWork()
    }


    private fun subscribeToCommonWork() {
        repo.publishRelay
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                if (!isViewDestroyed)
                    singleLiveData.value = LiveDataEvent(it)
            }, { }).addTo(compositeDisposable)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
        Log.e("MY_TAG_DEBUG", "onCleared ${this::class.simpleName}")
    }

    override fun onForwardClick() {
        router.moveForward()
    }
}