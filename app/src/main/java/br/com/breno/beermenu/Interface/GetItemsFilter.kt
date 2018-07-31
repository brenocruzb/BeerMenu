package br.com.breno.beermenu.Interface

import android.content.Intent

interface GetItemsFilter {
    fun getItemsFilter(requestCode: Int, resultCode: Int, data: Intent)
}