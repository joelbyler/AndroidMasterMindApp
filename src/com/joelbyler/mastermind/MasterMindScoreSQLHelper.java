	package com.joelbyler.mastermind;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MasterMindScoreSQLHelper extends SQLiteOpenHelper {
	
	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "mastermind.db";

	public MasterMindScoreSQLHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String sql = 
				"CREATE TABLE [SCORES] (" +
				"[_id] INTEGER PRIMARY KEY AUTOINCREMENT, " +
				"[game_date] TEXT, " + 
				"[game_score] INT);";
		db.execSQL(sql);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		if (oldVersion >= newVersion) {
			return;
		}
	}

	public void persist(MasterMind masterMind) {
		
		SimpleDateFormat dateFormatter = new SimpleDateFormat("MM/dd/yyyy hh:mm");

		final SQLiteDatabase db = getReadableDatabase();
	
		ContentValues values = new ContentValues();
		values.put("game_date", dateFormatter.format(new Date()));
		values.put("game_score", masterMind.getNumberOfGuesses());

		db.insertOrThrow("SCORES", null, values);
		db.close();
		close();
	}
	
	public Cursor getCursorForEntireList(Context context) {
		SQLiteDatabase db = getReadableDatabase();
		return db.rawQuery("SELECT [_id], [game_score], [game_date] from [SCORES]", null);
	}
	
}
