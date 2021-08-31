package silas.apps.workbench.ui.editor

import android.content.Context
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import silas.apps.workbench.MainActivity
import silas.apps.workbench.databinding.FragmentEditorBinding
import java.io.File
import java.io.FileInputStream

class EditorFragment : Fragment() {

    private var binding: FragmentEditorBinding? = null
    private lateinit var viewModel: EditorViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentEditorBinding.inflate(inflater, container, false)
        val args = EditorFragmentArgs.fromBundle(requireArguments())
        viewModel = ViewModelProvider(this).get(EditorViewModel::class.java)
        viewModel.needsRefresh.observe(viewLifecycleOwner, {
            if (it) {
                binding?.cvMain?.invalidate()
            }
        })
        binding?.cvMain?.setOnDraw(viewModel)
        binding?.cvMain?.setOnSizeChanged(viewModel)
        viewModel.viewModelScope.launch {
            withContext(Dispatchers.IO) {
                Log.e("name", args.imageUri)
                val dir = context?.getDir("assets", Context.MODE_PRIVATE)
                try {
                    val bitmap = BitmapFactory.decodeFile(File(dir, "images/${args.imageUri}").absolutePath)
                    withContext(Dispatchers.Main) {
                        viewModel.setImageBitmap(bitmap)
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
                withContext(Dispatchers.Main) {
                    binding!!.cvMain.invalidate()
                }
            }
        }
        return binding!!.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}