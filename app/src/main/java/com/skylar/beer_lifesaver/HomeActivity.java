package com.skylar.beer_lifesaver;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity {
    @BindView(R.id.BeerInput)
    EditText mBeerInput;
    @BindView(R.id.bSearch)
    Button mFindBeerStyle;
    @BindView(R.id.tLogo)
    TextView mLogo;
    @BindView(R.id.bSaved)
    Button mSaved;
    private DatabaseReference mBeerStyleReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        mBeerStyleReference = FirebaseDatabase
                .getInstance()
                .getReference()
                .child(Constants.FIREBASE_CHILD_SEARCH_BEER_STYLE);


        mBeerStyleReference.addValueEventListener(new ValueEventListener() { //attach listener

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) { //something changed!
                for (DataSnapshot userInputSnapshot : dataSnapshot.getChildren()) {
                    String userInput = mBeerInput.getText().toString();
                    Log.d("beerstyles updated", "userInput: " + userInput); //log
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) { //update UI here if error occurred.

            }
        });

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        mFindBeerStyle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userInput = mBeerInput.getText().toString();
                saveInputToFirebase(userInput);
                Intent intent = new Intent(HomeActivity.this, BeerStyleListActivity.class);
                intent.putExtra("userInput", userInput);
                startActivity(intent);

                Toast.makeText(HomeActivity.this, "Ahoy!! Here are your beers styles...", Toast.LENGTH_LONG).show();

            }
        });
    }
    public void saveInputToFirebase(String userInput) {
        mBeerStyleReference.push().setValue(userInput);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mBeerStyleReference.removeEventListener((ChildEventListener) mBeerStyleReference);

    }


}
