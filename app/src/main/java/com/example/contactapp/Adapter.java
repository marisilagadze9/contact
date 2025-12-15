package com.example.contactapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class Adapter extends BaseAdapter {
    List<Contact> contactList;
    LayoutInflater inflater;

    Adapter(Context context,List<Contact>contactList ){
        this.contactList=contactList;
        inflater=LayoutInflater.from(context);

    }
    @Override
    public int getCount() {
        return contactList.size();
    }

    @Override
    public Object getItem(int position) {
        return contactList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null){
            convertView=inflater.inflate(R.layout.layout,parent,false);
        }
        Contact c=(Contact) getItem(position);
        TextView t=convertView.findViewById(R.id.tt);
        TextView n=convertView.findViewById(R.id.nn);
        t.setText(c.GetNumber().toString());
        n.setText(c.GetName().toString());

        return convertView;
    }
}
