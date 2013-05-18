package com.jdsv650.TicXOToe;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class MyMainActivity extends Activity {

	
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_main);
        
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my_main, menu);
        return true;
    }
    
    public void button1Clicked(View v) {
    	Intent i = new Intent(this, BoardActivity.class);
    	i.putExtra("vComp", "1"); //Optional parameters
    	startActivity(i);
    }
    
    public void button2Clicked(View v) {
    	Intent i = new Intent(this, BoardActivity.class);
    	i.putExtra("vComp", "0"); //Optional parameters
    	startActivity(i);
    }
}
