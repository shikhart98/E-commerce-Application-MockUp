package com.example.shikh.application.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shikh.application.R;
import com.example.shikh.application.model.book;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by shikh on 24-06-2018.
 */

public class BookAdapter extends BaseAdapter {

    ArrayList<book> books = new ArrayList<>();
    Context context;

    public BookAdapter(ArrayList<book> books, Context context) {
        this.books = books;
        this.context = context;
    }

    @Override
    public int getCount() {
        return books.size();
    }

    @Override
    public book getItem(int position) {
        return books.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            LayoutInflater li = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = li.inflate(R.layout.item_book,parent,false);
        }
        book currentBook = getItem(position);
        ImageView bookImg = convertView.findViewById(R.id.bookImg);
        TextView book_name = convertView.findViewById(R.id.book_name);
        TextView book_auth_name = convertView.findViewById(R.id.book_auth_name);
        TextView book_rating = convertView.findViewById(R.id.book_rating);
        TextView book_price = convertView.findViewById(R.id.book_price);

        Picasso.get().load(currentBook.getImg_url()).into(bookImg);
        book_name.setText(currentBook.getBook_name());
        book_auth_name.setText(currentBook.getBook_auth());
        book_rating.setText(currentBook.getBook_rating()+"");
        book_price.setText("Rs. "+currentBook.getBook_price());


        return convertView;
    }
}
