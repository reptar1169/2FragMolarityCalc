package com.reptar.test

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.reptar.test.databinding.FragmentFirstBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonFirst.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }


        binding.goButton.setOnClickListener {


            if (TextUtils.isEmpty(binding.etMass.text.toString())) {
                reset()
            }
             else if (TextUtils.isEmpty(binding.etMW.text.toString())){
                reset()

            } else if (TextUtils.isEmpty(binding.etConc.text.toString())){
                reset()

            } else {
                mult()}
        }

        binding.resetButton.setOnClickListener {
            reset()
        }
    }

    private fun mult(){

        val mgsDouble = binding.etMass.text.toString().toDouble()
        val mwDouble = binding.etMW.text.toString().toDouble()
        val concDouble = binding.etConc.text.toString().toDouble()
        val compound = Compound(mwDouble, mgsDouble, concDouble)
        val result = compound.calcVol()
        binding.vol.text = result.toString()
    }

    private fun reset(){
        binding.etMW.setText("")
        binding.etMass.setText("")
        binding.etConc.setText("")
        binding.vol.text = ""
    }

    class Compound(
        private val mw: Double,
        private val mass: Double,
        private val conc: Double
    ) {
        fun calcVol(): Double {
            return (mass / ((mw / 1000) * (conc / 1000)))
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}