package com.skylar.beer_lifesaver;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {
    private DatabaseReference mBeerStyleReference;
    ;
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    @BindView(R.id.BeerInput)
    EditText mBeerInput;
    @BindView(R.id.bSearch)
    Button mFindBeerStyle;
    @BindView(R.id.tLogo)
    TextView mLogo;
    @BindView(R.id.bSaved)
    Button mSaved;

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

////        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
////        mEditor = mSharedPreferences.edit();
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

            public void saveInputToFirebase(String userInput) {
                mBeerStyleReference.push().setValue(userInput);
            }

        });


        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

//display welcome message

                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    getSupportActionBar().setTitle("Welcome, " + user.getDisplayName() + "!");
                } else {

                }
            }
        };

    }

    @Override
    public void onClick(View view) {
        if (view == mFindBeerStyle) {
            String userInput = mBeerInput.getText().toString();
            if (!(userInput).equals("")) {
                addToSharedPreferences(userInput);
            }
            Intent intent = new Intent(HomeActivity.this, BeerStyleListActivity.class);
            intent.putExtra("userInput", userInput);
            startActivity(intent);
            finish();
        }
        if (view == mSaved) {
            Toast.makeText(this, "Sorry... currently unavailable!", Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_logout) {
            logout();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void logout() {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

    private void addToSharedPreferences(String userInput) {
        mEditor.putString(Constants.PREFERENCES_BEERSTYLE_KEY, userInput).apply();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mBeerStyleReference.removeEventListener((ChildEventListener) mBeerStyleReference);
    }
}
