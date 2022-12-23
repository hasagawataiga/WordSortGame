package orca.application.wordsortgame;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Register extends AppCompatActivity {
    // Layout Components
    EditText et_username, et_fullName, et_email, et_password, et_phoneNumber;
    Button btn_register;
    TextView tv_goToLogin, tv_returnHome;

    String username;
    ProgressBar progressBar;
    String email;
    String fullName;
    String password;
    int id;
    String phoneNumber;
    UserModel userModel;
    int score = 0;

    boolean isUserExisted = false;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference reference = database.getReference("User");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        et_fullName = findViewById(R.id.et_fullName);
        et_username = findViewById(R.id.et_username);
        et_email = findViewById(R.id.et_email);
        et_password = findViewById(R.id.et_password);
        et_phoneNumber = findViewById(R.id.et_phoneNumber);
        btn_register = findViewById(R.id.btn_register);
        tv_goToLogin = findViewById(R.id.tv_goToLogin);
        tv_returnHome = findViewById(R.id.tv_returnHome);

        progressBar = findViewById(R.id.progressBar);


    }
    public void register(View view){
        email = et_email.getText().toString().trim();
        password = et_password.getText().toString().trim();
        username = et_username.getText().toString().trim();
        phoneNumber = et_phoneNumber.getText().toString().trim();
        fullName = et_fullName.getText().toString().trim();
        DatabaseReference userExistedReference = FirebaseDatabase.getInstance().getReference().child("User").child(username);
        userExistedReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Log.d("username:", username);
                if(snapshot.exists()){
                    isUserExisted = true;
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        if(isUserExisted){
            et_username.setError("Username is existed.");
            return;
        }else{
            if(TextUtils.isEmpty(username)){
                et_username.setError("Username is required.");
                return;
            }

            if(TextUtils.isEmpty(fullName)){
                et_fullName.setError("FullName is required.");
                return;
            }

            if(TextUtils.isEmpty(email)){
                et_email.setError("Email is required.");
                return;
            }

            if(TextUtils.isEmpty(password)){
                et_password.setError("Password is required.");
                return;
            }

            if(TextUtils.isEmpty(phoneNumber)){
                et_phoneNumber.setError("Phone number is required.");
                return;
            }
            if(username.length() < 6){
                et_username.setError("Username must be greater or equals to 6 characters.");
            }
            if(password.length() < 8){
                et_password.setError("Password must be greater or equals to 8 characters.");
            }
            userModel = new UserModel(username, fullName, email, phoneNumber, password, score);
            reference.child(username).setValue(userModel);
            progressBar.setVisibility(View.VISIBLE);
            returnHome(view);
        }

    }


    private void isUsernameExisted(){
        DatabaseReference userExistedReference = FirebaseDatabase.getInstance().getReference().child("User").child(username);
        userExistedReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Log.d("username:", username);
                if(snapshot.exists()){
                    isUserExisted = true;
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void goToLogin(View view){
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }

    public void returnHome(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}