package staffbid.qbayapps.quadrobay.com.staffbid.Jobseekerdata;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
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
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Locale;

import staffbid.qbayapps.quadrobay.com.staffbid.AppController;
import staffbid.qbayapps.quadrobay.com.staffbid.MainActivity;
import staffbid.qbayapps.quadrobay.com.staffbid.R;

public class JobSeeker3AvailabilityActivity  extends AppCompatActivity implements View.OnClickListener,AdapterView.OnItemSelectedListener {


    String finallocation="no";
    ImageView backbutton;

    private int mYear, mMonth, mDay, mHour, mMinute;
    String employeetype;
    Spinner jispinnerdata;
    Button jiRegbutton,jistrdate,jiendate,jistrttim,jiendtim;
    ImageView jibackbutt;

    CheckBox jiall, jisunam,jisunpm,jimonam,jimonpm,jitueam,jituepm,jiwedam,jiwepm,jithuam,jithurpm,jifriam,jifripm,jisatam,jisatpm;


    String jobstartdate1,jobenddate1,jobstartime,jobenndtime;

String loca;

    Integer k=1;

    String jobpass,jobmail,jobusername,jobuserid,jobfirstname,joblastname,jobdateofbirth,jobphone,jobcompanyname,jobsrole,jobstarttdate,jonbenddate,jonbdescripton,jobrate;

    String sunday,monday,tuesday,wednesday,thursday,friday,saturday,alldays;

    RequestQueue requestQueue;

    AppController appController;

    TextView  userlocation1;
    Spinner spinnerloca;




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
    String[] fieldtype = { "Select Type","Server", "Bartender","Valet","Housekeeper","Steward","Hostes","Line Cook"};
    String[] city={"Alexander City" ,
            "Andalusia" ,
            "Anniston" ,
            "Athens",
            "Atmore",
            "Auburn" ,
            "Bessemer",
            "Birmingham",
            "Chickasaw," ,
            "Clanton",
            "Cullman",
            "Decatur",
            "Demopolis" ,
            "Dothan",
            "Enterprise",
            "Eufaula",
            "Florence",
            "Fort Payne",
            "Gadsden",
            "Greenville" ,
            "Guntersville" ,
            "Huntsville" ,
            "Jasper",
            "Marion",
            "Mobile",

            "Montgomery",
            "Opelika",
            "Ozark" ,
            "Phenix City",
            "Prichard",
            "Scottsboro" ,
            "Selma",
            "Sheffield",
            "Sylacauga" ,
            "Talladega",
            "Troy" ,
            "Tuscaloosa" ,
            "Tuscumbia",
            "Tuskegee",""};
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jobseekerthreeavailabilty);
        final Calendar myCalendar = Calendar.getInstance();
        requestQueue= Volley.newRequestQueue(getApplicationContext());

        Intent ininent = getIntent();

        appController=(AppController)getApplication().getApplicationContext();

        sunday="No";
        monday="No";
        tuesday="No";
        wednesday="No";
        thursday="No";
        friday="No";
        saturday="No";
        alldays="No";
        jobuserid=   ininent.getStringExtra("userid");
        jobfirstname= ininent.getStringExtra("firstname");
        joblastname=  ininent.getStringExtra("lastname" );
        jobdateofbirth=   ininent.getStringExtra("dateofbirth");
        jobphone=  ininent.getStringExtra("phone");
        jobcompanyname=  ininent.getStringExtra("companyname");
        jobsrole=ininent.getStringExtra("jobrole");
        jobstarttdate=ininent.getStringExtra("startdate");
        jonbenddate= ininent.getStringExtra("enddate");
        jonbdescripton=     ininent.getStringExtra("description");
        jobrate= ininent.getStringExtra("rate");
        //jobusername=ininent.getStringExtra("usname");

spinnerloca= (Spinner) findViewById(R.id.spinner_search);
jobmail=appController.getUsergmai();
jobpass=appController.getUserpass();
        jobusername=appController.getUsername();

        ArrayAdapter aa1 = new ArrayAdapter(this,android.R.layout.simple_spinner_item,location);
        aa1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerloca.setAdapter(aa1);

        jispinnerdata=findViewById(R.id.jispinner);

        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,fieldtype);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        jispinnerdata.setAdapter(aa);

                jiRegbutton=findViewById(R.id.jiregisterdata);
                jibackbutt=findViewById(R.id.jibackbutton);
                jistrdate=findViewById(R.id.jistartdate);
                jiendate=findViewById(R.id.jienddate);
                jistrttim=findViewById(R.id.jistarttime);
                jiendtim=findViewById(R.id.jiendtime);

        userlocation1=findViewById(R.id.userlocationtex);

                jiall=findViewById(R.id.jialldays);
                jisunam=findViewById(R.id.jisundayam);
                jisunpm=findViewById(R.id.jisundaypm);
                jimonam=findViewById(R.id.jimondayam);
                jimonpm=findViewById(R.id.jimondaypm);
                jitueam=findViewById(R.id.jimondayam);
                jituepm=findViewById(R.id.jituesdaypm);
                jiwedam=findViewById(R.id.jiwedam);
                jiwepm=findViewById(R.id.jiwedpm);
                jithuam=findViewById(R.id.jiwedam);
                jiwepm=findViewById(R.id.jiwedpm);
                jithuam=findViewById(R.id.jithursam);
                jithurpm=findViewById(R.id.jithurpm);
                jifriam=findViewById(R.id.jifriam);
                jifripm=findViewById(R.id.jifripm);
                jisatam=findViewById(R.id.jisatam);
                jisatpm=findViewById(R.id.jisatupm);

        backbutton=findViewById(R.id.jibackbutton);
        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


        spinnerloca.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                finallocation=location[i];

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });








                jiRegbutton.setOnClickListener(this);
        jispinnerdata.setOnItemSelectedListener(this);


        final DatePickerDialog.OnDateSetListener startdate = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);


                String myFormat = "MM/dd/yyyy";
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
                jistrdate.setText(sdf.format(myCalendar.getTime()));
            }

        };


        final DatePickerDialog.OnDateSetListener endadte = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                String myFormat = "MM/dd/yyyy";
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
                jiendate.setText(sdf.format(myCalendar.getTime()));
            }

        };







jistrdate.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

        new DatePickerDialog(JobSeeker3AvailabilityActivity.this, startdate, myCalendar
                .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)).show();


    }
});

jiendate.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(00);
        cal.set(year, month, day, 00, 00, 00);

        new DatePickerDialog(JobSeeker3AvailabilityActivity.this, endadte, myCalendar
                .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)).show();





    }

});

jistrttim.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

        final Calendar c = Calendar.getInstance();
        mHour = c.get(Calendar.HOUR_OF_DAY);
        mMinute = c.get(Calendar.MINUTE);

        // Launch Time Picker Dialog
        TimePickerDialog timePickerDialog = new TimePickerDialog(JobSeeker3AvailabilityActivity.this,  new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay,
                                          int minute) {

                  String      finalhr,finalmin;
                        if (hourOfDay<=9){

                            finalhr="0"+hourOfDay;
                        }else {

                            finalhr=String.valueOf(hourOfDay);
                        }
                        if (minute<=9){
                            finalmin="0"+minute;
                        }else {
                            finalmin=String.valueOf(minute);

                        }


//
//                      if (hourOfDay<=9 && minute<=9 || minute<=0){
//
//                            jistrttim.setText("0"+hourOfDay + ":"+"0" + minute);
//                        }else {
//
                      jistrttim.setText(finalhr + ":" + finalmin);
//                        }

                       // jistrttim.setText(hourOfDay + ":" + fin);
                    }
                }, mHour, mMinute, false);
        timePickerDialog.show();
    }

});

jiendtim.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

        final Calendar c = Calendar.getInstance();
        mHour = c.get(Calendar.HOUR_OF_DAY);
        mMinute = c.get(Calendar.MINUTE);

        // Launch Time Picker Dialog
        TimePickerDialog timePickerDialog = new TimePickerDialog(JobSeeker3AvailabilityActivity.this,  new TimePickerDialog.OnTimeSetListener() {

            @Override
            public void onTimeSet(TimePicker view, int hourOfDay,
                                  int minute) {

                String      finalhr,finalmin;
                if (hourOfDay<=9){

                    finalhr="0"+hourOfDay;
                }else {

                    finalhr=String.valueOf(hourOfDay);
                }
                if (minute<=9){
                    finalmin="0"+minute;
                }else {
                    finalmin=String.valueOf(minute);

                }

//                if (hourOfDay<=9 && minute<=9 || minute<=0){
//
//                    jiendtim.setText("0"+hourOfDay + ":"+"0" + minute);
//                }else {

                    jiendtim.setText(finalhr + ":" + finalmin);
              //  }
            }
        }, mHour, mMinute, false);
        timePickerDialog.show();
    }



});

    }

    @Override
    public void onClick(View v) {


        switch (v.getId()){

            case R.id.jibackbutton:

            onBackPressed();
            break;

            case R.id.jiregisterdata:



                 jobstartime=jistrttim.getText().toString();
                 jobenndtime=jiendtim.getText().toString();

                 jobstartdate1=jistrdate.getText().toString();
                 jobenddate1=jiendate.getText().toString();

               // finallocation=userlocation1.getText().toString();

                if (jiall.isChecked()){


                    alldays="All";

                    sunday="Yes";
                    monday="Yes";
                    tuesday="Yes";

                            wednesday="Yes";
                    thursday="Yes";
                    friday="Yes";
                            saturday ="Yes";
                }else {

                    if (jisunam.isChecked()){

                        sunday="am";
                    }
                    if (jisatpm.isChecked()){
                        sunday+=" : pm";
                    }
                    if (jimonam.isChecked()){

                        monday="am";
                    }
                    if (jimonpm.isChecked()){
                        monday+=" : pm";
                    }

                    if (jitueam.isChecked()){

                        tuesday="am";
                    }
                    if (jituepm.isChecked()){
                        tuesday+=" : pm";
                    }
                    if (jiwedam.isChecked()){

                        wednesday="am";
                    }
                    if (jiwepm.isChecked()){
                        wednesday+=" : pm";
                    }

                    if (jithuam.isChecked()){

                        thursday="am";
                    }
                    if (jithurpm.isChecked()){
                        thursday+=" : pm";
                    }

                    if (jifriam.isChecked()){

                        friday="am";
                    }
                    if (jifripm.isChecked()){
                        friday+=" : pm";
                    }
                    if (jisatam.isChecked()){

                        saturday="am";
                    }
                    if (jisatpm.isChecked()){
                        saturday+=" : pm";
                    }

                }




                if (employeetype.length()==0){


                }else if (jobstartdate1.length()==0){

                }else if (jobenddate1.length()==0){

                }else {

                    if (finallocation==location[0]){
                        alertBox("Error","Please select state");

                    }else if (employeetype==fieldtype[0]){

                        alertBox("Error","Please select type");

                    }

                    else{

                        updatedataserver();
                    }


                // updatedataserver();

                 break;
                }
        }



    }

    private void updatedataserver() {


JSONObject jsonObject=new JSONObject();


        try {

            jsonObject.put("Name",jobusername);
            jsonObject.put("Email",jobmail);
            jsonObject.put("Password",jobpass);
            jsonObject.put("Type","Job seeker");
            jsonObject.put("Firstname",jobfirstname);
            jsonObject.put("Lastname",joblastname);
            jsonObject.put("DOB",jobdateofbirth);
            jsonObject.put("Phone",jobphone);
            jsonObject.put("Company_name",jobcompanyname);
            jsonObject.put("Job_role",jobsrole);
            jsonObject.put("Start_date",jobstarttdate);
            jsonObject.put("End_date",jonbenddate);
            jsonObject.put("Description",jonbdescripton);
            jsonObject.put("Start_free_date",jobstartdate1);
            jsonObject.put("End_free_date",jobenddate1);


                jsonObject.put("Alldays", alldays);

                jsonObject.put("Sunday",sunday);


                jsonObject.put("Monday",monday);


            jsonObject.put("Tuesday",tuesday);
            jsonObject.put("Wednesday",wednesday);

            jsonObject.put("Thursday",thursday);
            jsonObject.put("Friday",friday);
            jsonObject.put("Saturday",saturday);
            jsonObject.put("AM",jobstartime);
            jsonObject.put("PM",jobenndtime);
            jsonObject.put("Jobseeker_id",jobuserid);
            jsonObject.put("Location",finallocation);

        } catch (JSONException e) {
            e.printStackTrace();
        }











        String url="http://quadrobay.co.in/staffbid_api_new/public/api/jobseekerdetails/signup-jobseeker?token=24462a501a5aa6ca239d1ade0fc90c9c";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

               String res = null;


              // String res=response.toString();

                try {



                     res=response.getString("Response");
                    String id=response.getString("Jobseeker_id");
                    if (res.contains("success") && id.contains(jobuserid)){

                       k=2;

                       alertBox("Successfully Registered","Please go to Login Page");



                    }else {

                        alertBox("Status",res);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();

                    alertBox("Notification",res);

                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();

                alertBox("Error","check your input Information or Internet connection");

            }
        });


        requestQueue.add(jsonObjectRequest);


            }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        employeetype=fieldtype[position];
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
    void alertBox(String title,String msg){

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);

        alertDialog.setTitle(title);

        alertDialog.setMessage(msg);

        alertDialog.setNegativeButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {


                if (k==2){

                    Intent ini=new Intent(JobSeeker3AvailabilityActivity.this,MainActivity.class);
                    startActivity(ini);
                }
            }
        });


        alertDialog.show();
    }
}
