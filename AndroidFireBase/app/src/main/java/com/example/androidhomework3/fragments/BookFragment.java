package com.example.androidhomework3.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.androidhomework3.R;
import com.example.androidhomework3.adapters.BookAdapter;
import com.example.androidhomework3.models.Book;

import java.util.ArrayList;

public class BookFragment extends Fragment {
    private ArrayList<Book> books = new ArrayList<>();
    private BookAdapter bookAdapter = new BookAdapter(books);

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.book_fragment,container,false);
        //setup recycler
        //getposts
        return view;
    }
}
