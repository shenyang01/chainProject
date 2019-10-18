package com.sy.chainproject.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.sy.chainproject.R;
import com.sy.chainproject.utils.LogUtils;

import java.util.List;


/**
 * @ company zxcg
 * @ name sy
 */
public abstract class BaseAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<T> mlist;
    private LayoutInflater miInflater;
    private int TYPE_EMPTY = -1;
    private int TYPE_FOOTER = 0;
    private int TYPE_ITEM = 1;
    private FootHolder FootHolder = null;
    private int layout;
    private boolean isFoot;
    private BaseViewHolder.ViewOnclick viewOnclick=null;
    protected BaseAdapter(Context context, int layout, List<T> mlist) {
        this(context,layout,mlist,null);
    }
    protected BaseAdapter(Context context, int layout, List<T> mlist,BaseViewHolder.ViewOnclick viewOnclick) {
        this(context,layout,mlist,false,viewOnclick);
    }
    protected BaseAdapter(Context context, int layout, List<T> mlist, boolean isFoot,BaseViewHolder.ViewOnclick viewOnclick) {
        this.mlist = mlist;
        this.layout = layout;
        this.isFoot = isFoot;
        this.viewOnclick = viewOnclick;
        miInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if(viewType == TYPE_EMPTY){
            view = miInflater.inflate(R.layout.item_detaempty, parent, false);
            LogUtils.e("viewType  "+viewType);
            return new DataEmptyHolder(view);
        }
        else if (viewType == TYPE_FOOTER) {
            view = miInflater.inflate(R.layout.item_footitem, parent, false);
            return new FootHolder(view);
        } else {
            view = miInflater.inflate(layout, parent, false);
            if(null!=viewOnclick)
                return new BaseViewHolder(view,viewOnclick);
            else return new BaseViewHolder(view);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (mlist == null||mlist.size()==0)
            return TYPE_EMPTY;
         else if (position + 1 == getItemCount() && isFoot) {
            return TYPE_FOOTER;
        } else {
            return TYPE_ITEM;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof BaseViewHolder) {
            convert((BaseViewHolder) holder, mlist.get(position), position);
        } else if (holder instanceof FootHolder) {
            FootHolder = (FootHolder) holder;
        } else {
            ((DataEmptyHolder) holder).data_empty.setText("暂无数据");
        }

    }

    public void setFootText(String str) {
        if (FootHolder != null)
            FootHolder.footTv.setText(str);
    }

    public void setFootVisibility(int v) {
        if (FootHolder != null)
            FootHolder.footTv.setVisibility(v);
    }

    public int getVisiblityState() {
        if (FootHolder != null)
            return FootHolder.footTv.getVisibility();
        return View.GONE;
    }

    /**
     * 设置数据的方法
     */
    public abstract void convert(BaseViewHolder holder, T data, int position);

    @Override
    public int getItemCount() {
        if (mlist == null||mlist.size()==0)
            return 1;
        else if (isFoot)
            return mlist.size() + 1;
        else
            return mlist.size();
    }

    static class FootHolder extends RecyclerView.ViewHolder {
        TextView footTv;

        FootHolder(View itemView) {
            super(itemView);
            footTv = itemView.findViewById(R.id.footTv);
        }
    }

    /**
     * 数据为空时
     */
    static class DataEmptyHolder extends RecyclerView.ViewHolder {
        TextView data_empty;

        DataEmptyHolder(View itemView) {
            super(itemView);
            data_empty = itemView.findViewById(R.id.data_emptys);
        }
    }

}
