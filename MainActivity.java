package com.r.myapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.io.File;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "MainActivity";
    public static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 42;
    private EditText editDescription;
    private Button captureImageBtn;
    private ImageView imgPoster;
    private Button submitBtn;
    public String photoFileName = "photo.jpg";
    private File photoFile;
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editDescription = findViewById(R.id.editDescription);
        captureImageBtn = findViewById(R.id.captureImageBtn);
        imgPoster = findViewById(R.id.imgPoster);
        submitBtn = findViewById(R.id.submitBtn);
        captureImageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchCamera();
            }
        });

        final ProgressBar pb = (ProgressBar) findViewById(R.id.pbLoading);



        //queryPosts();
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String description = editDescription.getText().toString();
                // on some click or some loading we need to wait for...
                if (description.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Description cannot be empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (photoFile == null || imgPoster.getDrawable() == null){
                    Toast.makeText(MainActivity.this, "There is no image!!", Toast.LENGTH_SHORT).show();
                    return;
                }

                pb.setVisibility(ProgressBar.VISIBLE);
                // run a background job and once complete
                pb.setVisibility(ProgressBar.INVISIBLE);

                ParseUser currentUser = ParseUser.getCurrentUser();
                savePost(description, currentUser, photoFile);









            }
        });

    }
