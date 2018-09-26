package staffbid.qbayapps.quadrobay.com.staffbid.Jobseekerdata.JobFragment;

import android.app.Fragment;
import android.content.Context;
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

import staffbid.qbayapps.quadrobay.com.staffbid.AppController;
import staffbid.qbayapps.quadrobay.com.staffbid.R;

public class JPostedJobsFragment extends Fragment {

    View view;
    RecyclerView recyclerView;
    RequestQueue requestQueuep;
    JSONArray jsonObject;
    AppController appController;
    String usertype;
    String empid;
    JSONArray jsonArray;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        view = inflater.inflate(R.layout.latestjobaframent, container, false);

        appController=(AppController)getActivity().getApplication();


        requestQueuep= Volley.newRequestQueue(getActivity());
        usertype=appController.getUsertype();
        empid=appController.getUserid();

        recyclerView=view.findViewById(R.id.recycler);

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity());

        recyclerView.setLayoutManager(linearLayoutManager);
JSONObject jsonObject=new JSONObject();

        try {
            jsonObject.put("Employer_id",empid);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        String url="http://quadrobay.co.in/staffbid_api_new/public/api/jobinformations/posted-jobs?token=24462a501a5aa6ca239d1ade0fc90c9c\n";
        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, url, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {


                JSONArray jsonArray= null;
                try {
                    jsonArray = response.getJSONArray("Jobseeker_profile");
                    MyAdapter myAdapter=new MyAdapter(getActivity(),jsonArray);
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

            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.latestjobadapter, parent, false);
            MyAdapter.ViewHolder viewa = new MyAdapter.ViewHolder(v);

            return viewa;
        }

        @Override
        public void onBindViewHolder(MyAdapter.ViewHolder holder, final int position) {



                holder.apply11.setVisibility(View.VISIBLE);


            try {
                JSONObject jsonOb=jsondata.getJSONObject(position);

                holder.title11.setText("Title : "+jsonOb.getString("Title"));
                holder.description11.setText("Description : "+jsonOb.getString("Description"));
                holder.ctc11.setText("Ctc : "+jsonOb.getString("EOC"));
                holder.location11.setText(jsonOb.getString("Location"));
                holder.contact1.setText(jsonOb.getString("Contact"));

            } catch (JSONException e) {
                e.printStackTrace();
            }






        }

        @Override
        public int getItemCount() {

            return jsondata.length();
        }


        class ViewHolder extends RecyclerView.ViewHolder {


            TextView title11,description11,ctc11,location11,contact1;
            Button apply11;

            public ViewHolder(View itemView) {
                super(itemView);

//                title11 = (TextView) itemView.findViewById(R.id.title1);
//                description11 = (TextView) itemView.findViewById(R.id.description);
//                ctc11 = (TextView) itemView.findViewById(R.id.ctc);
//                location11 = (TextView) itemView.findViewById(R.id.location);
//                apply11 = (Button) itemView.findViewById(R.id.applyjob);

             //   contact1=itemView.findViewById(R.id.contactnumber1);

            }
        }
    }





}


