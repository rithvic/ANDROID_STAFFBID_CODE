package staffbid.qbayapps.quadrobay.com.staffbid.Jobseekerdata;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
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

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import staffbid.qbayapps.quadrobay.com.staffbid.DashboardActivity;
import staffbid.qbayapps.quadrobay.com.staffbid.R;

public class Jobseeker2Experience extends AppCompatActivity implements View.OnClickListener,AdapterView.OnItemSelectedListener {

    ImageView backbutton;

    Button Registercontinue, Employer, userstartdate,userenddate;
    TextView signup;
    String servusername,serveusertype,serveuserid,userfnametext,userlastnametext,userdateofbirthtext,usermobilenumbertext;

    String[] fieldtype = { "Select Type","Server", "Bartender","Housekeeper","Steward","Hostes","Valet","Line Cook"};

    RequestQueue requestQueue;
    String regcompanyname,regjobrole,registersalary,regjobdescription,regstartdate,regenddate;
    EditText usercompanyname,userhourlysalary,userdescription;
    Spinner userjobrole;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jobexperience);
         final Calendar myCalendar = Calendar.getInstance();

        Intent intent1=getIntent();

        serveusertype=intent1.getStringExtra("type");
        serveuserid= intent1.getStringExtra("User_Id");
        userfnametext=intent1.getStringExtra("usernametext");
        userlastnametext=intent1.getStringExtra("userlastnametext");
        userdateofbirthtext=intent1.getStringExtra("userdateofbirthtext");
        usermobilenumbertext=intent1.getStringExtra("usermobilenumbertext");
        servusername= intent1.getStringExtra("usernamereg");

        usercompanyname=findViewById(R.id.companyname);
        userjobrole=findViewById(R.id.jobrole);

        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,fieldtype);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        userjobrole.setAdapter(aa);
        userjobrole.setOnItemSelectedListener(this);


        userhourlysalary=findViewById(R.id.hourlysalary);
        userdescription=findViewById(R.id.description);
        userstartdate=findViewById(R.id.startdate);
        userenddate=findViewById(R.id.enddate);
        Registercontinue=findViewById(R.id.registercontinue);
        Registercontinue.setOnClickListener(this);
        backbutton=findViewById(R.id.backbutton1);

        backbutton.setOnClickListener(this);
        requestQueue= Volley.newRequestQueue(getApplicationContext());

        userstartdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



            }
        });

        final DatePickerDialog.OnDateSetListener startdate = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                String myFormat = "MM/dd/yyyy";
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
                userstartdate.setText(sdf.format(myCalendar.getTime()));
            }

        };

        final DatePickerDialog.OnDateSetListener enddate = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                String myFormat = "MM/dd/yyyy";
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
                userenddate.setText(sdf.format(myCalendar.getTime()));
            }

        };
        userstartdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(Jobseeker2Experience.this, startdate, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        userenddate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(Jobseeker2Experience.this, enddate, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.backbutton1:

                onBackPressed();
                break;
            case R.id.registercontinue:


                regcompanyname=usercompanyname.getText().toString();
               // regjobrole=userjobrole.getText().toString();
                regjobdescription=userdescription.getText().toString();
                registersalary=userhourlysalary.getText().toString();
                regstartdate=userstartdate.getText().toString();
                regenddate=userenddate.getText().toString();

                if (regcompanyname.length()==0){
                    usercompanyname.setError("Enter your company name");
                }else if(regjobrole.length()==0){
                }else if (regjobdescription.length()==0){

                    userdescription.setError("Enter your job description");
                }else if (regstartdate.length()==0){
                    userstartdate.setError("Select your start date");
                }else if(regenddate.length()==0){
                    userenddate.setError("Select your end date");
                }else {
                    if (regjobrole == fieldtype[0]) {

                        alertBox("Error","Please select type");

                    } else {

                        Intent ininent = new Intent(Jobseeker2Experience.this, JobSeeker3AvailabilityActivity.class);
                        ininent.putExtra("userid", serveuserid);
                        ininent.putExtra("firstname", userfnametext);
                        ininent.putExtra("lastname", userlastnametext);
                        ininent.putExtra("dateofbirth", userdateofbirthtext);
                        ininent.putExtra("phone", usermobilenumbertext);
                        ininent.putExtra("companyname", regcompanyname);
                        ininent.putExtra("jobrole", regjobrole);
                        ininent.putExtra("startdate", regstartdate);
                        ininent.putExtra("enddate", regenddate);
                        ininent.putExtra("description", regjobdescription);
                        ininent.putExtra("rate", registersalary);

                        ininent.putExtra("usname", servusername);

                        startActivity(ininent);
                        break;
                    }
                }



               break;
        }

    }



       void updateServerData() {


           String url="http://quadrobay.co.in/staffbid_api/public/api/user/sencond-level-employee-signup?token=24462a501a5aa6ca239d1ade0fc90c9c";

           StringRequest stringRequest = new StringRequest(Request.Method.POST, url, getPostResponseListener(), getPostErrorListener()) {
               protected Map<String, String> getParams() throws com.android.volley.AuthFailureError {
                   Map<String, String> params = new HashMap<String, String>();



                   Intent ininent=new Intent(Jobseeker2Experience.this,JobSeeker3AvailabilityActivity.class);
                   ininent.putExtra("userid",serveuserid);
                   ininent.putExtra("firstname",userfnametext);
                   ininent.putExtra("lastname",userlastnametext );
                   ininent.putExtra("dateofbirth",userdateofbirthtext);
                   ininent.putExtra("phone",usermobilenumbertext);
                   ininent.putExtra("companyname",regcompanyname);
                   ininent.putExtra("jobrole",regjobrole);
                   ininent.putExtra("startdate",regstartdate);
                   ininent.putExtra("enddate",regenddate);
                   ininent.putExtra("description",regjobdescription);
                   ininent.putExtra("rate",registersalary);


                   startActivity(ininent);

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
                   if (response.contains("Already register")){

                       alertBox("Error","Already Registerd user id");
                   }

                   try {
                       JSONObject json=new JSONObject(response);

                       String value=json.getString("Response");
                      // String regid=json.getString("User_Id");
                       if (value.contains("success")) {




                               Intent intetn=new Intent(Jobseeker2Experience.this,DashboardActivity.class);
                               intetn.putExtra("User_Id",serveuserid);
                               startActivity(intetn);



                           }
                       else{

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

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        regjobrole=fieldtype[position];
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
