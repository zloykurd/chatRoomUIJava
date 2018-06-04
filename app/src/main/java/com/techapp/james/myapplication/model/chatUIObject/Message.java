package com.techapp.james.myapplication.model.chatUIObject;

import com.techapp.james.myapplication.util.DefaultTimeFormatter;
import com.techapp.james.myapplication.util.TimeFormatter;

import java.util.Calendar;

public class Message {
    public ChatUser user = null;
    public boolean userNameVisiblity = true;
    public boolean isRight = false;
    public String content = "";
    private TimeFormatter sendTimeFormatter = null;
    public Calendar sendTime = Calendar.getInstance();

    public String getTimeText() {
        return sendTimeFormatter.getFormattedTimeText(sendTime);
    }

    Message(ChatUser user, boolean isRight, boolean userNameVisiblity, Calendar sendTime, TimeFormatter sendTimeFormatter, String content) {
        this.user = user;
        this.isRight = isRight;
        this.userNameVisiblity = userNameVisiblity;
        if (sendTime != null) {
            this.sendTime = sendTime;
        }
        this.sendTimeFormatter = sendTimeFormatter;
        this.content = content;
        if (sendTimeFormatter == null) {
            this.sendTimeFormatter = new DefaultTimeFormatter();
        }
    }

    public static class MessageBuilder {
        private ChatUser user;
        private boolean isRight;
        private boolean userNameVisiblity;
        private Calendar sendTime;
        private TimeFormatter timeFormatter = null;
        private String content = "";

        public MessageBuilder setUser(ChatUser user) {
            this.user = user;
            return this;
        }

        public MessageBuilder setIsRight(boolean isRight) {
            this.isRight = isRight;
            return this;
        }

        public MessageBuilder setUserNameVisiblity(boolean userNameVisiblity) {
            this.userNameVisiblity = userNameVisiblity;
            return this;
        }

        public MessageBuilder setContent(String content) {
            this.content = content;
            return this;
        }

        public MessageBuilder setSendTime(Calendar sendTime) {
            this.sendTime = sendTime;
            return this;
        }

        public MessageBuilder setTimeFormatter(TimeFormatter timeFormatter) {
            this.timeFormatter = timeFormatter;
            return this;
        }

        public Message createMessage() {
            return new Message(user, isRight, userNameVisiblity, sendTime, timeFormatter, content);
        }
    }
}
