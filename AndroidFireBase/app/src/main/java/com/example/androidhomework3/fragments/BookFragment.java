package com.example.androidhomework3.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidhomework3.R;
import com.example.androidhomework3.adapters.BookAdapter;
import com.example.androidhomework3.models.Book;
import com.example.androidhomework3.models.firebasemodels.BookFB;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static com.example.androidhomework3.models.Constants.ARG_FB_BOOKS;

public class BookFragment extends Fragment {
    private ArrayList<Book> books = new ArrayList<>();
    private BookAdapter bookAdapter = new BookAdapter(books);

    //UI
    private EditText titleEditText;
    private EditText authorEditText;
    private EditText descriptionEditText;

    private DatabaseReference database;

    private View currentView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        database = FirebaseDatabase.getInstance().getReference();


    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.book_fragment,container,false);
        currentView = view;
        setupView();
        return view;
    }

    public void setupView(){
        setupRecyclerView();

        titleEditText = currentView.findViewById(R.id.book_title_edit);
        authorEditText = currentView.findViewById(R.id.book_author_edit);
        descriptionEditText = currentView.findViewById(R.id.book_description_edit);
        getBooks();
        if (books.contains(new Book(titleEditText.getText().toString(),authorEditText.getText().toString(),
                descriptionEditText.getText().toString()))) {
            currentView.findViewById(R.id.add_update_button).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    updateBook(new Book(titleEditText.toString(),authorEditText.toString(),descriptionEditText.toString()));
                    Toast.makeText(getContext(),"Book Updated!",Toast.LENGTH_SHORT);
                }
            });
        }
        else
        {
            currentView.findViewById(R.id.add_update_button).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    insertBook();
                }
            });
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        database.addValueEventListener(bookListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        database.removeEventListener(bookListener);
    }

    private void setupRecyclerView(){
        RecyclerView recyclerView = currentView.findViewById(R.id.book_rv);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        bookAdapter = new BookAdapter(books);
        recyclerView.setAdapter(bookAdapter);
    }

    public void getBooks(){
        database.child(ARG_FB_BOOKS).get().addOnSuccessListener(dataSnapshot -> {
            Log.i("firebase","Go value ${dataSnapshot.value}");
            books.clear();
            DataSnapshot bookSnapshot = dataSnapshot.child(ARG_FB_BOOKS);
            Iterable<DataSnapshot> dataSnapshots = bookSnapshot.getChildren();
            for (DataSnapshot item : dataSnapshots){
                BookFB bookFB = item.getValue(BookFB.class);
                if (bookFB != null)
                {
                    Book book = new Book(bookFB);
                    books.add(book);
                }
            }
            bookAdapter.notifyDataSetChanged();
        }).addOnFailureListener(dataSnapshot->
        Log.e("firebase","Error getting data",new Throwable()));
    }

    public void updateBook(Book book){
        //to-do
    }

    public void insertBook(){
        if(titleEditText.getText().toString() == null ||
                authorEditText.getText().toString() == null ||
                descriptionEditText.getText().toString() == null){
            Toast.makeText(getContext(),"Cannot insert null values in database!",Toast.LENGTH_SHORT).show();
            return;
        }
        String titleText = titleEditText.getText().toString();
        String authorText = authorEditText.getText().toString();
        String descriptionText = descriptionEditText.getText().toString();

        BookFB bookFB = new BookFB(titleText,authorText,descriptionText);
        database.child(ARG_FB_BOOKS).push().setValue(bookFB);
        Toast.makeText(getContext(),"Succsesfull insertion in database!",Toast.LENGTH_SHORT).show();
    }

    ValueEventListener bookListener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            books.clear();
            DataSnapshot bookSnapshot = snapshot.child(ARG_FB_BOOKS);
            Iterable<DataSnapshot> dataSnapshots = bookSnapshot.getChildren();
            for (DataSnapshot item : dataSnapshots){
                BookFB bookFB = item.getValue(BookFB.class);
                if (bookFB != null)
                {
                    Book book = new Book(bookFB);
                    books.add(book);
                }
            }
            bookAdapter.notifyDataSetChanged();
        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {
            Toast.makeText(getContext(),"Sorry something went wrong",Toast.LENGTH_SHORT);
        }
    };
}
