package staffbid.qbayapps.quadrobay.com.staffbid;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
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

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {


    Button Loginbutton, Employer;
    TextView signuppag;
    String id;
    Intent intent;
    AppController appController;
    LinearLayout linearLayout;

    EditText usermail1,userpas1;
    RequestQueue requestQueue;
    String usermaidid,userpassswordid;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    public static final String MyPREFERENCES = "MyPrefs" ;
    String temptype;

    ImageView backbutton;

    TextView forgotpass1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);

        intent=getIntent();
        appController= (AppController) getApplicationContext();

        requestQueue= Volley.newRequestQueue(getApplicationContext());
        usermail1=findViewById(R.id.usermail);
        userpas1=findViewById(R.id.userpassword);
        linearLayout=findViewById(R.id.linear);
        id = intent.getStringExtra("type");

        if(id.isEmpty()){
            id="1";
        }
        Loginbutton=(Button) findViewById(R.id.loginbutton1);
        signuppag=findViewById(R.id.signuppage1);
        signuppag.setOnClickListener(this);
        Loginbutton.setOnClickListener(this);
        forgotpass1=findViewById(R.id.forgotpass);
        forgotpass1.setOnClickListener(this);
        backbutton=findViewById(R.id.backbutton1);
        backbutton.setOnClickListener(this);
        sharedPreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);


    }

    @Override
    public void onClick(View v) {


        switch (v.getId()){

            case R.id.forgotpass:

                Intent intentt=new Intent(LoginActivity.this,ForgotpassActivity.class);
                startActivity(intentt);
                break;

            case R.id.backbutton1:

                onBackPressed();
                break;



            case R.id.loginbutton1:


                usermaidid=usermail1.getText().toString();
                userpassswordid=userpas1.getText().toString();

                if (!isValidEmail(usermaidid)){

                    usermail1.setError("Enter your valid Email");

                }
               else if (usermaidid.length() ==0){
                    usermail1.setError("Enter your Email");

                }else if (userpassswordid.length()==0){
                    userpas1.setError("Enter your password");
                }
                else {


                    if (id.contains("1")) {
                        updateServerData1("Employee");

                       break;

                    } else {
                        updateServerData("Job seeker");

                        break;
                    }
               }
               break;
            case R.id.signuppage1:
                Intent inten=new Intent(LoginActivity.this,SignupActivity.class);
                startActivity(inten);
                break;
        }
    }
    public static boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }





    void updateServerData(final String type) {

temptype=type;
        String url="http://quadrobay.co.in/staffbid_api_new/public/api/jobseekerdetails/jobseeker-login?token=24462a501a5aa6ca239d1ade0fc90c9c";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, getPostResponseListener(), getPostErrorListener()) {
            protected Map<String, String> getParams() throws com.android.volley.AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("email",usermaidid);
                params.put("password",userpassswordid );



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


                try {
                    JSONObject json=new JSONObject(response);

                    String value=json.getString("Response");
                     JSONObject res=json.getJSONArray("Jobseeker_profile").getJSONObject(0);
                    AppController.getInstance().setUserDetail(res);




                    if (value.contains("success")) {

                        appController.setUsertype("Job seeker");

                        //appController.setUserDetail(res);
                        AppController.getInstance().setUserDetail(res);
                        appController.setUserid(res.getString("Jobseeker_id"));
                        appController.setUsertype(temptype);
                        Intent n=new Intent(LoginActivity.this,DashboardActivity.class);
                        startActivity(n);

                    }
                    else{

                        alertBox("Error",response);
                    }


                } catch (JSONException e) {
                    e.printStackTrace();

                    alertBox("Error","Please check  entered data");
                }


            }
        };
    }

    private Response.ErrorListener getPostErrorListener() {
        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                alertBox("Error","Please check your internet connection");
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
    void updateServerData1(final String type) {

        temptype=type;
        String url="http://quadrobay.co.in/staffbid_api_new/public/api/employerdetails/employer-login?token=24462a501a5aa6ca239d1ade0fc90c9c";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, getPostResponseListener1(), getPostErrorListener1()) {
            protected Map<String, String> getParams() throws com.android.volley.AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("type",type);
                params.put("email",usermaidid);
                params.put("password",userpassswordid );



                return params;


            }

            ;
        };


        requestQueue.add(stringRequest);
    }

    private Response.Listener<String> getPostResponseListener1() {
        return new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject json = new JSONObject(response);

                    String value = json.getString("Response");
                    JSONObject res = json.getJSONArray("Employer_profile").getJSONObject(0);
                    AppController.getInstance().setUserDetail(res);


                    if (value.contains("success")) {


                        //appController.setUserDetail(res);
                        AppController.getInstance().setUserDetail(res);
                        appController.setUserid(res.getString("Employer_id"));
                        appController.setUsertype("Employee");
                        Intent n = new Intent(LoginActivity.this, DashboardActivity.class);
                        startActivity(n);

                    } else {

                        alertBox("Error", response);
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                    alertBox("Error","Please check your information");
                }
            }


        };
    }

    private Response.ErrorListener getPostErrorListener1() {
        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                alertBox("Error","Please check your internet connection");
            }
        };
    }

}
