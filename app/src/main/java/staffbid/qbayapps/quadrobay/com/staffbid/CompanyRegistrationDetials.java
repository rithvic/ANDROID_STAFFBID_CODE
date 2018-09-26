package staffbid.qbayapps.quadrobay.com.staffbid;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Random;

public class CompanyRegistrationDetials extends AppCompatActivity implements View.OnClickListener {


    Spinner spinnerloca;

    String finallocation;


    String[] location={
            "Select State",
            "Alabama",
            "Alaska" ,
            "Arizona",
            "Arkansas",
            "California" ,
            "Coloradon",
            "Connecticut" ,       "Delaware" ,
            "Florida",
            "Georgia",
            "Hawaii ",
            "Idaho ",
            "Illinois",

            "Indiana ",
            "Iowa",
            "Kansas ",
            "Kentucky ",
            "Louisiana",
            "Maine ",
            "Maryland " ,
            "Massachusetts",
            "Michigan" ,
            "Minnesota" ,
            "Mississippi ",
            "Missouri" ,
            "Montana","Nebraska ",
            "Nevada ",
            "New Hampshire",
            "New Jersey",
            "New Mexico",
            "New York",
            "North Carolina ",
            "North Dakota ",
            "Ohio",
            "Oklahoma ",
            "Oregon ",
            "Pennsylvania Rhode Island ",
            "South Carolina",
            "South Dakota ",
            "Tennessee",
            "Texas ",
            "Utah ",
            "Vermont ",
            "Virginia ",
            "Washington ",
            "West Virginia ",
            "Wisconsin ",
            "Wyoming"    };



    Button register1;

    RequestQueue requestQueue;

    TextView signup;
AppController appController;
    String serveusertype, serveuserid;
    ImageView backbutton;

    int k=0;
    String compbrandname, compnumber, compvatnumber, compcontactname, compaddressline1, compcity, comppostalcode, compdesciption;

    String compaddressline2="no";
    EditText brandname1, compnumber1, vatnumber1, contactname1, addressline1, addressline2, city1, postalcode1, description1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.companyregistration_layout);

        Intent intent1 = getIntent();
        serveusertype = intent1.getStringExtra("type");
        serveuserid = intent1.getStringExtra("User_Id");

        brandname1 = findViewById(R.id.brandname);
        compnumber1 = findViewById(R.id.companynumber);
        vatnumber1 = findViewById(R.id.vatnumber);
        contactname1 = findViewById(R.id.contactname);
        addressline1 = findViewById(R.id.addressline1);
        addressline2 = findViewById(R.id.addressline2);
        city1 = findViewById(R.id.city);
        postalcode1 = findViewById(R.id.postalcode);
        description1 = findViewById(R.id.companydescription);
        register1 = findViewById(R.id.uregisterdata);
        spinnerloca= (Spinner) findViewById(R.id.spinner_search);


        ArrayAdapter aa1 = new ArrayAdapter(this,android.R.layout.simple_spinner_item,location);
        aa1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerloca.setAdapter(aa1);
        spinnerloca.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                finallocation=location[i];

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        backbutton = findViewById(R.id.backbutton1);
        backbutton.setOnClickListener(this);
        appController=(AppController)getApplication().getApplicationContext();

        requestQueue = Volley.newRequestQueue(getApplicationContext());
        register1.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.backbutton1:

                onBackPressed();
                break;
            case R.id.uregisterdata:

                compbrandname = brandname1.getText().toString();
                compnumber = compnumber1.getText().toString();
                compvatnumber = vatnumber1.getText().toString();
                compcontactname = contactname1.getText().toString();
                compaddressline1 = addressline1.getText().toString();
                compaddressline2 = addressline2.getText().toString();
                if (compaddressline2.contains("")){

                    compaddressline2="Not Available";
                }

                compcity = city1.getText().toString();
                comppostalcode = postalcode1.getText().toString();
                compdesciption = description1.getText().toString();


                if (compbrandname.length() == 0) {
                    brandname1.setError("Enter your company name");
                } else if (compnumber.length() == 0) {
                    compnumber1.setError("Enter your company number");
                } else if (compvatnumber.length() == 0) {
                    vatnumber1.setError("Enter your company vat number");
                } else if (compcontactname.length() == 0) {
                    contactname1.setError("Enter your contact person name");
                } else if (compaddressline1.length() == 0) {
                    addressline1.setError("Enter your company address");

                } else if (finallocation.length() == 0) {
                    city1.setError("Enter your city");
                } else if (compdesciption.length() == 0) {
                    description1.setError("Enter your last name");
                } else {

                    if (finallocation==location[0]){
                        alertBox("Error","Please select state");

                    }else {

                        updatedataserver();

                    }


                }
                break;


        }


    }

    private void updatedataserver() {


        JSONObject jsonObject = new JSONObject();


String empid=String.valueOf(Randomgeneration());
appController.setUserid(empid);


        try {

            jsonObject.put("Name", appController.getUsername());
            jsonObject.put("Email", appController.getUsergmai());
            jsonObject.put("Password", appController.getUserpass());
            jsonObject.put("Type", "Employer");
            jsonObject.put("Brand_name", compbrandname);
            jsonObject.put("VAT_number", compvatnumber);
            jsonObject.put("Address_line_1", compaddressline1);
            jsonObject.put("Address_line_2", compaddressline2);
            jsonObject.put("Company_name", compbrandname);
            jsonObject.put("City", finallocation);
            jsonObject.put("Zipcode", comppostalcode);
            jsonObject.put("Company_description", compdesciption);
            jsonObject.put("Employer_id",empid);


        } catch (JSONException e) {
            e.printStackTrace();
        }


        String url = "http://quadrobay.co.in/staffbid_api_new/public/api/employerdetails/signup-employer?token=24462a501a5aa6ca239d1ade0fc90c9c";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {



                 String res=response.toString();

                try {
                    if (response.getString("Response").equals("success")){


                        k=1;

                        alertBox("Registered","Your registration is successfull. Please Login");



                    }else {
                        alertBox("Error",res);

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    alertBox("Information","Please check your registered details");
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();

                alertBox("Information","Please check your Internet connection or Internal error");
            }
        });


        requestQueue.add(jsonObjectRequest);


    }
    int Randomgeneration(){
        Random rand = new Random();

        int rand_int1 = rand.nextInt(1000000000);


        return rand_int1;
    }









//    void updateServerData() {
//
//
//        String url="http://quadrobay.co.in/staffbid_api/public/api/user/second-level-jobseeker-signup?token=24462a501a5aa6ca239d1ade0fc90c9c";
//
//        JSONObject stringRequest = new JSONObject(Request.Method.POST, url, getPostResponseListener(), getPostErrorListener()) {
//            protected Map<String, String> getParams() throws com.android.volley.AuthFailureError {
//                Map<String, String> params = new HashMap<String, String>();
//                params.put("Brand_name",compbrandname);
//                params.put("Company_number",compnumber);
//                params.put("VAT_number",compvatnumber );
//                params.put("Contact_name",compcontactname);
//                params.put("Phone_number","nill");
//                params.put("Address_line_1",compaddressline1);
//                params.put("Address_line_2",compaddressline2);
//                params.put("Start_date",compcity);
//                params.put("End_date",comppostalcode);
//                params.put("Company_description",compdesciption);
//                params.put("Rate","nill");
//                params.put("User_id",serveuserid);
//
//
//
//                return params;
//
//
//            }
//
//            ;
//        };
//
//
//        requestQueue.add(stringRequest);
//    }
//
//    private Response.Listener<String> getPostResponseListener() {
//        return new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                if (response.contains("Already register")){
//
//                    alertBox("Error","Already Registerd user id");
//                }
//
//                try {
//                    JSONObject json=new JSONObject(response);
//
//                    String value=json.getString("Response");
//                    // String regid=json.getString("User_Id");
//                    if (value.contains("success")) {
//
//
//
//
//                        Intent intetn=new Intent(CompanyRegistrationDetials.this,DashboardActivity.class);
//                        intetn.putExtra("User_Id",serveuserid);
//                        intetn.putExtra("type","Employee");
//                        startActivity(intetn);
//
//
//
//
//                    }
//                    else{
//
//                        alertBox("Error",response);
//                    }
//
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//
//
//            }
//        };
//    }

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

                if (k==1){

                    Intent in=new Intent(CompanyRegistrationDetials.this,MainActivity.class);
                    startActivity(in);
                }


            }
        });


        alertDialog.show();
    }

}
