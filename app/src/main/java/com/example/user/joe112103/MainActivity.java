package com.example.user.joe112103;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //使用volley抓取網站的json檔資料
        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);// 建立Requestqueue(對列)
        //建立要求(request):1.url 2.成功了do what? 3.失敗了do what?
        /*因有些網頁在傳XML資料時,雖然網頁本身為UTF8編碼,但Html 標頭檔卻未明確說明此網頁為UTF8編碼,將導致volley抓取時
          會顯示亂碼,因此要建立一個class (MyUTF8StringRequest) ,強制將網路收到的資料指定為UTF8,使volley接收時才能正確顯示
           */
        StringRequest request = new MyUTF8StringRequest("http://udn.com/rssfeed/news/1",
        //StringRequest request = new StringRequest("http://data.ntpc.gov.tw/od/data/api/BF90FA7E-C358-4CDA-B579-B6C84ADC96A1?$format=json",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) { //成功
                        Log.d("NET", response);

                        //方法2:用Gson 抓 json檔資料
//                        Gson gson = new Gson();
                        //建立Animal class定義 loc[]陣列的內容
//                        Animal loc[] = gson.fromJson(response, Animal[].class);
//                        for (Animal a : loc)
//                        {
//                            Log.d("NET", a.district);
//                        }

                        //方法1:用JsonArray 放進JsonObject(物件) 來抓json檔資料
//                        try {
//                            JSONArray array = new JSONArray(response);//jason檔是一個陣列檔案,陣列中包含屬性
//                            for (int i=0;i<array.length();i++)
//                            {
//                                JSONObject obj = array.getJSONObject(i);//將jason陣列屬性依順序放入 obj(物件)
//                                String str = obj.getString("district");//抓取物件裡面的資料
//                                Log.d("NET", str);//show出資料
//                            }
//
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) { //失敗

            }
        });
        queue.add(request); //將request加入 requestqueue
        queue.start(); //啟動queue

    }
}
