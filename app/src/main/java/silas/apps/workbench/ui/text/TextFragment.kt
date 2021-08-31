package silas.apps.workbench.ui.text

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import silas.apps.workbench.databinding.TextFragmentBinding

class TextFragment : Fragment() {

    private var binding: TextFragmentBinding? = null
    private lateinit var viewModel: TextViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = TextFragmentBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(TextViewModel::class.java)
        binding!!.cvMain.setOnDraw(viewModel)
        return binding!!.root
    }

}