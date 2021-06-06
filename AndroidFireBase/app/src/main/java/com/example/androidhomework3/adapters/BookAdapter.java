package com.example.androidhomework3.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidhomework3.R;
import com.example.androidhomework3.interfaces.IFirebaseAdapterComunication;
import com.example.androidhomework3.interfaces.IFragmentActivityCommunication;
import com.example.androidhomework3.models.Book;

import java.util.ArrayList;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewHolder> {
    private ArrayList<Book> books;
    private IFragmentActivityCommunication iFragmentActivityCommunication;
    private IFirebaseAdapterComunication iFirebaseAdapterComunication;

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
        ImageView arrow = holder.itemView.findViewById(R.id.arrow_image);
        TextView title = holder.itemView.findViewById(R.id.book_title);
        TextView author = holder.itemView.findViewById(R.id.book_author);
        TextView description = holder.itemView.findViewById(R.id.book_description);
        arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (iFirebaseAdapterComunication != null){
                    Book newBook = new Book(title.getText().toString(),
                            author.getText().toString(),
                            description.getText().toString());
                    iFirebaseAdapterComunication.deleteItem(newBook);
                }
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (iFragmentActivityCommunication != null){
                    iFragmentActivityCommunication.onFullItemFragment(title.getText().toString(),
                            author.getText().toString(),description.getText().toString());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return books.size();
    }


    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        if (recyclerView.getContext() instanceof IFragmentActivityCommunication){
            iFragmentActivityCommunication = (IFragmentActivityCommunication) recyclerView.getContext();
        }
        if (recyclerView.getContext() instanceof  IFirebaseAdapterComunication){
            iFirebaseAdapterComunication = (IFirebaseAdapterComunication) recyclerView.getContext();
        }
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
            arrow = view.findViewById(R.id.arrow_image);
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
