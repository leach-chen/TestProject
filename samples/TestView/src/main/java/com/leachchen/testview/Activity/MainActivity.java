package com.leachchen.testview.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.leachchen.testview.R;
import com.leachchen.testview.View.ArcProgressView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ArcProgressView arcprogress_v;
    private List<Class> mClassList = new ArrayList<>();
    private List<String> mStringList = new ArrayList<>();
    private RecyclerView rv_testview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rv_testview = (RecyclerView)this.findViewById(R.id.rv_testview);

        mClassList.add(ArrowTextViewActivity.class);mStringList.add("箭头文本");
        mClassList.add(ClockViewActivity.class);mStringList.add("绘制时钟");
        mClassList.add(ScrollBackActivity.class);mStringList.add("滑动回弹");
        mClassList.add(ScrollBackListActivity.class);mStringList.add("滑动列表回弹");

        MyAdapter myAdapter = new MyAdapter();

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rv_testview.setLayoutManager(layoutManager);
        rv_testview.setAdapter(myAdapter);

/*        arcprogress_v = (ArcProgressView)this.findViewById(R.id.arcprogress_v);
        //arcprogress_v.setValue(300);
        arcprogress_v.setValue(new Random().nextInt(20), new Random().nextInt(20),
                new Random().nextInt(20));*/

    }


   /* public void scrollTo(View v){mLinearLayout.scrollTo(-60,-100);} //相对于初始位置移动
      public void scrollBy(View v){mLinearLayout.scrollBy(-60,-100);}  //相对于自身位置移动
    */


   class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHoler>
   {
       @Override
       public ViewHoler onCreateViewHolder(ViewGroup parent, int viewType) {
           View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_main_item,parent,false);
           return new ViewHoler(view);
       }

       @Override
       public void onBindViewHolder(ViewHoler holder, int position) {
           holder.tv_go.setText(mStringList.get(position));
       }

       @Override
       public int getItemCount() {
           return mClassList.size();
       }

       public class ViewHoler extends RecyclerView.ViewHolder
       {
           private TextView tv_go;
           public ViewHoler(View itemView) {
               super(itemView);
               tv_go = (TextView)itemView.findViewById(R.id.tv_go);
               tv_go.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View view) {
                       int position = getLayoutPosition();
                       Intent intent = new Intent(MainActivity.this,mClassList.get(position));
                       MainActivity.this.startActivity(intent);
                   }
               });
           }
       }
   }


}
