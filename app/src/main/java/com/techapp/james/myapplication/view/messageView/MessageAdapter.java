package com.techapp.james.myapplication.view.messageView;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.techapp.james.myapplication.R;
import com.techapp.james.myapplication.model.chatUIObject.Message;

import java.util.ArrayList;

public class MessageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context = null;
    private ArrayList<Message> data = null;
    private int LEFT = 0, RIGHT = 1;

    MessageAdapter(Context context, ArrayList<Message> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public int getItemViewType(int position) {
        Message msg = data.get(position);
        if (msg.isRight) {
            return RIGHT;
        }
        return LEFT;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = null;
        if (viewType == RIGHT) {
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.message_view_item_right, parent, false);
            //v.getLayoutParams().height = RecyclerView.LayoutParams.WRAP_CONTENT;
            //v.getLayoutParams().width = parent.getWidth();
            return new ItemViewHolder(v);
        } else {
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.message_view_item_left, parent, false);

            //v.getLayoutParams().height = RecyclerView.LayoutParams.WRAP_CONTENT;
            //v.getLayoutParams().width = parent.getWidth();
            return new ItemViewHolder(v);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
        Message msg = data.get(position);
        itemViewHolder.container.removeAllViews();
        if (msg.userNameVisiblity) {
            //  Log.d("pass", "inflate");
            View userNameView = LayoutInflater.from(context)
                    .inflate(R.layout.message_view_user_name_text,
                            itemViewHolder.container, true);
            itemViewHolder.userTextView = userNameView
                    .findViewById(R.id.userNameTextView);
            itemViewHolder.userTextView.setText(msg.user.getName());
        }
        View msgView = LayoutInflater.from(context).inflate(R.layout
                .message_view_message, itemViewHolder.container, true);
        itemViewHolder.msgTextView = msgView
                .findViewById(R.id.messageTextView);
        itemViewHolder.msgTextView.setText(msg.content);
        itemViewHolder.timeTextView.setText(msg.getTimeText());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {
        LinearLayout container = null;
        TextView timeTextView = null;
        TextView userTextView = null;
        TextView msgTextView = null;

        ItemViewHolder(View itemView) {
            super(itemView);
            container = itemView.findViewById(R.id.container);
            timeTextView = itemView.findViewById(R.id.timeTextView);
        }
    }
}
