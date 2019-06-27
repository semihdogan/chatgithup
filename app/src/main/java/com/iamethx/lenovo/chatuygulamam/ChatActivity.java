package com.iamethx.lenovo.chatuygulamam;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.UUID;

public class ChatActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    EditText mesajtext;
    RecyclerView recyclerView;
    RecyclerAdepter recyclerAdepter;
    FirebaseDatabase database;
    DatabaseReference databaseReference;
    private ArrayList<String> chatmessages=new ArrayList<>();


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==R.id.çıkıs){
            mAuth.signOut();
            Intent ıntent=new Intent(getApplicationContext(),KayitActivity.class);
            startActivity(ıntent);


        }
        else if (item.getItemId()==R.id.profile){
            Intent ıntent=new Intent(getApplicationContext(),ProfilActivity.class);
            startActivity(ıntent);

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        //chatmessagess.add("ajdls");
        //chatmessagess.add("mlkajsdljslm");
        mesajtext=findViewById(R.id.chat_akctivity_mesajtext);
        recyclerView=findViewById(R.id.recyclerView);
        recyclerAdepter=new RecyclerAdepter(chatmessages);

        RecyclerView.LayoutManager recylerviewmenager=new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(recylerviewmenager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(recyclerAdepter);


        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference();
    }

    public void gönder(View view) {
        final String messageToSend = mesajtext.getText().toString();
        UUID uuid = UUID.randomUUID();
        String uuidString = uuid.toString();
        FirebaseUser user = mAuth.getCurrentUser();
        String userEmail = user.getEmail().toString();
        databaseReference.child("chats").child(uuidString).child("usermessage").setValue(messageToSend);
        databaseReference.child("Chats").child(uuidString).child("useremail").setValue(userEmail);
        mesajtext.setText("");
    }
}
