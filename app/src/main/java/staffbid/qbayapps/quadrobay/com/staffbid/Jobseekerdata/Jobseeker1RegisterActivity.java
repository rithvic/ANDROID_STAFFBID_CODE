package staffbid.qbayapps.quadrobay.com.staffbid.Jobseekerdata;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Locale;

import staffbid.qbayapps.quadrobay.com.staffbid.AppController;
import staffbid.qbayapps.quadrobay.com.staffbid.R;

@RequiresApi(api = Build.VERSION_CODES.N)
public class Jobseeker1RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    AppController appController;

    LinearLayout linearLayout;
    TextView userfname, userlname,usermobilenumber;
    Button usercontinue;
    String userfnametext,userlastnametext,userdateofbirthtext,usermobilenumbertext;
    private Calendar myCalendar = Calendar.getInstance();
    Button continuebutt,userdateofbirth;


    String serveuserid,serveusertype,usname=null;

ImageView backbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jobseekerregisteration);
        appController=(AppController) getApplicationContext();

        Intent intent1=getIntent();
        serveusertype=intent1.getStringExtra("type");
        serveuserid=intent1.getStringExtra("User_Id");
        usname=intent1.getStringExtra("uname");



        final DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                String myFormat = "MM/dd/yyyy";
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
                userdateofbirth.setText(sdf.format(myCalendar.getTime()));
            }

        };
        userfname=findViewById(R.id.userfirstname);
        userlname=findViewById(R.id.userlastname);
        linearLayout=findViewById(R.id.linear);
        userdateofbirth=findViewById(R.id.userdateofbirth);

userdateofbirth.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        new DatePickerDialog(Jobseeker1RegisterActivity.this, datePickerListener, myCalendar
                .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)).show();
    }
});


        usermobilenumber=findViewById(R.id.usermobilenumber);
        backbutton=findViewById(R.id.backbutton1);
        backbutton.setOnClickListener(this);


        continuebutt=findViewById(R.id.employeecontinue);
        continuebutt.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.backbutton1:
                onBackPressed();
                break;

            case R.id.employeecontinue:


                userfnametext=userfname.getText().toString();
                userlastnametext=userlname.getText().toString();
                userdateofbirthtext=userdateofbirth.getText().toString();
                usermobilenumbertext=usermobilenumber.getText().toString();
                if (userfnametext.length()==0){
                    userfname.setError("Enter your first name");
                }else if (userlastnametext.length()==0){
                    userlname.setError("Enter your last name");
                }else if (userdateofbirthtext.length()==0){
                    userdateofbirth.setError("select your date of birth");
                }else if (usermobilenumbertext.length()==0){
                    usermobilenumber.setError("Enter your mobile number");
                }else {


                    appController.setUsertype(serveusertype);
                    appController.setUserid(serveuserid);


                    Intent intentnext=new Intent(Jobseeker1RegisterActivity.this,Jobseeker2Experience.class);
                    intentnext.putExtra("type",serveusertype);
                    intentnext.putExtra("User_Id",serveuserid);
                    intentnext.putExtra("usernametext",userfnametext);
                    intentnext.putExtra("userlastnametext",userlastnametext);
                    intentnext.putExtra("userdateofbirthtext",userdateofbirthtext);
                    intentnext.putExtra("usermobilenumbertext",usermobilenumbertext);
                    intentnext.putExtra("usernamereg",usname);
                    startActivity(intentnext);

break;

                }



//                Intent ini=new Intent(RegisterActivity.this,EmployeePreviousjob.class);
//                startActivity(ini);
//                break;
        }
    }
//    private void updateResult() {
//
//        Map<String, String> params = new HashMap<>();
//       // params.put("name",usernamestring);
////        params.put("email", usermailstring);
////        params.put("password",userpassstring);
////        params.put("userid",id);
//        params.put("type","Employee");
//        int SocketTimeout = 30000;
//        RetryPolicy retry = new DefaultRetryPolicy(SocketTimeout,
//                DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
//
//        VolleyCustomPostRequest jsObjRequest = new VolleyCustomPostRequest(Request.Method.POST, "http://quadrobay.co.in/staffbid_api/public/api/user/signup-information?token=24462a501a5aa6ca239d1ade0fc90c9c ", params, new Response.Listener<JSONObject>() {
//
//            @Override
//            public void onResponse(JSONObject response) {
//                Log.e("Response", response.toString());
//                String res=response.toString();
//                if (res.contains("Employee")){
////                    Intent emp1 = new Intent(SignupActivity.this, DashboardActivity.class);
////                    emp1.putExtra("type","employer");
////                    startActivity(emp1);
//                }else if (res.contains("Job seeker")){
////                    Intent emp = new Intent(SignupActivity.this, MainActivity.class);
////                    emp.putExtra("type","jobseeker");
////                    startActivity(emp);
//                }else {
//
//                    snackbar("Something gonna error please check your email and password");
//                    Intent emp = new Intent(Jobseeker1RegisterActivity.this, MainActivity.class);
//                    emp.putExtra("type","jobseeker");
//                    startActivity(emp);
//                }
//
//
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Log.e("Error", error.toString());
//            }
//        });
//        jsObjRequest.setRetryPolicy(retry);
//        AppController.getInstance().addToRequestQueue(jsObjRequest, "tag_json_obj");
//    }



    public void snackbar(String message){
        Snackbar snackbar = Snackbar
                .make(linearLayout, message, Snackbar.LENGTH_LONG);

        snackbar.show();
    }

}