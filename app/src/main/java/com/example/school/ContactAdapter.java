package com.example.school;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.SectionIndexer;
import android.widget.TextView;

import java.util.List;

import bean.ContactInfo;

public class ContactAdapter extends BaseAdapter implements SectionIndexer {

    private LayoutInflater inflater;
    private List<ContactInfo> mContactInfoList;

    public ContactAdapter(Context context, List<ContactInfo> list){
        mContactInfoList = list;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mContactInfoList.size();
    }

    @Override
    public Object getItem(int position) {
        return mContactInfoList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        ViewHolder holder;
        if (convertView == null){
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.contact_list_item, null);
            holder.tv_letter = (TextView) convertView.findViewById(R.id.tv_contact_index_letter);
            holder.tv_name = (TextView) convertView.findViewById(R.id.tv_contact_name);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        ContactInfo contactInfo = mContactInfoList.get(position);

        int section = getSectionForPosition(position);
        int startSectionPosition = getPositionForSection(section);
        if (position == startSectionPosition){
            holder.tv_letter.setVisibility(View.VISIBLE);
            holder.tv_letter.setText(String.valueOf(contactInfo.getSortLetters().charAt(0)));
        }else{
            holder.tv_letter.setVisibility(View.GONE);
        }

        if (false){
            holder.tv_name.setText(contactInfo.getRawName() + " -> " + contactInfo.getPinyinName());
        } else {
            holder.tv_name.setText(contactInfo.getRawName());
        }

        return convertView;
    }

    private class ViewHolder {
        TextView tv_letter;
        TextView tv_name;
    }

    @Override
    public Object[] getSections() {
        return null;
    }

    @Override
    public int getPositionForSection(int sec) {
        for (int i = 0; i < getCount(); i++) {
            if (sec == getSectionForPosition(i)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int getSectionForPosition(int position) {
        return mContactInfoList.get(position).getSortLetters().charAt(0);
    }

    public void updateContactInfoList(List<ContactInfo> list) {
        mContactInfoList = list;
        notifyDataSetChanged();
    }
}
