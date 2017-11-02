package com.example.om.taskdemosudnya;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;


import com.android.volley.toolbox.NetworkImageView;

import java.util.List;


public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.MyViewHolder> {

    private List<Adaptelass> AdaptelassList;
    private Context context;
    private LayoutInflater inflater;

    public MyRecyclerAdapter(Context context, List<Adaptelass> AdaptelassList) {

        this.context = context;
        this.AdaptelassList = AdaptelassList;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View rootView = inflater.inflate(R.layout.recyclerviewitems, parent, false);
        return new MyViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Adaptelass Adaptelassfeeds = AdaptelassList.get(position);

        holder.title.setText(Adaptelassfeeds.getimgName());

        holder.imageview.setImageUrl(Adaptelassfeeds.getImgURL(), AppController.getInstance(context).getImageLoader());

    }

    @Override
    public int getItemCount() {
        return AdaptelassList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView  title;
        private NetworkImageView imageview;



        public MyViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title_view);

            imageview = (NetworkImageView) itemView.findViewById(R.id.thumbnail);

        }
    }

}
