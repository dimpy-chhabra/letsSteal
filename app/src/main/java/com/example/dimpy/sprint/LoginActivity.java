package com.example.dimpy.sprint;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.v7.app.ActionBar;
import android.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText et1;
    RadioGroup rg1;
    RadioButton rbF, rbM;
    Button b1, btAbout;
    String sex = "F";
    String Name="";
    ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        actionBar = getSupportActionBar();
        actionBar.hide();

        et1 = (EditText) findViewById(R.id.etName);
        rg1=(RadioGroup)findViewById(R.id.radioGroup);
        rbF = (RadioButton)findViewById(R.id.rbFemale);
        rbM = (RadioButton)findViewById(R.id.rbMale);
        b1 = (Button)findViewById(R.id.bt1);
        btAbout = (Button)findViewById(R.id.btAbout);

        Name = et1.getText().toString();
        if(rbM.isChecked()){
            sex="M";
        }

        btAbout.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View view){
                AlertDialog.Builder builder;
                Context context = LoginActivity.this;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    builder = new AlertDialog.Builder(context, android.R.style.Theme_DeviceDefault_Dialog_Alert);
                } else {
                    builder = new AlertDialog.Builder(context);
                }
                builder.setTitle("About us/app")
                        .setMessage("Hi! Welcome to 'Let's Steal'. \nThis app runs on the fractional Knapsack Problem." +
                        "\n1.Click on an item as many times as the number of kgs you'd like to add of it to your knapsack" +
                        "\n2.Long Press an item to remove a single unit of it." +
                        "\n3.Steal as much as you can" +
                        "\n\nHope you have fun!")
                       .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                }
                        })
                        .setIcon(R.drawable.knap)
                        .show();
            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Name = et1.getText().toString();
                if(rbM.isChecked()){
                    sex="M";
                }
                SharedPreferences preferences = getApplicationContext().getSharedPreferences("MPref", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("name", ""+Name );
                editor.putString("sex", ""+sex);
                editor.putString("logged_in", "true" );
                editor.commit();
                Toast.makeText(LoginActivity.this, "Shared Pref added! \n 'sup "+Name, Toast.LENGTH_LONG).show();
                Intent i = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        });


    }
}
