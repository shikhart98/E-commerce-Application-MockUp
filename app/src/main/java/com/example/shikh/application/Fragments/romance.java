package com.example.shikh.application.Fragments;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.shikh.application.R;
import com.example.shikh.application.adapter.BookAdapter;
import com.example.shikh.application.database.DatabaseHelper;
import com.example.shikh.application.database.table.BookTable;
import com.example.shikh.application.model.book;

import java.util.ArrayList;


public class romance extends Fragment {

    ArrayList<book> Books;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Books = new ArrayList<>();
        DatabaseHelper helper = new DatabaseHelper(getActivity());
        SQLiteDatabase read = helper.getReadableDatabase();
        Books = BookTable.getAllBooks(read);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_romance, container, false);
        ListView lv_romance = view.findViewById(R.id.lv_romance);
        BookAdapter adapter = new BookAdapter(Books, getActivity());
        lv_romance.setAdapter(adapter);
        return view;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
