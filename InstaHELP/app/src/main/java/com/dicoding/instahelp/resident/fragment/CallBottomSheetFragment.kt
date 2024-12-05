package com.dicoding.instahelp.resident.fragment

import android.Manifest
import android.content.ActivityNotFoundException
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.dicoding.instahelp.databinding.LayoutBottomSheetCallBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class CallBottomSheetFragment : BottomSheetDialogFragment() {

    private val requestPermissionCode = 100 // renamed to follow Kotlin convention
    private lateinit var speechRecognizer: SpeechRecognizer
    private var _binding: LayoutBottomSheetCallBinding? = null
    private val binding get() = _binding!!

    // ActivityResultLauncher for Speech-to-Text
    private val speechResultLauncher: ActivityResultLauncher<Intent> =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == -1) {  // RESULT_OK
                val data = result.data
                val resultList = data?.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
                resultList?.let {
                    // Display the result of Speech-to-Text
                    val spokenText = it[0]
                    binding.headerText.text = spokenText
                }
            }
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Initialize binding
        _binding = LayoutBottomSheetCallBinding.inflate(inflater, container, false)

        // Check for microphone permission
        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.RECORD_AUDIO)
            != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(Manifest.permission.RECORD_AUDIO),
                requestPermissionCode
            )
        }

        // Initialize SpeechRecognizer
        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(requireContext())

        // Handle mic button click
        binding.micButton.setOnClickListener {
            startSpeechToText()
        }

        return binding.root
    }

    private fun startSpeechToText() {
        // Create the Speech-to-Text intent
        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "id-ID") // Bahasa Indonesia

        try {
            speechResultLauncher.launch(intent)
        } catch (e: ActivityNotFoundException) {
            Toast.makeText(requireContext(), "Speech-to-Text not supported on this device", Toast.LENGTH_SHORT).show()
        }
    }

    // Handle the microphone permission request result
    @Deprecated("Deprecated in Java")
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        if (requestCode == requestPermissionCode) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted, Speech-to-Text can be started
                Toast.makeText(requireContext(), "Microphone permission granted", Toast.LENGTH_SHORT).show()
            } else {
                // Permission denied, inform the user
                Toast.makeText(requireContext(), "Microphone permission is required for Speech-to-Text", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        speechRecognizer.destroy()
    }
}
