package com.example.mr_g.smartrecyclerview;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.recyclerviewutil.docaration.adapter.HeaderFooterAdapter;
import com.example.recyclerviewutil.docaration.banner.RecyclerViewBanner;
import com.example.recyclerviewutil.docaration.docaration.GridItemDocaration;
import com.example.recyclerviewutil.docaration.holder.BaseViewHolder;
import com.example.recyclerviewutil.docaration.listener.ItemClickListener;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private RecyclerViewBanner recyclerview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
       // setSupportActionBar(toolbar);
        recyclerview = (RecyclerViewBanner) findViewById(R.id.recyclerview);

//        recyclerview.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
//        recyclerview.addItemDecoration(new LinnerItemDocaration(this, LinearLayoutManager.VERTICAL));
        ArrayList<String> strings = new ArrayList<>();
        strings.add("1");
        strings.add("2");
        strings.add("3");
        strings.add("4");
        recyclerview.setDate(strings);
//        HeaderFooterAdapter headerFooterAdapter = new HeaderFooterAdapter(strings,R.layout.comment) {
//            @Override
//            public void convert(BaseViewHolder holder, Object o) {
//               // holder.getView()
//            }
//        };
//        headerFooterAdapter.setOnItemCLickListener(new ItemClickListener() {
//            @Override
//            public void click(int position) {
//
//                Log.e(TAG, "click: "+position );
//            }
//        });
//        TextView textView = new TextView(this);
//        TextView textView1 = new TextView(this);
//        View view = LayoutInflater.from(this).inflate(R.layout.comment_header, recyclerview,false);
//        View view2 = LayoutInflater.from(this).inflate(R.layout.comment_header, recyclerview,false);
//        textView.setText("niaho");
//        textView1.setText("niaho");
//       // headerFooterAdapter.addHeader(view);
//         headerFooterAdapter.addFooter(view2);
       // recyclerview.setAdapter(headerFooterAdapter);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recyclerview.startPlay(true);
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
   class BaseAdapter extends RecyclerView.Adapter<BaseAdapter.MyViewHolder>{


       @Override
       public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
           View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.comment, parent, false);
           return new MyViewHolder(view);
       }

       @Override
       public void onBindViewHolder(MyViewHolder holder, int position) {

       }

       @Override
       public int getItemCount() {
           return 50;
       }

       class MyViewHolder extends RecyclerView.ViewHolder{

           public MyViewHolder(View itemView) {
               super(itemView);
             //  findViewById(R.id.)
           }
       }

   }

}
