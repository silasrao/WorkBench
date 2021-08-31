package silas.apps.workbench.ui.crop

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import silas.apps.workbench.R

class CropFragment : Fragment() {

    companion object {
        fun newInstance() = CropFragment()
    }

    private lateinit var viewModel: CropViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.crop_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CropViewModel::class.java)
        // TODO: Use the ViewModel
    }

}