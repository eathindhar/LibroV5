package com.mahendradevan.eathindhar.librov5;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.Firebase;

public class RegActivity extends AppCompatActivity {

    Button registerButton;
    EditText reg_name, reg_mail, reg_phno, reg_pass;
    String name, mail, phno, pass;
    String Base_url="https://libro2-2cd1d.firebaseio.com/";
    Firebase fbdb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);

        Firebase.setAndroidContext(RegActivity.this);
        fbdb = new Firebase(Base_url);

        registerButton = (Button)findViewById(R.id.register_btn);
        reg_name = (EditText)findViewById(R.id.name_reg);
        reg_mail = (EditText)findViewById(R.id.email_reg);
        reg_phno = (EditText)findViewById(R.id.phnum_reg);
        reg_pass = (EditText)findViewById(R.id.pass_reg);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name=reg_name.getText().toString();
                mail=reg_mail.getText().toString();
                phno=reg_phno.getText().toString();
                pass=reg_pass.getText().toString();

                if(!(name.isEmpty()) && !(mail.isEmpty()) && !(phno.isEmpty()) && !(pass.isEmpty()) )
                {

                    new MyTask().execute();
                    finish();

                    Toast.makeText(getApplicationContext(),"Value Added",Toast.LENGTH_SHORT).show();

                }

            }
        });

    }

    public class MyTask extends AsyncTask<String ,String ,String> {
        @Override
        protected String doInBackground(String... params) {
            try {
                System.out.println("hello");
                RegCreds rd = new RegCreds();
                rd.setName(name);
                rd.setPass(pass);
                rd.setMail(mail);
                rd.setPhone(phno);

                fbdb.child("/Accounts").child("/Members").child("/"+name + "_user").setValue(rd);
                Toast.makeText(RegActivity.this, "Value Added", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(RegActivity.this, HomePage.class);
                startActivity(i);


            } catch (Exception e) {
                System.out.println("Exception "+e);
            }
            return null;
        }
    }
}
