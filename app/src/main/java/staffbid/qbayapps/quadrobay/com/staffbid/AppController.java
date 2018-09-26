package staffbid.qbayapps.quadrobay.com.staffbid;

import android.app.Application;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.ArrayList;

public class AppController extends Application {

    private RequestQueue mRequestQueue;

    public static final String TAG=AppController.class.getSimpleName();

    private static AppController mInstance;

    JSONObject userDetail;

    public ArrayList<String> getLocationinfo() {
        return locationinfo;
    }

    public void setLocationinfo(ArrayList<String> locationinfo) {
        this.locationinfo = locationinfo;
    }

    ArrayList<String> locationinfo;
    String userid;
    String usertype;
    String usergmai;
    String username;

    public JSONObject getUserDetail() {
        return userDetail;
    }

    public void setUserDetail(JSONObject userDetail) {
        this.userDetail = userDetail;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserpass() {
        return userpass;
    }

    public void setUserpass(String userpass) {
        this.userpass = userpass;
    }

    String userpass;
    public String getUsergmai() {
        return usergmai;
    }

    public void setUsergmai(String usergmai) {
        this.usergmai = usergmai;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUsertype() {
        return usertype;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }

    public void onCreate() {

        super.onCreate();
        mInstance=this;



    }

    public static synchronized AppController getInstance(){
        return mInstance;
    }

    public RequestQueue getRequestQueue(){


        if (mRequestQueue==null){
            mRequestQueue= Volley.newRequestQueue(getApplicationContext());

        }
        return mRequestQueue;


    }

    public <T> void addToRequestQueue(Request<T> req, String tag){
        req.setTag(TextUtils.isEmpty(tag)? TAG:tag);
        getRequestQueue().add(req);
    }
}
