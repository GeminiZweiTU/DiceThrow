package edu.temple.dicethrow

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.os.bundleOf
import kotlin.random.Random

class DieFragment : Fragment() {

    companion object {
        private const val DIESIDE = "sidenumber"
        private const val CURRENT_ROLL_KEY = "currentroll"

        fun newInstance(sides: Int): DieFragment =
            DieFragment().apply {
                arguments = bundleOf(DIESIDE to sides)
            }
    }

    lateinit var dieTextView: TextView

    var dieSides: Int = 6
    var currentRoll: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Get sides (default 6 if not passed)
        dieSides = arguments?.getInt(DIESIDE, 6) ?: 6
        // Restore saved roll if present
        currentRoll = savedInstanceState?.getInt(CURRENT_ROLL_KEY)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_die, container, false).apply {
            dieTextView = findViewById(R.id.dieTextView)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (currentRoll != null) {
            dieTextView.text = currentRoll.toString()
        } else {
            rollDie()
        }

        view.setOnClickListener { rollDie() }
        }

    fun rollDie() {
        currentRoll = Random.nextInt(1, dieSides + 1)
        dieTextView.text = currentRoll.toString()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        currentRoll?.let { outState.putInt(CURRENT_ROLL_KEY, it) }
    }
}