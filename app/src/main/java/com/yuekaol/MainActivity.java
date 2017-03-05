package com.yuekaol;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.yuekaol.adapter.OkHttpManager;
import com.yuekaol.adapter.R1Adapter;
import com.yuekaol.adapter.R2Adapter;
import com.yuekaol.adapter.R3Adapter;

import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import okhttp3.Request;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mR1;
    private RecyclerView mR2;
    private RecyclerView mR3;
    private TextView mR2_t;
    private TextView mR3_t;
    private R1Adapter r1Adapter;
    private List<Bean.RsBean> rs;
    private R2Adapter r2Adapter;
    private R3Adapter r3Adapter;
    private List<Bean.RsBean.ChildrenBeanX>  children;
    private List<Bean.RsBean.ChildrenBeanX.ChildrenBean> children1;
    private List<Bean.RsBean.ChildrenBeanX.ChildrenBean> children0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initHeader();
        initWidget();
        setWidgetState();

    }

    @Override
    protected void onResume() {
        super.onResume();
        getUrl();
    }

    private void initHeader() {

    }

    private void initWidget() {

        mR1 = (RecyclerView) findViewById(R.id.re_1);
        mR1.addItemDecoration(new DividerItemDecoration(MainActivity.this,DividerItemDecoration.VERTICAL));
        mR1.addItemDecoration(new DividerItemDecoration(MainActivity.this,DividerItemDecoration.HORIZONTAL));

        r1Adapter = new R1Adapter(MainActivity.this);
        mR1.setLayoutManager(new LinearLayoutManager(this));
        mR1.setAdapter(r1Adapter);

        mR2 = (RecyclerView) findViewById(R.id.re_2);
        mR2.addItemDecoration(new DividerItemDecoration(MainActivity.this,DividerItemDecoration.VERTICAL));
        mR2.addItemDecoration(new DividerItemDecoration(MainActivity.this,DividerItemDecoration.HORIZONTAL));
        r2Adapter = new R2Adapter(this);
        mR2.setLayoutManager(new GridLayoutManager(this,3));
        mR2.setAdapter(r2Adapter);

        mR3 = (RecyclerView) findViewById(R.id.re_3);
        mR3.addItemDecoration(new DividerItemDecoration(MainActivity.this,DividerItemDecoration.VERTICAL));
        mR3.addItemDecoration(new DividerItemDecoration(MainActivity.this,DividerItemDecoration.HORIZONTAL));
        r3Adapter = new R3Adapter(this);
        mR3.setLayoutManager(new GridLayoutManager(this,3));
        mR3.setAdapter(r3Adapter);

        mR2_t = (TextView) findViewById(R.id.re_2_tv);
        mR3_t = (TextView) findViewById(R.id.re_3_tv);


    }

    private void setWidgetState() {

        r1Adapter.setOnRVitemClickListener(new R1Adapter.OnRVitemClickListener() {

            @Override
            public void onClickListener(int position) {

                children = rs.get(position).getChildren();

                mR2_t.setText(children.get(0).getDirName());
                children0 = MainActivity.this.children.get(0).getChildren();
                r2Adapter.addList(children0);

                mR3_t.setText(MainActivity.this.children.get(1).getDirName());
                children1 = MainActivity.this.children.get(1).getChildren();
                r3Adapter.addList(children1);

            }
        });

        r2Adapter.setOnRVitemClickListener(new R2Adapter.OnRVitemClickListener() {
            @Override
            public void onClickListener(int position) {

                Toast.makeText(MainActivity.this,children0.get(position).getDirName(),Toast.LENGTH_SHORT).show();

            }
        });

        r3Adapter.setOnRVitemClickListener(new R3Adapter.OnRVitemClickListener() {
            @Override
            public void onClickListener(int position) {

                Toast.makeText(MainActivity.this,children1.get(position).getDirName(),Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void getUrl(){

        String url = "http://mock.eoapi.cn/success/4q69ckcRaBdxhdHySqp2Mnxdju5Z8Yr4";
        OkHttpManager.getAsync(url, new OkHttpManager.DataCallBack() {
            @Override
            public void requestFailure(Request request, IOException e) {

            }

            @Override
            public void requestSuccess(String result) throws Exception {

                Gson gson = new Gson();

                Bean bean = gson.fromJson(result, Bean.class);

                rs = bean.getRs();

                Log.d("zhulei", rs.get(0).getDirName() + "-------");

                r1Adapter.addList(rs);

            }
        });
    }
}
