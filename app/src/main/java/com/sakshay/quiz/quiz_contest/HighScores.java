package com.sakshay.quiz.quiz_contest;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.widget.TextView;

import com.sakshay.quiz.R;

public class HighScores extends AppCompatActivity {

	private SQLiteDatabase db;
	private Cursor c;
	TextView scorerName,score;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_high_scores);
		scorerName=(TextView)findViewById(R.id.scorerName);
		score=(TextView)findViewById(R.id.score);
		openDatabase();
		c=db.rawQuery("SELECT * FROM scores", null);
		if(!c.moveToFirst())
			scorerName.setText("No high scores yet!");
		else
		{
			scorerName.setText(c.getString(0));
			score.setText(c.getString(1));
		}
	}
	
	protected void openDatabase() {
        db = openOrCreateDatabase("ScoresDB.db", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS scores(name VARCHAR, score NUMBER)");
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_high_scores, menu);
		return true;
	}

}
