package com.joelbyler.mastermind;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class MasterMindBaseActivity extends Activity {
	
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	MenuInflater inflater = getMenuInflater();
    	inflater.inflate(R.menu.mainmenu, menu);
    	return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
		Intent intent = null;
    	if (item.getItemId() == R.id.menu_about) {
    		showAboutDialog();
			//intent = new Intent(this.getApplicationContext(), AboutActivity.class);
    	}
    	if (item.getItemId() == R.id.menu_instructions) {
    		intent = new Intent(this.getApplicationContext(), InstructionsActivity.class);
    	}
    	if (item.getItemId() == R.id.menu_prefs) {
    		intent = new Intent(this.getApplicationContext(), MasterMindPreferencesActivity.class);
    	}
    	if (item.getItemId() == R.id.menu_scores) {
    		intent = new Intent(this.getApplicationContext(), MasterMindScoresActivity.class);
    	}
    	if (item.getItemId() == R.id.menu_reset && this instanceof MasterMindActivity) {
    		resetInputs();
    	}

    	if (intent != null) {
			startActivity(intent);
		}
    	return true;
    }

	public void resetInputs() {
		// To be overridden by MasterMindActivity
	}
	
	public void showAboutDialog() {
        AlertDialog.Builder alertbox = new AlertDialog.Builder(this);
        alertbox.setMessage(getResources().getText(R.string.about_text));
        alertbox.setNeutralButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
                // nothing to do, just show the dialog
            }
        });
        alertbox.show();		
	}
        
}