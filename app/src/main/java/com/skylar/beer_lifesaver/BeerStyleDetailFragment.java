package com.skylar.beer_lifesaver;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import network.BeerStyle;
import network.Datum;

public class BeerStyleDetailFragment extends Fragment implements View.OnClickListener{

    @BindView(R.id.styleNameTextView)
    TextView mStyleName;
    @BindView(R.id.saveButton)
    Button mSaveButton;

    private BeerStyle mBeerStyle;

    public BeerStyleDetailFragment() {
    }

    public static BeerStyleDetailFragment newInstance(BeerStyle beerStyle) {
        BeerStyleDetailFragment styleFragment = new BeerStyleDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable("beerStyle", Parcels.wrap(beerStyle));
        styleFragment.setArguments(args);
        return styleFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBeerStyle = Parcels.unwrap(getArguments().getParcelable("beerStyle"));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_beer_style_detail, container, false);
        ButterKnife.bind(this, view);


        List<String> data = new ArrayList<>();

        for (Datum datum: mBeerStyle.getData()) {
            datum.getName();
        }

        mStyleName.setText((CharSequence) mBeerStyle.getName());
        return view;
    }
    @Override
    public void onClick(View view) {
        if (view == mSaveButton) {
            DatabaseReference beerStyleRef = FirebaseDatabase
                    .getInstance()
                    .getReference(Constants.FIREBASE_CHILD_BEER_STYLES);

            DatabaseReference pushRef = beerStyleRef.push();
            pushRef.setValue(mBeerStyle);

            Toast.makeText(getContext(), "Saved", Toast.LENGTH_SHORT).show();
        }
    }
}