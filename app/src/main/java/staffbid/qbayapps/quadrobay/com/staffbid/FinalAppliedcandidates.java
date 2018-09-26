package staffbid.qbayapps.quadrobay.com.staffbid;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import java.util.Map;

public class FinalAppliedcandidates extends Activity {


    AppController appController;
    RequestQueue requestQueue;
    ImageView imag123;

    String userid,usertype,jobid;

    TextView ta;
    View view;
    RecyclerView recyclerView;
    ImageView img1;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.employerappliedcandidate);

recyclerView=findViewById(R.id.recyclerjob);
LinearLayoutManager linear=new LinearLayoutManager(FinalAppliedcandidates.this);
        linear.setReverseLayout(true);
        linear.setStackFromEnd(true);

recyclerView.setLayoutManager(linear);
        requestQueue= Volley.newRequestQueue(getApplicationContext());


        appController=(AppController)getApplicationContext();

        userid=appController.getUserid();
        usertype=appController.getUsertype();


img1=findViewById(R.id.imagebackbutt);
       // imag123=f
        ta=findViewById(R.id.visi);
        final Intent ini=getIntent();


     jobid=ini.getStringExtra("jobid");




        Map<String,String>params=new HashMap<>();

        params.put("Job_id",jobid);

        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


        String posurl="http://quadrobay.co.in/staffbid_api_new/public/api/jobinformations/applied-canditates?token=24462a501a5aa6ca239d1ade0fc90c9c";


        VolleyCustomPostRequest jsonObjectRequest=new VolleyCustomPostRequest(Request.Method.POST, posurl, params, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                String res = null;
int len = 0;
                JSONArray jsarray = null;
                try {
                     res=response.getString("Response");

                     jsarray=response.getJSONArray("Apllied_candidates");
                     len=jsarray.getJSONObject(0).length();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

if (len==0){
    ta.setVisibility(View.VISIBLE);
    recyclerView.setVisibility(View.GONE);


}else {

    MyAdapter myAdapter=new MyAdapter(FinalAppliedcandidates.this,jsarray);
    recyclerView.setAdapter(myAdapter);


}
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {


                error.printStackTrace();

            }
        });

        requestQueue.add(jsonObjectRequest);



    }



    class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {




        Context context;

        JSONArray jsondata;


        MyAdapter(Context context,JSONArray json) {


            this.context = context;

            this.jsondata=json;



        }

        @Override
        public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View v;

            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.emppliedadapter, parent, false);
            MyAdapter.ViewHolder viewa = new MyAdapter.ViewHolder(v);

            return viewa;
        }

        @Override
        public void onBindViewHolder(MyAdapter.ViewHolder holder, final int position) {


            try {
                JSONObject jsobj=jsondata.getJSONObject(position);


                holder.apppost.setText("Name : "+jsobj.getString("Name"));
                holder.appname.setText("Contact : "+jsobj.getString("Phone"));
                holder.applocation.setText("Email : "+jsobj.getString("Email"));
                holder.appcontact.setText("Location : "+jsobj.getString("Location"));
                holder.appexperience.setText("Job Role : "+jsobj.getString("Job_role"));
                holder.appavilability.setText("Description : "+"\n"+   "   "+jsobj.getString("Description"));




            } catch (JSONException e) {
                e.printStackTrace();
            }


        }

        @Override
        public int getItemCount() {

            return jsondata.length();
        }


        class ViewHolder extends RecyclerView.ViewHolder {


            TextView apppost,appname,applocation,appcontact,appexperience,appavilability;

            public ViewHolder(View itemView) {
                super(itemView);

                apppost = (TextView) itemView.findViewById(R.id.appname1);
                appname = (TextView) itemView.findViewById(R.id.appcontact1);
                applocation = (TextView) itemView.findViewById(R.id.appemail1);
                appcontact = (TextView) itemView.findViewById(R.id.applocation1);
                appexperience = (TextView) itemView.findViewById(R.id.appavailable1);

                appavilability=itemView.findViewById(R.id.appliedexperience);

             }
        }
    }

}

