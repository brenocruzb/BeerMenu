package br.com.breno.beermenu.Model.Dao

import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import br.com.breno.beermenu.MVP

class DbCore constructor(private val presenter: MVP.PresenterInter) : SQLiteOpenHelper(presenter.getContext(), NAME_DB, null, 1) {

    private var sInstance: DbCore? = null

    companion object {
        const val NAME_DB = "BeerMenu"
    }

    @Synchronized
    fun instance(): DbCore {

        if (sInstance == null) {
            sInstance = DbCore(presenter)
        }
        return sInstance as DbCore
    }

    override fun onCreate(db: SQLiteDatabase?) {
        /*ID beer*/
        db?.execSQL("CREATE TABLE beer( id_beer INTEGER NOT NULL)")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE if EXISTS beer")
        onCreate(db)
    }
}