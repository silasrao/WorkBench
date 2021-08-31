package silas.apps.workbench.ui.main

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.OpenableColumns
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import silas.apps.workbench.Constants
import silas.apps.workbench.databinding.FragmentMainBinding
import java.io.File
import java.io.FileOutputStream

class MainFragment : Fragment() {
    private var binding: FragmentMainBinding? = null

    @SuppressLint("Range")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)

        val imagePickLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == Activity.RESULT_OK) {
                val fileName = it.data!!.dataString!!
                it.data?.data?.let { it1 ->
                    var name = File(fileName).name
                    val query =
                        context?.applicationContext?.contentResolver?.query(it1, null, null, null)
                    query?.let {
                        if (query.moveToFirst()) {
                            name = query.getString(query.getColumnIndex(OpenableColumns.DISPLAY_NAME))
                        }
                        query.close()
                    }
                    val dir = context?.getDir("assets", Context.MODE_PRIVATE)
                    dir?.let {
                        context?.applicationContext?.contentResolver?.openInputStream(it1).use {stream ->
                            val outputFile = File(dir, "images/$name")
                            val parentFile = outputFile.parentFile
                            if (!parentFile!!.exists()) {
                                if (!parentFile.mkdirs()) {
                                    Log.e("File", "Unable to create!!!")
                                }
                            }
                            Log.e("Image", "output file: ${outputFile.absolutePath}")
                            val outputStream = FileOutputStream(outputFile)
                            stream?.let { notNullStream ->
                                notNullStream.copyTo(outputStream, 1024)
                                outputStream.flush()
                                outputStream.close()
                                notNullStream.close()
                                findNavController().navigate(MainFragmentDirections.actionMainFragmentToEditorFragment(name))
                            }
                        }
                    }
                }
            }
        }

        binding!!.btnEditor.setOnClickListener {
            val intent = Intent(Intent.ACTION_OPEN_DOCUMENT).apply {
                addCategory(Intent.CATEGORY_OPENABLE)
                type = "image/*"

                // Optionally, specify a URI for the file that should appear in the
                // system file picker when it loads.
//                putExtra(DocumentsContract.EXTRA_INITIAL_URI, pickerInitialUri)
            }
            imagePickLauncher.launch(intent)
        }

        binding!!.btnAndroid.setOnClickListener {
            findNavController().navigate(MainFragmentDirections.actionMainFragmentToEditorFragment(Constants.ANDROID_URI))
        }

        binding!!.btnEverest.setOnClickListener {
            findNavController().navigate(MainFragmentDirections.actionMainFragmentToEditorFragment(Constants.MOUNT_EVEREST_URI))
        }

        binding!!.btnText.setOnClickListener {
            findNavController().navigate(MainFragmentDirections.actionMainFragmentToTextFragment())
        }

        binding!!.btnShape.setOnClickListener {
            findNavController().navigate(MainFragmentDirections.actionMainFragmentToShapeFragment())
        }

        return binding!!.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    companion object {
    }
}