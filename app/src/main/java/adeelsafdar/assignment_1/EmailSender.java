package adeelsafdar.assignment_1;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import javax.mail.Authenticator;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import java.util.Properties;


public class EmailSender extends Activity implements View.OnClickListener {

    Session session = null;
    ProgressDialog mProgressDialog = null;
    Context context = null;

    EditText reciep, sub, msg;
    String rec, subject, textMessage;

    private final String mSenderEmail = "muadeelsafdar@gmail.com";
    private final String mSenderPassword = "Student108";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_sender);

        context = this;
        Button login = (Button) findViewById(R.id.mEmailSendButton);
        reciep = (EditText) findViewById(R.id.mToEditText);
        sub = (EditText) findViewById(R.id.mSubjectEditText);
        msg = (EditText) findViewById(R.id.mMessageEditText);

        login.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        rec = reciep.getText().toString();
        subject = sub.getText().toString();
        textMessage = msg.getText().toString();
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        session = javax.mail.Session.getDefaultInstance(props, new Authenticator(){
            protected PasswordAuthentication getPasswordAuthentication(){
                return new PasswordAuthentication(mSenderEmail, mSenderPassword);
            }
        });
        mProgressDialog.show(context, "", "Sending Email...", true);
        RetreiveFeedTask task = new RetreiveFeedTask();
        task.execute();
    }

    class RetreiveFeedTask extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... strings) {
            try{
                MimeMessage message = new MimeMessage(session);
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
            mProgressDialog.dismiss();
            reciep.setText("");
            msg.setText("");
            sub.setText("");
            Toast.makeText(getApplicationContext(), "Message Sent", Toast.LENGTH_LONG).show();
        }
    }
}