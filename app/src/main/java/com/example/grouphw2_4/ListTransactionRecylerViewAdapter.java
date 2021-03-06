package com.example.grouphw2_4;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import java.util.List;

import javaapplication1.Account;
import javaapplication1.Products;

/**
 * {@link RecyclerView.Adapter}
 * TODO: Replace the implementation with code for your data type.
 */
public class ListTransactionRecylerViewAdapter extends RecyclerView.Adapter<ListTransactionRecylerViewAdapter.ViewHolder> {
    private List<Account> accountList;
    private int accPos;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    // data is passed into the constructor
    ListTransactionRecylerViewAdapter(Context context, List<Account> a_accList, int a_accPos) {
        this.mInflater = LayoutInflater.from(context);
        this.accountList = a_accList;
        this.accPos = a_accPos;
    }

    // inflates the row layout from xml when needed
    @Override
    public ListTransactionRecylerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.recyclerview_row, parent, false);
        return new ListTransactionRecylerViewAdapter.ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ListTransactionRecylerViewAdapter.ViewHolder holder, int position) {
        String category = accountList.get(accPos).getTransaction(position).TransactionID;
        holder.myTextView.setText("TID: ");
        holder.myTextView.append(category);
        holder.myTextView.append(" | Customer: ");
        String customer = accountList.get(accPos).getTransaction(position).getCName();
        holder.myTextView.append(customer);
        holder.myTextView.append(" | Pname: ");
        String pname = accountList.get(accPos).getTransaction(position).getPName();
        holder.myTextView.append(pname);
        holder.myTextView.append(" | Quality: ");
        int Q = accountList.get(accPos).getTransaction(position).getQuality();
        String Quality = Integer.toString(Q);
        holder.myTextView.append(Quality);
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return accountList.get(accPos).getTransactionList().size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView myTextView;

        ViewHolder(View itemView) {
            super(itemView);
            myTextView = itemView.findViewById(R.id.tvAnimalName);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    // convenience method for getting data at click position
    String getItem(int id) {
        return accountList.get(accPos).getTransaction(id).TransactionID;
    }

    // allows clicks events to be caught
    void setClickListener(ListTransactionRecylerViewAdapter.ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }

}
