package com.example.androishaper.serverimag;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Base64;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    ImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView=findViewById(R.id.imageView);
        String url="http://192.168.1.100/mysql_iot/bg.jpg";
        String urlo="http://192.168.1.100/AndroidVolley/show.php?eid=7";
        String urll="https://www.google.com/images/branding/googlelogo/2x/googlelogo_color_92x30dp.png";
        //Picasso.get().load(url).into(imageView);



        Picasso.get()
                .load(urlo)
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_foreground)
                 .resize(300,300)
                .into(new Target()
                {

                    @Override
                    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                        //imageView.setImageBitmap(bitmap);
                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                        byte[] imageBytes = baos.toByteArray();
                        String imageString = Base64.encodeToString(imageBytes, Base64.DEFAULT);
                        imageBytes = Base64.decode(imageString, Base64.DEFAULT);
                        Bitmap decodedImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
                        imageView.setImageBitmap(decodedImage);
                    }

                    @Override
                    public void onBitmapFailed(Exception e, Drawable errorDrawable) {
                        Toast.makeText(MainActivity.this,"result"+e,Toast.LENGTH_LONG).show();


                    }

                    @Override
                    public void onPrepareLoad(Drawable placeHolderDrawable) {
                        Toast.makeText(MainActivity.this,"result prepared",Toast.LENGTH_SHORT).show();

                    }
                });


    }



}
