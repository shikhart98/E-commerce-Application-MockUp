package com.example.shikh.application;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    EditText signin_email, signin_password;
    Button signin_btn;
    TextView signin_signup, signin_forgot_pass;
    Session session;
    ProgressBar progress_bar_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        session = new Session(this);

        signin_btn = findViewById(R.id.signin_btn);
        signin_email = findViewById(R.id.signin_email);
        signin_forgot_pass = findViewById(R.id.signin_forgot_pass);
        signin_password = findViewById(R.id.signin_password);
        signin_signup = findViewById(R.id.signin_signup);
        progress_bar_login = findViewById(R.id.progress_bar_login);
        progress_bar_login.setVisibility(View.GONE);

        if(session.loggedin()){
            startActivity(new Intent(LoginActivity.this,MainActivity.class));
            finish();
        }

        signin_signup.setOnClickListener(this);
        signin_btn.setOnClickListener(this);
        signin_forgot_pass.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.signin_btn :
                userSignIn();
                break;

            case R.id.signin_signup :
                Intent i =new Intent();
                i.setClass(LoginActivity.this,SignupActivity.class);
                startActivity(i);
                break;

            case R.id.signin_forgot_pass :
                Toast.makeText(this,"Yet to be implemented :)",Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private void userSignIn() {
        final String email = signin_email.getText().toString();
        final String password = signin_password.getText().toString();

        progress_bar_login.setVisibility(View.VISIBLE);

        if(email.isEmpty()){
            signin_email.setError("Email is required");
            signin_email.requestFocus();
            progress_bar_login.setVisibility(View.GONE);
            return;
        }

        if(password.isEmpty()){
            signin_password.setError("Password is required");
            signin_password.requestFocus();
            progress_bar_login.setVisibility(View.GONE);
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            signin_email.setError("Email address is invalid");
            signin_email.requestFocus();
            progress_bar_login.setVisibility(View.GONE);
            return;
        }

        if(password.length() < 6){
            signin_password.setError("Minimum length of password should be 6");
            signin_password.requestFocus();
            progress_bar_login.setVisibility(View.GONE);
            return;
        }

//        Verifying from database here.
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.LOGIN_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response.equals("You are successfully logged in!")){
                            progress_bar_login.setVisibility(View.GONE);
                            session.setLoggedin(true);
                            finish();
                            startActivity(new Intent(LoginActivity.this,MainActivity.class));
                        }
                        Toast.makeText(LoginActivity.this,response,Toast.LENGTH_LONG).show();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if(error instanceof TimeoutError){
                    Toast.makeText(LoginActivity.this,"Timeout Error!",Toast.LENGTH_SHORT).show();
                }else if(error instanceof NoConnectionError){
                    Toast.makeText(LoginActivity.this,"No Connection Error!",Toast.LENGTH_SHORT).show();
                }else if(error instanceof AuthFailureError){
                    Toast.makeText(LoginActivity.this,"Authentication Failure Error!",Toast.LENGTH_SHORT).show();
                }else if(error instanceof NetworkError){
                    Toast.makeText(LoginActivity.this,"Network Error!",Toast.LENGTH_SHORT).show();
                }else if(error instanceof ServerError){
                    Toast.makeText(LoginActivity.this,"Server Error!",Toast.LENGTH_SHORT).show();
                }else if(error instanceof ParseError){
                    Toast.makeText(LoginActivity.this,"JSON Parse Error!",Toast.LENGTH_SHORT).show();
                }
                progress_bar_login.setVisibility(View.GONE);
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<String,String>();

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
        MySingleton.getInstance(LoginActivity.this).addToRequestQueue(stringRequest);

    }
}
