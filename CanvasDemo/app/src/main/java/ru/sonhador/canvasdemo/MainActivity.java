package ru.sonhador.canvasdemo;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //
        ImageView ourFrame = (ImageView) findViewById(R.id.imageView);
        Bitmap outBitmap = Bitmap.createBitmap(750, 1500, Bitmap.Config.ARGB_8888);
        Canvas outCanvas = new Canvas(outBitmap);
        Paint paint = new Paint();
        outCanvas.drawColor(Color.BLACK);
        paint.setColor(Color.argb(255, 255, 255, 255));


        // random pixels
        Random random = new Random();
        for (int i=0; i<= 600; i++){
            int x = random.nextInt(750);
            int y = random.nextInt(1500);
            outCanvas.drawPoint(x, y, paint);
        }

        outCanvas.drawLine(0, 0, 7500, 1500, paint);
        paint.setColor(Color.argb(255, 0, 255, 0));

        paint.setTextSize(120f);
        outCanvas.drawText("hello canvas", 10, 750, paint);
        outCanvas.drawCircle(500, 500, 100, paint);
        paint.setColor(Color.argb(255, 0,0, 255));
        outCanvas.drawRect(500, 10, 200, 200, paint);

        ourFrame.setImageBitmap(outBitmap);



    }
}
