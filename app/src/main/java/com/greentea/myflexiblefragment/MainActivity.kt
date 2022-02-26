package com.greentea.myflexiblefragment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //melakukan pemanggilan home fragment
        val mFragmentManager = supportFragmentManager
        val mHomeFragment = HomeFragment()

        //mencari kelas HomeFragment
        val fragment = mFragmentManager.findFragmentByTag(HomeFragment::class.java.simpleName)

        //mencocokkan apakah fragment sama dengan HomeFragment
        if(fragment !is HomeFragment){
            Log.d("MyFlexibleFragment", "Fragment Name :" + HomeFragment::class.java.simpleName)
            mFragmentManager
                .beginTransaction() //memulai transaksi
                .add(R.id.frame_container, mHomeFragment, HomeFragment::class.java.simpleName) //menambahkan objek fragment ke dalam layout container -> fragment
                .commit() //mengeksekusi
        }
    }
}