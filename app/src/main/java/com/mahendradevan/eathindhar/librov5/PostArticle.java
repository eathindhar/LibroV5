package com.mahendradevan.eathindhar.librov5;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class PostArticle extends AppCompatActivity {
    int PICK_IMAGE_REQUEST= 2;
    TextView userName;
    ImageView img;
    EditText title,story;
    Button post;
    Spinner boom;
    String gen[] = {"Select Genre", "Rommance", "Science Fiction", "Fantasy", "Classics", "Biography", "Action", "Adventure", "Thriller", "Mystery", "Horror", "Poetry", "Spiritual"};
    String Base_url = "https://libro-ee6be.firebaseio.com";
    Uri filePath;
    StorageReference storageRef;
    String url,Name,ti,st,spinItem;
    Firebase fbdb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_article);

        userName = (TextView)findViewById(R.id.uname);
        img = (ImageView)findViewById(R.id.accimg);
        title = (EditText)findViewById(R.id.psttit);
        story = (EditText)findViewById(R.id.editText3);
        post = (Button)findViewById(R.id.post);

        fbdb = new Firebase(Base_url);
        FirebaseStorage storage = FirebaseStorage.getInstance();
        storageRef = storage.getReferenceFromUrl("gs://libro-ee6be.appspot.com/");
        Name = getIntent().getStringExtra("Name");

        boom = (Spinner) findViewById(R.id.boom);
        ArrayAdapter<String> array = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, gen);
        boom.setAdapter(array);

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_PICK);
                startActivityForResult(Intent.createChooser(intent, "Select Image"), PICK_IMAGE_REQUEST);
            }
        });

        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ti = title.getText().toString();
                st = story.getText().toString();
                spinItem = boom.getSelectedItem().toString();

                if(!ti.isEmpty() && !st.isEmpty()){
                    new MyTask().execute();
                    final StorageReference childRef = storageRef.child("Article").child("/"+spinItem).child("/"+userName).child("/"+ti);

                    UploadTask uploadTask = childRef.putFile(filePath);

                    uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Toast.makeText(PostArticle.this,"value added..",Toast.LENGTH_SHORT);
                            url = childRef.getDownloadUrl().toString();

                            fbdb.child("Posted Article").child("/"+spinItem).child("/"+ti).setValue(url);
                        }
                    });
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
                img.setImageBitmap(bitmap);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public class MyTask extends AsyncTask<String, String, String>{

        @Override
        protected String doInBackground(String... params) {
            PostArticleCreds pd = new PostArticleCreds();
            pd.setTitle(ti);
            pd.setStory(st);
            pd.setCat(spinItem);

            fbdb.child("/Article").child("/"+spinItem).child("/"+userName).child("/"+ti).setValue(pd);

            return null;
        }
    }
}
