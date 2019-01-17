package app.com.parkingdemo.login;
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
import app.com.parkingdemo.parking.MainActivity;
import app.com.parkingdemo.registeration.RegistrationActivity;

public class LoginActivity extends AppCompatActivity {

    TextView txtRegisterHere;
    Button btnLogin;
    EditText etEmail,etPassword;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        dbHelper=new DBHelper(this);
        initView();
        setListener();
    }

    private void setListener() {
    btnLogin.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
         if(!TextUtils.isEmpty(etEmail.getText()) && !TextUtils.isEmpty(etPassword.getText())){
             boolean isValidLogin=dbHelper.checkLogin(etEmail.getText().toString(),etPassword.getText().toString());
             if(isValidLogin){
                 startActivity(new Intent(LoginActivity.this, MainActivity.class));
             } else{
                 Toast.makeText(LoginActivity.this,"Invalid User",Toast.LENGTH_SHORT).show();
             }
         }else {
             Toast.makeText(LoginActivity.this,"Please Enter valid Email and Password",Toast.LENGTH_SHORT).show();
         }
        }
    });

    txtRegisterHere.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity(new Intent(LoginActivity.this, RegistrationActivity.class));
            finish();
        }
    });
    }

    private void initView() {
        txtRegisterHere=findViewById(R.id.txtRegisterHere);
        etEmail=findViewById(R.id.etLoginEmail);
        etPassword=findViewById(R.id.etLoginPassword);
        btnLogin=findViewById(R.id.btnLogin);
    }

}
