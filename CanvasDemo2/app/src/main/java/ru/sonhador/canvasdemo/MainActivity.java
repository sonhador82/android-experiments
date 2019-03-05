package ru.sonhador.canvasdemo;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.widget.ImageView;

public class MainActivity extends Activity {
    ImageView myImageView;
    Bitmap myBlankBitmap;
    Canvas myCanvas;
    Paint myPaint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int widthInPixels = 800;
        int heightInPixels = 600;
        myBlankBitmap = Bitmap.createBitmap(widthInPixels, heightInPixels,
                Bitmap.Config.ARGB_8888);
        myCanvas = new Canvas(myBlankBitmap);
        myImageView = new ImageView(this);
        myPaint = new Paint();

        myCanvas.drawColor(Color.argb(255, 0, 0, 255));
        myPaint.setTextSize(100);
        myPaint.setColor(Color.argb(255, 255,255,255));
        myCanvas.drawText("Hello world", 100, 100, myPaint);
        myPaint.setColor(Color.argb(255,212,207, 62));
        myCanvas.drawCircle(400,250,100,myPaint);

        myImageView.setImageBitmap(myBlankBitmap);
        setContentView(myImageView);
    }
}
