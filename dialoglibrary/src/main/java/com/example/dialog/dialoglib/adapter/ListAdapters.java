package com.example.dialog.dialoglib.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.example.dialog.dialoglib.R;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class ListAdapters extends BaseAdapter {

    private Context mComtext;
    private List<String> mData;

    public ListAdapters(Context context) {
        mComtext = context;
        mData = new ArrayList<>();
    }

    public void setData(List<String> data) {
        if (null != data) {
            mData = data;
        }
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int i) {
        return mData.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (null == view) {
            viewHolder = new ViewHolder();
            view = LayoutInflater.from(mComtext).inflate(R.layout.item_list, viewGroup, false);
            viewHolder.name = view.findViewById(R.id.name);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        viewHolder.name.setText(mData.get(i));

        return view;
    }

    static class ViewHolder {
        TextView name;
    }
}
