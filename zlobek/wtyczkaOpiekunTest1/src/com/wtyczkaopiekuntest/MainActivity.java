package com.wtyczkaopiekuntest;

import android.os.Bundle;
import android.app.Activity;
import android.os.Handler;
import android.content.Intent;

public class MainActivity extends Activity {

	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.przenikaniepowitalne);

    new Handler().postDelayed(new Thread() {
    	@Override
    	public void run() {
    		Intent ekranLogowania = new Intent(MainActivity.this, MenuLogowania.class);
    		MainActivity.this.startActivity(ekranLogowania);
    		MainActivity.this.finish();
    		overridePendingTransition(R.layout.fadein,R.layout.fadeout);
    	}
    }, Silnik.opoznienieEkranuPowitalnego);
 }
}
