package com.example.contactapp;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class Details extends AppCompatActivity {

    TextView name,number;
    ImageButton back,delete,sms,call,edit;
    db d;
    int id;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout3);
        name=findViewById(R.id.txtName);
        number=findViewById(R.id.txtNumber);
        back=findViewById(R.id.backbtn);
        delete=findViewById(R.id.btnDelete);
        sms=findViewById(R.id.btnSms);
        call=findViewById(R.id.btnCall);
        edit=findViewById(R.id.btnEdit);

        d=new db(this,"db",null,1);


        id=getIntent().getIntExtra("id",-1);
        String n=getIntent().getStringExtra("name");
        String t=getIntent().getStringExtra("number");
        name.setText(n);
        number.setText(t);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                d.Delete(id);
                finish();
            }
        });

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Details.this,Edit.class);
                i.putExtra("id",id);
                i.putExtra("name",name.getText().toString());
                i.putExtra("number",number.getText().toString());
                startActivity(i);
            }
        });
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(Details.this,new String[]{Manifest.permission.CALL_PHONE},1);
                }
                else{
                    String callnum=number.getText().toString().trim();
                    Intent i=new Intent(Intent.ACTION_CALL);
                    i.setData(Uri.parse("tel:"+callnum));
                    startActivity(i);
                }
            }
        });

        sms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String smsnum=number.getText().toString().trim();
                String message="first sms";
                Intent s=new Intent(Intent.ACTION_VIEW);
                s.setData(Uri.parse("smsto:"+smsnum));
                s.putExtra("sms_body",message);
                startActivity(s);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        Contact c=d.getContactById(id);
        name.setText(c.GetName());
        number.setText(c.GetNumber());
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==1){
            if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this, "წვდომა მიღებულია", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "წვდომა უარტოფილია", Toast.LENGTH_SHORT).show();
            }
        }
    }

}
