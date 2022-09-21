package com.example.ch5_mycontactlist;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class ContactDBHelper extends SQLiteOpenHelper {

    //names db file, required, must use .db extension
    private static final String DATABASE_NAME = "mycontacts.db";
    //holds db version number (initialized to 1)
    //every time db accessed, it's compared to this version
    //      if version higher, onUpgrade executed & number incremented by developer to upgrade existing db
    private static final int DATABASE_VERSION = 1;

    // Database creation sql statement
    private static final String CREATE_TABLE_CONTACT =
            "create table contact (_id integer primary key autoincrement, "
                    + "contactname text not null, streetaddress text, "
                    + "city text, state text, zipcode text, "
                    + "phonenumber text, cellnumber text, "
                    + "email text, birthday text);";

    public ContactDBHelper(Context context) {
        //calls superclass's constructor method
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //if db named in DATABASE_NAME doesn't exist, execute SQL assigned to CREATE_TABLE_CONTACT variable
        db.execSQL(CREATE_TABLE_CONTACT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Log = writes message to LogCat
        //LogCat = system to collect & view system debug info
        Log.w(ContactDBHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS contact");
        onCreate(db);
    }
}
