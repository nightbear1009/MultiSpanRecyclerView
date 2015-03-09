package com.triplelayoutrecyclerview;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {
    private MultiSpanRecyclerView mRecyclerview;

    public static class LineViewHolder extends RecyclerView.ViewHolder {
        private final TextView textView;
        private CardView cardView;

        public LineViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView.findViewById(R.id.cardview);
            textView = (TextView) itemView.findViewById(R.id.info_text);
        }

        public void bindview(int pos){
            textView.setText("pos "+pos);
        }
    }

    public static class MyAdapter extends RecyclerView.Adapter<LineViewHolder> {
        private int mViewType;
        public static final int BIG_VIEWTYPE = 0;
        public static final int LINE_VIEWTYPE = 1;
        public static final int GRID_VIEWTYPE = 2;
        public void setViewType(int viewType){
            mViewType = viewType;
        }

        @Override
        public int getItemViewType(int position) {

            return mViewType;
        }

        @Override
        public LineViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            if(mViewType == BIG_VIEWTYPE) {
                LayoutInflater inflater = LayoutInflater.from(parent.getContext());
                View view = inflater.inflate(R.layout.list_big_item, parent, false);
                return new LineViewHolder(view);
            }else if(mViewType == LINE_VIEWTYPE){
                LayoutInflater inflater = LayoutInflater.from(parent.getContext());
                View view = inflater.inflate(R.layout.list_line_item, parent, false);
                return new LineViewHolder(view);
            }else{
                LayoutInflater inflater = LayoutInflater.from(parent.getContext());
                View view = inflater.inflate(R.layout.list_grid_item, parent, false);
                return new LineViewHolder(view);
            }
        }

        @Override
        public void onBindViewHolder(LineViewHolder holder, int position) {
            holder.bindview(position);

        }

        @Override
        public int getItemCount() {
            return 200;
        }
    }

    private GridLayoutManager mManager;
    private MyAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        mRecyclerview = (MultiSpanRecyclerView) findViewById(R.id.recyclerview);
        mManager = new GridLayoutManager(this,2);
        mRecyclerview.setLayoutManager(mManager);
        mRecyclerview.setViewSpan(1);
        mAdapter = new MyAdapter();
        mRecyclerview.setAdapter(mAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_big) {
            mRecyclerview.setViewSpan(1);
            mAdapter.setViewType(MyAdapter.BIG_VIEWTYPE);
            mAdapter.notifyDataSetChanged();
            return true;
        }else if(id == R.id.action_grid){
            mRecyclerview.setViewSpan(2);
            mAdapter.setViewType(MyAdapter.GRID_VIEWTYPE);
            mAdapter.notifyDataSetChanged();
            return true;
        }else if(id == R.id.action_line){
            mRecyclerview.setViewSpan(1);
            mAdapter.setViewType(MyAdapter.LINE_VIEWTYPE);
            mAdapter.notifyDataSetChanged();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
