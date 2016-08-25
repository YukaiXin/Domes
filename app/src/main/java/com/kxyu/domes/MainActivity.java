package com.kxyu.domes;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.kxyu.domes.GridViewDemo.GridViewActivity;

/**
 * Created by lewa on 16-8-23.
 */
public class MainActivity extends Activity implements View.OnClickListener {

    Button gridViewBtn;
    Button videoBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
        gridViewBtn = (Button) findViewById(R.id.button);
        videoBtn = (Button) findViewById(R.id.button2);


        gridViewBtn.setOnClickListener(this);
        videoBtn.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {

        if(v.getId() == R.id.button){
            Intent intent = new Intent(this,GridViewActivity.class);
            startActivity(intent);
        }else if(v.getId() == R.id.button2){
            Intent intent = new Intent(this,VideoShowActivity.class);
            startActivity(intent);
        }

    }
}
