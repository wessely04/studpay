package com.example.studpay;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class RegisterActivity extends AppCompatActivity {
    EditText registerUsername,registerPassword,confirmPassword;
    Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        registerUsername=findViewById(R.id.username);
        registerPassword=findViewById(R.id.signUpPassword);
        confirmPassword=findViewById(R.id.confirm_password);
        register=findViewById(R.id.registerButton);



    Database db = new Database(getApplicationContext(), "studpay", null, 1);

    register.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        String username=registerUsername.getText().toString();
        String pass=registerPassword.getText().toString();
        String conPass=confirmPassword.getText().toString();

         if(pass.isEmpty() || conPass.isEmpty() || username.isEmpty()){
            Toast.makeText(getApplicationContext(),"Fill the credentials",Toast.LENGTH_SHORT).show();
        }
        else if(pass.length()<8){
            Toast.makeText(getApplicationContext(),"password length < 8",Toast.LENGTH_SHORT).show();
        }
        else if(pass.equals(conPass)){
            db.register(username,pass);
            Toast.makeText(getApplicationContext(),"success",Toast.LENGTH_SHORT).show();
            startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
        }
        else{
           Toast.makeText(getApplicationContext(),"Password and conform password should be same",Toast.LENGTH_SHORT).show();
        }
    }
});
    }
}