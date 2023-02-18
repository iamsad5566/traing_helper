package com.training_helper

import android.graphics.Typeface
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.setPadding
import com.training_helper.`object`.ExitDialog
import com.training_helper.databinding.FragmentFirstBinding
import com.training_helper.intf.IOBackPressed
import com.training_helper.model.ButtonAnimation

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment(), IOBackPressed {

    private var _binding: FragmentFirstBinding? = null
    private val buttonAnimation: ButtonAnimation = ButtonAnimation()

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

        val button: ImageView = view.findViewById<ImageView>(R.id.playButton)
        val resetButton: ImageView = view.findViewById<ImageView>(R.id.reset)
        val timeGear: ImageView = view.findViewById<ImageView>(R.id.timeGear)
        val setGear: ImageView = view.findViewById<ImageView>(R.id.setGear)
        val restTime: TextView = view.findViewById(R.id.restTime)
        val counter: Long = 60000

        buttonAnimation.buttonEffect(button, resetButton, timeGear, setGear, restTime, counter)

        val listContainer: LinearLayout = view.findViewById<LinearLayout>(R.id.setContainer)
        val textView: TextView = TextView(view.context)
        textView.text = "test_F_line1"
        textView.setTypeface(Typeface.MONOSPACE, Typeface.BOLD)
        textView.setPadding(20)
        textView.textSize = 20F
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