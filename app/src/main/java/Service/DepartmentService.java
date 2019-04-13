package Service;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Message;
import android.os.Handler;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

import utils.Tools;

public class DepartmentService implements IDepartmentService{

    //部门列表数据发送
    @SuppressLint("HandlerLeak")
    public void departmentList(Context context, final Handler handler) {
        // TODO Auto-generated method stub
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        String url = Tools.url_departmentList;
        //	final CustomHandler customHandler = new CustomHandler(context);

        /**
         * Request.Method.POST : 请求方式
         */
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                url, new Response.Listener<String>() {
            // public String verString;

            public void onResponse(String s) {
                //返回信息
                //Log.e("str", s);
                //string=s;
                //Log.e("string", string);
                Message message = new Message();
                // 1登录
                message.arg1 = 3;
                message.obj = s;
                handler.sendMessage(message);
            }
        }, new Response.ErrorListener() {

            public void onErrorResponse(VolleyError volleyError) {

            }
        }) {

            protected Map<String, String> getParams() throws AuthFailureError {
                //实例一个map对象
                Map<String, String> map = new HashMap<String, String>();

                return map;
            }
        };
        // 3 将post请求添加到队列中
        requestQueue.add(stringRequest);
    }
}
