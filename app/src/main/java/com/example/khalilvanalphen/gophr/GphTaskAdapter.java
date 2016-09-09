package com.example.khalilvanalphen.gophr;

import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class GphTaskAdapter extends ArrayAdapter<GphTask> {

    Context context;
    int layoutResourceId;
    ArrayList<GphTask> data;

    private List<TaskHolder> lstHolders;
    private Handler mHandler = new Handler();
    private LayoutInflater lf;

    private Runnable updateRemainingTimeRunnable = new Runnable() {
        @Override
        public void run() {
            synchronized (lstHolders) {
                long currentTime = System.currentTimeMillis();
                for (TaskHolder holder : lstHolders) {
                    holder.updateTimeRemaining(currentTime);
                }
            }
        }
    };

    private void startUpdateTimer() {
        Timer tmr = new Timer();
        tmr.schedule(new TimerTask() {
            @Override
            public void run() {
                mHandler.post(updateRemainingTimeRunnable);
            }
        }, 1000, 1000);
    }

    public GphTaskAdapter(Context context, int layoutResourceId, ArrayList<GphTask> data) {
        super(context, 0, data);
        lf = LayoutInflater.from(context);
        lstHolders = new ArrayList<>();
        startUpdateTimer();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TaskHolder holder = null;
        if (convertView == null) {
            holder = new TaskHolder();
            convertView = lf.inflate(R.layout.task_list_row_item, parent, false);
            holder.txtTitle = (TextView) convertView.findViewById(R.id.txtTitle);
            holder.txtTime = (TextView) convertView.findViewById(R.id.txtTime);
            convertView.setTag(holder);
            synchronized (lstHolders) {
                lstHolders.add(holder);
            }
        } else {
            holder = (TaskHolder) convertView.getTag();
        }

        holder.setData(getItem(position));

        return convertView;
    }

    static class TaskHolder {
        ImageView imgIcon;
        TextView txtTitle;
        TextView txtTime;
        GphTask task;

        public void setData(GphTask item) {
            task = item;
            txtTitle.setText(item.getTitle());
            updateTimeRemaining(System.currentTimeMillis());
        }

        public void updateTimeRemaining(long currentTime) {
            long timeDiff = (task.getSpawnTime() + 1000 * 60 * 60 * 24) - currentTime;
            if (timeDiff > 0) {
                int seconds = (int) (timeDiff / 1000) % 60;
                int minutes = (int) ((timeDiff / (1000 * 60)) % 60);
                int hours = (int) ((timeDiff / (1000 * 60 * 60)) % 24);
                txtTime.setText(hours + ":" + minutes + ":" + seconds + ":");
            } else {
                txtTime.setText("Expired!");
            }
        }
    }
}
