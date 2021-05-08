package ru.spectr.viewmodelinheritancetest.ui.base

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import ru.spectr.viewmodelinheritancetest.ui.dialog.Dialog
import ru.spectr.viewmodelinheritancetest.ui.dialog.DialogListener

abstract class BaseFragment<VM : BaseViewModel> : Fragment(), DialogListener {
    abstract val viewModel: VM

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.isViewDestroyed = false

        viewModel.singleLiveData.observe(viewLifecycleOwner) { event ->
            event.getContentIfNotHandled()?.let {
                Dialog.newInstance(it).show(childFragmentManager, Dialog::class.simpleName)
            }
        }
    }

    override fun onForwardClick() {
        viewModel.onForwardClick()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.w("MY_TAG_DEBUG", "onDestroyView ${this::class.simpleName}")
        viewModel.isViewDestroyed = true
    }
}