package dev.thec0dec8ter.tmdb;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SigninActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;

    private AppCompatEditText editEmail;
    private AppCompatEditText editPassword;
    private AppCompatButton btnSignIn;
    private AppCompatTextView txtCreateAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        firebaseAuth = FirebaseAuth.getInstance();

        editEmail = findViewById(R.id.edit_email);
        editPassword = findViewById(R.id.edit_password);
        btnSignIn = findViewById(R.id.btn_signin);
        txtCreateAccount = findViewById(R.id.txt_create_account);

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = editEmail.getText().toString();
                String password = editPassword.getText().toString();

                if(email.isEmpty()){
                    editEmail.setError("E-mail cannot be empty");
                }else if(password.isEmpty()){
                    editPassword.setError("Password cannot be empty");
                }else{
                    sign_in(email, password);
                }
            }
        });

        txtCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SigninActivity.this, SignupActivity.class));
            }
        });

    }

    public void sign_in(String email, String password){
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            startActivity(new Intent(SigninActivity.this, MainActivity.class));
                            finish();
                        }else {
                            Toast.makeText(SigninActivity.this,
                                    "An error occurred, please try again.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    @Override
    protected void onResume() {
        super.onResume();

        FirebaseUser user = firebaseAuth.getCurrentUser();
        if(user != null){
            startActivity(new Intent(SigninActivity.this, MainActivity.class));
            finish();
        }
    }
}