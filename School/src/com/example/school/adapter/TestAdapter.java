package com.example.school.adapter;

import java.util.ArrayList;

import com.example.school.R;
import com.example.school.model.TestData;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class TestAdapter extends RecyclerView.Adapter<TestAdapter.TestHandler> {
	private static String LOG_TAG = "TestAdapter";
	private ArrayList<TestData> mDataset;
	private static MyClickListener myClickListener;

	public static class TestHandler extends RecyclerView.ViewHolder implements
			OnClickListener {
		TextView title;
		ImageView arrowImage;
		LinearLayout descLayout;
		boolean arrowBool = true;

		public TestHandler(View itemView) {
			super(itemView);
			title = (TextView) itemView.findViewById(R.id.test_title);
			Log.i(LOG_TAG, "Adding Listener");
			itemView.setOnClickListener(this);
		}

		@Override
		public void onClick(View v) {
			myClickListener.onItemClick(getPosition(), v);
		}
	}

	public void setOnItemClickListener(MyClickListener myClickListener) {
		this.myClickListener = myClickListener;
	}

	public TestAdapter(ArrayList<TestData> myDataset) {
		mDataset = myDataset;
	}

	@Override
	public TestHandler onCreateViewHolder(ViewGroup parent, int viewType) {
		// TODO Auto-generated method stub
		View view = LayoutInflater.from(parent.getContext()).inflate(
				R.layout.test_view_row, parent, false);
		
		TestHandler dataObjectHolder = new TestHandler(view);
		return dataObjectHolder;
	}

	@Override
	public void onBindViewHolder(TestHandler holder, int position) {
		// TODO Auto-generated method stub
		holder.title.setText(mDataset.get(position).getTitle());
	}

	public void addItem(TestData dataObj, int index) {
		mDataset.add(index, dataObj);
		notifyItemInserted(index);
	}

	public void deleteItem(int index) {
		mDataset.remove(index);
		notifyItemRemoved(index);
	}

	@Override
	public int getItemCount() {
		// TODO Auto-generated method stub
		return mDataset.size();
	}

	public interface MyClickListener {
		public void onItemClick(int position, View v);
	}
}
