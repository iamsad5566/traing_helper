package com.training_helper

import android.os.Bundle
import android.util.Log
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.setPadding
import androidx.fragment.app.FragmentManager
import com.training_helper.`object`.ExitDialog
import com.training_helper.databinding.FragmentFirstBinding
import com.training_helper.intf.IOBackPressed

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment(), IOBackPressed {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO) // Ban the dark mode
        _binding = FragmentFirstBinding.inflate(inflater, container, false)

        activity?.onBackPressedDispatcher?.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                val alert = ExitDialog()
                alert.show(activity!!.supportFragmentManager, "log")
            }
        })

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        binding.buttonFirst.setOnClickListener {
//            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
//        }

        var listContainer: LinearLayout = view.findViewById<LinearLayout>(R.id.setContainer)
        val textView: TextView = TextView(view.context)
        textView.text = "test_F_line"
        textView.setPadding(10)
        textView.gravity = Gravity.LEFT
        listContainer.addView(textView)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onBackPressed(): Boolean {
        return true
    }
}