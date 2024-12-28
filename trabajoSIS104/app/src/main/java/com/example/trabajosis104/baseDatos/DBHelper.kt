package com.example.trabajosis104.baseDatos
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context): SQLiteOpenHelper(context, DATABASE_NOMBRE, null, DATABASE_VERSION) {
    companion object{
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NOMBRE = "lugares.db"
        private const val TABLE_NOMBRE = "lugares"
        private const val COLUMN_ID = "id"
        private const val COLUMN_NOMBRE = "nombre"
        private const val COLUMN_DESCRIPCION = "descripcion"
    }

    override fun onCreate(p0: SQLiteDatabase?) {
        val createTable = ("CREATE TABLE $TABLE_NOMBRE("
                    + "$COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "$COLUMN_NOMBRE TEXT,"
                    + "$COLUMN_DESCRIPCION TEXT)")

        p0?.execSQL(createTable)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        val dropTable:String = ("DROP TABLE IF EXISTS $TABLE_NOMBRE")
        p0?.execSQL(dropTable)
        onCreate(p0)
    }

    fun insertarLugar(lugar: Lugar): Long {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(COLUMN_NOMBRE,lugar.nombre)
        values.put(COLUMN_DESCRIPCION,lugar.descripcion)
        return db.insert(TABLE_NOMBRE, null, values)
    }

    fun obtenerLugares(): List<Lugar> {
        val listaLugares = ArrayList<Lugar>()
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM $TABLE_NOMBRE", null)

        if (cursor.moveToFirst()) {
            do {
                // Crea una instancia de Lugar a partir de los datos del cursor
                val lugar = Lugar(
                    id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID)),
                    nombre = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NOMBRE)),
                    descripcion = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DESCRIPCION))
                )
                // Añade la instancia de Lugar a la lista
                listaLugares.add(lugar)
            } while (cursor.moveToNext())
        }
        cursor.close()
        return listaLugares
    }

    fun eliminarLugar(id: Int): Int {
        val db = this.writableDatabase
        return db.delete(TABLE_NOMBRE, "$COLUMN_ID=?", arrayOf(id.toString()))
    }

    fun actualizarLugar(lugar: Lugar): Int {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(COLUMN_NOMBRE, lugar.nombre)
        values.put(COLUMN_DESCRIPCION, lugar.descripcion)

        return db.update(TABLE_NOMBRE, values, "$COLUMN_ID=?", arrayOf(lugar.id.toString()))
    }

}