package com.example.vindh.myapplication;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import static android.R.id.list;



public class contactadapter extends ArrayAdapter {
    List list=new ArrayList<>();

    public contactadapter(@NonNull Context context, @LayoutRes int resource) {
        super(context, resource);
    }


    public void add(contacts obj){
      super.add(obj);
        list.add(obj);
    }

    @Override
    public int getCount(){
        return list.size();
    }

    @Nullable
    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View row;
        row=convertView;
        contactholder ch;
        if(row==null){
            LayoutInflater lf= (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row=lf.inflate(R.layout.row_layout,parent,false);
            ch=new contactholder();
            ch.tvp=(TextView)row.findViewById(R.id.p);

            ch.tvh=(TextView)row.findViewById(R.id.h);
            ch.tvtemp=(TextView)row.findViewById(R.id.temp);
            row.setTag(ch);
        }else{
            ch=(contactholder)row.getTag();
        }

        contacts c=(contacts)this.getItem(position);
        ch.tvp.setText(c.getPressure());

        ch.tvtemp.setText(c.gettemp());
        String h=c.gethumid();
        h=h.substring(0,h.length()-2);
        ch.tvh.setText(h);
        return row;
    }

    static class contactholder{
        TextView tvp,tvtemp,tvh;
    }
}
