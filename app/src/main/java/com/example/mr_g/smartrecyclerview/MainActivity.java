package com.example.mr_g.smartrecyclerview;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.util.DiffUtil;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.recyclerviewutil.docaration.adapter.BaseAdapter;
import com.example.recyclerviewutil.docaration.adapter.HeaderFooterAdapter;
import com.example.recyclerviewutil.docaration.banner.BannerAdapter;
import com.example.recyclerviewutil.docaration.banner.RecyclerViewBanner;
import com.example.recyclerviewutil.docaration.docaration.GridItemDocaration;
import com.example.recyclerviewutil.docaration.holder.BaseViewHolder;
import com.example.recyclerviewutil.docaration.listener.ItemClickListener;
import com.example.recyclerviewutil.docaration.util.BaseDiffCallBack;

import java.util.ArrayList;
import java.util.StringTokenizer;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private RecyclerView recyclerview;
    private HeaderFooterAdapter headerFooterAdapter;
    private BaseAdapter baseAdapter;
    private ArrayList<String> newstrings;
    private ArrayList<String> strings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
       // setSupportActionBar(toolbar);
       // recyclerview = (RecyclerViewBanner) findViewById(R.id.recyclerview);
        recyclerview = (RecyclerView) findViewById(R.id.recyclerview);
       // recyclerview.loadUrl();
//        recyclerview.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
//        recyclerview.addItemDecoration(new LinnerItemDocaration(this, LinearLayoutManager.VERTICAL));
        strings = new ArrayList<>();
        strings.add("1");
        strings.add("2");
        strings.add("3");
        strings.add("4");

//        BannerAdapter<String> stringBannerAdapter = new BannerAdapter<String>(strings) {
//            @Override
//            public void setImg(ImageView banner_img, String url) {
//                banner_img.setBackgroundColor(Color.RED);
//            }
//        };
//        recyclerview.setDate(stringBannerAdapter);

        recyclerview.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
         baseAdapter = new BaseAdapter(strings, R.layout.comment) {
             @Override
             public void convert(BaseViewHolder holder, int position) {


             }
         };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.Callback() {
            @Override
            public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                // 拖拽的标记，这里允许上下左右四个方向
                int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN | ItemTouchHelper.LEFT |
                        ItemTouchHelper.RIGHT;
                // 滑动的标记，这里允许左右滑动
                int swipeFlags = ItemTouchHelper.START | ItemTouchHelper.END |ItemTouchHelper.DOWN |ItemTouchHelper.UP;
                return makeMovementFlags(dragFlags, swipeFlags);
            }

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                // 移动时更改列表中对应的位置并返回true
//                Collections.swap(newsList, viewHolder.getAdapterPosition(), target
//                        .getAdapterPosition());
                return true;
            }
            /*
                    当onMove返回true时调用
                 */
            @Override
            public void onMoved(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, int
                    fromPos, RecyclerView.ViewHolder target, int toPos, int x, int y) {
                super.onMoved(recyclerView, viewHolder, fromPos, target, toPos, x, y);
                // 移动完成后刷新列表
                baseAdapter.notifyItemMoved(viewHolder.getAdapterPosition(), target
                        .getAdapterPosition());
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                // 将数据集中的数据移除
              //  newsList.remove(viewHolder.getAdapterPosition());
                // 刷新列表
                baseAdapter.notifyItemRemoved(viewHolder.getAdapterPosition());
            }
        });
        itemTouchHelper.attachToRecyclerView(recyclerview);
        recyclerview.setAdapter(baseAdapter);
        //recyclerview
        // holder.getView()
//        headerFooterAdapter = new HeaderFooterAdapter(strings, R.layout.comment) {
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
               // recyclerview.startPlay(true);
                ArrayList<String> strings_new = new ArrayList<>();
                strings_new.addAll(strings);

                strings_new.add(2,"5");
                DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new BaseDiffCallBack(MainActivity.this.strings, strings_new), true);
                //利用Di
                // ffUtil.DiffResult对象的dispatchUpdatesTo（）方法，传入RecyclerView的Adapter
                //别忘了将新数据给Adapter
//        list = newlist;
             //   MainActivity.this.strings =newstrings;
                strings.clear();
                strings.addAll(strings_new);
                baseAdapter.setDates(strings_new);
       diffResult.dispatchUpdatesTo(baseAdapter);
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
//   class BaseAdapter extends RecyclerView.Adapter<BaseAdapter.MyViewHolder>{
//
//
//       @Override
//       public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//           View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.comment, parent, false);
//           return new MyViewHolder(view);
//       }
//
//       @Override
//       public void onBindViewHolder(MyViewHolder holder, int position) {
//
//       }
//
//       @Override
//       public int getItemCount() {
//           return 50;
//       }
//
//       class MyViewHolder extends RecyclerView.ViewHolder{
//
//           public MyViewHolder(View itemView) {
//               super(itemView);
//             //  findViewById(R.id.)
//           }
//       }
//
//   }

}
