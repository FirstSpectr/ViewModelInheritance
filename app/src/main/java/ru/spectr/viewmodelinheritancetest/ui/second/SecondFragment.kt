package ru.spectr.viewmodelinheritancetest.ui.second

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

class SecondFragment : BaseFragment<SecondViewModel>() {
    override val viewModel: SecondViewModel by inject<SecondViewModelImpl>()
    private var binding: MainFragmentBinding? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val binding = MainFragmentBinding.inflate(layoutInflater, container, false)
        this.binding = binding
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        KTP.openRootScope()
            .openSubScope(this)
            .installVMBinding<SecondViewModelImpl>(this)
            .closeOnViewModelCleared(this)
            .inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.apply {
            viewModel.secondLiveData.observe(viewLifecycleOwner) {
                viewModelHash.text = it
            }
            viewModel.repoHash.observe(viewLifecycleOwner) {
                repoHash.text = it
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}