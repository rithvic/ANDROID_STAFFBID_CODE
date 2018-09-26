package staffbid.qbayapps.quadrobay.com.staffbid;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import staffbid.qbayapps.quadrobay.com.staffbid.FragmentActivity.LatestJobActivity;

public class Secondfragment extends Fragment {

    View view;
    RecyclerView recyclerView;
    RequestQueue requestQueuep;
    JSONArray jsonObject;
    AppController appController;
    onclickupdate updatedataemp;

    String data = null;
    String usertype;
    JSONArray jsonArray;
    String empid;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        view = inflater.inflate(R.layout.latestjobaframent, container, false);

        appController=(AppController)getActivity().getApplication();


        requestQueuep= Volley.newRequestQueue(getActivity());
        usertype=appController.getUsertype();
        empid=appController.getUserid();

        recyclerView=view.findViewById(R.id.recycler);

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity());
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);


        recyclerView.setLayoutManager(linearLayoutManager);

        HashMap<String,String> params=new HashMap<>();

        params.put("Employer_id",empid);
        updatedataemp= new onclickupdate() {
            @Override
            public void updatedata(View view, int position, JSONArray jsonarr) {


//                try {
//                     data=jsonarr.getJSONObject(position).getString("Job_id");
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//
//                alertBox("title",data);
                JSONObject jsonobj = null;

                try {
                     jsonobj=jsonarr.getJSONObject(position);
                    data=jsonarr.getJSONObject(position).getString("Job_id");
                } catch (JSONException e) {
                    e.printStackTrace();
                }


                clickdataUploadserver(jsonobj);

            }
        };


        String url="http://quadrobay.co.in/staffbid_api_new/public/api/jobinformations/get-job-listview?token=24462a501a5aa6ca239d1ade0fc90c9c\n";
        VolleyCustomPostRequest jsonObjectRequest=new VolleyCustomPostRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {


                JSONArray jsonArray= null;
                try {
                    jsonArray = response.getJSONArray("Jobseeker_profile");
                    MyAdapter myAdapter=new MyAdapter(getActivity(),jsonArray,updatedataemp);
                    recyclerView.setAdapter(myAdapter);

                } catch (JSONException e) {

                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });


        requestQueuep.add(jsonObjectRequest);

        return view;
    }

    private void clickdataUploadserver(JSONObject jsonObject) {


         JSONObject jsondata=new JSONObject();

        try {
            jsondata.put("Title",jsonObject.getString("Title"));


            jsondata.put("Description",jsonObject.getString("Description"));
            jsondata.put("Start_date",jsonObject.getString("Start_date"));
            jsondata.put("End_date",jsonObject.getString("End_date"));

            jsondata.put("Start_time" ,jsonObject.getString("Start_time"));
            jsondata.put("End_time",jsonObject.getString("End_time"));
            jsondata.put("Alldays" ,jsonObject.getString("Alldays"));
            jsondata.put("Sunday", jsonObject.getString("Sunday"));
            jsondata.put("Monday" , jsonObject.getString("Monday"));
            jsondata.put("Tuesday" , jsonObject.getString("Tuesday"));
            jsondata.put("Wednesday" , jsonObject.getString("Wednesday"));
            jsondata.put("Thursday" , jsonObject.getString("Thursday"));
            jsondata.put("Friday", jsonObject.getString("Friday"));
            jsondata.put("Saturday" , jsonObject.getString("Saturday"));
            jsondata.put("Location" , jsonObject.getString("Location"));
            jsondata.put("Contact", jsonObject.getString("Contact"));
            jsondata.put("Jobseeker_id",empid);
            jsondata.put("Job_id",data);
            jsondata.put("Payment",jsonObject.getString("Payment"));













        } catch (JSONException e) {
            e.printStackTrace();
        }



        String posurl="http://quadrobay.co.in/staffbid_api_new/public/api/applyjobs/apply-jobs?token=24462a501a5aa6ca239d1ade0fc90c9c";


        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.POST, posurl, jsondata, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {



                alertBox("Information",response.toString());


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {


                error.printStackTrace();

            }
        });

        requestQueuep.add(jsonObjectRequest);









    }


    class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {


        onclickupdate dataupload;

        Context context;

        JSONArray jsondata;


        MyAdapter(Context context,JSONArray json,onclickupdate data) {


            this.context = context;

            this.jsondata=json;

            this.dataupload=data;

        }

        @Override
        public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View v;

            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.latestjobadapter, parent, false);
            MyAdapter.ViewHolder viewa = new MyAdapter.ViewHolder(v);

            return viewa;
        }

        @Override
        public void onBindViewHolder(MyAdapter.ViewHolder holder, final int position) {

holder.apply11.setVisibility(View.VISIBLE);
            holder.viewappliedcandi.setVisibility(View.GONE);

            try {
                JSONObject jsonOb=jsondata.getJSONObject(position);

                holder.latitle.setText(jsonOb.getString("Title"));
                holder.locattex.setText(jsonOb.getString("Location"));
                holder.ladescription.setText("    \n   "  +jsonOb.getString("Description"));
                holder.lapay.setText("$/Hour : "+jsonOb.getString("Payment"));
                holder.lacon.setText("Phone  : "+jsonOb.getString("Contact"));
                holder.lastrdate.setText(jsonOb.getString("Start_date"));
                holder.laendate.setText(jsonOb.getString("End_date"));
                holder.lastrtime.setText(jsonOb.getString("Start_time"));
                holder.laendtime.setText(jsonOb.getString("End_time"));
                holder.lalistdays.setText("All Days            :  "+jsonOb.getString("Alldays")+"\n"+"Sunday             :  "+jsonOb.getString("Sunday")+"\n"+"Monday            :  " +jsonOb.getString("Monday")+"\n"+"Tuesday            :  "+jsonOb.getString("Tuesday")+"\n"+"Wednesday      :  "+jsonOb.getString("Wednesday")+"\n"+"Thursday          :  "+jsonOb.getString("Thursday")+"\n"+"Friday                :  "+jsonOb.getString("Friday")+"\n"+"Saturday           :  " +jsonOb.getString("Saturday"));
                // holder.lastrtime.setText(jsonOb.getString("Description"));









            } catch (JSONException e) {
                e.printStackTrace();
            }






        }

        @Override
        public int getItemCount() {

            return jsondata.length();
        }


        class ViewHolder extends RecyclerView.ViewHolder {


            TextView latitle,ladescription,lastrdate,laendate,lastrtime,laendtime,lalistdays,lapay,lacon,locattex;
            Button apply11,viewappliedcandi;

            public ViewHolder(View itemView) {
                super(itemView);

               locattex = (TextView) itemView.findViewById(R.id.locationviewtext);
                latitle = (TextView) itemView.findViewById(R.id.latesttitle1);
                ladescription = (TextView) itemView.findViewById(R.id.latestdescription);
                lapay = (TextView) itemView.findViewById(R.id.lapay);
                lacon = (TextView) itemView.findViewById(R.id.lacontact);
                lastrdate = (TextView) itemView.findViewById(R.id.laststartdate);

                laendate=itemView.findViewById(R.id.laenddate);
                lastrtime=itemView.findViewById(R.id.lateststartime);
                laendtime=itemView.findViewById(R.id.latestendtime);

                lalistdays=itemView.findViewById(R.id.latestlistdays);

                apply11=itemView.findViewById(R.id.latestjobapply);
                viewappliedcandi=itemView.findViewById(R.id.viewappliedcandidates);

                apply11.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View view) {
                 dataupload.updatedata(view,getAdapterPosition(),jsondata);

                 }
                });

            }
        }
    }
    void alertBox(String title,String msg){

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());

        alertDialog.setTitle(title);

        alertDialog.setMessage(msg);

        alertDialog.setNegativeButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {

            }
        });


        alertDialog.show();
    }




}

interface onclickupdate{
    void updatedata(View view,int position,JSONArray jsonarr);
}








//http://quadrobay.co.in/staffbid_api_new/public/api/applyjobs/apply-jobs?token=24462a501a5aa6ca239d1ade0fc90c9c
//
//        {
//        "Title" : "Moahmed",
//        "Description" : "mohamed@gmail.com",
//        "Start_date" : "mohamed@123",
//        "End_date" : "Job seeker",
//        "Start_time" : "ret4er",
//        "End_time" : "uihdfiu",
//        "Alldays" : "sdfsef",
//        "Sunday" : "sfeesf",
//        "Monday" : "dfsef",
//        "Tuesday" : "sefdwe",
//        "Wednesday" : "sdfse",
//        "Thursday" : "sfse",
//        "Friday" : "sdfa",
//        "Saturday" : "sdfsa",
//        "Location" : "asdawd",
//        "Contact" : "sdaf",
//        "Jobseeker_id" : "asdas1235678",
//        "Job_id" : "xyz1234"
//        }