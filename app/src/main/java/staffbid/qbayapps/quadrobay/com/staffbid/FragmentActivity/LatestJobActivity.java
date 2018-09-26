package staffbid.qbayapps.quadrobay.com.staffbid.FragmentActivity;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import staffbid.qbayapps.quadrobay.com.staffbid.AppController;
import staffbid.qbayapps.quadrobay.com.staffbid.FinalAppliedcandidates;
import staffbid.qbayapps.quadrobay.com.staffbid.R;
import staffbid.qbayapps.quadrobay.com.staffbid.VolleyCustomPostRequest;

public class LatestJobActivity   extends Fragment {

    View view;
    RecyclerView recyclerView;
    RequestQueue requestQueuep;
    JSONArray jsonObject;
    AppController appController;


    String usertype;
    JSONArray jsonArray;
    String empid;

    TextView tview;


    onclickdata onclickdata;
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


         view = inflater.inflate(R.layout.latestjobaframent, container, false);

        appController=(AppController)getActivity().getApplication();


         requestQueuep= Volley.newRequestQueue(getActivity());
         usertype=appController.getUsertype();
         empid=appController.getUserid();

         recyclerView=view.findViewById(R.id.recycler);
         tview=view.findViewById(R.id.notposted);

            LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity());
            linearLayoutManager.setReverseLayout(true);
            linearLayoutManager.setStackFromEnd(true);

            recyclerView.setLayoutManager(linearLayoutManager);

            HashMap<String,String>params=new HashMap<>();

            params.put("Employer_id",empid);


            String url="http://quadrobay.co.in/staffbid_api_new/public/api/jobinformations/posted-jobs?token=24462a501a5aa6ca239d1ade0fc90c9c";
            VolleyCustomPostRequest jsonObjectRequest=new VolleyCustomPostRequest(Request.Method.POST, url, params, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {

                    String res = null;

                    JSONArray jsonArray= null;
                    try {
                        res=response.getString("Response");
                        jsonArray = response.getJSONArray("Jobseeker_profile");

                       
                    } catch (JSONException e) {

                        Log.e("res","");
                    }
                    if (res.contains("No data found")){

                        tview.setVisibility(View.VISIBLE);
                        recyclerView.setVisibility(View.GONE);
                    }else {
                        recyclerView.setVisibility(View.VISIBLE);
                        tview.setVisibility(View.GONE);
                        MyAdapter myAdapter = new MyAdapter(getActivity(), jsonArray, onclickdata);
                        recyclerView.setAdapter(myAdapter);
                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });
             onclickdata=new onclickdata() {
                @Override
                public void onclickmethos(View v, int position, JSONArray arr) {
                    String jobid = null;

                    try {
                         jobid=arr.getJSONObject(position).getString("Job_id");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                    if (jobid!=null){
                        Intent aaa=new Intent(getActivity(), FinalAppliedcandidates.class);
                        aaa.putExtra("jobid",jobid);
                        startActivity(aaa);

                    }

                }
            };

requestQueuep.add(jsonObjectRequest);

return view;
}



    class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {



        Context context;

      JSONArray jsondata;
        onclickdata onclickdata;


        MyAdapter(Context context,JSONArray json,onclickdata onclik) {


            this.context = context;

            this.jsondata=json;

            this.onclickdata=onclik;


        }

        @Override
        public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View v;

            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.latestjobadapter, parent, false);
            ViewHolder viewa = new ViewHolder(v);

            return viewa;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, final int position) {



            try {
                JSONObject jsonOb=jsondata.getJSONObject(position);

                holder.latitle.setText(jsonOb.getString("Title"));

                       holder.locationtex.setText(jsonOb.getString("Location"));
                holder.ladescription.setText("    \n   "  +jsonOb.getString("Description"));
                holder.lapay.setText("$/Hour : "+jsonOb.getString("Payment"));
                holder.lacon.setText("Phone  : "+jsonOb.getString("Contact"));
                holder.lastrdate.setText(jsonOb.getString("Start_date"));
                holder.laendate.setText(jsonOb.getString("End_date"));
                holder.lastrtime.setText(jsonOb.getString("Start_time"));
                holder.laendtime.setText(jsonOb.getString("End_time"));
                holder.lalistdays.setText("AllDays            :  "+jsonOb.getString("Alldays")+"\n"+
                                          "Sunday            :  "+jsonOb.getString("Sunday")+"\n"+
                                          "Monday           :  " +jsonOb.getString("Monday")+"\n"+
                                          "Tuesday          :  "+jsonOb.getString("Tuesday")+"\n"+
                                          "Wednesday     :  "+jsonOb.getString("Wednesday")+"\n"+
                                          "Thursday         :  "+jsonOb.getString("Thursday")+"\n"+
                                          "Friday              :  "+jsonOb.getString("Friday")+"\n"+
                                          "Saturday          :  " +jsonOb.getString("Saturday"));
               // holder.lastrtime.setText(jsonOb.getString("Description"));









            } catch (JSONException e) {
                e.printStackTrace();
            }




//rds@gmail.com

        }

        @Override
        public int getItemCount() {

            return jsondata.length();
        }


        class ViewHolder extends RecyclerView.ViewHolder {


            TextView latitle,ladescription,lastrdate,laendate,lastrtime,laendtime,lalistdays,lapay,lacon,locationtex;
            Button viewallapplied;

            public ViewHolder(View itemView) {
                super(itemView);
                locationtex = (TextView) itemView.findViewById(R.id.locationviewtext);

                latitle = (TextView) itemView.findViewById(R.id.latesttitle1);
                ladescription = (TextView) itemView.findViewById(R.id.latestdescription);
                lapay = (TextView) itemView.findViewById(R.id.lapay);
                lacon = (TextView) itemView.findViewById(R.id.lacontact);
                lastrdate = (TextView) itemView.findViewById(R.id.laststartdate);

                laendate=itemView.findViewById(R.id.laenddate);
                lastrtime=itemView.findViewById(R.id.lateststartime);
                laendtime=itemView.findViewById(R.id.latestendtime);

                lalistdays=itemView.findViewById(R.id.latestlistdays);

                viewallapplied=itemView.findViewById(R.id.viewappliedcandidates);



                viewallapplied.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                         onclickdata.onclickmethos(view,getAdapterPosition(),jsondata);
                    }
                });


            }
        }
    }

interface onclickdata{


            void onclickmethos(View v,int position,JSONArray arr);
}


}

