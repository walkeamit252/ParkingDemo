package app.com.parkingdemo.registeration;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import app.com.parkingdemo.R;
import app.com.parkingdemo.database.DBHelper;
import app.com.parkingdemo.login.LoginActivity;

public class RegistrationActivity extends AppCompatActivity {
    TextView txtLoginHere;
    Button btnRegister;
    EditText etEmail,etPassword;

    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        dbHelper=new DBHelper(this);
        initView();
        setListener();
    }

    private void setListener() {
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!TextUtils.isEmpty(etEmail.getText()) &&
                !TextUtils.isEmpty(etPassword.getText())){
                  dbHelper.insertUser(etEmail.getText().toString(),etPassword.getText().toString());
                    Toast.makeText(RegistrationActivity.this,"Registration Success",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(RegistrationActivity.this, LoginActivity.class));
                    finish();
                }
            }
        });

        txtLoginHere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegistrationActivity.this, LoginActivity.class));
                finish();
            }
        });

    }

    private void initView() {
        txtLoginHere=findViewById(R.id.txtLoginHere);
        etEmail=findViewById(R.id.etRegisterEmail);
        etPassword=findViewById(R.id.etRegisterPassword);
        btnRegister=findViewById(R.id.btnRegister);
    }

    }
