package orca.application.wordsortgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    UserModel userModel;
    Button btn_start;
    TextView tv_username2;
    TextView tv_score3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_start = findViewById(R.id.btn_start);
        tv_username2 = findViewById(R.id.tv_username2);
        tv_score3 = findViewById(R.id.tv_score3);
        Bundle bundle = getIntent().getExtras();
        try{
            userModel = bundle.getParcelable("user");
            tv_username2.setText(userModel.getFullName());
            tv_score3.setText(Integer.toString(userModel.getScore()));
        }catch(Exception e){
            tv_username2.setText("Guest");
            tv_score3.setText("0");
        }
    }
    public void startGame(View view){
        Intent intent;
        if(tv_username2.getText().toString().equals("Guest")){
            intent = new Intent(this, Home.class);
        }else{
            intent = new Intent(this, Game.class);
        }
        startActivity(intent);
    }
}