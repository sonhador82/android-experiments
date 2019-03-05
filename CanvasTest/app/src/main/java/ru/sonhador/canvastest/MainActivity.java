package ru.sonhador.canvastest;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.media.Image;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener {

    private final static String TAG = "CanvasTemp";
    private TextView debug;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        debug = (TextView) findViewById(R.id.debug_block);
        debug.append("HelloWorld\n");

        ImageView outFrame = (ImageView) findViewById(R.id.imageView);
        outFrame.setOnTouchListener(this);

        Bitmap outBitmap = Bitmap.createBitmap(600, 600, Bitmap.Config.ARGB_8888);

        Canvas outCanvas = new Canvas(outBitmap);
        Paint paint = new Paint();

        outCanvas.drawColor(Color.BLUE);

        paint.setColor(Color.argb(255, 255,255,255));

        Random random = new Random();
        for (int i=0; i<600; i++){
            int x = random.nextInt(600);
            int y = random.nextInt(600);
            outCanvas.drawPoint(x, y, paint);
        }
        outFrame.setImageBitmap(outBitmap);
        Log.d(TAG, "onCreate: Test");
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        debug.append("Action: " + motionEvent.getAction());
        switch (motionEvent.getAction()){
            case MotionEvent.ACTION_DOWN:
                debug.append("Touch down");
                debug.append("X: " + motionEvent.getX() +  " Y: " + motionEvent.getY());
            case MotionEvent.ACTION_UP:
                debug.append("Touch UP");
                debug.append("X: " + motionEvent.getX() +  " Y: " + motionEvent.getY());
            default:
                debug.append("some action");
        }
        return false;
    }
}
