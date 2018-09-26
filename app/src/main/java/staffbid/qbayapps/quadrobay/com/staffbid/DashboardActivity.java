package staffbid.qbayapps.quadrobay.com.staffbid;

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

import staffbid.qbayapps.quadrobay.com.staffbid.FragmentActivity.JobPostedActivity;
import staffbid.qbayapps.quadrobay.com.staffbid.FragmentActivity.LatestJobActivity;
import staffbid.qbayapps.quadrobay.com.staffbid.FragmentActivity.ProfileActivity;

public class DashboardActivity extends Activity implements View.OnClickListener {


    SharedPreferences sharedPreferences;
    String uyserid;

    String usertype;
    public static final String mypreference = "MyPrefs";

    ImageView backbutton;

    TextView latestjobs,jobsposted,profileview;
    AppController appController;

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
        appController=(AppController) getApplication().getApplicationContext();
        sharedPreferences= getApplication().getSharedPreferences("MyPrefs",Context.MODE_PRIVATE);

         uyserid=sharedPreferences.getString("userid", "");

         usertype=appController.getUsertype();



         if (usertype.contains("Employee")){

             latestjobs.setText("Previously Posted Jobs");
                     jobsposted.setText("Posting a Job");
             profileview.setText("Profile");

             LatestJobActivity calen=new LatestJobActivity();
             FragmentManager fm=getFragmentManager();
             FragmentTransaction fragmentTransaction = fm.beginTransaction();
             fragmentTransaction.replace(R.id.frameLayout, calen);
             fragmentTransaction.commit();

             latestjobs.setBackgroundColor(getResources().getColor(R.color.white));
             latestjobs.setTextColor(getResources().getColor(R.color.colorAccent));

             profileview.setTextColor(getResources().getColor(R.color.white));
             jobsposted.setTextColor(getResources().getColor(R.color.white));
             // latestjobs.setBackgroundColor(getResources().getColor(R.color.light));
             jobsposted.setBackgroundColor(jobsposted.getContext().getResources().getColor(R.color.colorAccent));
             profileview.setBackgroundColor(profileview.getContext().getResources().getColor(R.color.colorAccent));


         }else {

             latestjobs.setBackgroundColor(getResources().getColor(R.color.white));
             latestjobs.setTextColor(getResources().getColor(R.color.colorAccent));

             profileview.setTextColor(getResources().getColor(R.color.white));
             jobsposted.setTextColor(getResources().getColor(R.color.white));
             // latestjobs.setBackgroundColor(getResources().getColor(R.color.light));
             jobsposted.setBackgroundColor(jobsposted.getContext().getResources().getColor(R.color.colorAccent));
             profileview.setBackgroundColor(profileview.getContext().getResources().getColor(R.color.colorAccent));

             Secondfragment toolfrag=new Secondfragment();
             FragmentManager fm2=getFragmentManager();
             FragmentTransaction fmtransaction = fm2.beginTransaction();
             fmtransaction.replace(R.id.frameLayout, toolfrag);
             fmtransaction.commit();



         }



    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.backbutton1:

                onBackPressed();

                break;

            case R.id.calender:


                latestjobs.setBackgroundColor(getResources().getColor(R.color.white));
                latestjobs.setTextColor(getResources().getColor(R.color.colorAccent));

                profileview.setTextColor(getResources().getColor(R.color.white));
                jobsposted.setTextColor(getResources().getColor(R.color.white));
                // latestjobs.setBackgroundColor(getResources().getColor(R.color.light));
                jobsposted.setBackgroundColor(jobsposted.getContext().getResources().getColor(R.color.colorAccent));
                profileview.setBackgroundColor(profileview.getContext().getResources().getColor(R.color.colorAccent));

                if (usertype.contains("Employee")){

                LatestJobActivity calen=new LatestJobActivity();
                FragmentManager fm=getFragmentManager();
                FragmentTransaction fragmentTransaction = fm.beginTransaction();
                fragmentTransaction.replace(R.id.frameLayout, calen);
                fragmentTransaction.commit();

                break;}
                else {


                    Secondfragment toolfrag=new Secondfragment();
                    FragmentManager fm2=getFragmentManager();
                    FragmentTransaction fmtransaction = fm2.beginTransaction();
                    fmtransaction.replace(R.id.frameLayout, toolfrag);
                    fmtransaction.commit();


                    break;
                }
            case R.id.birthclub:
                jobsposted.setTextColor(getResources().getColor(R.color.colorAccent));
                jobsposted.setBackgroundColor(getResources().getColor(R.color.white));

                latestjobs.setTextColor(getResources().getColor(R.color.white));
                profileview.setTextColor(getResources().getColor(R.color.white));

                latestjobs.setBackgroundColor(latestjobs.getContext().getResources().getColor(R.color.colorAccent));
                profileview.setBackgroundColor(profileview.getContext().getResources().getColor(R.color.colorAccent));


if (usertype.contains("Employee")) {
    JobPostedActivity birth = new JobPostedActivity();

    FragmentManager fmbirth = getFragmentManager();
    FragmentTransaction fmtrans = fmbirth.beginTransaction();
    fmtrans.replace(R.id.frameLayout, birth);
    fmtrans.commit();
    break;

}else {

                SecondTwofragments toolfrag = new SecondTwofragments();
                FragmentManager fm2 = getFragmentManager();
                FragmentTransaction fmtransaction = fm2.beginTransaction();
                fmtransaction.replace(R.id.frameLayout, toolfrag);
                fmtransaction.commit();
                break;

            }

            case R.id.tools:
                profileview.setTextColor(getResources().getColor(R.color.colorAccent));
                latestjobs.setTextColor(getResources().getColor(R.color.white));
                jobsposted.setTextColor(getResources().getColor(R.color.white));

                profileview.setBackgroundColor(getResources().getColor(R.color.white));
                latestjobs.setBackgroundColor(latestjobs.getContext().getResources().getColor(R.color.colorAccent));
                jobsposted.setBackgroundColor(jobsposted.getContext().getResources().getColor(R.color.colorAccent));


                    ProfileActivity toolfrag = new ProfileActivity();
                    FragmentManager fm2 = getFragmentManager();
                    FragmentTransaction fmtransaction = fm2.beginTransaction();
                    fmtransaction.replace(R.id.frameLayout, toolfrag);
                    fmtransaction.commit();

                    break;


        }
    }
}
