package com.kxyu.domes.activitys;



        import android.app.Activity;
        import android.os.Bundle;
        import android.util.Log;
        import android.view.GestureDetector;
        import android.view.MotionEvent;
        import android.view.View;
        import android.view.View.OnTouchListener;
        import android.widget.TextView;
        import android.widget.Toast;

        import com.kxyu.domes.R;

/**
 * Created by yuki_cool on 2016/11/24.
 */

public class GestureActivity  extends Activity implements OnTouchListener{

        private GestureDetector mGestureDetector;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_gesture_layout);

            mGestureDetector = new GestureDetector(new simpleGestureListener());

            TextView tv = (TextView)findViewById(R.id.tv);
            tv.setOnTouchListener(this);
            tv.setFocusable(true);
            tv.setClickable(true);
            tv.setLongClickable(true);
        }

        public boolean onTouch(View v, MotionEvent event) {
            // TODO Auto-generated method stub
            return mGestureDetector.onTouchEvent(event);
        }

        private class simpleGestureListener extends
                GestureDetector.SimpleOnGestureListener {

            /*****OnGestureListener�ĺ���*****/

            final int FLING_MIN_DISTANCE = 100, FLING_MIN_VELOCITY = 200;

            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
                                   float velocityY) {


                if (e1.getX() - e2.getX() > FLING_MIN_DISTANCE
                        && Math.abs(velocityX) > FLING_MIN_VELOCITY) {
                    // Fling left
                    Log.i("MyGesture", "Fling left");
                    Toast.makeText(GestureActivity.this, "Fling Left", Toast.LENGTH_SHORT).show();
                } else if (e2.getX() - e1.getX() > FLING_MIN_DISTANCE
                        && Math.abs(velocityX) > FLING_MIN_VELOCITY) {
                    // Fling right
                    Log.i("MyGesture", "Fling right");
                    Toast.makeText(GestureActivity.this, "Fling Right", Toast.LENGTH_SHORT).show();
                }
                return true;
            }

        }

}
