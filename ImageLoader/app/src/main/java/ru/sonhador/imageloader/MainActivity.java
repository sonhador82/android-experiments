package ru.sonhador.imageloader;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.ImageDecoder;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Main Activity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ConstraintLayout layout = (ConstraintLayout) findViewById(R.id.c_layout);

        // BitmapFactory.Options opts = new BitmapFactory.Options();
        // opts.inMutable = true;
        Bitmap picture = BitmapFactory.decodeResource(getResources(), R.drawable.testimage);
        Bitmap pic2 = picture.copy(Bitmap.Config.ARGB_8888, true);
        Log.d(TAG, "onCreate: pixel: " + picture.getByteCount());
        Log.d(TAG, "onCreate: width: " + picture.getWidth());
        Log.d(TAG, "onCreate: height: " + picture.getHeight());
        pic2.setPixel(1000, 10, Color.argb(50, 255, 0, 0));
        pic2.setPixel(1001, 10, Color.argb(255, 255, 0, 0));
        pic2.setPixel(1002, 10, Color.argb(50, 255, 0, 0));
        // Bitmap pic2 = picture.copy(Bitmap.Config.ARGB_8888, false);
        ImageView iView = new ImageView(getBaseContext());
        iView.setImageBitmap(pic2);
        layout.addView(iView);
        Log.d(TAG, "onCreate: shown: " + iView.isShown());
    }
}
