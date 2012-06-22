package com.joelbyler.mastermind;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MasterMindActivity extends MasterMindBaseActivity {
    private MasterMind masterMind;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        masterMind = new MasterMind(getMaxAttemptsPreference());
        
    }
    
    @Override
    public Object onRetainNonConfigurationInstance() {
    	return masterMind;
    }
    
    public void processGuess(View v) {
    	
    	GuessResult result = updateMasterMindGuess();
        checkForMaxAttempts();
        updateMessageOnScreen(result);
        
    }
    
    private int getInputIntegerSafely(int fieldId) {
		EditText field = (EditText) findViewById(fieldId);
		try {
			return Integer.parseInt(field.getText().toString());
		}
		catch (NumberFormatException e) {
			return 0;
		}
    }

	private GuessResult updateMasterMindGuess() {
    	int[] guess = new int[]{ getInputIntegerSafely(R.id.guessNumberOne),
								 getInputIntegerSafely(R.id.guessNumberTwo),
								 getInputIntegerSafely(R.id.guessNumberThree),
								 getInputIntegerSafely(R.id.guessNumberFour)};
    	GuessResult result = masterMind.guess(guess);
		return result;
	}

	private void updateMessageOnScreen(GuessResult result) {
		TextView message = (TextView) findViewById(R.id.message);
    	if(result.isCorrect()) {
    		message.setText(R.string.congrats);
    		message.append(" in " + masterMind.getNumberOfGuesses() + " tries");
    		persistScore();
    	}
    	else {
        	message.setText("You have " + result.getCorrectNumbers() + " numbers and " + result.getCorrectPositions() + " positions correct.");
    	}
	}

	private void checkForMaxAttempts() {
		int maxAttempts = getMaxAttemptsPreference();
    	if(masterMind.getNumberOfGuesses() > maxAttempts) {
            AlertDialog.Builder alertbox = new AlertDialog.Builder(this);
            alertbox.setMessage(R.string.max_attempts_exceeded);
            alertbox.setNeutralButton("Ok", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// Just want to be OK ;)
				}});            
            alertbox.show();
    	}
	}

	private int getMaxAttemptsPreference() {
		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        int maxAttempts = Integer.parseInt(prefs.getString("max_attempts", "20"));
		return maxAttempts;
	}
	
	@Override
	public void resetInputs() {
		resetFieldById(R.id.guessNumberOne);
		resetFieldById(R.id.guessNumberTwo);
		resetFieldById(R.id.guessNumberThree);
		resetFieldById(R.id.guessNumberFour);
        masterMind = new MasterMind(getMaxAttemptsPreference());
		TextView message = (TextView) findViewById(R.id.message);
		message.setText(R.string.hello);
	}
	
    private void resetFieldById(int fieldId) {
		EditText field = (EditText) findViewById(fieldId);
		field.setText("");
    }
    
    public void persistScore () {

    	MasterMindScoreSQLHelper scoreDao = new MasterMindScoreSQLHelper(this);
    	
    	scoreDao.persist(masterMind);
    	

	}
    
}