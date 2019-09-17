package com.example.android.navigation


import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.android.navigation.databinding.FragmentTitleBinding
import kotlinx.android.synthetic.main.fragment_title.view.*

class TitleFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        // We are not in a View, we need to inflate the layout fragment_title to the parent ViewGroup
        // We are still using DataBindingUtil to get FragmentTitleBinding
        val binding = DataBindingUtil.inflate<FragmentTitleBinding>(inflater, R.layout.fragment_title, container, false)
        binding.playButton.setOnClickListener {
            // SageArgs -> NavDirections
            it.findNavController().navigate(TitleFragmentDirections.actionTitleFragmentToGameFragment())
        }

        // OVERFLOW MENU
        // 1. Tells Android that our TitleFragment has a menu
        setHasOptionsMenu(true)

        // binding.root is the root view fragment
        return binding.root
    }

    // OVERFLOW MENU
    // 2. We need to override onCreateOptionsMenu
    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        // 3. Inflates our menu resource (R.menu.overflow_menu) using provided inflater and menu structure
        inflater?.inflate(R.menu.overflow_menu, menu)
    }

    // OVERFLOW MENU
    // 4. We need to override onOptionsItemsSelected to connect it to our NavigationUI
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return NavigationUI.onNavDestinationSelected(item!!,
                view!!.findNavController())
                || super.onOptionsItemSelected(item)
    }
}
