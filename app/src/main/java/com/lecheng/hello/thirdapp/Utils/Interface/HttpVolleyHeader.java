package com.lecheng.hello.thirdapp.Utils.Interface;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.Map;

/**
 * Created by Lee on 2016/8/11.
 */
public class HttpVolleyHeader extends StringRequest {
    HeaderInterface headerInterface;

    public HttpVolleyHeader(int method, String url, HeaderInterface headerInterface, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        super(method, url, listener, errorListener);
        this.headerInterface = headerInterface;
    }

    public HttpVolleyHeader(String url, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        super(url, listener, errorListener);
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return headerInterface.addHeader();
    }

    public interface HeaderInterface {
        public Map<String ,String> addHeader();
    }
}
