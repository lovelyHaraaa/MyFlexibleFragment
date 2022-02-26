package com.greentea.myflexiblefragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.fragment.app.DialogFragment

/**
 * A simple [Fragment] subclass.
 * Use the [OpenDialogFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class OpenDialogFragment : DialogFragment() {

    private lateinit var btnChoose: Button
    private lateinit var btnClose: Button
    private lateinit var rgOptions: RadioGroup
    private lateinit var rbSaf: RadioButton
    private lateinit var rbMou: RadioButton
    private lateinit var rbLvg: RadioButton
    private lateinit var rbMoyes: RadioButton

    private var optionDialogueListener: OnOptionDialogListener? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_open_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //menyamakan id
        btnChoose = view.findViewById(R.id.btn_choose)
        btnClose = view.findViewById(R.id.btn_close)
        rgOptions = view.findViewById(R.id.rg_options)
        rbSaf = view.findViewById(R.id.rb_saf)
        rbLvg = view.findViewById(R.id.rb_lvg)
        rbMou = view.findViewById(R.id.rb_mou)
        rbMoyes = view.findViewById(R.id.rb_moyes)

        //ketika memilih choose, maka nanti akan mengembalikan nilai sesuai yang dipilih
        btnChoose.setOnClickListener{
            val checkedRadioButtonId = rgOptions.checkedRadioButtonId
            if(checkedRadioButtonId != -1){
                var coach: String? = when(checkedRadioButtonId){
                    R.id.rb_saf -> rbSaf.text.toString().trim()
                    R.id.rb_mou -> rbMou.text.toString().trim()
                    R.id.rb_lvg -> rbLvg.text.toString().trim()
                    R.id.rb_moyes -> rbMoyes.text.toString().trim()
                    else -> null
                }
                optionDialogueListener?.onOptionChosen(coach)
                dialog?.dismiss()
            }
        }

        //jika kita memilih close, maka dialog akan ditutup
        btnClose.setOnClickListener{
            dialog?.cancel()
        }
    }

    //mengelola fragment ketika di panggil atau dimatikan
    override fun onAttach(context: Context) {
        super.onAttach(context)

        val fragment = parentFragment

        if(fragment is DetailCategoryFragment){
            this.optionDialogueListener = fragment.optionDialogListener
        }
    }

    override fun onDetach() {
        super.onDetach()
        this.optionDialogueListener = null
    }

    interface OnOptionDialogListener {
        fun onOptionChosen(text: String?)
    }

}