package staffbid.qbayapps.quadrobay.com.staffbid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{


    Button Employee,Employer;
    TextView signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


            Employee= findViewById(R.id.employee);
            Employer=findViewById(R.id.employer);
            signup=(TextView) findViewById(R.id.signup);
            signup.setOnClickListener(this);
            Employer.setOnClickListener(this);
            Employee.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.signup:
                Intent signup=new Intent(MainActivity.this,SignupActivity.class);
                startActivity(signup);
                break;

            case R.id.employee:
                Intent login1=new Intent(MainActivity.this,LoginActivity.class);
                login1.putExtra("type","1");
                startActivity(login1);
                break;

            case R.id.employer:
                Intent login=new Intent(MainActivity.this,LoginActivity.class);
                login.putExtra("type","2");
                startActivity(login);
                break;

        }

    }
}
