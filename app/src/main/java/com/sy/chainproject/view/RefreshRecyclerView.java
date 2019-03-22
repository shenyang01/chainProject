package com.sy.chainproject.view;

/**
 * @ company pite
 * @ name sy
 **/

import android.content.Context;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import com.sy.chainproject.adapter.BaseAdapter;

/**
 * 上拉加载更多数据 newState 0 静止 1，用户手指移动 2 惯性滚动
 */
public class RefreshRecyclerView extends RecyclerView {

    private boolean isLoading;  //判断当前是否正在上拉状态
    private boolean isfull; //判断当前是否正在刷新脚布局
    private boolean isDown; //判断当前是否处于下拉刷新的状态
    private int currentNewState = -1;//当前滚动状态
    private boolean state = true; //判断是否还有数据
    public OnPullRefresh mOnPullRefresh;

    public RefreshRecyclerView(Context context) {
        super(context);
        init();
    }

    public RefreshRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RefreshRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public void init() {
        setLayoutManager(new LinearLayoutManager(getContext()));
        addOnScrollListener(new OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                Log.e("tag","onScrollStateChanged");
                currentNewState = newState;
                if (!state && !isfull) {
                    isfull = true;
                    new Handler().postDelayed(() -> {
                        ((BaseAdapter)getAdapter()).setFootText("没有更多数据了");
                        getAdapter().notifyItemRemoved(getAdapter().getItemCount());
                        isfull = false;
                    }, 2000);
                }
            }

            //滚动时回调
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (getAdapter() == null || getLayoutManager() == null||isDown){
                    //((BaseAdapter) getAdapter()).setFootVisibility(View.GONE);
                    return;
                }

                if (currentNewState == -1) { //当前没有滑动 处理数据不足一页的情况
                    ((BaseAdapter) getAdapter()).setFootVisibility(View.GONE);
                    return;
                }
                /*if (dy > 0) { //有滑动
                    if (((SimpleAdapter) getAdapter()).getVisiblityState() == View.GONE) {
                        Log.e("tag"," onScrolled");
                        ((SimpleAdapter) getAdapter()).setFootVisibility(View.VISIBLE);
                    }
                }*/
                int lastVisibleItemPosition = ((LinearLayoutManager) getLayoutManager()).findLastVisibleItemPosition();
                if (lastVisibleItemPosition + 1 == getAdapter().getItemCount()&&dy > 0) {
                    Log.e("tag","onScrolled  "+dy+isLoading);
                    if (!isLoading) {
                        setisLoading(true);
                        if(mOnPullRefresh==null)
                            return;
                        mOnPullRefresh.PullRefresh();
                    }
                }
            }
        });
    }

    /***
     * setAdapter
     */
    public void setRecyclerViewAdapter(Adapter adapter) {
        setAdapter(adapter);
    }

    /**
     * @param b 当前是否正在更新数据
     */
    public boolean setisLoading(boolean b) {
        isLoading = b;
        return isLoading;
    }

    /**
     * @param d 判断当前是否正在下拉
     */
    public boolean setIsDown(boolean d) {
        isDown = d;
        return isDown;
    }
    /***
     *
     * @param state 判断当前是否还有数据
     */
    public boolean setIsData(boolean state) {
        this.state = state;
        return state;
    }
    public void setOnPullRefresh(OnPullRefresh mOnPullRefresh) {
        this.mOnPullRefresh = mOnPullRefresh;
    }

    public interface OnPullRefresh {
        void PullRefresh();
    }
}
