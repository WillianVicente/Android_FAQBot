package com.chatapp.chatapp;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class MessageAdapter <T> extends BaseAdapter {

    private List<T> messages;
    private Activity context;

    public MessageAdapter(Activity context, List <T> messages) {
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
        ViewHolder viewHolder = null;
        TextView text_message_body = null;
        TextView text_message_time = null;

        if (convertView == null){
            LayoutInflater inflater =
                    (LayoutInflater) context.
                            getSystemService(
                                    Context.
                                            LAYOUT_INFLATER_SERVICE);

            convertView =
                    inflater.inflate(R.layout.message_sent, viewGroup, false);
            text_message_body =
                    convertView.findViewById(R.id.text_message_body);
            text_message_time =
                    convertView.findViewById(R.id.text_message_time);


            viewHolder = new ViewHolder();
            viewHolder.text_message_body = text_message_body;
            viewHolder.text_message_time = text_message_time;
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        T msg = getItem(i);
        viewHolder.text_message_body.setText(((Message)msg).getMessage());
        viewHolder.text_message_time.setText("11:40");
        return convertView;
    }

    private class ViewHolder{
        TextView text_message_body;
        TextView text_message_time;
    }
}
