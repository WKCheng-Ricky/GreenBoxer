package a0611076.qrdoc;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class SheetActivity extends AppCompatActivity {

    EditText editTextItemName, editTextBrand;
    Button buttonAddItem;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sheet);

        editTextItemName = findViewById(R.id.et_item_name);
        editTextBrand = findViewById(R.id.et_brand);
        
        buttonAddItem = findViewById(R.id.btn_add_item);
        buttonAddItem.setOnClickListener(v -> {
            addItemToSheet();
        });
    }

    private void addItemToSheet() {
        final ProgressDialog loading = ProgressDialog.show(this,"Adding Item","Please wait");
        //final ProgressBar loading = ProgressBar.
        final String name = editTextItemName.getText().toString().trim();
        final String brand = editTextBrand.getText().toString().trim();

        //https://script.google.com/macros/s/AKfycbwwUikLwhBRXS3bpYc6_5_I5I3vXA8JgDrlSBEBPO5d8LTPJx6G6pFTrbhP18uU9bam/exec
        //https://script.google.com/macros/s/AKfycbzNHuiaUfKVRxa1SiqR-Alo_iQWjPnq26EGQTcH4KR8i5LI8QzWICNoyCt4pyaHTR8UEA/exec
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://script.google.com/macros/s/AKfycbxcedUULab1MbfVPaYYpyzPX4VZvP5-PFprvkpMlLGh38kXm5ewAxN3Q0d307a4H6SlQQ/exec",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        loading.dismiss();
                        Toast.makeText(SheetActivity.this,response,Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                        startActivity(intent);

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> parmas = new HashMap<>();

                //here we pass params
                parmas.put("action","addItem");
                parmas.put("itemName",name);
                parmas.put("brand",brand);

                return parmas;
            }
        };

        int socketTimeOut = 5000;// u can change this .. here it is 50 seconds

        RetryPolicy retryPolicy = new DefaultRetryPolicy(socketTimeOut, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        stringRequest.setRetryPolicy(retryPolicy);

        RequestQueue queue = Volley.newRequestQueue(this);

        queue.add(stringRequest);
    }


}