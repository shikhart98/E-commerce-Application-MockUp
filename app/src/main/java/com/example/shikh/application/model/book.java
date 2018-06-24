package com.example.shikh.application.model;

/**
 * Created by shikh on 24-06-2018.
 */

public class book {
    String book_name;
    String book_auth;
    int book_price;
    int book_rating;
    String img_url;
    String book_category;


    public book(String book_name, String book_auth, int book_price, int book_rating, String img_url, String book_category) {
        this.book_name = book_name;
        this.book_auth = book_auth;
        this.book_price = book_price;
        this.book_rating = book_rating;
        this.img_url = img_url;
        this.book_category = book_category;

    }

    public String getBook_category() {
        return book_category;
    }

    public void setBook_category(String book_category) {
        this.book_category = book_category;
    }

    public String getBook_name() {
        return book_name;
    }

    public String getBook_auth() {
        return book_auth;
    }

    public int getBook_price() {
        return book_price;
    }

    public int getBook_rating() {
        return book_rating;
    }

    public void setBook_name(String book_name) {
        this.book_name = book_name;
    }

    public void setBook_auth(String book_auth) {
        this.book_auth = book_auth;
    }

    public void setBook_price(int book_price) {
        this.book_price = book_price;
    }

    public void setBook_rating(int book_rating) {
        this.book_rating = book_rating;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getImg_url() {

        return img_url;
    }
}
