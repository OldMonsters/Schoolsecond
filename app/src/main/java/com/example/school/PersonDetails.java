package com.example.school;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import bean.ContactInfo;

public class PersonDetails extends Activity {

    private TextView mNameTv, mPhoneNumberTv, mEmailAddressTv, mMarksTv;
    private ImageView mHeadImageIv;
    private List<ContactInfo> mContactInfoList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_people_details);

        //获取Intent对象
        Intent intent = getIntent();
        //取出key对应的value值
        String name = intent.getStringExtra("name");

        initView();
        mNameTv.setText(name);
    }

    private void initView() {
        mNameTv = (TextView) findViewById(R.id.tv_name);
        mPhoneNumberTv = (TextView) findViewById(R.id.tv_phone_number);
        mEmailAddressTv = (TextView) findViewById(R.id.tv_email_address);
        mMarksTv = (TextView) findViewById(R.id.tv_marks);
        mHeadImageIv = (ImageView) findViewById(R.id.iv_head_image);
    }
}
