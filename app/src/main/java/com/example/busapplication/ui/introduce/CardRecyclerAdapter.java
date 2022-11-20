package com.example.busapplication.ui.introduce;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.busapplication.R;
import com.example.busapplication.data.Introduce;

import java.util.ArrayList;

public class CardRecyclerAdapter extends RecyclerView.Adapter<CardRecyclerAdapter.ViewHolder> {
    private static final String TAG = "CardRecyclerAdapter";

    private ArrayList<Introduce> mBusData;
    private View.OnClickListener mListener;

    /**
     * Provide a reference to the type of views that you are using (custom ViewHolder)
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView textView;
        private final ImageView imageView;
        final LinearLayout linearLayout;

        public ViewHolder(View v) {
            super(v);
            // Define click listener for the ViewHolder's View.
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d(TAG, "Element " + getAdapterPosition() + " clicked.");
                }
            });
            textView = (TextView) v.findViewById(R.id.textView_busName_card);
            imageView = (ImageView) v.findViewById(R.id.bus_image);
            linearLayout = (LinearLayout) v.findViewById(R.id.card_linearLayout);
        }

        public TextView getTextView() {
            return textView;
        }

        public ImageView getImageView() {
            return imageView;
        }
    }
    // END_INCLUDE(recyclerViewSampleViewHolder)
    public CardRecyclerAdapter(ArrayList<Introduce> busData) {
        //Log.d(TAG, "called card recycler adapter");
        mBusData = busData;
    }

    // BEGIN_INCLUDE(recyclerViewOnCreateViewHolder)
    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view.
        //Log.d(TAG, "called onCreateViewHoler");
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.content_card, viewGroup, false);
        return new ViewHolder(v);
    }
    // END_INCLUDE(recyclerViewOnCreateViewHolder)

    // BEGIN_INCLUDE(recyclerViewOnBindViewHolder)
    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        Log.d(TAG, "Element " + position + " set.");

        // Get element from your dataset at this position and replace the contents of the view
        // with that element
        //Log.d(TAG, mBusData.get(position).getName());
        viewHolder.getTextView().setText(mBusData.get(position).getName());
        viewHolder.getImageView().setImageResource(mBusData.get(position).getImg());

        viewHolder.linearLayout.setId(viewHolder.getAdapterPosition());
        viewHolder.linearLayout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                mListener.onClick(view);
            }
        });
    }

    public void setOnItemClickListener(View.OnClickListener listener){
        mListener = listener;
    }

    // END_INCLUDE(recyclerViewOnBindViewHolder)

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mBusData.size();
    }
}
