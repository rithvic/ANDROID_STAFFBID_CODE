package staffbid.qbayapps.quadrobay.com.staffbid.Jobseekerdata.JobFragment;

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

public class JProfileFragment extends Fragment {

    SharedPreferences sharedPreferences;
    String uyserid;

    String usertype;


    AppController appController;
    TextView usernametxt,userprofiletext,dateofbirthtxt,mobilenumbertxt,experiencetext,jobroletxt,startandendtxt,descriptiontxt,nametxt,emailtxt,profiletext1;

    RequestQueue requestQueue;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.profileframent, container, false);

        sharedPreferences= getActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);

        //  uyserid=sharedPreferences.getString("userid", "");

        //usertype=sharedPreferences.getString("usertype", "");




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



        userprofiletext.setText(usertype);

//        if (usertype.contains("Job seeker")){
//
//            profiletext1.setVisibility(View.VISIBLE);
//        }else {
//
//            profiletext1.setVisibility(View.GONE);
//        }



        updateServerData();


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
