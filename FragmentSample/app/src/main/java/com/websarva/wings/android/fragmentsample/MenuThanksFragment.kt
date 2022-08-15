package com.websarva.wings.android.fragmentsample

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener

class MenuThanksFragment : Fragment() {
    private var _isLayoutXLarge = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val menuListFragment = fragmentManager?.findFragmentById(R.id.fragmentMenuList)
        Log.i("aaa","${menuListFragment}")
        if(menuListFragment == null){
            _isLayoutXLarge = false
            Log.i("aaa","normal size fix(thanks)")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_menu_thanks, container, false)
        val extras: Bundle?
        if(_isLayoutXLarge){
            Log.i("aaa","large onCreateView()")
            extras = arguments
        } else {
            Log.i("aaa","normal onCreateView()")
            val intent = activity?.intent
            extras = intent?.extras
        }
        val menuName = extras?.getString("menuName")
        val menuPrice = extras?.getString("menuPrice")
        val tvMenuName = view.findViewById<TextView>(R.id.tvMenuName)
        val tvMenuPrice = view.findViewById<TextView>(R.id.tvMenuPrice)

        tvMenuName.text = menuName
        tvMenuPrice.text = menuPrice

        val btBackButton = view.findViewById<Button>(R.id.btBackButton)
        btBackButton.setOnClickListener(ButtonClickListener())

        return view
    }

    private inner class ButtonClickListener : View.OnClickListener{
        override fun onClick(view: View) {
            Log.i("aaa","ButtonClickListener()")
            if(_isLayoutXLarge){
                Log.i("aaa","large ButtonClickListener()")
                val transaction = fragmentManager?.beginTransaction()
                transaction?.remove(this@MenuThanksFragment)
                transaction?.commit()
            } else {
                Log.i("aaa","normal ButtonClickListener()")
                activity?.finish()
            }
        }
    }
}