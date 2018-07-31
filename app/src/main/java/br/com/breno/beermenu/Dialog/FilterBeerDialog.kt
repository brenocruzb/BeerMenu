package br.com.breno.beermenu.Dialog

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.TextInputLayout
import android.support.v4.app.DialogFragment
import android.support.v7.app.AlertDialog
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import br.com.breno.beermenu.MainActivity
import br.com.breno.beermenu.R
import br.com.breno.beermenu.Util


class FilterBeerDialog: DialogFragment() {

    private lateinit var editText3: EditText
    private lateinit var editText4: EditText

    private lateinit var textInputLayout3: TextInputLayout
    private lateinit var textInputLayout4: TextInputLayout

    @SuppressLint("InflateParams")
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(activity!!)

        val inflater = activity!!.layoutInflater
        val view = inflater.inflate(R.layout.dialog_beer_filter, null)

        val checkBox1 = view.findViewById<CheckBox>(R.id.checkBox1)
        val checkBox2 = view.findViewById<CheckBox>(R.id.checkBox2)
        val checkBox3 = view.findViewById<CheckBox>(R.id.checkBox3)
        val checkBox4 = view.findViewById<CheckBox>(R.id.checkBox4)

        val editText1 = view.findViewById<EditText>(R.id.editText1)
        val editText2 = view.findViewById<EditText>(R.id.editText2)

        editText3 = view.findViewById(R.id.editText3)
        editText4 = view.findViewById(R.id.editText4)

        textInputLayout3 = view.findViewById(R.id.inputLayout3)
        textInputLayout4 = view.findViewById(R.id.inputLayout4)

        val buttonSave = view.findViewById<Button>(R.id.buttonSave)
        val buttonCancel = view.findViewById<Button>(R.id.buttonCancel)

        checkBox1.setOnCheckedChangeListener{ _, isChecked: Boolean ->
            editText1.isEnabled = isChecked
        }

        checkBox2.setOnCheckedChangeListener{ _, isChecked: Boolean ->
            editText2.isEnabled = isChecked
        }

        checkBox3.setOnCheckedChangeListener{ _, isChecked: Boolean ->
            editText3.isEnabled = isChecked
        }

        checkBox4.setOnCheckedChangeListener{ _, isChecked: Boolean ->
            editText4.isEnabled = isChecked
        }

        buttonCancel.setOnClickListener { dismiss() }
        buttonSave.setOnClickListener {
            if(validate()){
                val intent = Intent()
                if(checkBox1.isChecked && editText1.text.trim().isNotEmpty()) intent.putExtra(getString(R.string.name), editText1.text)
                if(checkBox2.isChecked && editText2.text.trim().isNotEmpty()) intent.putExtra(getString(R.string.yeast), editText2.text)
                if(checkBox3.isChecked && editText3.text.trim().isNotEmpty()) intent.putExtra(getString(R.string.brewed_before), editText3.text)
                if(checkBox4.isChecked && editText4.text.trim().isNotEmpty()) intent.putExtra(getString(R.string.brewed_after), editText4.text)

                (activity as MainActivity).getItemsFilter(targetRequestCode, Util.FILTER_BEER, intent)

//                targetFragment?.onActivityResult(targetRequestCode, Util.FILTER_BEER, activity?.intent)
                dismiss()
            }
        }

        builder.setView(view)
        return builder.create()
    }

    private fun validate(): Boolean{
        val result1: Boolean = editText3.text.trim().isEmpty() || (editText3.text.trim().length == 7 && editText3.text[2] == '-' && editText3.text.substring(0, 2).toIntOrNull() != null && editText3.text.substring(3, editText3.text.length).toIntOrNull() != null)
        val result2: Boolean = editText4.text.trim().isEmpty() || (editText4.text.trim().length == 7 && editText4.text[2] == '-' && editText4.text.substring(0, 2).toIntOrNull() != null && editText4.text.substring(3, editText4.text.length).toIntOrNull() != null)

        if(!result1) textInputLayout3.error = getString(R.string.invalide_input) else textInputLayout3.isErrorEnabled = false
        if(!result2) textInputLayout4.error = getString(R.string.invalide_input) else textInputLayout4.isErrorEnabled = false

        return result1 && result2
    }
}