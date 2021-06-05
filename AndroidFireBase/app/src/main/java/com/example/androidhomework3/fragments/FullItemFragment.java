package com.example.androidhomework3.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.androidhomework3.R;

public class FullItemFragment extends Fragment {
    private String title;
    private String author;
    private String description;

    private TextView titleText;
    private TextView authorText;
    private TextView descriptionText;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        Bundle bundle = this.getArguments();
        if (bundle != null){
            title = bundle.getString("title");
            author = bundle.getString("author");
            description = bundle.getString("description");
        }
        else {
            Toast.makeText(getContext(), "Something went wrong...", Toast.LENGTH_LONG);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.item_book_full,container,false);
        initializeUI(view);
        return view;
    }

    private void initializeUI(View view){
        titleText = view.findViewById(R.id.book_title_full);
        titleText.setText(title);
        authorText = view.findViewById(R.id.book_author_full);
        authorText.setText(author);
        descriptionText = view.findViewById(R.id.book_description_full);
        descriptionText.setText(description);
    }
}
