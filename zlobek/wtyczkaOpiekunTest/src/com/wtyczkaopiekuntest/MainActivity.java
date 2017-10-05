package com.wtyczkaopiekuntest;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;

public class MainActivity extends Activity {

	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    	Intent ekranLogowania = new Intent(MainActivity.this, MenuLogowania.class);
    	MainActivity.this.startActivity(ekranLogowania);
    	MainActivity.this.finish();
 }
}
