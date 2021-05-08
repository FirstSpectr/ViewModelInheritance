package ru.spectr.viewmodelinheritancetest.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.spectr.viewmodelinheritancetest.R
import ru.spectr.viewmodelinheritancetest.databinding.MainActivityBinding
import ru.spectr.viewmodelinheritancetest.di.RootModule
import ru.spectr.viewmodelinheritancetest.entities.Router
import ru.spectr.viewmodelinheritancetest.di.installVMBinding
import ru.spectr.viewmodelinheritancetest.ui.main.MainFragment
import ru.spectr.viewmodelinheritancetest.ui.second.SecondFragment
import toothpick.ktp.KTP
import toothpick.ktp.delegate.inject
import toothpick.smoothie.viewmodel.closeOnViewModelCleared

class AppActivity : AppCompatActivity() {
    private lateinit var binding: MainActivityBinding
    private val viewModel by inject<AppViewModelImpl>()
    private val router by inject<Router>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        KTP.openRootScope()
            .installModules(RootModule())
            .installVMBinding<AppViewModelImpl>(this)
            .closeOnViewModelCleared(this)
            .inject(this)

        binding.triggerButton.setOnClickListener { viewModel.onTriggerClick() }
        binding.delayButton.setOnClickListener { viewModel.onDelayClick() }

        router.moveForward = {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, SecondFragment())
                .addToBackStack(null)
                .commit()
        }

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment())
                .commitNow()
        }
    }
}