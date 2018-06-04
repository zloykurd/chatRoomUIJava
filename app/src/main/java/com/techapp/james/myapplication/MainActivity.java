package com.techapp.james.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.techapp.james.myapplication.model.chatUIObject.Message;
import com.techapp.james.myapplication.model.chatUIObject.MyUser;
import com.techapp.james.myapplication.view.messageView.MessageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MessageView list = (MessageView) findViewById(R.id.messageView);
        MyUser user = new MyUser();
        user.id = "1234";
        user.name = "James";
        Message msg = new Message.MessageBuilder().setUser(user)
                .setContent("hello")
                .setIsRight(true)
                .setUserNameVisiblity(true)
                .createMessage();
        Log.d("Message", msg.content + "  " + msg.userNameVisiblity);
        list.setMessage(msg);
        msg = new Message.MessageBuilder().setUser(user)
                .setContent("hello")
                .setIsRight(false)
                .setUserNameVisiblity(true)
                .createMessage();

        for (int i = 0; i < 100; i++) {
            list.setMessage(msg);
        }
    }
}
