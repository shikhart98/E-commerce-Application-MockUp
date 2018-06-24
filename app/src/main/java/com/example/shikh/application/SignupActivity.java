package com.example.shikh.application;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class SignupActivity extends AppCompatActivity implements View.OnClickListener {

    EditText signup_name, signup_email, signup_password;
    Button signup_btn;
    TextView signup_signin;
    ProgressBar progress_bar_signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        signup_name = findViewById(R.id.signup_name);
        signup_email = findViewById(R.id.signup_email);
        signup_password = findViewById(R.id.signup_password);
        signup_btn = findViewById(R.id.signup_btn);
        signup_signin = findViewById(R.id.signup_signin);
        progress_bar_signup = findViewById(R.id.progress_bar_signup);
        progress_bar_signup.setVisibility(View.GONE);

        signup_btn.setOnClickListener(this);
        signup_signin.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.signup_btn:
                registerUser();
                break;
            case R.id.signup_signin:
                finish();
                break;
        }
    }

    private void registerUser() {
        final String name = signup_name.getText().toString();
        final String email = signup_email.getText().toString();
        final String password = signup_password.getText().toString();
        progress_bar_signup.setVisibility(View.VISIBLE);

        if(name.isEmpty()){
            signup_name.setError("Name is required");
            signup_name.requestFocus();
            progress_bar_signup.setVisibility(View.GONE);
            return;
        }

        if(email.isEmpty()){
            signup_email.setError("Email is required");
            signup_email.requestFocus();
            progress_bar_signup.setVisibility(View.GONE);
            return;
        }

        if(password.isEmpty()){
            signup_password.setError("Password is required");
            signup_password.requestFocus();
            progress_bar_signup.setVisibility(View.GONE);
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            signup_email.setError("Email address is invalid");
            signup_email.requestFocus();
            progress_bar_signup.setVisibility(View.GONE);
            return;
        }

        if(password.length() < 6){
            signup_password.setError("Minimum length of password should be 6");
            signup_password.requestFocus();
            progress_bar_signup.setVisibility(View.GONE);
            return;
        }

//        Storing into database.
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.REGISTER_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response.contains("Please check your inbox!") || response.contains("E-mail already exists!")){
                            progress_bar_signup.setVisibility(View.GONE);
                        }
                        Toast.makeText(SignupActivity.this,response,Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                    if(error instanceof TimeoutError){
                        Toast.makeText(SignupActivity.this,"Timeout Error!",Toast.LENGTH_SHORT).show();
                    }else if(error instanceof NoConnectionError){
                        Toast.makeText(SignupActivity.this,"No Connection Error!",Toast.LENGTH_SHORT).show();
                    }else if(error instanceof AuthFailureError){
                        Toast.makeText(SignupActivity.this,"Authentication Failure Error!",Toast.LENGTH_SHORT).show();
                    }else if(error instanceof NetworkError){
                        Toast.makeText(SignupActivity.this,"Network Error!",Toast.LENGTH_SHORT).show();
                    }else if(error instanceof ServerError){
                        Toast.makeText(SignupActivity.this,"Server Error!",Toast.LENGTH_SHORT).show();
                    }else if(error instanceof ParseError){
                        Toast.makeText(SignupActivity.this,"JSON Parse Error!",Toast.LENGTH_SHORT).show();
                    }
                progress_bar_signup.setVisibility(View.GONE);
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<String,String>();

                params.put(Constants.KEY_NAME, name);
                params.put(Constants.KEY_EMAIL, email);
                params.put(Constants.KEY_PASSWORD, password);

                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String,String> headers = new HashMap<String,String>();
                headers.put("User-Agent","Application");
                return headers;
            }
        };
        MySingleton.getInstance(SignupActivity.this).addToRequestQueue(stringRequest);

    }
}
