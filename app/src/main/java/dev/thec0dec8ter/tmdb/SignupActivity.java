package dev.thec0dec8ter.tmdb;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignupActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;

    private AppCompatEditText editEmail;
    private AppCompatEditText editPassword;
    private AppCompatEditText editConfirmPassword;
    private AppCompatButton btnSignUp;
    private AppCompatTextView txtSignAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        editEmail = findViewById(R.id.edit_email);
        editPassword = findViewById(R.id.edit_password);
        editConfirmPassword = findViewById(R.id.edit_confrim_password);
        btnSignUp = findViewById(R.id.btn_signup);
        txtSignAccount = findViewById(R.id.txt_sign_account);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = editEmail.getText().toString();
                String password = editPassword.getText().toString();
                String confirmPassword = editConfirmPassword.getText().toString();

                if(email.isEmpty()){
                    editEmail.setError("E-mail cannot be empty");
                }else if(password.isEmpty()){
                    editPassword.setError("Password cannot be empty");
                }else if(!confirmPassword.equals(password)){
                    editConfirmPassword.setError("does not match password");
                }else{
                    sign_up(email, password);
                }
            }
        });

        txtSignAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignupActivity.this, SigninActivity.class));
            }
        });

    }

    public void sign_up(String email, String password){
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        startActivity(new Intent(SignupActivity.this, MainActivity.class));
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e("SignupActivity: " , e.getMessage());
                        Toast.makeText(SignupActivity.this,
                                "An error occurred, please try again.",
                                Toast.LENGTH_SHORT).show();
                    }
                });
    }

}