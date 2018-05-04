package com.chatapp.chatapp;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class MessageListAdapter<T> extends BaseAdapter {

    private List<T> messages;
    private Activity context;

    public MessageListAdapter(Activity context, List <T> messages) {
        super();
        this.messages = messages;
        this.context = context;
    }


    @Override
    public int getCount() {
        return this.messages.size();
    }

    @Override
    public T getItem(int i) {
        return this.messages.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        View view;
        T msg = getItem(i);

        if (convertView == null) {
            if(((Message) msg).getActor().equals("Usuario")){
                view = LayoutInflater.from(context).inflate(R.layout.message_sent, viewGroup, false);
                viewHolder = new ViewHolder(view);
                view.setTag(viewHolder);

            }else{
                view = LayoutInflater.from(context).inflate(R.layout.message_receive, viewGroup, false);
                viewHolder = new ViewHolder(view);
                view.setTag(viewHolder);
            }
        } else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.body.setText(((Message) msg).getMessage());
        viewHolder.time.setText(((Message) msg).getTime());

        return view;
    }

    private class ViewHolder{
        final TextView body;
        final TextView time;

        public ViewHolder(View view) {
            body = view.findViewById(R.id.text_message_body);
            time = view.findViewById(R.id.text_message_time);
        }
    }
}
