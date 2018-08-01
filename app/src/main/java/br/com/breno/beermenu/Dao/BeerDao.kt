package br.com.breno.beermenu.Dao

import android.annotation.TargetApi
import android.content.ContentValues
import android.content.Context
import android.os.Build
import android.util.Log

class BeerDao constructor(context: Context) {
    private val dbCore: DbCore = DbCore(context).instance()
    private val nameDao = "Beer"

    @TargetApi(Build.VERSION_CODES.KITKAT)
    fun insert(idBeer: Int): Long{
        try {
            dbCore.writableDatabase.use { db ->
                val values = ContentValues()
                values.put("id_beer", idBeer)
                return db.insert("beer", null, values)
            }
        } catch (e: Exception) {
            Log.e("Error", "Insert $nameDao - " + e.message)
        }
        return 0
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    fun delete(idBeer: Int): Boolean{
        try {
            dbCore.writableDatabase.use { db ->
                return db.delete("beer", "id_beer = $idBeer", null) > 0
            }
        }catch (e: Exception){
            Log.e("Error", "delete $nameDao - " + e.message)
        }
        return false
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    fun getList(sql: String): ArrayList<Int>{
        val listIdBeer = ArrayList<Int>()
        dbCore.writableDatabase.use { db ->
            db.rawQuery(sql, null).use { data ->

                if (data.count > 0) {
                    data.moveToFirst()
                    do {
                        try {
                            val idBeer = data.getInt(data.getColumnIndex("id_beer"))
                            listIdBeer.add(idBeer)
                        } catch (e: Exception) {
                            Log.e("Error", "Get all $nameDao - " + e.message)
                        }

                    } while (data.moveToNext())
                }
            }
        }
        return listIdBeer
    }
}