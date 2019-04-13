package com.example.school;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class DepartmentActivity extends Activity {

    private ListView mDepartmentLv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.department_list);

        initView();
        initEvent();
    }

    private void initView() {
        mDepartmentLv = (ListView) findViewById(R.id.lv_department);
    }

    private void initEvent() {
        //设置列表点击事件
        mDepartmentLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });
    }
}
