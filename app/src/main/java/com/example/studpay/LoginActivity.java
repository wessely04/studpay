package com.example.studpay;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class LoginActivity extends AppCompatActivity {

     EditText inputUsername,inputPassword;
     Button login;
     TextView signUpQuestion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_loginactivity);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
               inputUsername=findViewById(R.id.emailEditText);
               inputPassword=findViewById(R.id.passwordEditText);
               login=findViewById(R.id.loginButton);
               signUpQuestion=findViewById(R.id.signUpTextView);


              Database db=new Database(getApplicationContext(),"studpay",null,1);

        Intent  nextActivity = new Intent(LoginActivity.this, RegisterActivity.class);
        Intent  nextActivity1 = new Intent(LoginActivity.this, MainActivity.class);

              signUpQuestion.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View view) {
                      startActivity(nextActivity);
                      }
              });
              login.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View view) {
                      String loginUsername=inputUsername.getText().toString();
                      String loginPassword=inputPassword.getText().toString();

                      if(loginPassword.isEmpty()  || loginUsername.isEmpty()){
                          Toast.makeText(getApplicationContext(),"Fill the credentials",Toast.LENGTH_SHORT).show();
                      }
                      else if(db.login(loginUsername,loginPassword)==1){
                          nextActivity1.putExtra("username",loginUsername);
                          Toast.makeText(getApplicationContext(), "login success", Toast.LENGTH_SHORT).show();
                          startActivity(nextActivity1);
                          }
                      else{
                          Toast.makeText(getApplicationContext(), "Invalid credentials", Toast.LENGTH_SHORT).show();
                      }
                  }
              });
    }
}