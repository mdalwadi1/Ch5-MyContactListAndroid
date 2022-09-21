package com.example.ch5_mycontactlist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;

public class ContactDataSource {

    //lightweight database that comes with Android OS
    private SQLiteDatabase database;
    private ContactDBHelper dbHelper;

    public ContactDataSource(Context context) {
        dbHelper = new ContactDBHelper(context);
    }

    //throws SQLException = looking for any errors with SQL parsing/execution
    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public boolean insertContact(Contact c) {
        //false until update and insert methods return Boolean to say if operation succeeded, then true
        boolean didSucceed = false;
        try {
            //ContentValues = object that stores sets of key/value pairs to assign contact data to correct fields
            ContentValues initialValues = new ContentValues();

            initialValues.put("contactname", c.getContactName());
            initialValues.put("streetaddress", c.getStreetAddress());
            initialValues.put("city", c.getCity());
            initialValues.put("state", c.getState());
            initialValues.put("zipcode", c.getZipCode());
            initialValues.put("phonenumber", c.getPhoneNumber());
            initialValues.put("cellnumber", c.getCellNumber());
            initialValues.put("email", c.getEMail());
            initialValues.put("birthday",String.valueOf(c.getBirthday().getTimeInMillis()));

            //inserts contact values and returns number of records/rows successfully inserted
            //number of rows inserted is compared to 0, and if greater, operation = successful
            didSucceed = database.insert("contact", null, initialValues) > 0;
        }
        //If exception is thrown, nothing happens, because it's false (initial value was false)
        catch (Exception e) {
            //Do nothing -will return false if there is an exception
        }
        return didSucceed;
    }

    public boolean updateContact(Contact c) {
        boolean didSucceed = false;
        try {
            //need's contact's ID, pulls value from selected contact, and assigned to rowID
            Long rowId = (long) c.getContactID();
            ContentValues updateValues = new ContentValues();

            updateValues.put("contactname", c.getContactName());
            updateValues.put("streetaddress", c.getStreetAddress());
            updateValues.put("city", c.getCity());
            updateValues.put("state", c.getState());
            updateValues.put("zipcode", c.getZipCode());
            updateValues.put("phonenumber", c.getPhoneNumber());
            updateValues.put("cellnumber", c.getCellNumber());
            updateValues.put("email", c.getEMail());
            updateValues.put("birthday",
                    String.valueOf(c.getBirthday().getTimeInMillis()));

            //places changes in database
            //if operation = success --> return number of records affected; greater than 0 = successful operation
            didSucceed = database.update("contact", updateValues, "_id=" + rowId, null) > 0;
        }
        catch (Exception e) {
            //Do nothing -will return false if there is an exception
        }
        return didSucceed;
    }

    public int getLastContactId() {
        //integer variable holds retrieved ID; default value = -1
        int lastId;
        try {
            //gets max value for _id field in table
            //last contact entered will have max value bc _id field is autoincremented
            String query = "Select MAX(_id) from contact";
            //cursor declared and assigned to hold query execution results
            Cursor cursor = database.rawQuery(query, null);

            //moveToFirst = move to first record in returned data
            cursor.moveToFirst();
            //fields in record set are indexed starting at 0
            lastId = cursor.getInt(0);
            cursor.close();
        }
        catch (Exception e) {
            lastId = -1;
        }
        return lastId;
    }
}
