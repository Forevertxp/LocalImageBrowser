package com.xpown.localimagebrowser;


import android.os.Bundle;
import android.widget.GridView;
import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Demo描述:
 * Android利用LruCache为GridView加载大量本地图片完整示例,防止OOM
 * <p/>
 * 更多参考:
 * http://blog.csdn.net/lfdfhl
 */
public class MainActivity extends Activity {
    private GridView mGridView;
    private ImageAdapter mGridViewAdapter;

    private ArrayList<ImageItem> dataList;
    private ImageHelper helper;
    public static List<ImageBucket> contentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        ImageHelper albumHelper = new ImageHelper();
        helper = albumHelper.getHelper();
        helper.init(getApplicationContext());

        contentList = helper.getImagesBucketList(false);
        dataList = new ArrayList<ImageItem>();

        for (int i = 0; i < contentList.size(); i++) {
            dataList.addAll(contentList.get(i).imageList);
        }
        mGridView = (GridView) findViewById(R.id.gridView);
        mGridViewAdapter = new ImageAdapter(this, 0, dataList, mGridView);
        mGridView.setAdapter(mGridViewAdapter);
    }

}
