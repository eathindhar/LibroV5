package com.mahendradevan.eathindhar.librov5;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.FirebaseStorage;
import com.firebase.client.Firebase;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.util.ArrayList;

import droidninja.filepicker.FilePickerBuilder;
import droidninja.filepicker.FilePickerConst;

public class PostBook extends AppCompatActivity {
    Spinner boom;
    String gen[]={"Select Genre","Rommance","Science Fiction","Fantasy","Classics","Biography","Action","Adventure","Thriller","Mystery","Horror","Poetry","Spiritual"};
    ArrayList<String> filepath = new ArrayList<String>();
    GridView gv;
    String Base_url="https://libro-ee6be.firebaseio.com/";
    //String Storeage_url = "gs://libro-ee6be.appspot.com/";
    Firebase fbdb;
    Button pstbtn, btn;
    String bktit,bkdesc, spinItem, authname, price, Name,isbntxt;
    EditText bkt,bkd,auth,pri, isbn;
    StorageReference img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_book);


        fbdb = new Firebase(Base_url);


        Name=getIntent().getStringExtra("Name");

        pstbtn=(Button)findViewById(R.id.postbtn);

        bkt=(EditText)findViewById(R.id.bktit_txt);
        bkd=(EditText)findViewById(R.id.bkdec_txt);
        auth=(EditText)findViewById(R.id.ath_txt);
        pri=(EditText)findViewById(R.id.prc_txt);
        isbn=(EditText)findViewById(R.id.isbntxt);
        //img=FirebaseStorage.getInstance().getReference();
        boom = (Spinner)findViewById(R.id.boom);
        ArrayAdapter<String> array = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,gen);
        boom.setAdapter(array);

        boom.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        gv=(GridView)findViewById(R.id.gv);

        btn=(Button)findViewById(R.id.pikimg);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filepath.clear();
                FilePickerBuilder.getInstance().setMaxCount(5)
                        .setSelectedFiles(filepath)
                        .setActivityTheme(R.style.AppTheme)
                        .pickPhoto(PostBook.this);
            }
        });

        pstbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bktit=bkt.getText().toString();
                bkdesc =bkd.getText().toString();
                authname=auth.getText().toString();
                price=pri.getText().toString();
                spinItem = boom.getSelectedItem().toString();
                isbntxt=isbn.getText().toString();

                if(!(bktit.isEmpty())&&!(bkdesc.isEmpty())&&!(authname.isEmpty())&&!(price.isEmpty())){
                    new MyTask().execute();
                    finish();

                    Toast.makeText(getApplicationContext(),"Value Added",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(getApplicationContext(),"Value Not Added",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch(requestCode){
            case FilePickerConst.REQUEST_CODE:

                if(resultCode==RESULT_OK && data!=null){
                    filepath = data.getStringArrayListExtra(FilePickerConst.KEY_SELECTED_PHOTOS);
                    imagepickeradapter imgpkr;
                    ArrayList<imagepickeradapter> imgp = new ArrayList<>();

                    try{
                        for(String path:filepath){
                            imgpkr=new imagepickeradapter();
                            imgpkr.setName(path.substring(path.lastIndexOf("/")+1));

                            imgpkr.setUri(Uri.fromFile(new File(path)));
                            imgp.add(imgpkr);
                        }
                        gv.setAdapter(new CustAdapter(this,imgp));
                        Toast.makeText(this, "Total ="+String.valueOf(imgp.size()), Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(Intent.ACTION_PICK);
                        i.setType("Image/*");
                        Uri uri = data.getData();
                        /*StorageReference strref = img.child("/"+isbntxt).child(uri.getLastPathSegment());
                        strref.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                Toast.makeText(PostBook.this, "Value Added", Toast.LENGTH_SHORT).show();

                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(PostBook.this, "Value Not Added", Toast.LENGTH_SHORT).show();

                            }
                        });*/
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                }
        }
    }

    public class MyTask extends AsyncTask<String ,String ,String> {
        @Override
        protected String doInBackground(String... params) {
            try {
                System.out.println("hello");
                PostBookCreds pd = new PostBookCreds();
                pd.setBktit(bktit);
                pd.setBkdesc(bkdesc);
                pd.setAuthname(authname);
                pd.setPrice(price);
                pd.setSpinItem(spinItem);
                pd.setIsbn(isbntxt);

                fbdb.child("/Books").child("/sale").child("/"+spinItem).child("/ISBN_"+isbntxt).setValue(pd);
                Toast.makeText(PostBook.this, "Value Added", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(PostBook.this, HomePage.class);
                startActivity(i);


            } catch (Exception e) {
                System.out.println("Exception "+e);
            }
            return null;
        }
    }

}
