package com.timetablereader.app;


import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import org.bson.Document;

import java.util.Iterator;

public class MongoConnection {

    private static MongoDatabase database;

    public MongoDatabase init() {
        MongoClient mongoClient = MongoClients.create(
                "mongodb+srv://oop2:oop2@oop2.h15yd.mongodb.net/TimeTableReader?retryWrites=true&w=majority");
        database = mongoClient.getDatabase("TimeTableReader");

        return database;

    }
    public MongoCollection getCollection (String collectionName){
        MongoCollection collection = database.getCollection(collectionName);
        return collection;
    }

}