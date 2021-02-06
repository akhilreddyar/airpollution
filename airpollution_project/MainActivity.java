package com.example.airpollution_project;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private FirebaseDatabase database;
    public String temp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Write a message to the database
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        database = FirebaseDatabase.getInstance();
//        final TextView    t1 = (TextView)findViewById(R.id.textView) ;
        valueFinder("humidity: ",(TextView)findViewById(R.id.humidity));
        valueFinder("temperature : ",(TextView)findViewById(R.id.temperature));
        valueFinder("Carbon Monoxide :  ",(TextView)findViewById(R.id.carbon_monoxide));
        final Button reload = (Button)findViewById(R.id.reload);
        reload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                valueFinder("humidity: ",(TextView)findViewById(R.id.humidity));
                valueFinder("temperature : ",(TextView)findViewById(R.id.temperature));
                valueFinder("Carbon Monoxide :  ",(TextView)findViewById(R.id.carbon_monoxide));
            }
        });
    }
    private String valueFinder(String ref,final TextView t1){
        DatabaseReference myRef = database.getReference(ref);
        temp ="k";
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                try {
                    String value = dataSnapshot.getValue().toString();
                    temp = value;
                    t1.setText(temp);
                }
                catch (Exception e){
                    Log.d("noevvvvsssvvvvvvvvvv ", "Value is: " );
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
        return temp;
    }
}
