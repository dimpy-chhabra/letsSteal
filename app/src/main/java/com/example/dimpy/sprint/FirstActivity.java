package com.example.dimpy.sprint;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class FirstActivity extends AppCompatActivity {
    TextView fullscreen_content;
    ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        fullscreen_content=(TextView) findViewById(R.id.fullscreen_content);

        actionBar = getSupportActionBar();
        actionBar.hide();

        fullscreen_content.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                SharedPreferences spref = getSharedPreferences("MPref", Context.MODE_PRIVATE);
                String a = spref.getString("name", null); // getting String
                String b = spref.getString("sex", null);
                String c = spref.getString("logged_in", "false");
                //high scores ADDDDDDD

                Log.e("in FullScreen Activity ","name : "+a+" sex : "+b+" isloggedin? : "+c);
                if(c.equals("true")){
                    Log.e("in  Activity in if","name : "+a+" sex: "+b+" isloggedin? : "+c);
                    Intent i  = new Intent(FirstActivity.this, MainActivity.class);
                    startActivity(i);
                    finish();
                }else {
                    Intent i  = new Intent(FirstActivity.this, LoginActivity.class);
                    startActivity(i);
                    finish();
                }
            }
        });
    }
}