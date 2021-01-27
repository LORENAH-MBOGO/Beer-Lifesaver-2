package com.skylar.beer_lifesaver.adapters;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.skylar.beer_lifesaver.BeerStyleDetailActivity;
import com.skylar.beer_lifesaver.BeerStyleListActivity;
import com.skylar.beer_lifesaver.Constants;
import com.skylar.beer_lifesaver.R;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import network.BeerStyle;


public class FirebaseBeerStyleViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    View mView;
    Context mContext;
    @BindView(R.id.styleNameTextView)
    TextView styleNameTextView;
    public FirebaseBeerStyleViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
        mContext = itemView.getContext();
        ButterKnife.bind(this, itemView);
        itemView.setOnClickListener(this);
    }


    public void bindStyle(BeerStyle beerStyle) {
        TextView beerStyleNameTextView = mView.findViewById(R.id.styleNameTextView);

        beerStyleNameTextView.setText((CharSequence) beerStyle.getName());
    }
    @Override
        public void onClick(View view) {
        final ArrayList<BeerStyle> beerStyles = new ArrayList<>();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_BEER_STYLES).child(uid);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    beerStyles.add(snapshot.getValue(BeerStyle.class));
                }

                int itemPosition = getLayoutPosition();

                Intent intent = new Intent(mContext, BeerStyleDetailActivity.class);
                intent.putExtra("position", itemPosition + "");
                intent.putExtra("beerStyles", Parcels.wrap(beerStyles));

                mContext.startActivity(intent);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}