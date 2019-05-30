package ui;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.example.yun.bottomdemo.R;



import java.util.ArrayList;

public class ListActivity extends Activity {

    // 声明ListView控件
    private ListView mListView;

    // 声明数组链表，其装载的类型是ListItem(封装了一个Drawable和一个String的类)
    private ArrayList<ListItem> mList;

    //当前选中的行
    private int current_index;

    /**
     * Acitivity的入口方法
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 指定Activity的布局使用activity_list.xml
        setContentView(R.layout.activity_list);


        // 通过findviewByID获取到ListView对象
        mListView = (ListView) findViewById(R.id.list);

        // 获取Resources对象
        Resources res = this.getResources();

        mList = new ArrayList<ListActivity.ListItem>();

        // 初始化data，装载八组数据到数组链表mList中
        ListItem item = new ListItem();
        item.setImage(res.getDrawable(R.drawable.img1));
        item.setTitle("广西大学石碑");
        mList.add(item);

        item = new ListItem();
        item.setImage(res.getDrawable(R.drawable.img2));
        item.setTitle("马君武雕像");
        mList.add(item);

        item = new ListItem();
        item.setImage(res.getDrawable(R.drawable.img3));
        item.setTitle("广西大学图书馆");
        mList.add(item);

        item = new ListItem();
        item.setImage(res.getDrawable(R.drawable.img4));
        item.setTitle("汇学堂");
        mList.add(item);

        item = new ListItem();
        item.setImage(res.getDrawable(R.drawable.img5));
        item.setTitle("西体育馆");
        mList.add(item);

        item = new ListItem();
        item.setImage(res.getDrawable(R.drawable.img6));
        item.setTitle("广西大学正门");
        mList.add(item);

        item = new ListItem();
        item.setImage(res.getDrawable(R.drawable.img7));
        item.setTitle("大礼堂");
        mList.add(item);

        item = new ListItem();
        item.setImage(res.getDrawable(R.drawable.img8));
        item.setTitle("综合楼");
        mList.add(item);

        // 获取MainListAdapter对象
        MainListViewAdapter adapter = new MainListViewAdapter();

        // 将MainListAdapter对象传递给ListView视图
        mListView.setAdapter(adapter);


        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapter, View view, int position,
                                    long id) {
                current_index = position;

                System.out.println(position);
                switch (position) {
                    case (0):
                        Intent intent = new Intent(ListActivity.this,ui.DMSBActivity.class);
                        startActivity(intent);
                        break;
                    case (1):
                        Intent intent1 = new Intent(ListActivity.this,ui.MJWActivity.class);
                        startActivity(intent1);
                        break;
//                    case (2):
//                        Intent intent2 = new Intent(MainActivity.this, com.vuforia.VuforiaSamples.ui.ActivityList.ActivityLauncher.class);
//                        startActivity(intent2);
//                        break;
                }

            }

        });
    }




    public void goMain(View view){

        Intent intent = new Intent(ListActivity.this,MainActivity.class);
        startActivity(intent);

    }



//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.activity_main, menu);
//        return true;
//    }

    /**
     * 定义ListView适配器MainListViewAdapter
     */
    class MainListViewAdapter extends BaseAdapter {

        /**
         * 返回item的个数
         */
        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return mList.size();
        }

        /**
         * 返回item的内容
         */
        @Override
        public Object getItem(int position) {
            // TODO Auto-generated method stub
            return mList.get(position);
        }

        /**
         * 返回item的id
         */
        @Override
        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return position;
        }

        /**
         * 返回item的视图
         */
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ListItemView listItemView;

            // 初始化item view
            if (convertView == null) {
                // 通过LayoutInflater将xml中定义的视图实例化到一个View中
                convertView = LayoutInflater.from(ListActivity.this).inflate(
                        R.layout.items, null);

//                if (current_index == position){
//                    convertView.setBackgroundColor(getResources().getColor(R.color.light_gray));
//                }
//                else {
//                    convertView.setBackgroundColor(getResources().getColor(R.color.white));
//                }


                // 实例化一个封装类ListItemView，并实例化它的两个域
                listItemView = new ListItemView();
                listItemView.imageView = (ImageView) convertView
                        .findViewById(R.id.image);
                listItemView.textView = (TextView) convertView
                        .findViewById(R.id.title);

                // 将ListItemView对象传递给convertView
                convertView.setTag(listItemView);
            }
            else {
                // 从converView中获取ListItemView对象
                listItemView = (ListItemView) convertView.getTag();
            }

            // 获取到mList中指定索引位置的资源
            Drawable img = mList.get(position).getImage();
            String title = mList.get(position).getTitle();

            // 将资源传递给ListItemView的两个域对象
            listItemView.imageView.setImageDrawable(img);
            listItemView.textView.setText(title);

            // 返回convertView对象
            return convertView;
        }

    }

    /**
     * 封装两个视图组件的类
     */
    class ListItemView {
        ImageView imageView;
        TextView textView;
    }

    /**
     * 封装了两个资源的类
     */
    class ListItem {
        private Drawable image;
        private String title;

        public Drawable getImage() {
            return image;
        }

        public void setImage(Drawable image) {
            this.image = image;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

    }
}

