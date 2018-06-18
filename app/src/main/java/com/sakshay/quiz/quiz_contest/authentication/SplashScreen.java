package com.sakshay.quiz.quiz_contest.authentication;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.Window;
import android.view.WindowManager;

import com.sakshay.quiz.R;
import com.sakshay.quiz.quiz_contest.authentication.FirstPage;

public class SplashScreen extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
        WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.splash);
		Thread t=new Thread()
		{
			public void run()
			{
				try
				{
					Thread.sleep(3000);
					Intent intent=new Intent(getApplicationContext(), FirstPage.class);
					startActivity(intent);
				}
				catch (Exception e) 
				{
					 e.printStackTrace();
				}
			}
		};
		t.start();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
