package com.sakshay.quiz.quiz_contest.authentication;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.sakshay.quiz.R;
import com.sakshay.quiz.quiz_contest.MenuPage;
import com.sakshay.quiz.quiz_contest.Model.DatabaseModel;

public class RegisterScreen extends AppCompatActivity {
	Intent i=null;
	ImageView im=null;
	EditText tv1,tv2,tv3,tv4;
	boolean flag=false;
	SQLiteDatabase db=null;
	private Cursor c;
	private DatabaseModel myModel;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.signin);
		tv1=(EditText)findViewById(R.id.name);
		tv2=(EditText)findViewById(R.id.email_id);
		tv3=(EditText)findViewById(R.id.phone);
		tv4=(EditText)findViewById(R.id.password);
		db=openOrCreateDatabase("mydb", MODE_PRIVATE, null);
		db.execSQL("create table if not exists login(name varchar,mobile_no varchar,email_id varchar,password varchar,flag varchar)");
	}
	
	public void action(View v)
	{
	switch(v.getId())
	{
	case R.id.login: 
		i=new Intent(this,LoginScreen.class);
		startActivityForResult(i, 500);
		overridePendingTransition(R.anim.slide_in_top, R.anim.slide_out_bottom); 
		finish();
		break;
	case R.id.signin:
		String name=tv1.getText().toString();
		String email_id=tv2.getText().toString();
		String mobile_no=tv3.getText().toString();
		String password=tv4.getText().toString();
		if(name==null||name==""||name.length()<3)
		{
			show("Please Enter Correct Name.");
		}
		else if(mobile_no==null||mobile_no==""||mobile_no.length()<10)
		{
			show("Please Enter Correct mobile number.");
		}
		else if(email_id==null||email_id==""||email_id.length()<10)
		{
			show("Please Enter Correct Email id.");
		}
		else if(password==null||password==""||password.length()<6)
		{
			show("Please Enter Strong Password.");
		}
		else
		{
			db.execSQL("insert into login values('"+name+"','"+mobile_no+"','"+email_id+"','"+password+"','nothing')");//to insert
			i=new Intent(this,LoginScreen.class);
			Toast.makeText(this, "Successfully Registered,Login to continue", Toast.LENGTH_SHORT).show();
			startActivityForResult(i, 500);
			overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left); 
			db.close();
			finish();
		}
		break;
	}
  }
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
	} 
	
	public void show(String str)
	{
	Toast.makeText(this, str, Toast.LENGTH_LONG).show();	
	}
	public void onBackPressed() {
		new AlertDialog.Builder(this).setIcon(android.R.drawable.ic_dialog_alert).setTitle("Exit")
				.setMessage("Are you sure?")
				.setPositiveButton("yes", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {

						Intent intent = new Intent(Intent.ACTION_MAIN);
						intent.addCategory(Intent.CATEGORY_HOME);
						intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
						startActivity(intent);
						finish();
					}
				}).setNegativeButton("no", null).show();
	}
}
