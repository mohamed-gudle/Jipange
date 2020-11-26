package com.timetablereader.app.data;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.timetablereader.app.utils.MongoConnection;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class User {

private String firstName;
private String lastName;
private String DOB;
private String password;
private String email;

    public User(String firstName,String lastName,String DOB,String email,String password){
        this.firstName=firstName;
        this.lastName=lastName;
        this.DOB=DOB;
        this.email=email;
        this.password=password;
    }
    public User(String email,String password){
        this.email=email;
        this.password=password;
    }


    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getDOB() {
        return DOB;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public boolean registerUser(User user1){
        MongoConnection.init();
        MongoCollection user = MongoConnection.getCollection("user");

        Gson gson = new Gson();
        String json = gson.toJson(user1);
        // Parse to bson document and insert
        Document doc = Document.parse(json);

        user.insertOne(doc);
        return true;
    }

    public boolean AuthenticateUser(){
        MongoConnection.init();
        MongoCollection user = MongoConnection.getCollection("user");
        BasicDBObject andQuery = new BasicDBObject();
        List<BasicDBObject> obj = new ArrayList<BasicDBObject>();
        obj.add(new BasicDBObject("email", this.getEmail()));
        obj.add(new BasicDBObject("password", this.getPassword()));
        andQuery.put("$and", obj);

        System.out.println(andQuery.toString());

        FindIterable iterable = user.find(andQuery);
        MongoCursor cursor = iterable.cursor();
        if(cursor.hasNext()) {
            return true;
        }
        return false;
    }


}
