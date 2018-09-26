package staffbid.qbayapps.quadrobay.com.staffbid;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class ForgotpassActivity extends AppCompatActivity implements View.OnClickListener,AdapterView.OnItemSelectedListener {



    EditText forgottext;

    ImageView backbut;
    String[] fieldtype = {  "Select Type","I'am a business", "I'am a job seeker"};
    Button forgot;
    RequestQueue requestQueue;
    Spinner spinner1;

    String usertype;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forotpass_layout);

        forgottext=findViewById(R.id.foroid);
        backbut=findViewById(R.id.ubackbutfor);
        forgot=findViewById(R.id.butforgot);
        spinner1=findViewById(R.id.spinner);
        spinner1.setOnItemSelectedListener(this);
        requestQueue= Volley.newRequestQueue(getApplicationContext());
        backbut.setOnClickListener(this);

        forgot.setOnClickListener(this);

        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,fieldtype);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(aa);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.ubackbutfor:
                onBackPressed();
                break;

            case R.id.butforgot:

                String value=forgottext.getText().toString();

                if (value.length()==0){

                    forgottext.setError("Enter Your mail");
                }else if (!isValidEmail(value)){

                forgottext.setError("Enter your valid Email");

            }else {

                    if(usertype.contains("I'am a business")){
                        updateServerData(value,"Employer");

                    }else if (usertype.contains("I'am a job seeker")){


                        updateServerData(value,"Job seeker");
                    }else {


                        alertBox("Error","Please select user type");
                    }


                    break;

                }
                break;
        }
    }

    public static boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }


    void updateServerData(final String valuemail, final String typ) {


        String url="http://quadrobay.co.in/staffbid_api_new/public/api/jobseekerdetails/forgot-password?token=24462a501a5aa6ca239d1ade0fc90c9c";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, getPostResponseListener(), getPostErrorListener()) {
            protected Map<String, String> getParams() throws com.android.volley.AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();



                params.put("forgetemail",valuemail);

                params.put("type",typ);

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

               alertBox("Result",response);



            }
        };
    }

    private Response.ErrorListener getPostErrorListener() {
        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                alertBox(" Error","Please check input data entered or Internet connection");
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

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        usertype=fieldtype[i];
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
