package com.example.androidhomework3.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidhomework3.R;
import com.example.androidhomework3.models.Book;

import java.util.ArrayList;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewHolder> {
    private ArrayList<Book> books;

    public BookAdapter(ArrayList<Book> books)
    {
        this.books = books;
    }

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_book,parent,false);
        BookViewHolder bookViewHolder = new BookViewHolder(view);
        return bookViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {
        Book book = books.get(position);
        holder.bind(book);
    }

    @Override
    public int getItemCount() {
        return books.size();
    }


    class BookViewHolder extends RecyclerView.ViewHolder{
        private TextView bookTitle;
        private TextView bookAuthor;
        private TextView bookDescription;
        private ImageView arrow;

        public BookViewHolder(View view)
        {
            super(view);
            bookTitle = view.findViewById(R.id.book_title);
            bookAuthor = view.findViewById(R.id.book_author);
            bookDescription = view.findViewById(R.id.book_description);
        }

        public void bind(Book book)
        {
            bookTitle.setText(book.getTitle());
            bookAuthor.setText(book.getAuthor());
            bookDescription.setText(book.getDescription());
        }

        public void setVisibility(boolean vis)
        {

        }
    }
}
