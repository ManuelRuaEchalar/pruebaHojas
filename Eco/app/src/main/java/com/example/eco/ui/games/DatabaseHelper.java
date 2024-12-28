package com.example.eco.ui.games;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "GameProgress.db";
    private static final int DATABASE_VERSION = 1;

    // Tabla para el progreso de los minijuegos
    public static final String TABLE_GAME_PROGRESS = "game_progress";
    public static final String COLUMN_GAME_ID = "game_id";
    public static final String COLUMN_IS_UNLOCKED = "is_unlocked";

    // SQL para crear la tabla
    private static final String CREATE_TABLE_GAME_PROGRESS =
            "CREATE TABLE " + TABLE_GAME_PROGRESS + " (" +
                    COLUMN_GAME_ID + " INTEGER PRIMARY KEY, " +
                    COLUMN_IS_UNLOCKED + " INTEGER DEFAULT 0)";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_GAME_PROGRESS);
        // Insertar estados iniciales (solo el primer juego desbloqueado)
        db.execSQL("INSERT INTO " + TABLE_GAME_PROGRESS +
                " VALUES (1, 1)"); // Juego 1 desbloqueado
        db.execSQL("INSERT INTO " + TABLE_GAME_PROGRESS +
                " VALUES (2, 0)"); // Juego 2 bloqueado
        db.execSQL("INSERT INTO " + TABLE_GAME_PROGRESS +
                " VALUES (3, 0)"); // Juego 3 bloqueado
        db.execSQL("INSERT INTO " + TABLE_GAME_PROGRESS +
                " VALUES (4, 0)"); // Juego 4 bloqueado
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_GAME_PROGRESS);
        onCreate(db);
    }
}