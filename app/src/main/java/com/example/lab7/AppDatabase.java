package com.example.lab7;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
@Database(entities = {User.class}, version = 1)
public abstract  class AppDatabase extends RoomDatabase {
    private  static  final String DB_NAME ="room_db.db";
    private static AppDatabase instance;
    public static synchronized AppDatabase getInstance(Context ctx){
        if(instance == null)
            instance = Room.databaseBuilder(ctx.getApplicationContext(),AppDatabase.class,DB_NAME)
                    .allowMainThreadQueries()
                    .build();
        return instance;
    }
    public abstract UserDAO userDAO();
}
