package com.kxyu.domes.activitys;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;
import com.kxyu.domes.R;
import com.kxyu.domes.Utils.FileUtils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by yuki_cool on 2016/11/18.
 */

public class JsonActivity extends Activity implements View.OnClickListener {

    Button writeBtn;
    Button readBtn;
    TextView tv;
    HashMap<String, card> mMap = new HashMap<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json_layout);
        initMap();
        writeBtn = (Button) findViewById(R.id.writeForjson);
        readBtn = (Button) findViewById(R.id.readForjson);
        tv = (TextView) findViewById(R.id.showtext);
        writeBtn.setOnClickListener(this);
        readBtn.setOnClickListener(this);

    }

    private void initMap(){
        mMap.put("A", new card("Pop App", 1, 12323));
        mMap.put("B", new card("Pop Game", 12, 3232));
        mMap.put("C", new card("Navigation", 13, 3232));
        mMap.put("D", new card("Good Video", 14, 333));
    }
    private void writeJson(){
        Gson gson = new GsonBuilder().enableComplexMapKeySerialization().create();
        JSONObject allData = new JSONObject();
        String str = gson.toJson(mMap);
        Log.i("kxyu","string : "+str);
        FileUtils.writeFile(this, "cardinfo", str,true);
    }

    private void readjson(){
        Gson gson = new Gson();
        String data = FileUtils.readFile(this, "cardinfo");
        HashMap<String, card> tmpMap;
        tmpMap = gson.fromJson(data, new TypeToken<HashMap<String, card>>(){}.getType());
        Log.i("kxyu","readfile : "+tmpMap);
        if(tmpMap != null){
            Log.i("kxyu","readfile Map: "+tmpMap.get("A").showName);
        }
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.readForjson){
            readjson();
        }else if(view.getId() == R.id.writeForjson){
            writeJson();
        }
    }

    public class card{

        public  card(String showName, int id ,int color){
            this.showName = showName;
            this.id = id;
            this.color = color;
        }
        @SerializedName("showName")
        String showName;

        @SerializedName("color")
        int color;

        @SerializedName("i")
        int id;
    }
}
