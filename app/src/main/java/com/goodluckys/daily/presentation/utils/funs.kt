package com.goodluckys.daily.presentation.utils

import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.goodluckys.daily.R


fun Fragment.showToast(message:String){
    Toast.makeText(this.context,message,Toast.LENGTH_SHORT).show()
}