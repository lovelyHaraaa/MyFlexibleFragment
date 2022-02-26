package com.greentea.myflexiblefragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment(), View.OnClickListener {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment -> menyesuaikan tampilan layout .xml menjadi objek view
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    //pada activity menggunakan onCreated(), namun pada fragment menggunakan onViewCreated()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //menyamakan id, jangan lupa karena ini fragment, ada tambahan view
        val btnCategory: Button = view.findViewById(R.id.btn_category)
        btnCategory.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        if(p0?.id == R.id.btn_category){
            val mCategoryFragment = CategoryFragment()
            val mFragmentManager = parentFragmentManager
            mFragmentManager
                .beginTransaction()
                .apply {
                    //menempelkan sebuah fragment baru, yang awalnya Home Fragment, menjadi Category Fragment
                    replace(R.id.frame_container, mCategoryFragment, CategoryFragment::class.java.simpleName)

                    //supaya pas pencet back bisa kembali ke object fragment sebelumnya, yaitu Home Fragment
                    //jika dihapus maka aplikasi ketika ditekan back akan mengulang kembali aplikasi
                    addToBackStack(null)
                    commit()
                }
        }
    }
}