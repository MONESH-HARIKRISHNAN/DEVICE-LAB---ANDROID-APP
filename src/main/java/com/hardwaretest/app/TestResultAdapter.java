package com.hardwaretest.app;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

// TestResultAdapter.java - For RecyclerView in TestActivity
public class TestResultAdapter extends RecyclerView.Adapter<TestResultAdapter.ViewHolder> {
    private final List<TestResult> results;

    public TestResultAdapter() {
        this.results = new ArrayList<>();
    }

    public void updateResults(List<TestResult> newResults) {
        this.results.clear();
        this.results.addAll(newResults);
        notifyDataSetChanged();
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.test_result_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        TestResult result = results.get(position);
        
        holder.testName.setText(result.getTestName());
        holder.testResult.setText(result.getResult());
        
        // Set color based on pass/fail
        //noinspection deprecation
        int color = result.isPassed() ?
            holder.itemView.getContext().getResources().getColor(android.R.color.holo_green_dark) :
            holder.itemView.getContext().getResources().getColor(android.R.color.holo_red_dark);
            
        holder.testResult.setTextColor(color);
        
        // Set status icon
        int iconRes = result.isPassed() ? 
            android.R.drawable.ic_menu_info_details : 
            android.R.drawable.ic_dialog_alert;
        holder.statusIcon.setImageResource(iconRes);
        
        // Show details if available
        if (result.getDetails() != null && !result.getDetails().isEmpty()) {
            holder.testDetails.setVisibility(View.VISIBLE);
            holder.testDetails.setText(result.getDetails());
        } else {
            holder.testDetails.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView testName;
        TextView testResult;
        TextView testDetails;
        ImageView statusIcon;

        public ViewHolder(View itemView) {
            super(itemView);
            testName = itemView.findViewById(R.id.testName);
            testResult = itemView.findViewById(R.id.testResult);
            testDetails = itemView.findViewById(R.id.testDetails);
            statusIcon = itemView.findViewById(R.id.statusIcon);
        }
    }
}
