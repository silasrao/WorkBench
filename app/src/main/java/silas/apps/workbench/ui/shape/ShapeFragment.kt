package silas.apps.workbench.ui.shape

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import silas.apps.workbench.R
import silas.apps.workbench.databinding.ShapeFragmentBinding

class ShapeFragment : Fragment() {

    private var binding: ShapeFragmentBinding? = null
    private lateinit var viewModel: ShapeViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = ShapeFragmentBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(ShapeViewModel::class.java)
        binding!!.cvMain.setOnDraw(viewModel)
        return binding!!.root
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}