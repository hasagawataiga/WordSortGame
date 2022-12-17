package orca.application.wordsortgame;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.snapshot.ChildKey;

public class Login extends AppCompatActivity {

    UserModel userModel;

    EditText et_username, et_password;
    Button btn_login;
    TextView tv_gotoRegister, tv_returnHome2;
    ProgressBar progressBar;
    FirebaseAuth fAuth;
    // Init database
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference reference = database.getReference("User");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        et_username = findViewById(R.id.et_username2);
        et_password = findViewById(R.id.et_password);
        progressBar = findViewById(R.id.progressBar);
        fAuth = FirebaseAuth.getInstance();
        btn_login = findViewById(R.id.btn_login);
        tv_gotoRegister = findViewById(R.id.tv_goToRegister);
        tv_returnHome2 = findViewById(R.id.tv_returnHome2);
    }

    public void signIn(View view){
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                DataSnapshot childId1 = snapshot.child("1");
                if(childId1.child(et_username.getText().toString()).exists()){
                    userModel = childId1.child(et_username.getText().toString()).getValue(UserModel.class);
                    if(userModel.getPassword().equals(et_password.getText().toString())){
                        Toast.makeText(Login.this, "Welcome back!", Toast.LENGTH_SHORT).show();
                        Log.d("Account", userModel.toString());
//                        returnHome2(view);
                    }else{
                        Toast.makeText(Login.this, "Email or Password is not correct!", Toast.LENGTH_SHORT).show();
                        Log.d("Account", "The password is not correct.\n" + userModel.getPassword() + " is the right password");
                    }
                }else{
                    Toast.makeText(Login.this, "The account is not exist!", Toast.LENGTH_SHORT).show();
                    Log.d("Account", "The account is not exist.");
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void goToRegister(View view){
        Intent intent = new Intent(this,Register.class);
        startActivity(intent);
    }

    public void returnHome2(View view){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}