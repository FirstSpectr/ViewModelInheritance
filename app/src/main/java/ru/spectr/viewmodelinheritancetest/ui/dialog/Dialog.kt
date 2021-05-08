package ru.spectr.viewmodelinheritancetest.ui.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import ru.spectr.viewmodelinheritancetest.databinding.DialogABinding

class Dialog : DialogFragment() {
    var binding: DialogABinding? = null

    companion object {
        private const val ARG_TEXT = "ARG_TEXT"

        fun newInstance(text: String): Dialog {
            val args = Bundle()
            args.putString(ARG_TEXT, text)

            val fragment = Dialog()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val binding = DialogABinding.inflate(inflater, container, false)
        this.binding = binding
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.apply {
            counter.text = requireArguments().getString(ARG_TEXT)
            dismissButton.setOnClickListener { dismiss() }
            forward.setOnClickListener {
                (parentFragment as? DialogListener)?.onForwardClick()
            }
        }
    }
}