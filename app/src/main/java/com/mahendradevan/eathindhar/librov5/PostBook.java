package com.mahendradevan.eathindhar.librov5;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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

public class PostBook extends AppCompatActivity {
    int PICK_IMAGE_REQUEST = 111;
    Spinner boom;
    String gen[] = {"Select Genre", "Rommance", "Science Fiction", "Fantasy", "Classics", "Biography", "Action", "Adventure", "Thriller", "Mystery", "Horror", "Poetry", "Spiritual"};
    String Base_url = "https://libro-ee6be.firebaseio.com";

    Firebase fbdb;
    Button pstbtn;
    ImageView btn;
    String bktit, bkdesc, spinItem, authname, price, Name, isbntxt;
    EditText bkt, bkd, auth, pri, isbn;
    Uri filePath;
    StorageReference storageRef;
    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_book);


        fbdb = new Firebase(Base_url);
        FirebaseStorage storage = FirebaseStorage.getInstance();
        storageRef = storage.getReferenceFromUrl("gs://libro-ee6be.appspot.com/");
        Name = getIntent().getStringExtra("Name");

        pstbtn = (Button) findViewById(R.id.postbtn);

        bkt = (EditText) findViewById(R.id.bktit_txt);
        bkd = (EditText) findViewById(R.id.bkdec_txt);
        auth = (EditText) findViewById(R.id.ath_txt);
        pri = (EditText) findViewById(R.id.prc_txt);
        isbn = (EditText) findViewById(R.id.isbntxt);
        boom = (Spinner) findViewById(R.id.boom);
        ArrayAdapter<String> array = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, gen);
        boom.setAdapter(array);


        boom.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        btn = (ImageView) findViewById(R.id.pikimg);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent i = new Intent(Intent.ACTION_PICK);
//                i.setType("images/*");
//                startActivityForResult(i,GALLERY_INTENT);

                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_PICK);
                startActivityForResult(Intent.createChooser(intent, "Select Image"), PICK_IMAGE_REQUEST);
            }
        });

        pstbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bktit = bkt.getText().toString();
                bkdesc = bkd.getText().toString();
                authname = auth.getText().toString();
                price = pri.getText().toString();
                spinItem = boom.getSelectedItem().toString();
                isbntxt = isbn.getText().toString();

                if (!(bktit.isEmpty()) && !(bkdesc.isEmpty()) && !(authname.isEmpty()) && !(price.isEmpty())) {


                    final StorageReference childRef = storageRef.child("Posted Books").child("/"+spinItem).child("/"+isbntxt).child("/"+isbntxt+"image.jpeg");

                    //uploading the image
                    UploadTask uploadTask = childRef.putFile(filePath);

                    uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                            Toast.makeText(PostBook.this, "Upload successful", Toast.LENGTH_SHORT).show();

                            url = taskSnapshot.getDownloadUrl().toString();
                            System.out.println(url+"bow");

                           fbdb.child("posted books").child("/"+spinItem).child("/"+bktit).setValue(url);
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                            Toast.makeText(PostBook.this, "Upload Failed -> " + e, Toast.LENGTH_SHORT).show();
                        }
                    });
                    new MyTask().execute();
                    finish();

                    Toast.makeText(getApplicationContext(), "Value Added", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Value Not Added", Toast.LENGTH_SHORT).show();
                }


            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            filePath = data.getData();

            try {
                //getting image from gallery
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);

                //Setting image to ImageView
                btn.setImageBitmap(bitmap);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public class MyTask extends AsyncTask<String, String, String> {
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
                pd.setUrl(url);
                //Toast.makeText(PostBook.this, url, Toast.LENGTH_SHORT).show();
                fbdb.child("/Books").child("/sale").child("/" + spinItem).child("/ISBN_" + isbntxt).setValue(pd);
                Toast.makeText(PostBook.this, "Value Added", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(PostBook.this, HomePage.class);
                startActivity(i);


            } catch (Exception e) {
                System.out.println("Exception " + e);
            }
            return null;
        }
    }
}


