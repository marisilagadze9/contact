package com.example.contactapp;

public class Contact {
    String name;
    String number;
    int id;

    Contact(int id,String name,String number){
        this.id=id;
        this.name=name;
        this.number=number;
    }
    String GetName() {return name;}
    String GetNumber(){ return  number;}
    int  GetId(){ return id; }

    void SetName(String name){
        this.name=name;
    }

    void SetNumber(String number){
        this.number=number;
    }

}
