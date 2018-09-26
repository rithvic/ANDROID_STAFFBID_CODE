package staffbid.qbayapps.quadrobay.com.staffbid;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import staffbid.qbayapps.quadrobay.com.staffbid.Jobseekerdata.Jobseeker1RegisterActivity;

public class SignupActivity extends AppCompatActivity implements View.OnClickListener,AdapterView.OnItemSelectedListener {



    AppController appController;
    Spinner spinner1;
    TextView username,usermail,userpass;
    String usernamestring,usermailstring,userpassstring;
    LinearLayout linearLayout;
    Button registerbutton;
    String[] fieldtype = { "Select Type","I'm a business", "I'm a job seeker"};
    String usertype=null;
    RequestQueue requestQueue;

    String useriddata;
    TextView loginpage1;
    ImageView backbutton;


    public static final String mypreference = "mypref";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_layout);
            appController=(AppController) getApplicationContext();
            spinner1=findViewById(R.id.spinner);
            spinner1.setOnItemSelectedListener(this);
            username=findViewById(R.id.usernametext);
            usermail=findViewById(R.id.usermailtext);
            userpass=findViewById(R.id.userpasswordtext);
            linearLayout=findViewById(R.id.linear);
            registerbutton=findViewById(R.id.reisterbutton1);
            loginpage1=findViewById(R.id.loinpage);
            loginpage1.setOnClickListener(this);
            registerbutton.setOnClickListener(this);

        backbutton=findViewById(R.id.backbutton1);
        backbutton.setOnClickListener(this);
      ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,fieldtype);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(aa);

     //   spinner1.setOnItemSelectedListener(this);


requestQueue= Volley.newRequestQueue(SignupActivity.this);



    }


    @Override
    public void onClick(View v) {

switch (v.getId()){

    case R.id.backbutton1:
    onBackPressed();
    break;
    case R.id.loinpage:
        Intent ne=new Intent(SignupActivity.this,LoginActivity.class);
        startActivity(ne);
        break;

    case R.id.reisterbutton1:




        usernamestring=username.getText().toString();
        usermailstring=usermail.getText().toString();
        userpassstring=userpass.getText().toString();

        int idint=Randomgeneration();
        final String id=String.valueOf(idint);


        appController.setUserid(id);
        appController.setUsertype(usertype);
        appController.setUsergmai(usermailstring);
        appController.setUserpass(userpassstring);
        appController.setUsername(usernamestring);
        if (!isValidEmail(usermailstring)){

            usermail.setError("Enter your valid Email");

        }else if (usernamestring.length()==0){
            username.setError("Enter your name");
        }else if (usermailstring.length()==0){
            usermail.setError("Enter your mail id");
        }else if (userpassstring.length()==0){
            userpass.setError("Enter your password");

        }else if (usertype.length()==0){
            spinner1.setBackgroundColor(Color.RED);
        }else {


            if (usertype.contains("I'm a business")){

                Intent intetn=new Intent(SignupActivity.this,CompanyRegistrationDetials.class);
                intetn.putExtra("type","Employer");
                intetn.putExtra("User_Id",id);
                intetn.putExtra("uname",usernamestring);
                startActivity(intetn);
                break;
            }else if (usertype.contains("I'm a job seeker")){

                Intent intetn1=new Intent(SignupActivity.this,Jobseeker1RegisterActivity.class);
                intetn1.putExtra("type","Job seeker");
                intetn1.putExtra("User_Id",id);
                startActivity(intetn1);
                break;

            }else {

                alertBox("Error","Please check your information");
            }






       }
        break;
        }


    }

    public static boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }
//    private void updateResult() {
//
//        String url="http://quadrobay.co.in/staffbid_api/public/api/user/signup-information?token=24462a501a5aa6ca239d1ade0fc90c9c";
//        int idint=Randomgeneration();
//        String id=String.valueOf(idint);
//        useriddata=id;
//        Map<String, String> params = new HashMap<>();
//        params.put("name",usernamestring);
//        params.put("email", usermailstring);
//        params.put("password",userpassstring);
//        params.put("userid",id);
//        params.put("type",usertype);
//        int SocketTimeout = 30000;
//        RetryPolicy retry = new DefaultRetryPolicy(SocketTimeout,
//                DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
//
//        VolleyCustomPostRequest jsObjRequest = new VolleyCustomPostRequest(Request.Method.POST, url, params, new Response.Listener<JSONObject>() {
//
//            @Override
//            public void onResponse(JSONObject response) {
//                Log.e("Response", response.toString());
//                String res=response.toString();
//                if (res.contains("Employee")){
//                    Intent emp1 = new Intent(SignupActivity.this, DashboardActivity.class);
//                    emp1.putExtra("type","employer");
//                    startActivity(emp1);
//                }else if (res.contains("Job seeker")){
//                    Intent emp = new Intent(SignupActivity.this, MainActivity.class);
//                    emp.putExtra("type","jobseeker");
//                    startActivity(emp);
//                }else {
//
//                    snackbar("Something gonna error please check your email and password");
//                    Intent emp = new Intent(SignupActivity.this, MainActivity.class);
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

    int Randomgeneration(){
        Random rand = new Random();

        int rand_int1 = rand.nextInt(1000000000);


        return rand_int1;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


        usertype=fieldtype[position];


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


    void updateServerData() {


        String url="http://quadrobay.co.in/staffbid_api/public/api/user/signup-information?token=24462a501a5aa6ca239d1ade0fc90c9c";
        int idint=Randomgeneration();
        final String id=String.valueOf(idint);
            useriddata=id;
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, getPostResponseListener(), getPostErrorListener()) {
            protected Map<String, String> getParams() throws com.android.volley.AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("name",usernamestring);
                params.put("email", usermailstring);
                params.put("password",userpassstring);
                params.put("userid",id);
                params.put("type",usertype);

                return params;


            }

            ;
        };


        requestQueue.add(stringRequest);
    }

    private Response.Listener<String> getPostResponseListener() {
        return new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                if (response.contains("Email already register")){

                    alertBox("Error","Already Registerd user id");
                }
                try {
                    JSONObject json=new JSONObject(response);

                    String value=json.getString("Response");
                    String regid=json.getString("User_Id");

                    appController.setUserid(regid);
                    appController.setUsertype(usertype);
                    if (value.contains("success")&& regid.contains(useriddata)) {


                        if (usertype.contains("Employer")) {

                            Intent intetn=new Intent(SignupActivity.this,CompanyRegistrationDetials.class);
                            intetn.putExtra("type","Employer");
                            intetn.putExtra("User_Id",regid);
                            startActivity(intetn);

                        } else if (usertype.contains("Job seeker")) {
                            Intent intent=new Intent(SignupActivity.this,Jobseeker1RegisterActivity.class);
                            intent.putExtra("type","Job seeker");
                            intent.putExtra("User_Id",regid);
                            startActivity(intent);

                        }else if (response.contains("Email already register")){

                            alertBox("Error","Already Registerd user id");
                        }
                    }else{

                        alertBox("Error",response);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        };
    }

    private Response.ErrorListener getPostErrorListener() {
        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

               alertBox("Registration Error","Please check input data entered");
            }
        };
    }
    void alertBox(String title,String msg){

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);

        alertDialog.setTitle(title);

        alertDialog.setMessage(msg);

        alertDialog.setNegativeButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {

            }
        });


        alertDialog.show();
    }

}
