package com.sy.chainproject.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.sy.chainproject.R;

import java.util.List;

/**
 * @ data  2019/4/29 16:18
 * @ author  zxcg
 * 图片选择的适配器
 */

public class PicturesAdapter extends RecyclerView.Adapter<PicturesAdapter.ViewHolder> implements View.OnClickListener{
    private Context context;
   // private List<Uri> list;
    private List<Uri> list;
    private ViewOnclick onclick = null;

    public PicturesAdapter(Context context, List<Uri> list, ViewOnclick onclick) {
        this.context = context;
        this.list = list;
        this.onclick = onclick;
    }

    @NonNull
    @Override
    public PicturesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.rv_item_pictures,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (list==null||position == list.size()) {
            holder.imageView.setImageResource(R.mipmap.add);
        } else holder.imageView.setImageURI(list.get(position));
        holder.imageView.setOnClickListener(this);
        holder.imageView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return list == null ? 1 : list.size() + 1;
    }

    @Override
    public void onClick(View v) {
        if (onclick != null) onclick.clickView(v, Integer.parseInt(v.getTag().toString()));
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.item_picture);
        }
    }

    public interface ViewOnclick {
        void clickView(View v, int position);
    }

    public interface ViewLongOnclick {
        void longClickView(View v, int position);
    }
}
