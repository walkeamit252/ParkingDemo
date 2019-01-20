package app.com.parkingdemo.login;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
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
import app.com.parkingdemo.utils.AppConstants;

public class LoginActivity extends AppCompatActivity {

    TextView txtRegisterHere;
    Button btnLogin;
    EditText etEmail,etPassword;
    DBHelper dbHelper;
    SharedPreferences sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        sharedPref =PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        dbHelper=new DBHelper(this);
        if(sharedPref.getInt(AppConstants.DATABASE_SETUP_KEY,0)==0){
            for(int i=1;i<=10;i++){
                dbHelper.insertParking(i+"");
            }
            sharedPref.edit().putInt(AppConstants.DATABASE_SETUP_KEY, 1).commit();
        }
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
                 sharedPref.edit().putString(AppConstants.LOGGEDIN_USER_ID, dbHelper.getUserId(etEmail.getText().toString())).commit();
                 startActivity(new Intent(LoginActivity.this, MainActivity.class));
                 finish();
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
