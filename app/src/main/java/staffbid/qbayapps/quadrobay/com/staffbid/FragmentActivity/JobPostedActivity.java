package staffbid.qbayapps.quadrobay.com.staffbid.FragmentActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Fragment;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ScrollView;
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
import java.util.Random;

import staffbid.qbayapps.quadrobay.com.staffbid.AppController;
import staffbid.qbayapps.quadrobay.com.staffbid.Jobseekerdata.JobSeeker3AvailabilityActivity;
import staffbid.qbayapps.quadrobay.com.staffbid.R;

public class JobPostedActivity extends Fragment implements  View.OnClickListener,AdapterView.OnItemSelectedListener {

    View view;



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





    AppController appController;
    String usertype;
    String userid;
    private int mYear, mMonth, mDay, mHour, mMinute;

    String tiletype;
    RequestQueue requestQueuep;
    EditText userdescription, userctc, userlocation, usercontact;
    Button updatedata1;

    Button userenddate,userstartdate;

    RequestQueue requestQueue;
    Spinner usertitle;

    String finaalstrdate,finalstrenddate;

    CardView card;
    TextView developerd;


    Button jistrttim,jiendtim;

    CheckBox jiall, jisunam,jisunpm,jimonam,jimonpm,jitueam,jituepm,jiwedam,jiwepm,jithuam,jithurpm,jifriam,jifripm,jisatam,jisatpm;
    String jostarttime,jobendtime;

    String sunday,monday,tuesday,wednesday,thursday,friday,saturday,alldays;



    String[] fieldtype = {"Select Type", "Server", "Bartender", "Housekeeper", "Steward", "Hostes", "Valet", "Line Cook"};

    String setitle, sedescription, sectc, selocation, secontact;


    ScrollView scroll;

    @RequiresApi(api = Build.VERSION_CODES.N)
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        view = inflater.inflate(R.layout.jobspostedframent, container, false);

        requestQueuep = Volley.newRequestQueue(getActivity().getApplicationContext());
        appController = (AppController) getActivity().getApplication();
        usertype = appController.getUsertype();
        userid = appController.getUserid();
       // developerd=view.findViewById(R.id.deveope);
        sunday="No";
        monday="No";
        tuesday="No";
        wednesday="No";
        thursday="No";
        friday="No";
        saturday="No";
        alldays="No";



        String type=appController.getUsertype();

        developerd=view.findViewById(R.id.deveope);
         scroll=view.findViewById(R.id.scrollvisib);

        if (type.contains("Job seeker")){

           scroll.setVisibility(View.GONE);
            developerd.setVisibility(View.VISIBLE);
        }


        usertitle = view.findViewById(R.id.userti);
        userdescription = view.findViewById(R.id.udescp);
        userctc = view.findViewById(R.id.uctc);
        userlocation = view.findViewById(R.id.ulocation);
       updatedata1 = view.findViewById(R.id.uapplyjob);
        usercontact=view.findViewById(R.id.jocontact);
        jistrttim=view.findViewById(R.id.jistarttime);
        jiendtim=view.findViewById(R.id.jiendtime);


        jiall=view.findViewById(R.id.jialldays);
        jisunam=view.findViewById(R.id.jisundayam);
        jisunpm=view.findViewById(R.id.jisundaypm);
        jimonam=view.findViewById(R.id.jimondayam);
        jimonpm=view.findViewById(R.id.jimondaypm);
        jitueam=view.findViewById(R.id.jimondayam);
        jituepm=view.findViewById(R.id.jituesdaypm);
        jiwedam=view.findViewById(R.id.jiwedam);
        jiwepm=view.findViewById(R.id.jiwedpm);
        jithuam=view.findViewById(R.id.jiwedam);
        jiwepm=view.findViewById(R.id.jiwedpm);
        jithuam=view.findViewById(R.id.jithursam);
        jithurpm=view.findViewById(R.id.jithurpm);
        jifriam=view.findViewById(R.id.jifriam);
        jifripm=view.findViewById(R.id.jifripm);
        jisatam=view.findViewById(R.id.jisatam);
        jisatpm=view.findViewById(R.id.jisatupm);

        spinnerloca= (Spinner) view.findViewById(R.id.spinner_search);


        ArrayAdapter aa1 = new ArrayAdapter(getActivity(),android.R.layout.simple_spinner_item,location);
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


        updatedata1.setOnClickListener(this);


       // requestQueuep = Volley.newRequestQueue(getActivity());
        userstartdate=view.findViewById(R.id.startdate);
        userenddate=view.findViewById(R.id.enddate);
        final Calendar myCalendar = Calendar.getInstance();

      //  requestQueuep=Volley.newRequestQueue(getActivity());


        jistrttim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Calendar c = Calendar.getInstance();
                mHour = c.get(Calendar.HOUR_OF_DAY);
                mMinute = c.get(Calendar.MINUTE);

                // Launch Time Picker Dialog
                TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(),  new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay,
                                          int minute) {

//
//                        if (hourOfDay<=9 && minute<=9){
//
//                            jistrttim.setText("0"+hourOfDay + ":"+"0" + minute);
//                        }else {
//
//                            jistrttim.setText(hourOfDay + ":" + minute);
//                        }

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

                       jistrttim.setText(finalhr + ":" + finalmin);
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
                TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(),  new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay,
                                          int minute) {

//                        if (hourOfDay<=9 && minute<=9){
//
//                            jiendtim.setText("0"+hourOfDay + ":"+"0" + minute);
//                        }else {
//
//                            jiendtim.setText(hourOfDay + ":" + minute);
//                        }



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

                       jiendtim.setText(finalhr + ":" + finalmin);
                    }
                }, mHour, mMinute, false);
                timePickerDialog.show();
            }



        });






        ArrayAdapter aa = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, fieldtype);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        usertitle.setAdapter(aa);
        usertitle.setOnItemSelectedListener(this);



        final DatePickerDialog.OnDateSetListener startdate = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                String myFormat = "MM/dd/yy";
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

                String myFormat = "MM/dd/yy";
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
                userenddate.setText(sdf.format(myCalendar.getTime()));
            }

        };
        userstartdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(getActivity(), startdate, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        userenddate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(getActivity(), enddate, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });


        return view;


    }

    @Override
    public void onClick(View v) {


switch (v.getId()){

    case R.id.uapplyjob:



        if (jiall.isChecked()){



           // alldays="All Days";
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






        jostarttime= jistrttim.getText().toString();

        jobendtime=jiendtim.getText().toString();



        finaalstrdate=userstartdate.getText().toString();
        finalstrenddate=userenddate.getText().toString();


        sedescription = userdescription.getText().toString();
        sectc = userctc.getText().toString();
        selocation = userlocation.getText().toString();
        secontact = usercontact.getText().toString();

        if (finallocation==location[0]){
            alertBox("Error","Please select state");

        }else if(tiletype  ==fieldtype[0]) {

         alertBox("Error","Please select type");

        }else {
            updateServerData();
        }



//        if (tiletype.length() == 0) {
//
//        } else if (sedescription.length() == 0) {
//
//            userdescription.setError("Enter the description");
//
//        } else if (sectc.length() == 0) {
//
//            userctc.setError("Enter ctc");
//        } else if (selocation.length() == 0) {
//            userlocation.setError("Enter location");
//
//        } else if (secontact.length() == 0) {
//            usercontact.setError("Enter contact detials");
//
//        } else {




    //    }





        break;


}


    }

    void updateServerData() {



String jobid=String.valueOf(Randomgeneration());

            JSONObject jsonObject = new JSONObject();


            try {

                jsonObject.put("Title", tiletype);
                jsonObject.put("Description", sedescription);
                jsonObject.put("Start_date", finaalstrdate);
                jsonObject.put("End_date", finalstrenddate);
                jsonObject.put("Location", finallocation);
                jsonObject.put("Contact", secontact);
                jsonObject.put("Start_time", jostarttime);
                jsonObject.put("End_time", jobendtime);
                jsonObject.put("Alldays", alldays);




                jsonObject.put("Sunday", sunday);


                jsonObject.put("Monday", monday);


                jsonObject.put("Tuesday", tuesday);
                jsonObject.put("Wednesday", wednesday);

                jsonObject.put("Thursday", thursday);
                jsonObject.put("Friday", friday);
                jsonObject.put("Saturday", saturday);
                jsonObject.put("Employer_id", userid);
                jsonObject.put("Payment",sectc);
                jsonObject.put("Job_id", jobid);

            } catch (JSONException e) {
                e.printStackTrace();
            }


            String url = "http://quadrobay.co.in/staffbid_api_new/public/api/jobinformations/job-upload?token=24462a501a5aa6ca239d1ade0fc90c9c";
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {


                     String res=response.toString();


                     alertBox("Success","Posted Successfully");
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    error.printStackTrace();


                }
            });


            //requestQueue.add(jsonObjectRequest);
            AppController.getInstance().addToRequestQueue(jsonObjectRequest,"tag");


        }




        @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        tiletype  =fieldtype[position];
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
    void alertBox(String title,String msg){



        userdescription.setText("");
        userctc .setText("");
        userlocation.setText("");

        usercontact.setText("");
        userenddate.setText("");
        userstartdate.setText("");
        sunday="No";
        monday="No";
        tuesday="No";
        wednesday="No";
        thursday="No";
        friday="No";
        saturday="No";
        alldays="No";
        jistrttim.setText("Start Time");
        jiendtim.setText("End Time");
        userstartdate.setText("Start Date");
        userenddate.setText("End Date");
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());

        alertDialog.setTitle(title);

        alertDialog.setMessage(msg);

        alertDialog.setNegativeButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {

            }
        });


        alertDialog.show();
    }
    int Randomgeneration(){
        Random rand = new Random();

        int rand_int1 = rand.nextInt(10000000);


        return rand_int1;
    }

}
