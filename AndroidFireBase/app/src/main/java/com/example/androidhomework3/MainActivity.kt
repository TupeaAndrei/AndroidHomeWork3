package com.example.androidhomework3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.androidhomework3.fragments.BookFragment
import com.example.androidhomework3.fragments.FullItemFragment
import com.example.androidhomework3.interfaces.IFirebaseAdapterComunication
import com.example.androidhomework3.interfaces.IFragmentActivityCommunication
import com.example.androidhomework3.models.Book
import com.google.firebase.FirebaseApp

class MainActivity : AppCompatActivity(),IFragmentActivityCommunication,IFirebaseAdapterComunication {
    private var loadedFragment = BookFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        onAddInitialFragment()
    }

    fun onAddInitialFragment(){
        val fragmentManager = supportFragmentManager
        val transaction : FragmentTransaction = fragmentManager.beginTransaction()
        val currentFragment = BookFragment()
        transaction.add(R.id.f_container,currentFragment,"book_fragment").addToBackStack(null)
        transaction.commit()

        loadedFragment = currentFragment
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
        transaction.add(R.id.f_container,fragment,"book_fragment")
        transaction.commit()
    }

    override fun onFullItemFragment(title: String?, author: String?, description: String?) {
        //start process
        val fragmentManager = supportFragmentManager
        val transaction:FragmentTransaction = fragmentManager.beginTransaction()
        //initialize fragment + bundle
        val fragment = FullItemFragment()
        val bundle = Bundle()
        bundle.putString("title",title);
        bundle.putString("author",author);
        bundle.putString("description",description);
        fragment.arguments = bundle
        //finish transaction
        transaction.replace(R.id.f_container,fragment).addToBackStack(null)
        transaction.commit()
    }

    override fun deleteItem(book: Book?) {
        loadedFragment.deleteItem(book);
    }

}