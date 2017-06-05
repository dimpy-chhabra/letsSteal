package com.example.dimpy.sprint;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Build;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {



    Button bt1;
    TextView capacity, capacity_left_text_view,textViewName, textViewProfit;
    ImageView btShare,btLogout, dp;

    int capaci = 0;
    int capaci_left = 0;
    String name;
    private static Animation shakeAnimation;
    ActionBar actionBar;
    private MediaPlayer mediaPlayer;
    LinearLayout kgs_layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        shakeAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.shake);
        actionBar = getSupportActionBar();
        actionBar.hide();

        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.spooky);
        mediaPlayer.start();

        dp = (ImageView)findViewById(R.id.dp);
        btLogout = (ImageView)findViewById(R.id.btLogout);
        btShare = (ImageView)findViewById(R.id.btShare);
        textViewName=(TextView)findViewById(R.id.textViewName);
        capacity = (TextView)findViewById(R.id.capacity_text_view);
        textViewProfit = (TextView)findViewById(R.id.textViewProfit);
        kgs_layout = (LinearLayout)findViewById(R.id.kgs_layout);
        capacity_left_text_view = (TextView)findViewById(R.id.capacity_left_text_view);
        bt1=(Button)findViewById(R.id.bt1);
        SharedPreferences spref = getSharedPreferences("MPref", Context.MODE_PRIVATE);
        name = spref.getString("name", null); // getting String
        String sex = spref.getString("sex", null);

        Log.e(" in main activity ", " name >" + name + " sex is>" + sex);

        if(sex.equals("F"))
            dp.setImageResource(R.drawable.the_fem);
        else
            dp.setImageResource(R.drawable.the_mal);
        dp.setVisibility(View.VISIBLE);



        final int capa[] = { 16, 15, 17, 18, 19, 20, 25};
        int num = new Random().nextInt(7);
        capaci = capa[num%(7)];
        capaci_left = capaci;

        capacity_left_text_view.setText(""+capaci_left+"kgs");
        capacity.setText("You got : "+capaci+"kgs");
        textViewName.setText("Hi, "+name+" \nWelcome!");

        String namesCatI[] = {"Gold","Diamond","Pearls","Platinum"};
        String valsCatI[] = {"300","270","1000","600"};
        String weighsCatI[] = {"12","18","6","7"};

        int picCatI[] = {R.drawable.gold, R.drawable.diamond, R.drawable.pearls, R.drawable.jewelry};

        String namesCatII[] = {"Cash","Chest","Cocaine","EpitomePowerCaps"};
        String valsCatII[] = {"500","95","600","200"};
        String weighsCatII[] = {"17","15","3","10"};

        int picCatII[] = {R.drawable.cash, R.drawable.chocolate, R.drawable.pulses, R.drawable.rice};

        final ArrayList<Items> itemsArrayList = new ArrayList<>();

        int n1 = new Random().nextInt(4);
        int n2 = new Random().nextInt(4);
        int n3 = new Random().nextInt(4);

        itemsArrayList.add(new Items(namesCatI[n1], valsCatI[n2], weighsCatI[n3], picCatI[n1], "0"));
        itemsArrayList.add(new Items(namesCatII[n1], valsCatII[n2], weighsCatII[n3], picCatII[n1], "0"));
        itemsArrayList.add(new Items(namesCatI[(n1+2)%4], valsCatI[(n2+2)%4], weighsCatI[(n3+2)%4], picCatI[(n1+2)%4], "0"));

        ItemsAdapter itemsAdapter = new ItemsAdapter(getBaseContext(), itemsArrayList);
        final ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(itemsAdapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Items items = itemsArrayList.get(position);
                int i = Integer.parseInt(items.getSelectedWeight());
                if((i+1)<= Integer.parseInt(items.getItemWeight()) && (i+1)<=capaci && capaci_left>0){
                    i +=1;
                    items.setSelectedWeights(Integer.toString(i));
                    capaci_left -= 1;
                    capacity_left_text_view.setText(capaci_left+"kgs");
                    Log.e("for item index "+position," selected weight = "+Integer.parseInt(items.getSelectedWeight()));
                }else if(capaci_left==0){
                    new CustomToast().Show_Toast(getApplicationContext(), view, "Ain't carry anymore");
                    kgs_layout.startAnimation(shakeAnimation);
                }else{
                    listView.getChildAt(position).startAnimation(shakeAnimation);
                    new CustomToast().Show_Toast(getApplicationContext(), view, "Can't steal what doesn't exist");
                }
                int profitYet=0;
                for(int j = 0 ; j< 3 ; j++) {
                    int a = Integer.parseInt(itemsArrayList.get(j).getSelectedWeight());
                    int b = Integer.parseInt((itemsArrayList.get(j).getItemValue()));
                    int c = Integer.parseInt(itemsArrayList.get(j).getItemWeight());
                    int finalProfitUser = (a * b) / c;
                    profitYet += finalProfitUser;
                }
                textViewProfit.setText("Profit : $"+profitYet);
                ((ItemsAdapter)listView.getAdapter()).notifyDataSetChanged();
            }
        });


        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
              public boolean onItemLongClick(AdapterView<?> arg0, View v,int index, long arg3) {
                  String str=listView.getItemAtPosition(index).toString();
                  Log.e("onLOngClick "," "+str);
                  Items items = itemsArrayList.get(index);
                  int i = Integer.parseInt(items.getSelectedWeight());
                  if((i-1)>=0){
                      i -=1;
                      items.setSelectedWeights(Integer.toString(i));
                      capaci_left += 1;
                      capacity_left_text_view.setText(capaci_left+"kgs");
                      Log.e("for item index "+index," selected weight = "+Integer.parseInt(items.getSelectedWeight()));
                  }else{
                      listView.getChildAt(index).startAnimation(shakeAnimation);
                      new CustomToast().Show_Toast(getApplicationContext(), v, "you have selected no items to drop ");
                  }
                  int profitYet=0;
                  for(int j = 0 ; j< 3 ; j++) {
                      int a = Integer.parseInt(itemsArrayList.get(j).getSelectedWeight());
                      int b = Integer.parseInt((itemsArrayList.get(j).getItemValue()));
                      int c = Integer.parseInt(itemsArrayList.get(j).getItemWeight());
                      int finalProfitUser = (a * b) / c;
                      profitYet += finalProfitUser;
                  }
                  textViewProfit.setText("Profit : $"+profitYet);
                  ((ItemsAdapter)listView.getAdapter()).notifyDataSetChanged();
                        return true;
                    }
                });

        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int value=0;
                for(int i = 0 ; i< 3 ; i++) {
                    int a = Integer.parseInt(itemsArrayList.get(i).getSelectedWeight());
                    int b = Integer.parseInt((itemsArrayList.get(i).getItemValue()));
                    int c = Integer.parseInt(itemsArrayList.get(i).getItemWeight());
                    int finalProfitUser = (a * b) / c;
                    Log.e("in for loop "," "+value);
                    value += finalProfitUser;
                }
                float valuesItem[] = {Integer.parseInt((itemsArrayList.get(0).getItemValue())),
                        Integer.parseInt((itemsArrayList.get(1).getItemValue())),
                        Integer.parseInt((itemsArrayList.get(2).getItemValue()))};
                float weightsItems[] = { Integer.parseInt(itemsArrayList.get(0).getItemWeight()),
                        Integer.parseInt(itemsArrayList.get(1).getItemWeight()),
                        Integer.parseInt(itemsArrayList.get(2).getItemWeight()),
                        };

                int d = knapSackComp(capaci, valuesItem, weightsItems );
                Log.e("on CLick Results: ","user's ideal>"+value+" knapsack ideal"+d);
                //Toast.makeText(MainActivity.this, "", Toast.LENGTH_SHORT).show();

                if(value>= d*0.935 && value<=d){
                    mediaPlayer.stop();
                    mediaPlayer.release();
                    mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.won);
                    mediaPlayer.start();
                    AlertDialog.Builder builder;
                    Context context = MainActivity.this;
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        builder = new AlertDialog.Builder(context, android.R.style.Theme_DeviceDefault_Dialog_Alert);
                    } else {
                        builder = new AlertDialog.Builder(context);
                    }
                    builder.setTitle("You won!")
                            .setMessage("YOU WON! WOO-HOO\n\n\n\n\nYou were born a thief. Weren't ya?!\n  ")
                            .setPositiveButton("Play Again", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent intent = getIntent();
                                    finish();
                                    startActivity(intent);
                                }
                            })
                            .setNegativeButton("Exit", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    })
                            .setIcon(R.drawable.knap)
                            .show();
                    Toast.makeText(MainActivity.this, "YOU WIN!", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this, "Sorry Man! Optimal Profit is "+d, Toast.LENGTH_SHORT).show();
                    new android.os.Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent intent = getIntent();
                            finish();
                            startActivity(intent);
                        }
                    },1000);
                }
            }
        });

        btShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String shareBody = "Hi there, Check out this app, called 'Let's Steal' based on fractional Knapsack to steal as much as you possible can. To get an apk now... Reach me at dimpy.blithe@gmail.com";
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Greeting from WannaCry");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(sharingIntent, "Share Using"));
            }
        });

        btLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences = getApplicationContext().getSharedPreferences("MPref", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("name", "" );
                editor.putString("sex", "");
                editor.putString("logged_in", "false" );
                editor.commit();

                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
    private int knapSackComp(int m, float[] values, float[] weights) {
        float temp,temp1,temp2;
        float ratio[] = new float[3];
        for(int i=0;i<3;i++)
            ratio[i]=values[i]/weights[i];
        for(int i=0;i<3;i++)
        {
            for(int j=0;j<2;j++)
            {
                if(ratio[j]<ratio[j+1])
                {
                    temp=values[j];
                    values[j]=values[j+1];
                    values[j+1]=temp;
                    temp1=ratio[j];
                    ratio[j]=ratio[j+1];
                    ratio[j+1]=temp1;
                    temp2=weights[j];
                    weights[j]=weights[j+1];
                    weights[j+1]=temp2;
                }
            }
        }
        // int m=10,
        int profit=0;
        for(int i=0;i<3;i++)
        {
            if(m>0 && weights[i]<=m){
                m = m - (int)weights[i];
                profit+=values[i];
            }
            else {
                profit+=m*ratio[i];
            }
        }
        return profit;
    }

    @Override
    protected void onPause() {
        super.onPause();
        mediaPlayer.pause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mediaPlayer.stop();
        mediaPlayer.release();
    }
}