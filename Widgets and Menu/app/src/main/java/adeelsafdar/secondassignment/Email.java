package adeelsafdar.secondassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Email extends Activity implements View.OnClickListener{
        Session mSession = null;
        ProgressDialog mProgressDialog;
        Context mContext = null;

        EditText mRecipient, mSubject, mMessage;
        String rec, subject, textMessage;

        private final String mSenderEmail = "muadeelsafdar@gmail.com";
        private final String mSenderPassword = "Student108";

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_email);

            mContext = this;
            Button login = (Button) findViewById(R.id.mEmailSendButton);
            mRecipient = (EditText) findViewById(R.id.mToEditText);


            //Set Sender Email from Intent
            Intent intent = getIntent();
            mRecipient.setText(intent.getStringExtra("userEmail"));
            mSubject = (EditText) findViewById(R.id.mSubjectEditText);
            mMessage = (EditText) findViewById(R.id.mMessageEditText);

            login.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            rec = mRecipient.getText().toString();
            subject = mSubject.getText().toString();
            textMessage = mMessage.getText().toString();
            if(mRecipient.getText().toString().length() == 0){
                mRecipient.setError("Reciptient Email is Required!");
                Toast.makeText(mContext, "Email not Sent", Toast.LENGTH_SHORT).show();
                return;
            }
            else if(mSubject.getText().toString().length() == 0){
                mSubject.setError("Subject is Required!");
                Toast.makeText(mContext, "Email not Sent", Toast.LENGTH_SHORT).show();
                return;
            }
            else if(mMessage.getText().toString().length() == 0){
                mMessage.setError("Message is Required!");
                Toast.makeText(mContext, "Email not Sent", Toast.LENGTH_SHORT).show();
                return;
            }
            Properties props = new Properties();
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.socketFactory.port", "465");
            props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.port", "465");

            mSession = javax.mail.Session.getDefaultInstance(props, new Authenticator(){
                protected PasswordAuthentication getPasswordAuthentication(){
                    return new PasswordAuthentication(mSenderEmail, mSenderPassword);
                }
            });

//        mProgressDialog.show(mContext, "", "Sending Email...", true);
            mProgressDialog = new ProgressDialog(mContext);
            mProgressDialog.setMessage("Sending..");
            mProgressDialog.show();
            RetreiveFeedTask task = new RetreiveFeedTask();
            task.execute();
        }

class RetreiveFeedTask extends AsyncTask<String, Void, String> {

    @Override
    protected String doInBackground(String... strings) {
        try{
            MimeMessage message = new MimeMessage(mSession);
            message.setFrom(new InternetAddress(mSenderEmail));
            message.setRecipients(MimeMessage.RecipientType.TO, InternetAddress.parse(rec));
            message.setSubject(subject);
            message.setContent(textMessage, "text/html; charset=utf-8");
            Transport.send(message);

        }catch (MessagingException e){
            e.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(String aVoid) {

        if(mProgressDialog.isShowing()){
            mProgressDialog.dismiss();
        } else if(mProgressDialog != null){
            mProgressDialog.dismiss();
        }
        mRecipient.setText("");
        mMessage.setText("");
        mSubject.setText("");
        Toast.makeText(getApplicationContext(), "Message Sent", Toast.LENGTH_LONG).show();
    }
}
}
