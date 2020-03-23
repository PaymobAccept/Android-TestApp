package com.example.test;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.androidsdk.IntentKeys;
import com.example.androidsdk.Pay;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class MainActivity extends AppCompatActivity {

   private static final String TAG ="" ;
   //  send your generated Payment key
    String paymentKey="ZXlKaGJHY2lPaUpJVXpVeE1pSXNJblI1Y0NJNklrcFhWQ0o5LmV5SmpkWEp5Wlc1amVTSTZJa1ZIVUNJc0ltVjRjQ0k2TXpZd01EQXdNREF3TURBd01EQXdNREF3TURBd01EQXdNREF3TURBd01EQXdNREF3TURBd01ERTFPRFEzTkRZMk1UTXNJblZ6WlhKZmFXUWlPak0xTlRVc0ltOXlaR1Z5WDJsa0lqbzBOelUyTXpJNUxDSmhiVzkxYm5SZlkyVnVkSE1pT2pFd01Dd2ljRzFyWDJsd0lqb2lOREV1TWpNMExqRTJOaTR5TWpZaUxDSnBiblJsWjNKaGRHbHZibDlwWkNJNk5EZzFPQ3dpYkc5amExOXZjbVJsY2w5M2FHVnVYM0JoYVdRaU9tWmhiSE5sTENKaWFXeHNhVzVuWDJSaGRHRWlPbnNpWm1seWMzUmZibUZ0WlNJNklrTnNhV1ptYjNKa0lpd2liR0Z6ZEY5dVlXMWxJam9pVG1samIyeGhjeUlzSW5OMGNtVmxkQ0k2SWtWMGFHRnVJRXhoYm1RaUxDSmlkV2xzWkdsdVp5STZJamd3TWpnaUxDSm1iRzl2Y2lJNklqUXlJaXdpWVhCaGNuUnRaVzUwSWpvaU9EQXpJaXdpWTJsMGVTSTZJa3BoYzJ0dmJITnJhV0oxY21kb0lpd2ljM1JoZEdVaU9pSlZkR0ZvSWl3aVkyOTFiblJ5ZVNJNklrTlNJaXdpWlcxaGFXd2lPaUpqYkdGMVpHVjBkR1V3T1VCbGVHRXVZMjl0SWl3aWNHaHZibVZmYm5WdFltVnlJam9pS3pnMktEZ3BPVEV6TlRJeE1EUTROeUlzSW5CdmMzUmhiRjlqYjJSbElqb2lNREU0T1RnaUxDSmxlSFJ5WVY5a1pYTmpjbWx3ZEdsdmJpSTZJazVCSW4xOS5PYUxJQThqQmxjQ0ozR3I5bzFCNGZOcVplR1puTlZJZ2l5STd5TjZnY2hJVzhCbFZaVlFyTGVmMk0zQWJKbEdMaTROYU1QNFd0WmhQTkZRSmZ2cTdVZw==";
   // send your preferred Iframe ID
    int IframeID= 7898;
    // this is your Account default Endpoint URL for receiving Response callback  .
    String Endpoint ="https://accept.paymobsolutions.com/api/acceptance/post_pay";
    // transaction status
    String success = "";
    // transaction ID
    String Id ="";
    // transaction amount
    String amount_cents="";
    // The Integration ID used
    String integration_id="";
    //
    String has_parent_transaction="";
    String txn_response_code="";
    String  acq_response_code="";



    private void StartPayActivity(){

      Intent pay_intent = new Intent(this, Pay.class);
     pay_intent.putExtra(IntentKeys.PAYMENT_KEY, paymentKey);
     pay_intent.putExtra(String.valueOf(IntentKeys.IFRAMEID), IframeID);
     pay_intent.putExtra(IntentKeys.ENDPOINT_URL, Endpoint);
     startActivityForResult(pay_intent,1);



     }
     public void Init() {

        String Succeful = "true";
        String Declined = "false";

         if (success.equals(Succeful)  ){
             new SweetAlertDialog(MainActivity.this, SweetAlertDialog.SUCCESS_TYPE)
                     .setTitleText("Congratulations!!")
                     .setContentText("Your transaction is successful!")
                     .setConfirmText("check transaction ID!")
                     .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                         @Override
                         public void onClick(SweetAlertDialog sDialog) {
                             sDialog
                                     .setTitleText("Successful!!")
                                     .setContentText("ID:" + Id)
                                     .setConfirmText("OK")
                                     .setConfirmClickListener(null)
                                     .changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
                         }
                     })
                     .show();
     } else if (success.equals(Declined)) {

             new SweetAlertDialog(MainActivity.this, SweetAlertDialog.ERROR_TYPE)
                     .setTitleText("Ooops!!")
                     .setContentText("Your transaction is declined!")
                     .setConfirmText("check transaction ID!")
                     .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                         @Override
                         public void onClick(SweetAlertDialog sDialog) {
                             sDialog
                                     .setTitleText("declined!!")
                                     .setContentText("ID:" + Id)
                                     .setConfirmText("OK")
                                     .setConfirmClickListener(null)
                                     .changeAlertType(SweetAlertDialog.ERROR_TYPE);
                         }
                     })
                     .show();
         }
    }


    @Override

   protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
       super.onActivityResult(requestCode, resultCode, data);
      if(requestCode == 1 ){
           if (resultCode == RESULT_OK){
               success = data.getStringExtra("success");
               Id = data.getStringExtra("ID");
               amount_cents = data.getStringExtra("amount_cents");
               integration_id = data.getStringExtra("integration_id");
               has_parent_transaction = data.getStringExtra("has_parent_transaction");
               txn_response_code = data.getStringExtra("txn_response_code");
               acq_response_code = data.getStringExtra("acq_response_code");
               Init();
           }
      }
   }






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button pay = findViewById(R.id.paybtn);

        pay.setOnClickListener(new View.OnClickListener() {
       @Override
       public void onClick(View view) {
        StartPayActivity();



       }
});




    }
}
