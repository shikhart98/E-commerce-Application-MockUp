package com.example.shikh.application.database.table;

/**
 * Created by shikh on 24-06-2018.
 */

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.shikh.application.database.Consts.*;
import com.example.shikh.application.model.book;

import java.util.ArrayList;

import static com.example.shikh.application.database.Consts.*;

public class BookTable {

    private BookTable() {
    }

    public static final String TABLE_NAME = "Books";

    public interface Columns {
        String ID = "id";
        String NAME = "name";
        String AUTH = "auth";
        String PRICE = "price";
        String RATING = "rating";
        String URL = "url";
        String CATEGORY = "category";
    }

    public static final String CMD_CREATE_TABLE =
            CMD_CREATE_TABLE_INE + TABLE_NAME
                    + LBR
                    + Columns.ID + TYPE_INT + TYPE_PK_AI + COMMA
                    + Columns.NAME + TYPE_TEXT + COMMA
                    + Columns.AUTH + TYPE_TEXT + COMMA
                    + Columns.PRICE + TYPE_INT + COMMA
                    + Columns.RATING + TYPE_INT + COMMA
                    + Columns.URL + TYPE_TEXT + COMMA
                    + Columns.CATEGORY + TYPE_TEXT
                    + RBR + SEMI;

    public static long insertBook (book Book, SQLiteDatabase db){
        ContentValues newBook = new ContentValues();

        newBook.put(Columns.NAME,Book.getBook_name());
        newBook.put(Columns.AUTH,Book.getBook_auth());
        newBook.put(Columns.PRICE,Book.getBook_price());
        newBook.put(Columns.RATING,Book.getBook_rating());
        newBook.put(Columns.URL,Book.getImg_url());
        newBook.put(Columns.CATEGORY,Book.getBook_category());

        return db.insert(TABLE_NAME,null,newBook);
    }


    public static ArrayList<book> getAllBooks(SQLiteDatabase db){
        Cursor c = db.query(
                TABLE_NAME,
                new String[]{Columns.ID, Columns.NAME, Columns.AUTH, Columns.PRICE, Columns.RATING, Columns.URL, Columns.CATEGORY},
                null,
                null,
                null,
                null,
                null
        );

        ArrayList<book> Books = new ArrayList<>();
        c.moveToFirst();

        int indexID = c.getColumnIndex(Columns.ID);
        int indexNAME = c.getColumnIndex(Columns.NAME);
        int indexAUTH = c.getColumnIndex(Columns.AUTH);
        int indexPRICE = c.getColumnIndex(Columns.PRICE);
        int indexRATING = c.getColumnIndex(Columns.RATING);
        int indexURL = c.getColumnIndex(Columns.URL);
        int indexCATEGORY = c.getColumnIndex(Columns.CATEGORY);

        while(!c.isAfterLast()){
            Books.add(new book(
                    c.getString(indexNAME),
                    c.getString(indexAUTH),
                    c.getInt(indexPRICE),
                    c.getInt(indexRATING),
                    c.getString(indexURL),
                    c.getString(indexCATEGORY)
            ));
            c.moveToNext();
        }

        return Books;
    }

    public static ArrayList<book> getCategoryBooks(SQLiteDatabase db,String category){
        Cursor c = db.query(
                TABLE_NAME,
                new String[]{Columns.ID, Columns.NAME, Columns.AUTH, Columns.PRICE, Columns.RATING, Columns.URL, Columns.CATEGORY},
                Columns.CATEGORY+"=?",
                new String[]{category},
                null,
                null,
                null
        );

        ArrayList<book> Books = new ArrayList<>();
        c.moveToFirst();

        int indexID = c.getColumnIndex(Columns.ID);
        int indexNAME = c.getColumnIndex(Columns.NAME);
        int indexAUTH = c.getColumnIndex(Columns.AUTH);
        int indexPRICE = c.getColumnIndex(Columns.PRICE);
        int indexRATING = c.getColumnIndex(Columns.RATING);
        int indexURL = c.getColumnIndex(Columns.URL);
        int indexCATEGORY = c.getColumnIndex(Columns.CATEGORY);

        while(!c.isAfterLast()){
            Books.add(new book(
                    c.getString(indexNAME),
                    c.getString(indexAUTH),
                    c.getInt(indexPRICE),
                    c.getInt(indexRATING),
                    c.getString(indexURL),
                    c.getString(indexCATEGORY)
            ));
            c.moveToNext();
        }

        return Books;
    }

    public static void DeleteAll (SQLiteDatabase db){
        db.delete(TABLE_NAME,null,null);
    }
}
