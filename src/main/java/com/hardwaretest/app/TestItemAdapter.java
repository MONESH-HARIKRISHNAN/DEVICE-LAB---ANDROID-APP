package com.hardwaretest.app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

// TestItemAdapter.java - For GridView in MainActivity
public class TestItemAdapter extends BaseAdapter {
    private final List<TestItem> testItems;
    private final LayoutInflater inflater;

    public TestItemAdapter(Context context, List<TestItem> testItems) {
        this.testItems = testItems;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return testItems.size();
    }

    @Override
    public Object getItem(int position) {
        return testItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.test_item, parent, false);
            holder = new ViewHolder();
            holder.icon = convertView.findViewById(R.id.testIcon);
            holder.name = convertView.findViewById(R.id.testName);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        TestItem item = testItems.get(position);
        holder.icon.setImageResource(item.getIconResource());
        holder.name.setText(item.getName());

        return convertView;
    }



    private static class ViewHolder {
        ImageView icon;
        TextView name;
    }
}

