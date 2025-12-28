package com.example.contactapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    EditText searchtxt;
    Button add,search;
    ListView lv;
    Adapter ad;
    db d;
    List<Contact> list=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        searchtxt=findViewById(R.id.searchtxt);

        add=findViewById(R.id.add);
        lv=findViewById(R.id.lv);

        d=new db(this,"db",null,1);
        list.addAll(d.getinfo());
        ad=new Adapter(this,list);
        lv.setAdapter(ad);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,addActivity.class);
                startActivity(i);

            }
        });

        searchtxt.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String text=searchtxt.getText().toString().trim();
                list.clear();
                if(text.isEmpty()){
                    list.addAll(d.getinfo());
                }
                else{
                    list.addAll(d.GetSearch(text));
                }
                ad.notifyDataSetChanged();
            }
        });

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Contact c=list.get(position);

                Intent i=new Intent(MainActivity.this,Details.class);
                i.putExtra("id",c.GetId());
                i.putExtra("name",c.GetName());
                i.putExtra("number",c.GetNumber());
                startActivity(i);


            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();
       list.clear();
       list.addAll(d.getinfo());
       ad.notifyDataSetChanged();
    }
}