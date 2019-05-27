    package com.sy.chainproject.adapter;

    import android.support.v7.widget.RecyclerView;
    import android.util.SparseArray;
    import android.view.View;
    import android.widget.ImageView;
    import android.widget.TextView;

    /**
     * @ company zxcg
     * @ name sy
     */
    public class BaseViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        private SparseArray<View> views;
        private ViewOnclick viewOnclick;
        private ViewLongOnclick longClickView;
        private View itemView;
        /**
         * @param itemView 无点击事件
         */
        BaseViewHolder(View itemView) {
            super(itemView);
            this.itemView=itemView;
            views = new SparseArray<>();
        }

        /**
         * @param viewOnclick 有点击事件
         */
        BaseViewHolder(View itemView, ViewOnclick viewOnclick) {
            super(itemView);
            this.viewOnclick = viewOnclick;
            this.itemView=itemView;
            views = new SparseArray<>();
        }

        /**
         * @param viewOnclick 有点击事件 长按点击事件
         */
        BaseViewHolder(View itemView, ViewOnclick viewOnclick, ViewLongOnclick longOnclick) {
            super(itemView);
            this.viewOnclick = viewOnclick;
            this.longClickView = longOnclick;
            views = new SparseArray<>();
        }

        /**
         * 绑定id
         */
        public  <T extends View> T getView(int viewId) {
            View view = views.get(viewId);
            if (view == null) {
                view = itemView.findViewById(viewId);
                views.put(viewId, view);
            }
            return (T) view;
        }

        public void setText(int viewId, String str) {
            TextView view = getView(viewId);
            view.setText(str);
        }

        public void setImagerView(int viewId, int Resources) {
            ImageView imageView = getView(viewId);
            imageView.setImageResource(Resources);
        }

        public void setVisibility(int viewId, int visibility) {
            View view = getView(viewId);
            view.setVisibility(visibility);
        }

        public void setOnclick(int viewId, int position) {
            View view = getView(viewId);
            view.setOnClickListener(this);
            view.setOnLongClickListener(this);
            view.setTag(position);
        }

        public void setItemViewOnClick(int position){
            itemView.setOnClickListener(this);
            itemView.setTag(position);
        }

        @Override
        public void onClick(View v) {
            if (viewOnclick != null) {
                viewOnclick.clickView(v, Integer.valueOf(v.getTag().toString()));
            }
        }

        @Override
        public boolean onLongClick(View v) {
            if (longClickView != null) {
                longClickView.longClickView(v, Integer.valueOf(v.getTag().toString()));
                return true;
            }
            return false;
        }

        public interface ViewOnclick {
            void clickView(View v, int position);
        }

        public interface ViewLongOnclick {
            void longClickView(View v, int position);
        }
    }
