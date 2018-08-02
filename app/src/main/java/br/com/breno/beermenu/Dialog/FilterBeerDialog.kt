package br.com.breno.beermenu.Dialog

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.TextInputLayout
import android.support.v4.app.DialogFragment
import android.support.v7.app.AlertDialog
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import br.com.breno.beermenu.MainActivity
import br.com.breno.beermenu.R
import br.com.breno.beermenu.Util
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class FilterBeerDialog: DialogFragment() {

    private lateinit var checkBox1: CheckBox
    private lateinit var checkBox2: CheckBox
    private lateinit var checkBox3: CheckBox
    private lateinit var checkBox4: CheckBox
    private lateinit var checkBox5: CheckBox
    private lateinit var checkBox6: CheckBox
    private lateinit var checkBox7: CheckBox
    private lateinit var checkBox8: CheckBox
    private lateinit var checkBox9: CheckBox
    private lateinit var checkBox10: CheckBox
    private lateinit var checkBox11: CheckBox
    private lateinit var checkBox12: CheckBox
    private lateinit var checkBox13: CheckBox
    private lateinit var checkBox14: CheckBox
    private lateinit var checkBox15: CheckBox

    private lateinit var editText2: EditText
    private lateinit var editText3: EditText
    private lateinit var editText4: EditText
    private lateinit var editText5: EditText
    private lateinit var editText6: EditText
    private lateinit var editText7: EditText
    private lateinit var editText8: EditText
    private lateinit var editText9: EditText
    private lateinit var editText10: EditText
    private lateinit var editText11: EditText
    private lateinit var editText12: EditText
    private lateinit var editText13: EditText
    private lateinit var editText14: EditText
    private lateinit var editText15: EditText

    private lateinit var textInputLayout10: TextInputLayout
    private lateinit var textInputLayout11: TextInputLayout
    private lateinit var textInputLayout15: TextInputLayout

    private inline fun <reified T> Gson.fromJson(json: String) = this.fromJson<T>(json, object: TypeToken<T>() {}.type)

    @SuppressLint("InflateParams")
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(activity!!)

        val inflater = activity!!.layoutInflater
        val view = inflater.inflate(R.layout.dialog_beer_filter, null)

        initializeComponents(view)
        fillComponents()

        builder.setView(view)
        return builder.create()
    }

    private fun initializeComponents(view: View){
        checkBox1 = view.findViewById(R.id.checkBox1)
        checkBox2 = view.findViewById(R.id.checkBox2)
        checkBox3 = view.findViewById(R.id.checkBox3)
        checkBox4 = view.findViewById(R.id.checkBox4)
        checkBox5 = view.findViewById(R.id.checkBox5)
        checkBox6 = view.findViewById(R.id.checkBox6)
        checkBox7 = view.findViewById(R.id.checkBox7)
        checkBox8 = view.findViewById(R.id.checkBox8)
        checkBox9 = view.findViewById(R.id.checkBox9)
        checkBox10 = view.findViewById(R.id.checkBox10)
        checkBox11 = view.findViewById(R.id.checkBox11)
        checkBox12 = view.findViewById(R.id.checkBox12)
        checkBox13 = view.findViewById(R.id.checkBox13)
        checkBox14 = view.findViewById(R.id.checkBox14)
        checkBox15 = view.findViewById(R.id.checkBox15)

        editText2 = view.findViewById(R.id.editText2)
        editText3 = view.findViewById(R.id.editText3)
        editText4 = view.findViewById(R.id.editText4)
        editText5 = view.findViewById(R.id.editText5)
        editText6 = view.findViewById(R.id.editText6)
        editText7 = view.findViewById(R.id.editText7)
        editText8 = view.findViewById(R.id.editText8)
        editText9 = view.findViewById(R.id.editText9)
        editText10 = view.findViewById(R.id.editText10)
        editText11 = view.findViewById(R.id.editText11)
        editText12 = view.findViewById(R.id.editText12)
        editText13 = view.findViewById(R.id.editText13)
        editText14 = view.findViewById(R.id.editText14)
        editText15 = view.findViewById(R.id.editText15)

        textInputLayout10 = view.findViewById(R.id.inputLayout10)
        textInputLayout11 = view.findViewById(R.id.inputLayout11)
        textInputLayout15 = view.findViewById(R.id.inputLayout15)

        val buttonSave = view.findViewById<Button>(R.id.buttonSave)
        val buttonCancel = view.findViewById<Button>(R.id.buttonCancel)

        checkBox2.setOnCheckedChangeListener{ _, isChecked: Boolean ->
            editText2.isEnabled = isChecked
        }

        checkBox3.setOnCheckedChangeListener{ _, isChecked: Boolean ->
            editText3.isEnabled = isChecked
        }

        checkBox4.setOnCheckedChangeListener{ _, isChecked: Boolean ->
            editText4.isEnabled = isChecked
        }

        checkBox5.setOnCheckedChangeListener{ _, isChecked: Boolean ->
            editText5.isEnabled = isChecked
        }

        checkBox6.setOnCheckedChangeListener{ _, isChecked: Boolean ->
            editText6.isEnabled = isChecked
        }

        checkBox7.setOnCheckedChangeListener{ _, isChecked: Boolean ->
            editText7.isEnabled = isChecked
        }

        checkBox8.setOnCheckedChangeListener{ _, isChecked: Boolean ->
            editText8.isEnabled = isChecked
        }

        checkBox9.setOnCheckedChangeListener{ _, isChecked: Boolean ->
            editText9.isEnabled = isChecked
        }

        checkBox10.setOnCheckedChangeListener{ _, isChecked: Boolean ->
            editText10.isEnabled = isChecked
        }

        checkBox11.setOnCheckedChangeListener{ _, isChecked: Boolean ->
            editText11.isEnabled = isChecked
        }

        checkBox12.setOnCheckedChangeListener{ _, isChecked: Boolean ->
            editText12.isEnabled = isChecked
        }

        checkBox13.setOnCheckedChangeListener{ _, isChecked: Boolean ->
            editText13.isEnabled = isChecked
        }

        checkBox14.setOnCheckedChangeListener{ _, isChecked: Boolean ->
            editText14.isEnabled = isChecked
        }

        checkBox15.setOnCheckedChangeListener{ _, isChecked: Boolean ->
            editText15.isEnabled = isChecked
        }

        buttonCancel.setOnClickListener { dismiss() }
        buttonSave.setOnClickListener {
            if(validate()){
                val intent = Intent()
                if(checkBox1.isChecked) intent.putExtra(getString(R.string.favorite), true)
                if(checkBox2.isChecked && editText2.text.trim().isNotEmpty()) intent.putExtra(Util.Filter.ABV_GT, editText2.text)
                if(checkBox3.isChecked && editText3.text.trim().isNotEmpty()) intent.putExtra(Util.Filter.ABV_LT, editText3.text)
                if(checkBox4.isChecked && editText4.text.trim().isNotEmpty()) intent.putExtra(Util.Filter.IBU_GT, editText4.text)
                if(checkBox5.isChecked && editText5.text.trim().isNotEmpty()) intent.putExtra(Util.Filter.IBU_LT, editText5.text)
                if(checkBox6.isChecked && editText6.text.trim().isNotEmpty()) intent.putExtra(Util.Filter.EBC_GT, editText6.text)
                if(checkBox7.isChecked && editText7.text.trim().isNotEmpty()) intent.putExtra(Util.Filter.EBC_LT, editText7.text)
                if(checkBox8.isChecked && editText8.text.trim().isNotEmpty()) intent.putExtra(Util.Filter.BEER_NAME, editText8.text)
                if(checkBox9.isChecked && editText9.text.trim().isNotEmpty()) intent.putExtra(Util.Filter.YEAST, editText9.text)
                if(checkBox10.isChecked && editText10.text.trim().isNotEmpty()) intent.putExtra(Util.Filter.BREWED_BEFORE, editText10.text)
                if(checkBox11.isChecked && editText11.text.trim().isNotEmpty()) intent.putExtra(Util.Filter.BREWED_AFTER, editText11.text)
                if(checkBox12.isChecked && editText12.text.trim().isNotEmpty()) intent.putExtra(Util.Filter.HOPS, editText12.text)
                if(checkBox13.isChecked && editText13.text.trim().isNotEmpty()) intent.putExtra(Util.Filter.MALT, editText13.text)
                if(checkBox14.isChecked && editText14.text.trim().isNotEmpty()) intent.putExtra(Util.Filter.FOOD, editText14.text)
                if(checkBox15.isChecked && editText15.text.trim().isNotEmpty()) intent.putExtra(Util.Filter.IDS, editText15.text)


                (activity as MainActivity).getItemsFilter(targetRequestCode, Util.FILTER_BEER, intent)

//                targetFragment?.onActivityResult(targetRequestCode, Util.FILTER_BEER, activity?.intent)
                dismiss()
            }
        }
    }

    private fun validate(): Boolean{
        val result1: Boolean = editText10.text.trim().isEmpty() || editText10.text.matches("\\d{2}-\\d{4}".toRegex())
        val result2: Boolean = editText11.text.trim().isEmpty() || editText11.text.matches("\\d{2}-\\d{4}".toRegex())
        val result3: Boolean = editText15.text.trim().isEmpty() || editText15.text.matches("[0-9]+(\\|[0-9]+)*".toRegex())

        if(!result1) textInputLayout10.error = getString(R.string.invalide_input) else textInputLayout10.isErrorEnabled = false
        if(!result2) textInputLayout11.error = getString(R.string.invalide_input) else textInputLayout11.isErrorEnabled = false
        if(!result3) textInputLayout15.error = getString(R.string.invalide_input) else textInputLayout15.isErrorEnabled = false

        return result1 && result2 && result3
    }

    private fun fillComponents(){
        if (arguments != null && arguments!!.get(getString(R.string.filter)) != null) {

            val listFilter: HashMap<String, String> = Gson().fromJson(arguments!!.getString(getString(R.string.filter)))

            if(listFilter.containsKey(getString(R.string.favorite))){
                checkBox1.isChecked = true
            }

            if(listFilter.containsKey(Util.Filter.ABV_GT)){
                checkBox2.isChecked = true
                editText2.setText(listFilter[Util.Filter.ABV_GT])
            }

            if(listFilter.containsKey(Util.Filter.ABV_LT)){
                checkBox3.isChecked = true
                editText3.setText(listFilter[Util.Filter.ABV_LT])
            }

            if(listFilter.containsKey(Util.Filter.IBU_GT)){
                checkBox4.isChecked = true
                editText4.setText(listFilter[Util.Filter.IBU_GT])
            }

            if(listFilter.containsKey(Util.Filter.IBU_LT)){
                checkBox5.isChecked = true
                editText5.setText(listFilter[Util.Filter.IBU_LT])
            }

            if(listFilter.containsKey(Util.Filter.EBC_GT)){
                checkBox6.isChecked = true
                editText6.setText(listFilter[Util.Filter.EBC_GT])
            }

            if(listFilter.containsKey(Util.Filter.EBC_LT)){
                checkBox7.isChecked = true
                editText7.setText(listFilter[Util.Filter.EBC_LT])
            }

            if(listFilter.containsKey(Util.Filter.BEER_NAME)){
                checkBox8.isChecked = true
                editText8.setText(listFilter[Util.Filter.BEER_NAME])
            }

            if(listFilter.containsKey(Util.Filter.YEAST)){
                checkBox9.isChecked = true
                editText9.setText(listFilter[Util.Filter.YEAST])
            }

            if(listFilter.containsKey(Util.Filter.BREWED_BEFORE)){
                checkBox10.isChecked = true
                editText10.setText(listFilter[Util.Filter.BREWED_BEFORE])
            }

            if(listFilter.containsKey(Util.Filter.BREWED_AFTER)){
                checkBox11.isChecked = true
                editText11.setText(listFilter[Util.Filter.BREWED_AFTER])
            }

            if(listFilter.containsKey(Util.Filter.HOPS)){
                checkBox12.isChecked = true
                editText12.setText(listFilter[Util.Filter.HOPS])
            }

            if(listFilter.containsKey(Util.Filter.MALT)){
                checkBox13.isChecked = true
                editText13.setText(listFilter[Util.Filter.MALT])
            }

            if(listFilter.containsKey(Util.Filter.FOOD)){
                checkBox14.isChecked = true
                editText14.setText(listFilter[Util.Filter.FOOD])
            }

            if(listFilter.containsKey(Util.Filter.IDS)){
                if(listFilter.containsKey(getString(R.string.favorite))){
                    val filterFavorite = listFilter[getString(R.string.favorite)]
                    val filterIds = listFilter[Util.Filter.IDS]

                    var resultFilterIds = ""

                    if(!filterFavorite?.equals(filterIds)!!)
                        resultFilterIds = filterIds?.replace("$filterFavorite|", "")!!

                    if(resultFilterIds.isNotEmpty()){
                        checkBox15.isChecked = true
                        editText15.setText(resultFilterIds)
                    }

                }else{
                    checkBox15.isChecked = true
                    editText15.setText(listFilter[Util.Filter.IDS])
                }
            }
        }
    }
}