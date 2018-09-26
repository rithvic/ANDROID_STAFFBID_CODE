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

public class SecondTwofragments extends Fragment {

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


        view = inflater.inflate(R.layout.secondtwofragments, container, false);

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

        params.put("Jobseeker_id",empid);


        String url="http://quadrobay.co.in/staffbid_api_new/public/api/applyjobs/applied-jobseeker-jobs?token=24462a501a5aa6ca239d1ade0fc90c9c";
        VolleyCustomPostRequest jsonObjectRequest=new VolleyCustomPostRequest(Request.Method.POST, url, params, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                JSONArray js = null;

                try {
                     js=response.getJSONArray("Response");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if (js != null){
                    MyAdapter myAdapter=new MyAdapter(getActivity(),js);
                    recyclerView.setAdapter(myAdapter);
                }





            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });


        requestQueuep.add(jsonObjectRequest);

        return view;
    }







    }


    class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {


        onclickupdate dataupload;

        Context context;

        JSONArray jsondata;


        MyAdapter(Context context, JSONArray json) {


            this.context = context;

            this.jsondata = json;


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

       //     holder.apply11.setVisibility(View.VISIBLE);
            holder.viewappliedcand.setVisibility(View.GONE);

            try {
                JSONObject jsonOb = jsondata.getJSONObject(position);

                holder.latitle.setText(jsonOb.getString("Title")+"        Location : "+jsonOb.getString("Location"));
                holder.ladescription.setText("    \n   " + jsonOb.getString("Description"));
                holder.lapay.setText("$/Hour : " + jsonOb.getString("Payment"));
                holder.lacon.setText("Phone  : " + jsonOb.getString("Contact"));
                holder.lastrdate.setText(jsonOb.getString("Start_date"));
                holder.laendate.setText(jsonOb.getString("End_date"));
                holder.lastrtime.setText(jsonOb.getString("Start_time"));
                holder.laendtime.setText(jsonOb.getString("End_time"));
                holder.lalistdays.setText("AllDays             :  " + jsonOb.getString("Alldays") + "\n" + "Sunday             : " + jsonOb.getString("Sunday") + "\n" + "Monday           :  " + jsonOb.getString("Monday") + "\n" + "Tuesday            :  " + jsonOb.getString("Tuesday") + "\n" + "Wednesday     :  " + jsonOb.getString("Wednesday") + "\n" + "Thursday         :  " + jsonOb.getString("Thursday") + "\n" + "Friday            :  " + jsonOb.getString("Friday") + "\n" + "Saturday        :  " + jsonOb.getString("Saturday"));
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


            TextView latitle, ladescription, lastrdate, laendate, lastrtime, laendtime, lalistdays, lapay, lacon;
            Button apply11,viewappliedcand;

            public ViewHolder(View itemView) {
                super(itemView);

                latitle = (TextView) itemView.findViewById(R.id.latesttitle1);
                ladescription = (TextView) itemView.findViewById(R.id.latestdescription);
                lapay = (TextView) itemView.findViewById(R.id.lapay);
                lacon = (TextView) itemView.findViewById(R.id.lacontact);
                lastrdate = (TextView) itemView.findViewById(R.id.laststartdate);

                laendate = itemView.findViewById(R.id.laenddate);
                lastrtime = itemView.findViewById(R.id.lateststartime);
                laendtime = itemView.findViewById(R.id.latestendtime);

                lalistdays = itemView.findViewById(R.id.latestlistdays);

                apply11 = itemView.findViewById(R.id.latestjobapply);
                viewappliedcand=itemView.findViewById(R.id.viewappliedcandidates);

                apply11.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dataupload.updatedata(view, getAdapterPosition(), jsondata);

                    }
                });

            }
        }








}




