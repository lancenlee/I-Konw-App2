package com.itheima.leon.bottombardemo.app.home.fragment.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.itheima.leon.bottombardemo.R;
import com.itheima.leon.bottombardemo.app.home.fragment.bean.ResultBeanData;
import com.itheima.leon.bottombardemo.app.utils.Constants;

import java.util.List;

/**
 * Created by Lancen on 2017/4/22.
 */
public class HotGridViewAdapter extends BaseAdapter{
    private Context mContext;
    private List<ResultBeanData.ResultBean.HotInfoBean> datas;

    public HotGridViewAdapter(Context mContext,List<ResultBeanData.ResultBean.HotInfoBean>hot_info){
        this.mContext=mContext;
        this.datas=hot_info;

    }
    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView == null){
            convertView = View.inflate(mContext, R.layout.item_hot_grid_view,null);
            viewHolder = new ViewHolder();
            viewHolder.iv_hot = (ImageView) convertView.findViewById(R.id.iv_hot);
            viewHolder.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
            viewHolder.tv_price = (TextView) convertView.findViewById(R.id.tv_price);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        //根据位置得到对应的数据
        ResultBeanData.ResultBean.HotInfoBean hotInfoBean = datas.get(position);
        Glide.with(mContext).load(Constants.BASE_URL_IMAGE+hotInfoBean.getFigure()).into(viewHolder.iv_hot);
        viewHolder.tv_name.setText(hotInfoBean.getName());
        viewHolder.tv_price.setText("￥"+hotInfoBean.getCover_price());
        return convertView;
    }

    static class ViewHolder {
        ImageView iv_hot;
        TextView tv_name;
        TextView tv_price;
    }
}
