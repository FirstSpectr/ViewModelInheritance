package ru.spectr.viewmodelinheritancetest.data

import com.jakewharton.rxrelay2.PublishRelay
import io.reactivex.Single

class Repo {
    val publishRelay = PublishRelay.create<String>()

    private var triggerCount: Int = 0
    fun triggerCommonEvent() {
        publishRelay.accept("Event ${triggerCount++}")
    }

    fun getMainData(): Single<String> = Single.just("")

    fun getSecondData(): Single<String> = Single.just("")
}