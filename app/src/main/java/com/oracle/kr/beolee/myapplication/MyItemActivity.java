package com.oracle.kr.beolee.myapplication;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class MyItemActivity extends AppCompatActivity {
    private static final String TAG = "MyItemActivity";
    ArrayList<MyItem> myitems = new ArrayList<>();
    MyItemAdapter adapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: +++");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_item);

        //데이타 원본 준비
        for(int i=0; i< 10; i++) {
            myitems.add(new MyItem(i, i, "title" + i, "desc" + i));

        }
        //어댑터 생성
        adapter = new MyItemAdapter(this, R.layout.my_item, myitems);
        
        //어댑터 연결
        ListView listView = (ListView) findViewById(R.id.my_item_list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(MyItemActivity.this, myitems.get(position).title, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MyItemActivity.this, MyCommentActivity.class);
                intent.putExtra("selectedItem", myitems.get(position));
                try {          intent.putExtra("previousItem", myitems.get(position-1)); } catch (Exception e) { }
                try {          intent.putExtra("nextItem", myitems.get(position+1)); } catch (Exception e) { }
                startActivity(intent);
            }
        });
        new MyItemAsyncTask().execute("");
        Log.d(TAG, "onCreate: ---");

    }
    private class MyItemAsyncTask extends AsyncTask<String, Void, ArrayList<MyItem>> {
        private static final String TAG = "MyItemAsyncTask";
        @Override
        protected ArrayList<MyItem> doInBackground(String... params) {
            Log.d(TAG, "doInBackground: +++");
            InputStream in;
            StringBuffer sb = new StringBuffer();
            String jsonStr;
            ArrayList<MyItem> myItems = new ArrayList<>();

            try {
                URL url = new URL("http://jsonplaceholder.typicode.com/posts");
                HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
                urlConn.setDoInput(true);
                urlConn.setRequestMethod("GET");
                urlConn.setRequestProperty("Content-Type", "application/json");
//                urlConn.setRequestProperty("Content-Type", "text/html");
//                                urlConn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                urlConn.setUseCaches(false);
                urlConn.setConnectTimeout(10000);
                urlConn.setReadTimeout(10000);
                in = urlConn.getInputStream();
                BufferedReader br = new BufferedReader( new InputStreamReader(in));

                String line = "";

                while ((line=br.readLine())!=null) {
                    sb.append(line);
                }
                in.close();
                Log.d(TAG, sb.toString());
            } catch(Exception e) {
                e.printStackTrace();
            }

//            jsonStr = "[\n" +
//                    "  {\n" +
//                    "    \"userId\": 1,\n" +
//                    "    \"id\": 1,\n" +
//                    "    \"title\": \"sunt aut facere repellat provident occaecati excepturi optio reprehenderit\",\n" +
//                    "    \"body\": \"quia et suscipit\\nsuscipit recusandae consequuntur expedita et cum\\nreprehenderit molestiae ut ut quas totam\\nnostrum rerum est autem sunt rem eveniet architecto\"\n" +
//                    "  },\n" +
//                    "  {\n" +
//                    "    \"userId\": 1,\n" +
//                    "    \"id\": 2,\n" +
//                    "    \"title\": \"qui est esse\",\n" +
//                    "    \"body\": \"est rerum tempore vitae\\nsequi sint nihil reprehenderit dolor beatae ea dolores neque\\nfugiat blanditiis voluptate porro vel nihil molestiae ut reiciendis\\nqui aperiam non debitis possimus qui neque nisi nulla\"\n" +
//                    "  },\n" +
//                    "  {\n" +
//                    "    \"userId\": 1,\n" +
//                    "    \"id\": 3,\n" +
//                    "    \"title\": \"ea molestias quasi exercitationem repellat qui ipsa sit aut\",\n" +
//                    "    \"body\": \"et iusto sed quo iure\\nvoluptatem occaecati omnis eligendi aut ad\\nvoluptatem doloribus vel accusantium quis pariatur\\nmolestiae porro eius odio et labore et velit aut\"\n" +
//                    "  },\n" +
//                    "  {\n" +
//                    "    \"userId\": 1,\n" +
//                    "    \"id\": 4,\n" +
//                    "    \"title\": \"eum et est occaecati\",\n" +
//                    "    \"body\": \"ullam et saepe reiciendis voluptatem adipisci\\nsit amet autem assumenda provident rerum culpa\\nquis hic commodi nesciunt rem tenetur doloremque ipsam iure\\nquis sunt voluptatem rerum illo velit\"\n" +
//                    "  },\n" +
//                    "  {\n" +
//                    "    \"userId\": 1,\n" +
//                    "    \"id\": 5,\n" +
//                    "    \"title\": \"nesciunt quas odio\",\n" +
//                    "    \"body\": \"repudiandae veniam quaerat sunt sed\\nalias aut fugiat sit autem sed est\\nvoluptatem omnis possimus esse voluptatibus quis\\nest aut tenetur dolor neque\"\n" +
//                    "  },\n" +
//                    "  {\n" +
//                    "    \"userId\": 1,\n" +
//                    "    \"id\": 6,\n" +
//                    "    \"title\": \"dolorem eum magni eos aperiam quia\",\n" +
//                    "    \"body\": \"ut aspernatur corporis harum nihil quis provident sequi\\nmollitia nobis aliquid molestiae\\nperspiciatis et ea nemo ab reprehenderit accusantium quas\\nvoluptate dolores velit et doloremque molestiae\"\n" +
//                    "  },\n" +
//                    "  {\n" +
//                    "    \"userId\": 1,\n" +
//                    "    \"id\": 7,\n" +
//                    "    \"title\": \"magnam facilis autem\",\n" +
//                    "    \"body\": \"dolore placeat quibusdam ea quo vitae\\nmagni quis enim qui quis quo nemo aut saepe\\nquidem repellat excepturi ut quia\\nsunt ut sequi eos ea sed quas\"\n" +
//                    "  },\n" +
//                    "  {\n" +
//                    "    \"userId\": 1,\n" +
//                    "    \"id\": 8,\n" +
//                    "    \"title\": \"dolorem dolore est ipsam\",\n" +
//                    "    \"body\": \"dignissimos aperiam dolorem qui eum\\nfacilis quibusdam animi sint suscipit qui sint possimus cum\\nquaerat magni maiores excepturi\\nipsam ut commodi dolor voluptatum modi aut vitae\"\n" +
//                    "  },\n" +
//                    "  {\n" +
//                    "    \"userId\": 1,\n" +
//                    "    \"id\": 9,\n" +
//                    "    \"title\": \"nesciunt iure omnis dolorem tempora et accusantium\",\n" +
//                    "    \"body\": \"consectetur animi nesciunt iure dolore\\nenim quia ad\\nveniam autem ut quam aut nobis\\net est aut quod aut provident voluptas autem voluptas\"\n" +
//                    "  },\n" +
//                    "  {\n" +
//                    "    \"userId\": 1,\n" +
//                    "    \"id\": 10,\n" +
//                    "    \"title\": \"optio molestias id quia eum\",\n" +
//                    "    \"body\": \"quo et expedita modi cum officia vel magni\\ndoloribus qui repudiandae\\nvero nisi sit\\nquos veniam quod sed accusamus veritatis error\"\n" +
//                    "  },\n" +
//                    "  {\n" +
//                    "    \"userId\": 2,\n" +
//                    "    \"id\": 11,\n" +
//                    "    \"title\": \"et ea vero quia laudantium autem\",\n" +
//                    "    \"body\": \"delectus reiciendis molestiae occaecati non minima eveniet qui voluptatibus\\naccusamus in eum beatae sit\\nvel qui neque voluptates ut commodi qui incidunt\\nut animi commodi\"\n" +
//                    "  },\n" +
//                    "  {\n" +
//                    "    \"userId\": 2,\n" +
//                    "    \"id\": 12,\n" +
//                    "    \"title\": \"in quibusdam tempore odit est dolorem\",\n" +
//                    "    \"body\": \"itaque id aut magnam\\npraesentium quia et ea odit et ea voluptas et\\nsapiente quia nihil amet occaecati quia id voluptatem\\nincidunt ea est distinctio odio\"\n" +
//                    "  },\n" +
//                    "  {\n" +
//                    "    \"userId\": 2,\n" +
//                    "    \"id\": 13,\n" +
//                    "    \"title\": \"dolorum ut in voluptas mollitia et saepe quo animi\",\n" +
//                    "    \"body\": \"aut dicta possimus sint mollitia voluptas commodi quo doloremque\\niste corrupti reiciendis voluptatem eius rerum\\nsit cumque quod eligendi laborum minima\\nperferendis recusandae assumenda consectetur porro architecto ipsum ipsam\"\n" +
//                    "  },\n" +
//                    "  {\n" +
//                    "    \"userId\": 2,\n" +
//                    "    \"id\": 14,\n" +
//                    "    \"title\": \"voluptatem eligendi optio\",\n" +
//                    "    \"body\": \"fuga et accusamus dolorum perferendis illo voluptas\\nnon doloremque neque facere\\nad qui dolorum molestiae beatae\\nsed aut voluptas totam sit illum\"\n" +
//                    "  },\n" +
//                    "  {\n" +
//                    "    \"userId\": 2,\n" +
//                    "    \"id\": 15,\n" +
//                    "    \"title\": \"eveniet quod temporibus\",\n" +
//                    "    \"body\": \"reprehenderit quos placeat\\nvelit minima officia dolores impedit repudiandae molestiae nam\\nvoluptas recusandae quis delectus\\nofficiis harum fugiat vitae\"\n" +
//                    "  },\n" +
//                    "  {\n" +
//                    "    \"userId\": 2,\n" +
//                    "    \"id\": 16,\n" +
//                    "    \"title\": \"sint suscipit perspiciatis velit dolorum rerum ipsa laboriosam odio\",\n" +
//                    "    \"body\": \"suscipit nam nisi quo aperiam aut\\nasperiores eos fugit maiores voluptatibus quia\\nvoluptatem quis ullam qui in alias quia est\\nconsequatur magni mollitia accusamus ea nisi voluptate dicta\"\n" +
//                    "  },\n" +
//                    "  {\n" +
//                    "    \"userId\": 2,\n" +
//                    "    \"id\": 17,\n" +
//                    "    \"title\": \"fugit voluptas sed molestias voluptatem provident\",\n" +
//                    "    \"body\": \"eos voluptas et aut odit natus earum\\naspernatur fuga molestiae ullam\\ndeserunt ratione qui eos\\nqui nihil ratione nemo velit ut aut id quo\"\n" +
//                    "  },\n" +
//                    "  {\n" +
//                    "    \"userId\": 2,\n" +
//                    "    \"id\": 18,\n" +
//                    "    \"title\": \"voluptate et itaque vero tempora molestiae\",\n" +
//                    "    \"body\": \"eveniet quo quis\\nlaborum totam consequatur non dolor\\nut et est repudiandae\\nest voluptatem vel debitis et magnam\"\n" +
//                    "  }\n" +
//                    "]";
            // JSON 파싱
            try {
                JSONArray jsonArray = new JSONArray(sb.toString());
                for(int i=0; i<jsonArray.length(); i++) {
                    JSONObject jsonObj = jsonArray.getJSONObject(i);
                    myItems.add(new MyItem(jsonObj.getInt("id"),
                            jsonObj.getInt("userId"),
                            jsonObj.getString("title"),
                            jsonObj.getString("body")));
                }
            } catch(JSONException e) {

            }
            Log.d(TAG, "doInBackground: ---");
            return myItems;
        }

        protected void onPostExecute(ArrayList<MyItem> s) {
            super.onPostExecute(s);
            myitems.clear();
            myitems.addAll(s);
            adapter.notifyDataSetChanged();
        }
    }

}
