package com.techapp.james.myapplication.view.messageView;

import android.content.Context;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;

import com.techapp.james.myapplication.model.chatUIObject.Message;
import com.techapp.james.myapplication.view.messageView.MessageAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Timer;
import java.util.TimerTask;

public class MessageView extends RecyclerView {
    private ArrayList<Message> messageList = new ArrayList();
    private MessageAdapter messageAdapter = null;
    private OnKeyboardAppearListener keyboardAppearListener = null;
    private Long refreshInterval = 6000L;
    private int lastCompletelyVisibleItemPosition = -1;

    interface OnKeyboardAppearListener {
        void onKeyboardAppeared(boolean hasChanged);
    }

    public MessageView(Context context) {
        super(context);
    }

    public MessageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MessageView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        //set Recycler display data from bottom
        messageAdapter = new MessageAdapter(getContext(), messageList);
        LayoutManager layoutManager=new LinearLayoutManager(getContext());
        setHasFixedSize(true); //!!!
        layoutManager.setAutoMeasureEnabled(false);//!!! it cause itemView match_parent not work,So we need to false it.
        this.setLayoutManager(layoutManager);
        this.setAdapter(messageAdapter);
        //  ((LinearLayoutManager) getLayoutManager()).setStackFromEnd(true);


        final Handler handler = new Handler();
        Timer refreshtimer = new Timer(true);
        refreshtimer.schedule(new TimerTask() {
            public void run() {
                handler.post(new Runnable(){
                    @Override
                    public void run() {
                        messageAdapter.notifyDataSetChanged();
                    }
                });
            }
        }, 1000, refreshInterval );

        this.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastCompletelyVisibleItemPosition = ((LinearLayoutManager) recyclerView.getLayoutManager()).findLastCompletelyVisibleItemPosition();
            }
        });
        scrollToEnd();
    }

    public void setMessage(Message message) {
        addMessage(message);
        refresh();
    }

    private void addMessage(Message message) {
        messageList.add(message);
    }

    private void refresh() {
        sortMessages(messageList);
        messageAdapter.notifyDataSetChanged();
        messageAdapter.notifyItemRangeChanged(0, messageList.size() - 1);
    }

    private void remove(Message message) {
        messageList.remove(message);
        refresh();
    }

    //sort message
    private void sortMessages(ArrayList<Message> messages) {
        if (messages != null) {
            Collections.sort(messages, new Comparator<Message>() {
                @Override
                public int compare(Message first, Message second) {
                    if (first.sendTime.before(second.sendTime)) {
                        return -1;
                    } else if (first.sendTime.after(second.sendTime)) {
                        return 1;
                    } else {
                        return 0;
                    }
                }
            });
        }
    }

    public void setKeyboardAppearListener(OnKeyboardAppearListener keyboardAppearListener) {
        this.keyboardAppearListener = keyboardAppearListener;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (keyboardAppearListener != null && h < oldh) {
            keyboardAppearListener.onKeyboardAppeared(true);
        }
    }

    public boolean isCurrentLast() {
        return (messageList.size() - 1) == lastCompletelyVisibleItemPosition;
    }

    public void scrollToEnd() {
        scrollToPosition(messageList.size() - 1);
    }
}
