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
import android.widget.Toast;

import com.sakshay.quiz.R;
import com.sakshay.quiz.quiz_contest.MenuPage;
import com.sakshay.quiz.quiz_contest.Model.DatabaseModel;

public class LoginScreen extends AppCompatActivity{
	Intent i=null;
	EditText mobile,password2;
	boolean flag=false;
	SQLiteDatabase db=null;
	private Cursor c;
	private DatabaseModel myModel;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		mobile=(EditText)findViewById(R.id.phone2);
		password2=(EditText)findViewById(R.id.password2);
		db=openOrCreateDatabase("mydb", MODE_PRIVATE,null);
	//	db.execSQL("create table if not exists login(name varchar,mobile_no varchar,email_id varchar,password varchar,flag varchar)");

	}
	
	public void action(View v)
	{
	switch(v.getId())
	{
	case R.id.signin2: 
		i=new Intent(this,RegisterScreen.class);
		startActivityForResult(i, 500);
		overridePendingTransition(R.anim.slide_in_top, R.anim.slide_out_bottom); 
		finish();
		break;
	 case R.id.start:
		String mobile_no=mobile.getText().toString();
		String password=password2.getText().toString();
		if(mobile_no==null||mobile_no==""||mobile_no.length()<10)
		{
			show("Please Enter Correct mobile number.");
		}
		else if(password==null||password==""||password.length()<6)
		{
			show("Please Enter Correct Password.");
		}
		else
		{		
			Cursor c=db.rawQuery("select * from login where mobile_no='"+mobile_no+"' and password='"+password+"'",null);	//to fetch
			while(c.moveToNext()){//while the next row exist
				myModel = new DatabaseModel();
				myModel.setName(c.getString(0));
				myModel.setMobile(c.getString(1));
				myModel.setEmail(c.getString(2));

			}
			c.moveToFirst();//move to the first row
			if(c.getCount()>0)//if number of rows are more than zero
			{

			i=new Intent(this,MenuPage.class);
				i.putExtra("data", myModel);
				startActivity(i);
			overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
			db.close();
			finish();
			}
			else
				show("Wrong Password or Mobile number.");
			
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
