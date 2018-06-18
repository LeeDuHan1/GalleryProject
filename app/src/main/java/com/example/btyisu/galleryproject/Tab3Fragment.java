package com.example.btyisu.galleryproject;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.btyisu.galleryproject.Volley.MyVolley;

import org.json.JSONException;
import org.json.JSONObject;

public class Tab3Fragment extends Fragment {
    private TextView textView;
    private Button button;
    private final String server_url = "http://www.withhome360.com/test/test.php";

    public Tab3Fragment(){}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final RequestQueue requestQueue = MyVolley.getInstance(getActivity()).getRequestQueue();

        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.tab3_fragment, container, false);

        textView = (TextView) rootView.findViewById(R.id.tab3_textview);
        button = (Button) rootView.findViewById(R.id.tab3_button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,
                        server_url,
                        new JSONObject(),
                        networkSuccessListener(),
                        networkErrorListener());

                requestQueue.add(jsonObjectRequest);
            }
        });
        return rootView;
    }

    private Response.Listener<JSONObject> networkSuccessListener(){
        final String TAG = "networkSuccesListenr";
        return new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                String from_server = null;
                try{
                    from_server = response.getString("test");
                }catch (JSONException e){
                    e.printStackTrace();
                    Log.e(TAG, e.getMessage());
                }
                textView.setText(from_server);
            }
        };
    }

    private Response.ErrorListener networkErrorListener(){
        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), "network error", Toast.LENGTH_SHORT).show();
            }
        };
    }
}
