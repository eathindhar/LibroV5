package com.mahendradevan.eathindhar.librov5;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    EditText logtext, passtext;
    String uname, pass;
    Button logbtn;
    ImageButton regbtn;
    String Base_url="https://libro-ee6be.firebaseio.com/";
    Firebase fbdb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        logtext = (EditText)findViewById(R.id.logtext);
        passtext = (EditText)findViewById(R.id.passtext);
        logbtn = (Button)findViewById(R.id.logbtn);
        regbtn = (ImageButton)findViewById(R.id.regbtn);

        Firebase.setAndroidContext(MainActivity.this);
        fbdb = new Firebase(Base_url);

        logbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uname = logtext.getText().toString();
                pass = passtext.getText().toString();

                toast(uname+pass);
                System.out.println(uname+pass);
                fbdb=new Firebase(Base_url+"/Accounts/Members");
                toast("after url");
                fbdb.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        toast("advanced for enter");
                        sop("Advanced for loop enter");
                        for(DataSnapshot postdatasnapshot:dataSnapshot.getChildren()){
                            RegCreds rd = postdatasnapshot.getValue(RegCreds.class);
                            if(rd.getName().equals(uname)&&rd.getPass().equals(pass)){
                                toast("Login Successful");
                                Intent i = new Intent(MainActivity.this,HomePage.class);
                                i.putExtra("Name",uname);
                                startActivity(i);
                            }
                        }
                    }

                    @Override
                    public void onCancelled(FirebaseError firebaseError) {

                    }
                });

            }
        });

        regbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toast("Going to Register Activity");
                Intent i2 = new Intent(MainActivity.this, RegActivity.class);
                startActivity(i2);
            }
        });

    }
    private void toast(String Message){
        Toast.makeText(this,Message,Toast.LENGTH_SHORT).show();
    }
    private void sop(String Message){System.out.println(Message);}
}
