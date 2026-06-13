package com.demo.app

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.demo.app.databinding.FragmentNotificationPreferencesBinding

class NotificationPreferencesFragment : Fragment() {

    private var _binding: FragmentNotificationPreferencesBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: NotificationPreferencesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNotificationPreferencesBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(NotificationPreferencesViewModel::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupObservers()
        setupListeners()
    }

    private fun setupObservers() {
        viewModel.notificationEnabled.observe(viewLifecycleOwner, { isEnabled ->
            binding.switchNotification.isChecked = isEnabled
        })

        viewModel.notificationSound.observe(viewLifecycleOwner, { sound ->
            binding.textNotificationSound.text = sound
        })
    }

    private fun setupListeners() {
        binding.switchNotification.setOnCheckedChangeListener { _, isChecked ->
            viewModel.setNotificationEnabled(isChecked)
        }

        binding.buttonChangeSound.setOnClickListener {
            // Logic to change notification sound
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}