package com.bshapp.fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.navigation.Navigation
import com.bshapp.R
import com.bshapp.classes.PrefConfig

class ChooseStateFragment : Fragment() {
    private lateinit var tamilNaduState:CardView
    private lateinit var telanganaState:CardView
    private lateinit var apState:CardView
    private lateinit var karnatakaState:CardView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view=inflater.inflate(R.layout.fragment_choose_state, container, false)
        initializations(view)
        OnCardClickHandler(view)


        return view
    }

    private fun OnCardClickHandler(view: View) {
        tamilNaduState.setOnClickListener {
            context?.let { it1 -> PrefConfig.saveSelectedState(it1, "tamilnadu") }
            val action=ChooseStateFragmentDirections.actionChooseStateFragmentToOverViewLocationSelectFragment("tamil")
            Navigation.findNavController(view).navigate(action)
        }
        telanganaState.setOnClickListener {
            context?.let { it1 -> PrefConfig.saveSelectedState(it1, "Telangana") }
            val action=ChooseStateFragmentDirections.actionChooseStateFragmentToOverViewLocationSelectFragment("telugu")
            Navigation.findNavController(view).navigate(action)
        }
        apState.setOnClickListener {
            context?.let { it1 -> PrefConfig.saveSelectedState(it1, "AndhraPradesh") }
            val action=ChooseStateFragmentDirections.actionChooseStateFragmentToOverViewLocationSelectFragment("teluguAp")
            Navigation.findNavController(view).navigate(action)
        }
        karnatakaState.setOnClickListener {
            context?.let { it1 -> PrefConfig.saveSelectedState(it1, "Karnataka") }
            val action=ChooseStateFragmentDirections.actionChooseStateFragmentToOverViewLocationSelectFragment("kannada")
            Navigation.findNavController(view).navigate(action)
        }

    }

    private fun initializations(view: View) {
        tamilNaduState=view.findViewById(R.id.tamilnadu_Card)
        telanganaState=view.findViewById(R.id.hyderabad_card)
        apState=view.findViewById(R.id.andrapradesh_card)
        karnatakaState=view.findViewById(R.id.karnataka_card)
    }


}