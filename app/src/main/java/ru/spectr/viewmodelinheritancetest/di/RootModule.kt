package ru.spectr.viewmodelinheritancetest.di

import ru.spectr.viewmodelinheritancetest.data.Repo
import ru.spectr.viewmodelinheritancetest.entities.Router
import toothpick.config.Module

class RootModule : Module() {
    init {
        bind(Repo::class.java).toInstance(Repo())
        bind(Router::class.java).toInstance(Router())
    }
}