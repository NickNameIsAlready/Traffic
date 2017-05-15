package traffic.traffic1.util;

import android.util.Log;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

/**
 * Created by asdf on 2017/5/15.
 */

public class GsonRequest<T> extends JsonRequest<T> {

        private Gson mGson;

        private Class<T> mClass;

        public GsonRequest(int method, String url, JSONObject jsonRequest, Class<T> clazz, Response.Listener<T> listener,
                           Response.ErrorListener errorListener) {
            super(method, url, (jsonRequest == null) ? null : jsonRequest.toString(), listener,
                    errorListener);
            Log.d("url",url);
            Log.d("json",jsonRequest.toString());
            mGson = new Gson();
            mClass = clazz;
        }
        public GsonRequest(String url,JSONObject jsonRequest, Class<T> clazz, Response.Listener<T> listener,
                           Response.ErrorListener errorListener) {
            this(Method.POST, url,jsonRequest, clazz, listener, errorListener);
        }
        @Override
        protected Response<T> parseNetworkResponse(NetworkResponse response) {
            try {
                String jsonString = new String(response.data,
                        HttpHeaderParser.parseCharset(response.headers));
                Log.d("jsonString",jsonString);
                return Response.success(mGson.fromJson(jsonString, mClass),
                        HttpHeaderParser.parseCacheHeaders(response));
            } catch (UnsupportedEncodingException e) {
                return Response.error(new ParseError(e));
            }
        }
}
