
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DBHandler2 extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "contactsManager3";
    private static final String TABLE_CONTACTS = "contacts2";
    private static final String KEY_ID = "id";
    private static final String KEY_WORD_NAME = "wordname";
    private static final String KEY_STAR = "star";

    public DBHandler2(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        //3rd argument to be passed is CursorFactory instance
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_CONTACTS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_WORD_NAME + " TEXT,"
                + KEY_STAR + " TEXT"+")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    // Upgrading database
    public void onUpgrade(SQLiteDatabase db,int j,int i) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);

        // Create tables again
        onCreate(db);
    }
    public void deleteRow(int i){
        SQLiteDatabase db = this.getWritableDatabase();
        String s= "DELETE * FROM "+TABLE_CONTACTS;
        db.execSQL(s);

        db.close();
    }
    // code to add the new contact
    void addContact(Contacts2 contact) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_WORD_NAME, contact.getWordName()); // Contact Name
        values.put(KEY_STAR, contact.getStar()); // Contact Phone


        db.insert(TABLE_CONTACTS, null, values);
        db.close();
    }

    // code to get the single contact
    Contacts2 getContact(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_CONTACTS, new String[]{KEY_ID,
                        KEY_WORD_NAME, KEY_STAR}, KEY_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Contacts2 contact = new Contacts2(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2));
        return contact;
    }

    // code to get all contacts in a list view
    public List<Contacts2> getAllContacts() {
        List<Contacts2> contactList = new ArrayList<Contacts2>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_CONTACTS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Contacts2 contact = new Contacts2();
                contact.setID(Integer.parseInt(cursor.getString(0)));
                contact.setWordName(cursor.getString(1));
                contact.setStar(cursor.getString(2));

                // Adding contact to list
                contactList.add(contact);
            } while (cursor.moveToNext());
        }

        // return contact list
        return contactList;
    }

    // code to update the single contact
    public int updateContact(Contacts2 contact) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_WORD_NAME, contact.getWordName());
        values.put(KEY_STAR, contact.getStar());

        // updating row
        return db.update(TABLE_CONTACTS, values, KEY_ID + " = ?",
                new String[] { String.valueOf(contact.getID()) });
    }

    // Deleting single contact
    public void deleteContact(Contacts2 contact) {
        SQLiteDatabase db = this.getWritableDatabase();
        String selectQuery = "DELETE FROM "+TABLE_CONTACTS+ " WHERE "+KEY_WORD_NAME+" = "+'"'+contact.getWordName()+'"';
        db.execSQL(selectQuery);
        db.close();
        /*db.delete(TABLE_CONTACTS, KEY_ID + " = ?",
                new String[] { String.valueOf(contact.getID()) });
        db.close();*/
    }
    public void deleteWord(String word) {
        SQLiteDatabase db = this.getWritableDatabase();
        String selectQuery = "DELETE FROM "+TABLE_CONTACTS+ " WHERE "+KEY_WORD_NAME+" = "+'"'+word+'"';
        db.execSQL(selectQuery);
        db.close();
        /*db.delete(TABLE_CONTACTS, KEY_ID + " = ?",
                new String[] { String.valueOf(contact.getID()) });
        db.close();*/
    }

    // Getting contacts Count
    public int getRecordsCount() {
        int count = 0;
        String countQuery = "SELECT  * FROM " + TABLE_CONTACTS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        if(cursor != null && !cursor.isClosed()){
            count = cursor.getCount();
            cursor.close();
        }
        return count;
    }
    public int wordExist(String word){
        int count = 0;
        String countQuery = "SELECT  * FROM " + TABLE_CONTACTS+ " WHERE "+ KEY_WORD_NAME +" = "+'"'+word+'"';
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        if(cursor != null && !cursor.isClosed()){
            count = cursor.getCount();
            cursor.close();
        }
        return count;
    }
}
