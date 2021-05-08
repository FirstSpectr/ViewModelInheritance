package ru.spectr.viewmodelinheritancetest.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.spectr.viewmodelinheritancetest.databinding.MainFragmentBinding
import ru.spectr.viewmodelinheritancetest.di.installVMBinding
import ru.spectr.viewmodelinheritancetest.ui.base.BaseFragment
import toothpick.ktp.KTP
import toothpick.ktp.delegate.inject
import toothpick.smoothie.viewmodel.closeOnViewModelCleared

class MainFragment : BaseFragment<MainViewModel>() {
    override val viewModel: MainViewModel by inject<MainViewModelImpl>()
    private var binding: MainFragmentBinding? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val binding = MainFragmentBinding.inflate(inflater, container, false)
        this.binding = binding
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        KTP.openRootScope()
            .openSubScope(this)
            .installVMBinding<MainViewModelImpl>(this)
            .closeOnViewModelCleared(this)
            .inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.apply {
            viewModel.viewModelHash.observe(viewLifecycleOwner) {
                viewModelHash.text = it
            }
            viewModel.repoHash.observe(viewLifecycleOwner) {
                repoHash.text = it
            }
        }
    }
}