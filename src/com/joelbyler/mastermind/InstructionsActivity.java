package com.joelbyler.mastermind;

import android.os.Bundle;

public class InstructionsActivity extends MasterMindBaseActivity {
	/**
	 * @see android.app.Activity#onCreate(Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.instructions);
	}
}
