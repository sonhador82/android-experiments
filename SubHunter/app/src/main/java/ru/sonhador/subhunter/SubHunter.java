package ru.sonhador.subhunter;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Color;
import android.view.Display;
import android.util.Log;
import android.widget.ImageView;
import java.util.Random;


public class SubHunter extends Activity {
    private static final String TAG = "SUBHUNTER";
    int numberHorizontalPixels;
    int numberVerticalPixels;
    int blockSize;
    int gridWidth = 40;
    int gridHeight;
    float horizontalTouched = -100;
    float verticalTouched = -100;
    int subHorizontalPosition;
    int subVerticalPosition;
    boolean hit = false;
    int shotsTaken;
    int distanceFromSub;
    boolean debugging = true;

    ImageView gameView;
    Bitmap blankBitmap;
    Canvas canvas;
    Paint paint;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // get current display resolution
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        // init size based res
        numberHorizontalPixels = size.x;
        numberVerticalPixels = size.y;
        blockSize = numberHorizontalPixels / gridWidth;
        gridHeight = numberVerticalPixels / blockSize;

        blankBitmap = Bitmap.createBitmap(numberHorizontalPixels,
                numberVerticalPixels, Bitmap.Config.ARGB_8888);
        canvas = new Canvas(blankBitmap);
        gameView = new ImageView(this);
        paint = new Paint();
        setContentView(gameView);

        Log.d(TAG, "In oncreate");
        newGame();
        draw();
    }

    void newGame(){
        Random random = new Random();
        subHorizontalPosition = random.nextInt(gridWidth);
        subVerticalPosition = random.nextInt(gridHeight);
        shotsTaken = 0;
        Log.d(TAG, "newGame: ");
    }

    void draw(){
        gameView.setImageBitmap(blankBitmap);
        canvas.drawColor(Color.argb(255, 255, 255, 255));

        paint.setColor(Color.argb(255, 0,0,0));
        for(int i=0; i<gridWidth; i++){
            canvas.drawLine(blockSize*i, 0, blockSize*i,
                    numberVerticalPixels, paint);
        }

        for(int i = 0; i<gridHeight; i++){
            canvas.drawLine(0, blockSize*i, numberHorizontalPixels,
                    blockSize*i, paint);
        }

        canvas.drawRect(horizontalTouched*blockSize, verticalTouched*blockSize,
                (horizontalTouched*blockSize)+blockSize,
                (verticalTouched*blockSize)+blockSize, paint);

        paint.setTextSize(blockSize*2);
        paint.setColor(Color.argb(255, 0, 0, 255));
        canvas.drawText("Shots Taken: " + shotsTaken + " Distance: " +
            distanceFromSub, blockSize, blockSize*1.75f, paint);
        Log.d(TAG, "draw: ");
        if(debugging) {
            printDebuggingTest();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d(TAG, "onTouchEvent: ");
        if((event.getAction() & event.ACTION_MASK) == event.ACTION_UP){
            takeShot(event.getX(), event.getY());
        }
        return true;
    }

    void takeShot(float touchX, float touchY){
        Log.d(TAG, "takeShot: ");
        shotsTaken++;
        horizontalTouched = (int)touchX/blockSize;
        verticalTouched = (int)touchY/blockSize;
        hit = horizontalTouched == subHorizontalPosition
                && verticalTouched == subVerticalPosition;
        int horizontalGap = (int) horizontalTouched - subHorizontalPosition;
        int verticalGap = (int)verticalTouched - subVerticalPosition;
        distanceFromSub = (int)Math.sqrt(((horizontalGap*horizontalGap)+
                (verticalGap*verticalGap)));
        if(hit)
            boom();
        else
            draw();
    }

    void boom(){
        gameView.setImageBitmap(blankBitmap);
        canvas.drawColor(Color.argb(255, 255,0,0));
        paint.setColor(Color.argb(255,255,255,255));
        paint.setTextSize(blockSize*10);
        canvas.drawText("BOOM!", blockSize*4, blockSize*14, paint);
        paint.setTextSize(blockSize*2);
        canvas.drawText("Take a shot to start again", blockSize*8,
                blockSize*18, paint);
        newGame();
    }

    void printDebuggingTest(){
        paint.setTextSize(blockSize);
        canvas.drawText("numberHorizontalPixels = "
                        + numberHorizontalPixels,
                50, blockSize * 3, paint);

        canvas.drawText("numberVerticalPixels = "
                        + numberVerticalPixels,
                50, blockSize * 4, paint);

        canvas.drawText("blockSize = " + blockSize,
                50, blockSize * 5, paint);

        canvas.drawText("gridWidth = " + gridWidth,
                50, blockSize * 6, paint);

        canvas.drawText("gridHeight = " + gridHeight,
                50, blockSize * 7, paint);

        canvas.drawText("horizontalTouched = " +
                        horizontalTouched, 50,
                blockSize * 8, paint);

        canvas.drawText("verticalTouched = " +
                        verticalTouched, 50,
                blockSize * 9, paint);

        canvas.drawText("subHorizontalPosition = " +
                        subHorizontalPosition, 50,
                blockSize * 10, paint);

        canvas.drawText("subVerticalPosition = " +
                        subVerticalPosition, 50,
                blockSize * 11, paint);

        canvas.drawText("hit = " + hit,
                50, blockSize * 12, paint);

        canvas.drawText("shotsTaken = " +
                        shotsTaken,
                50, blockSize * 13, paint);

        canvas.drawText("debugging = " + debugging,
                50, blockSize * 12, paint);
    }
}

