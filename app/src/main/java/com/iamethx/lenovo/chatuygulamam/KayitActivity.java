package com.iamethx.lenovo.chatuygulamam;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class KayitActivity extends AppCompatActivity {
    EditText emailedittext, şifreedittext;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kayit);
        emailedittext = findViewById(R.id.useremailedittext);
        şifreedittext = findViewById(R.id.userşifreedittext);
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user=mAuth.getCurrentUser();
        if (user != null){
            Intent ıntent=new Intent(getApplicationContext(),ChatActivity.class);
            startActivity(ıntent);


        }
    }

    public void girişyap(View view) {
        mAuth.signInWithEmailAndPassword(emailedittext.getText().toString(),şifreedittext.getText().toString()).
                addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Intent ıntent=new Intent(getApplicationContext(),ChatActivity.class);
                            startActivity(ıntent);

                            Toast.makeText(getApplication()," Giriş Yapıldı",Toast.LENGTH_LONG).show();
                        }
                         else {
                            Toast.makeText(getApplication(),"Şifre ve ya email hatalı ama hangisi olduğunu söylemem",Toast.LENGTH_LONG).show();

                        }

                    }
                });
    }

    public void KayıtOl(View view) {
        mAuth.createUserWithEmailAndPassword(emailedittext.getText().toString(),şifreedittext.getText().toString()).
                addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(getApplication(),"Kayıt Başarılı",Toast.LENGTH_LONG).show();


                            Intent ıntent=new Intent(getApplicationContext(),ChatActivity.class);
                            startActivity(ıntent);


                        }else {
                            Toast.makeText(getApplication(),"Hata Oluştu",Toast.LENGTH_LONG).show();
                        }

                    }
                });

    }
}