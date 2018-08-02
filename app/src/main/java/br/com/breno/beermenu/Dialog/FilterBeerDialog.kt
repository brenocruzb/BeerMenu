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

    /**Componentes do dialog**/
    private lateinit var checkBoxFavorite: CheckBox
    private lateinit var checkBoxAbvGt: CheckBox
    private lateinit var checkBoxAbvLt: CheckBox
    private lateinit var checkBoxIbuGt: CheckBox
    private lateinit var checkBoxIbuLt: CheckBox
    private lateinit var checkBoxEbcGt: CheckBox
    private lateinit var checkBoxEbcLt: CheckBox
    private lateinit var checkBoxName: CheckBox
    private lateinit var checkBoxYeast: CheckBox
    private lateinit var checkBoxBrewedBefore: CheckBox
    private lateinit var checkBoxBrewedAfter: CheckBox
    private lateinit var checkBoxHops: CheckBox
    private lateinit var checkBoxMalt: CheckBox
    private lateinit var checkBoxFood: CheckBox
    private lateinit var checkBoxIds: CheckBox

    private lateinit var editTextAbvGt: EditText
    private lateinit var editTextAbvLt: EditText
    private lateinit var editTextIbuGt: EditText
    private lateinit var editTextIbuLt: EditText
    private lateinit var editTextEbcGt: EditText
    private lateinit var editTextEbcLt: EditText
    private lateinit var editTextName: EditText
    private lateinit var editTextYeast: EditText
    private lateinit var editTextBrewedBefore: EditText
    private lateinit var editTextBrewedAfter: EditText
    private lateinit var editTextHops: EditText
    private lateinit var editTextMalt: EditText
    private lateinit var editTextFood: EditText
    private lateinit var editTextIds: EditText

    private lateinit var inputLayoutBrewedBefore: TextInputLayout
    private lateinit var inputLayoutBrewedAfter: TextInputLayout
    private lateinit var inputLayoutIds: TextInputLayout

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
        checkBoxFavorite = view.findViewById(R.id.checkBoxFavorite)
        checkBoxAbvGt = view.findViewById(R.id.checkBoxAbvGt)
        checkBoxAbvLt = view.findViewById(R.id.checkBoxAbvLt)
        checkBoxIbuGt = view.findViewById(R.id.checkBoxIbuGt)
        checkBoxIbuLt = view.findViewById(R.id.checkBoxIbuLt)
        checkBoxEbcGt = view.findViewById(R.id.checkBoxEbcGt)
        checkBoxEbcLt = view.findViewById(R.id.checkBoxEbcLt)
        checkBoxName = view.findViewById(R.id.checkBoxName)
        checkBoxYeast = view.findViewById(R.id.checkBoxYeast)
        checkBoxBrewedBefore = view.findViewById(R.id.checkBoxBrewedBefore)
        checkBoxBrewedAfter = view.findViewById(R.id.checkBoxBrewedAfter)
        checkBoxHops = view.findViewById(R.id.checkBoxHops)
        checkBoxMalt = view.findViewById(R.id.checkBoxMalt)
        checkBoxFood = view.findViewById(R.id.checkBoxFood)
        checkBoxIds = view.findViewById(R.id.checkBoxIds)

        editTextAbvGt = view.findViewById(R.id.editTextAbvGt)
        editTextAbvLt = view.findViewById(R.id.editTextAbvLt)
        editTextIbuGt = view.findViewById(R.id.editTextIbuGt)
        editTextIbuLt = view.findViewById(R.id.editTextIbuLt)
        editTextEbcGt = view.findViewById(R.id.editTextEbcGt)
        editTextEbcLt = view.findViewById(R.id.editTextEbcLt)
        editTextName = view.findViewById(R.id.editTextName)
        editTextYeast = view.findViewById(R.id.editTextYeast)
        editTextBrewedBefore = view.findViewById(R.id.editTextBrewedBefore)
        editTextBrewedAfter = view.findViewById(R.id.editTextBrewedAfter)
        editTextHops = view.findViewById(R.id.editTextHops)
        editTextMalt = view.findViewById(R.id.editTextMalt)
        editTextFood = view.findViewById(R.id.editTextFood)
        editTextIds = view.findViewById(R.id.editTextIds)

        inputLayoutBrewedBefore = view.findViewById(R.id.inputLayoutBrewedBefore)
        inputLayoutBrewedAfter = view.findViewById(R.id.inputLayoutBrewedAfter)
        inputLayoutIds = view.findViewById(R.id.inputLayoutIds)

        val buttonSave = view.findViewById<Button>(R.id.buttonSave)
        val buttonCancel = view.findViewById<Button>(R.id.buttonCancel)

        //Caso um CheckBox seja marcado, seu campo de texto é habilitado.
        checkBoxAbvGt.setOnCheckedChangeListener{ _, isChecked: Boolean ->
            editTextAbvGt.isEnabled = isChecked
        }

        checkBoxAbvLt.setOnCheckedChangeListener{ _, isChecked: Boolean ->
            editTextAbvLt.isEnabled = isChecked
        }

        checkBoxIbuGt.setOnCheckedChangeListener{ _, isChecked: Boolean ->
            editTextIbuGt.isEnabled = isChecked
        }

        checkBoxIbuLt.setOnCheckedChangeListener{ _, isChecked: Boolean ->
            editTextIbuLt.isEnabled = isChecked
        }

        checkBoxEbcGt.setOnCheckedChangeListener{ _, isChecked: Boolean ->
            editTextEbcGt.isEnabled = isChecked
        }

        checkBoxEbcLt.setOnCheckedChangeListener{ _, isChecked: Boolean ->
            editTextEbcLt.isEnabled = isChecked
        }

        checkBoxName.setOnCheckedChangeListener{ _, isChecked: Boolean ->
            editTextName.isEnabled = isChecked
        }

        checkBoxYeast.setOnCheckedChangeListener{ _, isChecked: Boolean ->
            editTextYeast.isEnabled = isChecked
        }

        checkBoxBrewedBefore.setOnCheckedChangeListener{ _, isChecked: Boolean ->
            editTextBrewedBefore.isEnabled = isChecked
        }

        checkBoxBrewedAfter.setOnCheckedChangeListener{ _, isChecked: Boolean ->
            editTextBrewedAfter.isEnabled = isChecked
        }

        checkBoxHops.setOnCheckedChangeListener{ _, isChecked: Boolean ->
            editTextHops.isEnabled = isChecked
        }

        checkBoxMalt.setOnCheckedChangeListener{ _, isChecked: Boolean ->
            editTextMalt.isEnabled = isChecked
        }

        checkBoxFood.setOnCheckedChangeListener{ _, isChecked: Boolean ->
            editTextFood.isEnabled = isChecked
        }

        checkBoxIds.setOnCheckedChangeListener{ _, isChecked: Boolean ->
            editTextIds.isEnabled = isChecked
        }

        buttonCancel.setOnClickListener { dismiss() }
        buttonSave.setOnClickListener {
            if(validate()){
                //Os checkBox marcados que seus campos de textos não estejam vazios serão adicionados aos seus respectivos filtros.
                val intent = Intent()
                if(checkBoxFavorite.isChecked) intent.putExtra(getString(R.string.favorite), true)
                if(checkBoxAbvGt.isChecked && editTextAbvGt.text.trim().isNotEmpty()) intent.putExtra(Util.Filter.ABV_GT, editTextAbvGt.text)
                if(checkBoxAbvLt.isChecked && editTextAbvLt.text.trim().isNotEmpty()) intent.putExtra(Util.Filter.ABV_LT, editTextAbvLt.text)
                if(checkBoxIbuGt.isChecked && editTextIbuGt.text.trim().isNotEmpty()) intent.putExtra(Util.Filter.IBU_GT, editTextIbuGt.text)
                if(checkBoxIbuLt.isChecked && editTextIbuLt.text.trim().isNotEmpty()) intent.putExtra(Util.Filter.IBU_LT, editTextIbuLt.text)
                if(checkBoxEbcGt.isChecked && editTextEbcGt.text.trim().isNotEmpty()) intent.putExtra(Util.Filter.EBC_GT, editTextEbcGt.text)
                if(checkBoxEbcLt.isChecked && editTextEbcLt.text.trim().isNotEmpty()) intent.putExtra(Util.Filter.EBC_LT, editTextEbcLt.text)
                if(checkBoxName.isChecked && editTextName.text.trim().isNotEmpty()) intent.putExtra(Util.Filter.BEER_NAME, editTextName.text)
                if(checkBoxYeast.isChecked && editTextYeast.text.trim().isNotEmpty()) intent.putExtra(Util.Filter.YEAST, editTextYeast.text)
                if(checkBoxBrewedBefore.isChecked && editTextBrewedBefore.text.trim().isNotEmpty()) intent.putExtra(Util.Filter.BREWED_BEFORE, editTextBrewedBefore.text)
                if(checkBoxBrewedAfter.isChecked && editTextBrewedAfter.text.trim().isNotEmpty()) intent.putExtra(Util.Filter.BREWED_AFTER, editTextBrewedAfter.text)
                if(checkBoxHops.isChecked && editTextHops.text.trim().isNotEmpty()) intent.putExtra(Util.Filter.HOPS, editTextHops.text)
                if(checkBoxMalt.isChecked && editTextMalt.text.trim().isNotEmpty()) intent.putExtra(Util.Filter.MALT, editTextMalt.text)
                if(checkBoxFood.isChecked && editTextFood.text.trim().isNotEmpty()) intent.putExtra(Util.Filter.FOOD, editTextFood.text)
                if(checkBoxIds.isChecked && editTextIds.text.trim().isNotEmpty()) intent.putExtra(Util.Filter.IDS, editTextIds.text)

                //Manda os filtros selecionados para a mainActivity
                (activity as MainActivity).getItemsFilter(targetRequestCode, Util.FILTER_BEER, intent)
                dismiss()
            }
        }
    }

    /**Método responsável por validar informações de campos que tenham algum tipo de restrição**/
    private fun validate(): Boolean{
        val result1: Boolean = editTextBrewedBefore.text.trim().isEmpty() || editTextBrewedBefore.text.matches("\\d{2}-\\d{4}".toRegex())
        val result2: Boolean = editTextBrewedAfter.text.trim().isEmpty() || editTextBrewedAfter.text.matches("\\d{2}-\\d{4}".toRegex())
        val result3: Boolean = editTextIds.text.trim().isEmpty() || editTextIds.text.matches("[0-9]+(\\|[0-9]+)*".toRegex())

        if(!result1) inputLayoutBrewedBefore.error = getString(R.string.invalide_input) else inputLayoutBrewedBefore.isErrorEnabled = false
        if(!result2) inputLayoutBrewedAfter.error = getString(R.string.invalide_input) else inputLayoutBrewedAfter.isErrorEnabled = false
        if(!result3) inputLayoutIds.error = getString(R.string.invalide_input) else inputLayoutIds.isErrorEnabled = false

        return result1 && result2 && result3
    }

    /**Método responsável por preencher os componentes com os filtros existentes no momento.**/
    private fun fillComponents(){
        if (arguments != null && arguments!!.get(getString(R.string.filter)) != null) {

            val listFilter: HashMap<String, String> = Gson().fromJson(arguments!!.getString(getString(R.string.filter)))

            if(listFilter.containsKey(getString(R.string.favorite))){
                checkBoxFavorite.isChecked = true
            }

            if(listFilter.containsKey(Util.Filter.ABV_GT)){
                checkBoxAbvGt.isChecked = true
                editTextAbvGt.setText(listFilter[Util.Filter.ABV_GT])
            }

            if(listFilter.containsKey(Util.Filter.ABV_LT)){
                checkBoxAbvLt.isChecked = true
                editTextAbvLt.setText(listFilter[Util.Filter.ABV_LT])
            }

            if(listFilter.containsKey(Util.Filter.IBU_GT)){
                checkBoxIbuGt.isChecked = true
                editTextIbuGt.setText(listFilter[Util.Filter.IBU_GT])
            }

            if(listFilter.containsKey(Util.Filter.IBU_LT)){
                checkBoxIbuLt.isChecked = true
                editTextIbuLt.setText(listFilter[Util.Filter.IBU_LT])
            }

            if(listFilter.containsKey(Util.Filter.EBC_GT)){
                checkBoxEbcGt.isChecked = true
                editTextEbcGt.setText(listFilter[Util.Filter.EBC_GT])
            }

            if(listFilter.containsKey(Util.Filter.EBC_LT)){
                checkBoxEbcLt.isChecked = true
                editTextEbcLt.setText(listFilter[Util.Filter.EBC_LT])
            }

            if(listFilter.containsKey(Util.Filter.BEER_NAME)){
                checkBoxName.isChecked = true
                editTextName.setText(listFilter[Util.Filter.BEER_NAME])
            }

            if(listFilter.containsKey(Util.Filter.YEAST)){
                checkBoxYeast.isChecked = true
                editTextYeast.setText(listFilter[Util.Filter.YEAST])
            }

            if(listFilter.containsKey(Util.Filter.BREWED_BEFORE)){
                checkBoxBrewedBefore.isChecked = true
                editTextBrewedBefore.setText(listFilter[Util.Filter.BREWED_BEFORE])
            }

            if(listFilter.containsKey(Util.Filter.BREWED_AFTER)){
                checkBoxBrewedAfter.isChecked = true
                editTextBrewedAfter.setText(listFilter[Util.Filter.BREWED_AFTER])
            }

            if(listFilter.containsKey(Util.Filter.HOPS)){
                checkBoxHops.isChecked = true
                editTextHops.setText(listFilter[Util.Filter.HOPS])
            }

            if(listFilter.containsKey(Util.Filter.MALT)){
                checkBoxMalt.isChecked = true
                editTextMalt.setText(listFilter[Util.Filter.MALT])
            }

            if(listFilter.containsKey(Util.Filter.FOOD)){
                checkBoxFood.isChecked = true
                editTextFood.setText(listFilter[Util.Filter.FOOD])
            }

            if(listFilter.containsKey(Util.Filter.IDS)){
                if(listFilter.containsKey(getString(R.string.favorite))){
                    val filterFavorite = listFilter[getString(R.string.favorite)]
                    val filterIds = listFilter[Util.Filter.IDS]

                    var resultFilterIds = ""

                    if(!filterFavorite?.equals(filterIds)!!)
                        resultFilterIds = filterIds?.replace("$filterFavorite|", "")!!

                    if(resultFilterIds.isNotEmpty()){
                        checkBoxIds.isChecked = true
                        editTextIds.setText(resultFilterIds)
                    }

                }else{
                    checkBoxIds.isChecked = true
                    editTextIds.setText(listFilter[Util.Filter.IDS])
                }
            }
        }
    }
}