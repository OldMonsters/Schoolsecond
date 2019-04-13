package com.example.school;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import bean.ContactInfo;
import utils.ContactHelper;
import view.IndexSideBar;
import view.SearchEditText;

public class MainActivity extends Activity {

    //nox_adb.exe connect 127.0.0.1:62001
    private ListView mContactLv;
    private IndexSideBar mIndexSideBar;
    private ContactAdapter mContactAdapter;
    private SearchEditText mSearchEt;
    private List<ContactInfo> mContactInfoList;
    private TextView mLetterDialogTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initEvent();
        initContactAdapter();
    }

    private void initView() {
        mSearchEt = (SearchEditText) findViewById(R.id.contact_search);
        mLetterDialogTv = (TextView) findViewById(R.id.tv_letter_dialog);
        mIndexSideBar = (IndexSideBar) findViewById(R.id.sb_index_letter);
        mContactLv = (ListView) findViewById(R.id.lv_contacts);
    }

    private void initEvent() {
        //设置联系人列表的点击事件监听
        mContactLv.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //ContactInfo contactInfo = (ContactInfo) mContactAdapter.getItem(position);
                //Toast.makeText(MainActivity.this, contactInfo.getRawName(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, PersonDetails.class);
                Bundle bundle = new Bundle();
                bundle.putString("name", mContactInfoList.get(position).getRawName());
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        // 设置侧边栏的触摸事件监听
        mIndexSideBar.setOnTouchLetterListener(new IndexSideBar.OnTouchLetterListener(){
            @Override
            public void onTouchingLetterListener(String letter){
                mLetterDialogTv.setText(letter);
                mLetterDialogTv.setVisibility(View.VISIBLE);

                int position = mContactAdapter.getPositionForSection(letter.charAt(0));
                if (position != -1){
                    mContactLv.setSelection(position);  //跳至点击的字母
                }
            }

            @Override
            public void onTouchedLetterListener() {
                mLetterDialogTv.setVisibility(View.GONE);
            }
        });

        // 设置搜索框的文本内容改变事件监听
        mSearchEt.addTextChangedListener(new SearchEditText.MiddleTextWatcher(){
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count){
                List<ContactInfo> mFilterList = ContactHelper.contactFilter(s.toString(), mContactInfoList);
                mContactAdapter.updateContactInfoList(mFilterList);     //更新
                if (false){
                    Toast.makeText(MainActivity.this, s.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void initContactAdapter() {
        String[] contacts = new String[]{
                "露娜", "李白", "韩信", "太乙真人", "李元芳", "阿珂", "夏侯惇", "关羽", "张飞", "刘备", "貂蝉", "吕布", "王昭君", "武则天",
                "百里守约", "百里玄策", "司马懿", "孙策", "干将莫邪", "裴擒虎", "张良", "诸葛亮", "达摩", "蒙奇", "曹操", "钟馗", "钟无艳",
                "程咬金", "米莱狄", "狄仁杰", "后羿", "大乔", "小乔", "刘邦", "杨玉环", "马可波罗", "狂铁", "苏烈", "赵云", "公孙离", "鬼谷子",
                "成吉思汗", "哪吒", "杨戬", "嬴政", "女娲", "周瑜", "弈星", "扁鹊", "甄姬", "墨子", "高渐离", "亚瑟", "姜子牙", "宫本武藏",
                "牛魔", "庄周", "蔡文姬", "黄忠", "鲁班七号", "铠", "妲己", "白起", "安其拉", "不知火舞", "芈月", "项羽", "刘禅", "橘右京",
                "兰陵王", "典韦", "元歌", "明世隐", "雅典娜", "娜可露露", "东皇太一", "花木兰", "孙尚香", "孙膑", "虞姬", "孙悟空", "老夫子"
        };
        mContactInfoList = ContactHelper.setupContactInfoList(contacts);

        //设置侧边栏中的字母索引
        List<String> mLetterIndexList = ContactHelper.setupLetterIndexList(mContactInfoList);
        mIndexSideBar.setLetterIndexList(mLetterIndexList, false);

        //设置联系人列表的信息
        mContactAdapter = new ContactAdapter(this, mContactInfoList);
        mContactLv.setAdapter(mContactAdapter);
    }
}
