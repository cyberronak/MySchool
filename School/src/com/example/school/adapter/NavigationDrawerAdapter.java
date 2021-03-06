package com.example.school.adapter;
import android.content.Context;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Typeface;
import android.graphics.PorterDuff.Mode;
import android.view.LayoutInflater;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

import com.example.school.R;
import com.example.school.R.layout;
import com.example.school.model.NavDrawerItem;


public class NavigationDrawerAdapter extends RecyclerView.Adapter<NavigationDrawerAdapter.MyViewHolder> {
    List<NavDrawerItem> data = Collections.emptyList();
    private LayoutInflater inflater;
    private Context context;

	private Typeface custom_font;
	
    public NavigationDrawerAdapter(Context context, List<NavDrawerItem> data) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.data = data;

		// load custom fonts
		custom_font = Typeface.createFromAsset(context.getAssets(), "fonts/American_Typewriter_Regular.ttf");
    }

    public void delete(int position) {
        data.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.nav_drawer_row, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        NavDrawerItem current = data.get(position);
        if(position == 3 || position == 4 || position == 5 || position == 6)
        {
            holder.icon.setImageDrawable(current.getIcon());
            holder.title.setText(current.getTitle());
            holder.title.setTypeface(custom_font);
            holder.layout.setPadding(70, 0, 0, 0);
        }
        else
        {
            holder.icon.setImageDrawable(current.getIcon());
            holder.title.setText(current.getTitle());
            holder.title.setTypeface(custom_font);        	
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView icon;
        TextView title;
        RelativeLayout layout;

        public MyViewHolder(View itemView) {
            super(itemView);
            icon =(ImageView) itemView.findViewById(R.id.ivIcon);
            title = (TextView) itemView.findViewById(R.id.tvTitle);
            layout = (RelativeLayout) itemView.findViewById(R.id.rlDrawer);
        }
    }
}
