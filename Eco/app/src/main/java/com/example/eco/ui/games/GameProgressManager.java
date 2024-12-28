package com.example.eco.ui.games;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class GameProgressManager {
    private DatabaseHelper dbHelper;
    private Context context;

    public GameProgressManager(Context context) {
        this.context = context;
        dbHelper = new DatabaseHelper(context);
    }

    public boolean isGameUnlocked(int gameId) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] columns = {DatabaseHelper.COLUMN_IS_UNLOCKED};
        String selection = DatabaseHelper.COLUMN_GAME_ID + " = ?";
        String[] selectionArgs = {String.valueOf(gameId)};

        Cursor cursor = db.query(DatabaseHelper.TABLE_GAME_PROGRESS,
                columns, selection, selectionArgs, null, null, null);

        boolean isUnlocked = false;
        if (cursor != null && cursor.moveToFirst()) {
            isUnlocked = cursor.getInt(0) == 1;
            cursor.close();
        }
        return isUnlocked;
    }

    public void unlockGame(int gameId) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_IS_UNLOCKED, 1);

        String whereClause = DatabaseHelper.COLUMN_GAME_ID + " = ?";
        String[] whereArgs = {String.valueOf(gameId)};

        db.update(DatabaseHelper.TABLE_GAME_PROGRESS, values, whereClause, whereArgs);
    }
}