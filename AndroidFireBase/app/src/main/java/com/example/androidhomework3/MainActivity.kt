package com.example.androidhomework3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.androidhomework3.fragments.BookFragment
import com.example.androidhomework3.interfaces.IFragmentActivityCommunication
import com.google.firebase.FirebaseApp

class MainActivity : AppCompatActivity(),IFragmentActivityCommunication {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        onAddInitialFragment()
    }

    fun onAddInitialFragment(){
        val fragmentManager = supportFragmentManager
        val transaction : FragmentTransaction = fragmentManager.beginTransaction()
        transaction.add(R.id.f_container,BookFragment())
        transaction.commit()
    }

    override fun onReplaceFragment(tag:String){
        val fragmentManager = supportFragmentManager
        val fragment = Fragment()
        when(tag){
            //add cases
            else->{
                //to-do
            }
        }
        val transaction : FragmentTransaction = fragmentManager.beginTransaction()
        transaction.add(R.id.f_container,fragment)
        transaction.commit()
    }
}