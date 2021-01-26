package com.skylar.beer_lifesaver;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import com.skylar.beer_lifesaver.adapters.BeerStyleListAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;
import network.BeerApi;
import network.BeerClient;
import network.BeerStyle;
import network.Datum;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static java.nio.file.Paths.get;

public class BeerStyleListActivity extends AppCompatActivity {
    public static final String TAG = BeerStyleListActivity.class.getSimpleName();
    private static final String SANDBOX_KEY = Constants.SANDBOX_KEY;
    @BindView(R.id.errorTextView)
    TextView mErrorTextView;

    @BindView(R.id.progressBar)
    ProgressBar mProgressBar;

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;

    private BeerStyleListAdapter mAdapter;

    public List<Datum> mBeerStyles;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beers);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String userInput = intent.getStringExtra("userInput");
       get(userInput);
        BeerApi client = BeerClient.getClient();

        Call<BeerStyle> call = client.getStyles(SANDBOX_KEY);

        call.enqueue(new Callback<BeerStyle>() {

            @Override
            public void onResponse(Call<BeerStyle> call, Response<BeerStyle> response) {
                hideProgressBar();
//                Log.v(TAG, Integer.toString(mBeerStyles.size()));
//                Log.d("error123", String.valueOf(response.body()));

                if (response.isSuccessful()) {
                    mBeerStyles = response.body().getData();

                    mAdapter = new BeerStyleListAdapter(BeerStyleListActivity.this, mBeerStyles);
                    mRecyclerView.setAdapter(mAdapter);
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(BeerStyleListActivity.this);
                    mRecyclerView.setLayoutManager(layoutManager);
                    mRecyclerView.setHasFixedSize(true);
                    showStyles();
                } else {
                    showUnsuccessfulMessage();
                }
            }

            @Override
            public void onFailure(Call<BeerStyle> call, Throwable t) {

                hideProgressBar();
                showFailureMessage();
            }
        });
    }

    private void showFailureMessage() {
        mErrorTextView.setText("Something went wrong. Please check your Internet connection and try again later");
        mErrorTextView.setVisibility(View.VISIBLE);
    }

    private void showUnsuccessfulMessage() {
        mErrorTextView.setText("Something went wrong. Please try again later");
        mErrorTextView.setVisibility(View.VISIBLE);
    }

    private void showStyles() {
        mRecyclerView.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar() {
        mProgressBar.setVisibility(View.VISIBLE);

    }
}
