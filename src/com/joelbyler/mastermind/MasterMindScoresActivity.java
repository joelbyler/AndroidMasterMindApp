package com.joelbyler.mastermind;

import android.app.ListActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.SimpleCursorAdapter;

public class MasterMindScoresActivity extends ListActivity {
	/**
	 * @see android.app.Activity#onCreate(Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.score_list);
		
		setListAdapter();
		
	}
	
	private void setListAdapter() {
	   	MasterMindScoreSQLHelper scoreDao = new MasterMindScoreSQLHelper(this);

		Cursor cursor = scoreDao.getCursorForEntireList(this);
		startManagingCursor(cursor);
		
		String[] from = new String[] { "_id", "game_score", "game_date" };
		int[] to = new int[] { R.id.score_row_id, R.id.score_row_score, R.id.score_row_date };

		SimpleCursorAdapter cursorAdapter = new SimpleCursorAdapter(this, R.layout.row, cursor, from, to);
		
		setListAdapter(cursorAdapter);		
	}
	
	
}
