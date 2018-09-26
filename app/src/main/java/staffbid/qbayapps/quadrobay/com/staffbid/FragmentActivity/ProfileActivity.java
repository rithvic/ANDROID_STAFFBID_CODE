package staffbid.qbayapps.quadrobay.com.staffbid.FragmentActivity;

import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import staffbid.qbayapps.quadrobay.com.staffbid.AppController;
import staffbid.qbayapps.quadrobay.com.staffbid.R;

public class ProfileActivity extends Fragment {

    SharedPreferences sharedPreferences;
    String uyserid;

    String usertype;


    AppController appController;
    TextView locatext, usernametxt,userprofiletext,dateofbirthtxt,mobilenumbertxt,experiencetext,jobroletxt,startandendtxt,descriptiontxt,nametxt,emailtxt,profiletext1;

    RequestQueue requestQueue;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.profileframent, container, false);

        sharedPreferences= getActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);





        appController=(AppController)getActivity().getApplication();


        usertype=appController.getUsertype();
        uyserid=appController.getUserid();

        requestQueue= Volley.newRequestQueue(getActivity());

        usernametxt=view.findViewById(R.id.usernametext);
        userprofiletext=view.findViewById(R.id.userprofiletype);
        dateofbirthtxt=view.findViewById(R.id.dateofbirth);
        mobilenumbertxt=view.findViewById(R.id.mobilenum);
        experiencetext=view.findViewById(R.id.experiencecompany);
        jobroletxt=view.findViewById(R.id.jobroleprofile);
        startandendtxt=view.findViewById(R.id.startandenddate);
         descriptiontxt=view.findViewById(R.id.jobdescription);
        nametxt=view.findViewById(R.id.nameuser);
         emailtxt=view.findViewById(R.id.useremailid);
         profiletext1=view.findViewById(R.id.payment);
        locatext=view.findViewById(R.id.location11);


if (usertype.contains("Employee")){
    userprofiletext.setText("Employer");
}else {
    userprofiletext.setText("Job Seeker");

}


        if (usertype.contains("Employee")) {

            try {
                usernametxt.setText(""+AppController.getInstance().getUserDetail().getString("Name"));

                mobilenumbertxt.setText("Company Email : "+AppController.getInstance().getUserDetail().getString("Email"));
                profiletext1.setText("Type : "+AppController.getInstance().getUserDetail().getString("Type"));
                dateofbirthtxt.setText("Brand Name : "+AppController.getInstance().getUserDetail().getString("Brand_name"));
                jobroletxt.setText("Company Name : "+AppController.getInstance().getUserDetail().getString("Company_name"));
                descriptiontxt.setText("VAT Number : "+AppController.getInstance().getUserDetail().getString("VAT_number"));
                experiencetext.setText("Address Line 1 : "+AppController.getInstance().getUserDetail().getString("Address_line_1"));
                nametxt.setText("Address Line 2 : "+AppController.getInstance().getUserDetail().getString("Address_line_2"));
                // usernametxt.setText(AppController.getInstance().getUserDetail().getString("City"));
                emailtxt.setText("Zipcode : " +AppController.getInstance().getUserDetail().getString("Zipcode"));
                startandendtxt.setText("Company Description : "+AppController.getInstance().getUserDetail().getString("Company_description"));

                //locatext.setText("Location  :"+AppController.getInstance().getUserDetail().getString("Location"));

            } catch (JSONException e) {
                e.printStackTrace();
            }
            //updateServerData();

        }else {

            try {
                usernametxt.setText(""+AppController.getInstance().getUserDetail().getString("Name"));

                mobilenumbertxt.setText("Email : "+AppController.getInstance().getUserDetail().getString("Email"));
                dateofbirthtxt.setText("Date of Birth : "+AppController.getInstance().getUserDetail().getString("DOB"));
                profiletext1.setText("Phone Number : "+AppController.getInstance().getUserDetail().getString("Phone"));
                jobroletxt.setText("Job Role : "+AppController.getInstance().getUserDetail().getString("Job_role"));
                experiencetext.setText("Available from : "+AppController.getInstance().getUserDetail().getString("Start_free_date"));
                nametxt.setText("Available until : "+AppController.getInstance().getUserDetail().getString("End_free_date"));
                emailtxt.setText("Availabilty      : "+AppController.getInstance().getUserDetail().getString("Alldays")+" days"+"\n"
                                +"Sunday           : "+AppController.getInstance().getUserDetail().getString("Sunday")
                          +"\n" +"Monday          : "+AppController.getInstance().getUserDetail().getString("Monday")+
                            "\n"+"Tuesday          : "+ AppController.getInstance().getUserDetail().getString("Tuesday")
                          +"\n" +"Wednesday    : " +AppController.getInstance().getUserDetail().getString("Wednesday")+
                           "\n" +"Thursday        : "  +AppController.getInstance().getUserDetail().getString("Thursday")
                          +"\n" +"Friday             : " +AppController.getInstance().getUserDetail().getString("Friday")
                          +"\n" +"Saturday        : "  +AppController.getInstance().getUserDetail().getString("Saturday"));
                // usernametxt.setText(AppController.getInstance().getUserDetail().getString("City"));
                startandendtxt.setText("Company Name : "+AppController.getInstance().getUserDetail().getString("Company_name"));
                descriptiontxt.setText("Description : "+AppController.getInstance().getUserDetail().getString("Description"));


            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return view;
    }





    void updateServerData() {



        String url="http://quadrobay.co.in/staffbid_api/public/api/user/profile-information?token=24462a501a5aa6ca239d1ade0fc90c9c";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, getPostResponseListener(), getPostErrorListener()) {
            protected Map<String, String> getParams() throws com.android.volley.AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("User_id",uyserid);
                params.put("Type",usertype);




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


                    if (usertype.contains("Job seeker")) {
                        JSONObject jsonObject = new JSONObject(response);
                        JSONArray jsonArray = jsonObject.getJSONArray("Employee_profile");

                        for (int i = 0; i < jsonArray.length(); i++) {

                            JSONObject jsonObject1 = jsonArray.getJSONObject(i);

                            usernametxt.setText(jsonObject1.getString("Firstname") + " " + jsonObject1.getString("Lastname"));


                            dateofbirthtxt.setText(jsonObject1.getString("Dateofbirth"));


                            profiletext1.setText("hour : " + jsonObject1.getString("Rate") + " $");
                            profiletext1.setVisibility(View.VISIBLE);
                            mobilenumbertxt.setText(jsonObject1.getString("Mobile_number"));
                            experiencetext.setText(jsonObject1.getString("Company_name"));
                            jobroletxt.setText(jsonObject1.getString("Job_role"));
                            startandendtxt.setText(jsonObject1.getString("Start_date") + " - " + jsonObject1.getString("End_date"));
                            descriptiontxt.setText(jsonObject1.getString("Description"));
                            nametxt.setText(jsonObject1.getString("Name"));
                            emailtxt.setText(jsonObject1.getString("Email"));


                        }

                    }
                    else {


                        JSONObject jsonObject = new JSONObject(response);
                        JSONArray jsonArray = jsonObject.getJSONArray("Jobseeker_profile");
                        for (int i = 0; i < jsonArray.length(); i++) {

                            JSONObject jsonObject1 = jsonArray.getJSONObject(i);


                            usernametxt.setText(jsonObject1.getString("Brand_name"));

                            dateofbirthtxt.setText(jsonObject1.getString("Company_number"));

                            mobilenumbertxt.setText(jsonObject1.getString("Name"));
                            experiencetext.setText(jsonObject1.getString("VAT_number"));
                            jobroletxt.setText(jsonObject1.getString("Contact_name"));
                            startandendtxt.setText(jsonObject1.getString("Address_line_1")+"\n"+jsonObject1.getString("Address_line_2"));
                            descriptiontxt.setText(jsonObject1.getString("Start_date")+" - "+jsonObject1.getString("End_date"));
                            nametxt.setText(jsonObject1.getString("Company_description"));
                            emailtxt.setText(jsonObject1.getString("Email"));



                        }


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


            }
        };
    }


}
