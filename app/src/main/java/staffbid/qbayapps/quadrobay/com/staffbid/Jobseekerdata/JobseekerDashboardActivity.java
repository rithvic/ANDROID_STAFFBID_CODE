package staffbid.qbayapps.quadrobay.com.staffbid.Jobseekerdata;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import staffbid.qbayapps.quadrobay.com.staffbid.Jobseekerdata.JobFragment.JAppliedJobs;
import staffbid.qbayapps.quadrobay.com.staffbid.Jobseekerdata.JobFragment.JPostedJobsFragment;
import staffbid.qbayapps.quadrobay.com.staffbid.Jobseekerdata.JobFragment.JProfileFragment;
import staffbid.qbayapps.quadrobay.com.staffbid.R;

public class JobseekerDashboardActivity extends Activity implements View.OnClickListener {


    SharedPreferences sharedPreferences;
    String uyserid;

    String usertype;
    public static final String mypreference = "MyPrefs";

    ImageView backbutton;

    TextView latestjobs,jobsposted,profileview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboardactivity);

        latestjobs=findViewById(R.id.calender);
        jobsposted=findViewById(R.id.birthclub);
        profileview=findViewById(R.id.tools);

        latestjobs.setOnClickListener(this);
        jobsposted.setOnClickListener(this);
        backbutton=findViewById(R.id.backbutton1);
        backbutton.setOnClickListener(this);
        profileview.setOnClickListener(this);
        sharedPreferences= getApplication().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);

        uyserid=sharedPreferences.getString("userid", "");

        usertype=sharedPreferences.getString("usertype", "");

        if (usertype.contains("Employee")){

            jobsposted.setText("Job Posted");
        }else {

            jobsposted.setText("Job Applied");
        }

        JPostedJobsFragment calen=new JPostedJobsFragment();
        FragmentManager fm=getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, calen);
        fragmentTransaction.commit();

        latestjobs.setBackgroundColor(Color.GREEN);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.backbutton1:

                onBackPressed();

                break;

            case R.id.calender:

                latestjobs.setBackgroundColor(Color.GREEN);
                jobsposted.setBackgroundColor(jobsposted.getContext().getResources().getColor(R.color.colorAccent));
                profileview.setBackgroundColor(profileview.getContext().getResources().getColor(R.color.colorAccent));


                JPostedJobsFragment calen=new JPostedJobsFragment();
                FragmentManager fm=getFragmentManager();
                FragmentTransaction fragmentTransaction = fm.beginTransaction();
                fragmentTransaction.replace(R.id.frameLayout, calen);
                fragmentTransaction.commit();

                break;
            case R.id.birthclub:
                jobsposted.setBackgroundColor(Color.GREEN);
                latestjobs.setBackgroundColor(latestjobs.getContext().getResources().getColor(R.color.colorAccent));
                profileview.setBackgroundColor(profileview.getContext().getResources().getColor(R.color.colorAccent));


                    JAppliedJobs birth = new JAppliedJobs();

                    FragmentManager fmbirth = getFragmentManager();
                    FragmentTransaction fmtrans = fmbirth.beginTransaction();
                    fmtrans.replace(R.id.frameLayout, birth);
                    fmtrans.commit();
                    break;

            case R.id.tools:
                profileview.setBackgroundColor(Color.GREEN);
                latestjobs.setBackgroundColor(latestjobs.getContext().getResources().getColor(R.color.colorAccent));
                jobsposted.setBackgroundColor(jobsposted.getContext().getResources().getColor(R.color.colorAccent));



                JProfileFragment toolfrag=new JProfileFragment() ;
                FragmentManager fm2=getFragmentManager();
                FragmentTransaction fmtransaction = fm2.beginTransaction();
                fmtransaction.replace(R.id.frameLayout, toolfrag);
                fmtransaction.commit();

                break;

        }
    }
}
