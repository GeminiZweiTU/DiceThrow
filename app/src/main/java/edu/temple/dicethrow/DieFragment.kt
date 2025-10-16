package edu.temple.dicethrow

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import android.widget.TextView
import androidx.core.os.bundleOf
import kotlin.random.Random

class DieFragment : Fragment() {

    companion object {
        private const val DIESIDE = "sidenumber"

        fun newInstance(sides: Int): DieFragment =
            DieFragment().apply {
                arguments = bundleOf(DIESIDE to sides)
            }
    }

    lateinit var dieTextView: TextView

    private val dieSides: Int by lazy { arguments?.getInt(DIESIDE, 6) ?: 6 }
    var currentRoll: Int? = null

    private val viewModel: DieViewModel by viewModels()

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

        // Observe roll value and update UI
        viewModel.currentRoll.observe(viewLifecycleOwner) { roll ->
            dieTextView.text = roll.toString()
        }

        // Tap anywhere in the fragment to roll again
        view.setOnClickListener { viewModel.roll() }
    }

    fun rollDie() {
        currentRoll = Random.nextInt(1, dieSides + 1)
        dieTextView.text = currentRoll.toString()
    }
}