package com.stanny.demo.ui.widget;

import android.os.Bundle;

import com.stanny.demo.R;
import com.stanny.demo.ui.BaseActivity;
import com.zx.zxutils.other.ZXItemClickSupport;
import com.zx.zxutils.other.ZXRecyclerAdapter.ZXRecycleAdapter;
import com.zx.zxutils.other.ZXRecyclerAdapter.ZxRvHolder;
import com.zx.zxutils.util.ZXToastUtil;
import com.zx.zxutils.views.RecylerMenu.ZXRecyclerDeleteHelper;
import com.zx.zxutils.views.SwipeRecylerView.ZXSRListener;
import com.zx.zxutils.views.SwipeRecylerView.ZXSwipeRecyler;

import java.util.ArrayList;
import java.util.List;

public class SwipeRefreshRecylerActivity extends BaseActivity {

    private ZXSwipeRecyler swipeRecyler;
    private List<String> datalist = new ArrayList<>();
    private int totalNum = 100;
    ZXRecyclerDeleteHelper deleteHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe_refresh_recyler);
        swipeRecyler = findViewById(R.id.sr_layout);
//        deleteHelper = new ZXRecyclerDeleteHelper(this, swipeRecyler.getRecyclerView())
//                .setClickable(new ZXRecyclerDeleteHelper.OnItemClickListener() {
//                    @Override
//                    public void onItemClicked(int position) {
//                        ZXToastUtil.showToast("点击了栏目");
//                    }
//                })
//                .setSwipeOptionViews(R.id.tv_delete, R.id.tv_cancle)
//                .setSwipeable(R.id.ll_content, R.id.ll_menu, new ZXRecyclerDeleteHelper.OnSwipeOptionsClickListener() {
//                    @Override
//                    public void onSwipeOptionClicked(int viewID, int position) {
//                        switch (viewID) {
//                            case R.id.tv_delete:
//                                ZXToastUtil.showToast("删除第" + position + "个");
//                                break;
//                            case R.id.tv_cancle:
//                                ZXToastUtil.showToast("取消第" + position + "个");
//                                break;
//                            default:
//                                break;
//                        }
//                    }
//                });
        swipeRecyler.setAdapter(new TestAdapter())
                .showLoadInfo(true)
                .setSRListener(new ZXSRListener<String>() {
                    @Override
                    public void onItemClick(String item, int position) {
                        ZXToastUtil.showToast("点击:" + item.toString());
                    }

                    @Override
                    public void onItemLongClick(String item, int position) {
                        ZXToastUtil.showToast("长按:" + item.toString());
                    }

                    @Override
                    public void onRefresh() {
//                        swipeRecyler.stopRefresh();
                        addList();
//                        swipeRecyler.setLoadInfo("阿西吧");
                    }

                    @Override
                    public void onLoadMore() {
                        addList();
//                        swipeRecyler.setLoadInfo("啊了个阿西吧");
                    }
                });
        swipeRecyler.notifyDataSetChanged();
        ZXItemClickSupport.removeFrom(swipeRecyler.getRecyclerView());
        addList();
    }

    public void addList() {
        datalist.clear();
        for (int i = 0; i < 10; i++) {
            datalist.add(Math.random() * 10 + "");
        }
        swipeRecyler.stopRefresh();
        swipeRecyler.notifyDataSetChanged();
        swipeRecyler.setLoadInfo(50);
    }

    public class TestAdapter extends ZXRecycleAdapter {


        @Override
        public List<?> onItemList() {
            return datalist;
        }

        @Override
        public int onCreateViewLayoutID(int viewType) {
            return R.layout.layout_custom;
        }

        @Override
        public void onBindHolder(ZxRvHolder holder, Object itemEntity, int position) {
            holder.getTextView(R.id.tv_text).setText(datalist.get(position));
        }
    }
}
