package staffbid.qbayapps.quadrobay.com.staffbid.Jobseekerdata.JobFragment;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import staffbid.qbayapps.quadrobay.com.staffbid.AppController;
import staffbid.qbayapps.quadrobay.com.staffbid.R;

public class JAppliedJobs extends Fragment implements  View.OnClickListener{

    View view;

    AppController appController;
    String usertype;
    String userid;

    RequestQueue requestQueuep;
    EditText usertitle,userdescription,userctc,userlocation,usercontact;
    Button updatedata1;


    String setitle,sedescription,sectc,selocation,secontact;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        view = inflater.inflate(R.layout.jobspostedframent, container, false);

        requestQueuep= Volley.newRequestQueue(getActivity());
        appController=(AppController)getActivity().getApplication();
        usertype=appController.getUsertype();
        userid=appController.getUserid();


        usertitle=view.findViewById(R.id.userti);
        userdescription=view.findViewById(R.id.udescp);
        userctc=view.findViewById(R.id.uctc);
        userlocation=view.findViewById(R.id.ulocation);
        usercontact=view.findViewById(R.id.userti);

        updatedata1=view.findViewById(R.id.uapplyjob);


        updatedata1.setOnClickListener(this);


        requestQueuep= Volley.newRequestQueue(getActivity());




        return view;





    }

    @Override
    public void onClick(View v) {


        setitle=usertitle.getText().toString();
        sedescription=userdescription.getText().toString();
        sectc=userctc.getText().toString();
        selocation=userlocation.getText().toString();
        secontact=usercontact.getText().toString();



        if (setitle.length()==0){
            usertitle.setError("Enter Title");
        }else if (sedescription.length()==0){

            userdescription.setError("Enter the description");

        }else if (sectc.length()==0){

            userctc.setError("Enter ctc");

        }else if (selocation.length()==0){
            userlocation.setError("Enter location");


        }else if (secontact.length()==0){
            usercontact.setError("Enter contact detials");

        }else {
            updateServerData();



        }



    }
    void updateServerData() {


        String url="http://quadrobay.co.in/staffbid_api/public/api/jobupload/job-upload?token=24462a501a5aa6ca239d1ade0fc90c9c";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, getPostResponseListener(), getPostErrorListener()) {
            protected Map<String, String> getParams() throws com.android.volley.AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Title",setitle);
                params.put("Location", selocation);
                params.put("EOC",sectc);
                params.put("Contact",secontact);
                params.put("Description",sedescription);
                params.put("User_id",userid);

                return params;


            }


        };


        requestQueuep.add(stringRequest);
    }

    private Response.Listener<String> getPostResponseListener() {
        return new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


                alertBox("success","data uploaded successfull");


            }
        };
    }
    private Response.ErrorListener getPostErrorListener() {
        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                alertBox("Registration Error","Please check input data entered");
            }
        };
    }
    void alertBox(String title,String msg){



        usertitle.setText("");
        userdescription.setText("");
        userctc.setText("");
        userlocation.setText("");
        usercontact.setText("");

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());

        alertDialog.setTitle(title);

        alertDialog.setMessage(msg);

        alertDialog.setNegativeButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {




            }
        });


        alertDialog.show();
    }




    class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {



        Context context;

        JSONObject jsondata;


        MyAdapter(Context context,JSONObject json) {


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


//            try {
//                holder.title11.setText((CharSequence) jsondata.getJSONObject("Jobseeker_profile").getJSONArray("Title"));
////                holder.description11.setText(jsondata.getString("Description"));
////                holder.ctc11.setText(jsondata.getString("EOC"));
////                holder.location11.setText(jsondata.getString("Location"));
//
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }






        }

        @Override
        public int getItemCount() {

            return jsondata.length();
        }


        class ViewHolder extends RecyclerView.ViewHolder {


            TextView title11,description11,ctc11,location11;
            Button apply11;

            public ViewHolder(View itemView) {
                super(itemView);


//                title11 = (TextView) itemView.findViewById(R.id.title1);
//                description11 = (TextView) itemView.findViewById(R.id.description);
//                ctc11 = (TextView) itemView.findViewById(R.id.ctc);
//                location11 = (TextView) itemView.findViewById(R.id.location);
//                apply11 = (Button) itemView.findViewById(R.id.applyjob);


            }
        }
    }




}
