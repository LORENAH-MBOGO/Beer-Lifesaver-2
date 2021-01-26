package com.skylar.beer_lifesaver.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.skylar.beer_lifesaver.BeerStyleDetailActivity;
import com.skylar.beer_lifesaver.R;

import org.parceler.Parcels;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import network.Datum;

public class BeerStyleListAdapter extends RecyclerView.Adapter<BeerStyleListAdapter.StyleViewHolder> {

    private List<Datum> mBeerStyles;
    private Context mContext;


    public BeerStyleListAdapter(Context context, List<Datum> beerStyles) {
        mContext = context;
        mBeerStyles = beerStyles;
    }

    @Override
    public BeerStyleListAdapter.StyleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.beer_style_list_item, parent, false);

        StyleViewHolder viewHolder = new StyleViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull BeerStyleListAdapter.StyleViewHolder holder, int position) {
        holder.bindStyle(mBeerStyles.get(position));
    }

    @Override
    public int getItemCount() {
//        return 9;
            return    mBeerStyles.size();
    }

    public class StyleViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.styleNameTextView)
        TextView mStyleNameTextView;
        //         still in development?
//        @BindView(R.id.styleImageView)
//        ImageView mStyleImageView;


//         still in development?
//        @BindView(R.id.descriptionTextView)
//        TextView mDescriptionNameTextView;

        private Context mContext;


        public StyleViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            mContext = itemView.getContext();
            itemView.setOnClickListener(this);
        }

        public void bindStyle(@NonNull Datum beerStyle) {
            mStyleNameTextView.setText((CharSequence) beerStyle.getName());

        }


    @Override
    public void onClick(View v) {
        int itemPosition = getLayoutPosition();
        Intent intent = new Intent(mContext, BeerStyleListAdapter.class);
        intent.putExtra("position", itemPosition);
        intent.putExtra("beerStyles", Parcels.wrap(mBeerStyles));
        mContext.startActivity(intent);
    }
}
}




//    public class RestaurantViewHolder extends RecyclerView.ViewHolder {
//        @BindView(R.id.restaurantImageView) ImageView mRestaurantImageView;
//        @BindView(R.id.restaurantNameTextView) TextView mNameTextView;
//        @BindView(R.id.categoryTextView) TextView mCategoryTextView;
//        @BindView(R.id.ratingTextView) TextView mRatingTextView;
//
//
//
//
//    @Override
//    public BeerStyleListAdapter.StyleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.beer_view_items, parent, false);
//        StyleViewHolder viewHolder = new StyleViewHolder(view);
//        return viewHolder;
//    }
//
////    @NonNull
////    @Override
////    public BeerStyleListAdapter.StyleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
////        View view =LayoutInflater.from(parent.getContext()).inflate(R.layout.beer_view_items, parent, false);
//////        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.beer_view_items, parent, false);
////
////        StyleViewHolder viewHolder = new StyleViewHolder(view);
////        return viewHolder;
////    }
//
//    @Override
//    public void onBindViewHolder(@NonNull BeerStyleListAdapter.StyleViewHolder holder, int position) {
//        holder.bindStyle(datumList.get(position));
//    }
//
//
//    @Override
//    public int getItemCount() {
//               return  9;
////                   return   mBeerStyles.size();
//    }
//
//    public class StyleViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
//        @BindView(R.id.styleImageView)
//        ImageView mStyleImageView;
//        @BindView(R.id.styleNameTextView)
//        TextView mStyleNameTextView;
//        @BindView(R.id.descriptionTextView)
//        TextView mDescriptionNameTextView;
//
//        private Context mContext;
//
//        public StyleViewHolder(View itemView) {
//            super(itemView);
//            ButterKnife.bind(this, itemView);
//
//            mContext = itemView.getContext();
//            itemView.setOnClickListener(this);
//        }
//        public void bindStyle(@NonNull Datum data) {
//
//            mStyleNameTextView.setText(data.getName());
//            mDescriptionNameTextView.setText(data.getDescription());
//        }
//
//
//
//        }
//    }
//
//
//
