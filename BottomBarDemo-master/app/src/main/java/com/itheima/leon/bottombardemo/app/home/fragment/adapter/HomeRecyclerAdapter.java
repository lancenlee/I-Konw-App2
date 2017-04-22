package com.itheima.leon.bottombardemo.app.home.fragment.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.itheima.leon.bottombardemo.R;
import com.itheima.leon.bottombardemo.app.home.fragment.bean.ResultBeanData;
import com.itheima.leon.bottombardemo.app.utils.Constants;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerClickListener;
import com.youth.banner.listener.OnLoadImageListener;
import com.zhy.magicviewpager.transformer.ScaleInTransformer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lancen on 2017/4/18.
 */
public class HomeRecyclerAdapter extends RecyclerView.Adapter {
    /**
     * 广告条幅类型
     */
    public static final int BANNER = 0;

    /**
     * 频道类型
     */
    public static final int CHANNEL = 1;
    /**
     * 活动类型
     */
    public static final int ACT = 2;
    /**
     * 推荐类型
     */
    public static final int RECOMMEND = 3;
    /**
     * 热卖
     */
    public static final int HOT = 4;
    /**
     * 用来初始化布局
     */
    private LayoutInflater mLayoutInflater;
    private Context mContext;
    /**
     * 数据
     */
    private ResultBeanData.ResultBean resultBean;
    /**
     * 当前类型
     */
    private int currentType = BANNER;

    public HomeRecyclerAdapter(Context mContext, ResultBeanData.ResultBean resultBean) {
        this.mContext = mContext;
        this.resultBean = resultBean;
        mLayoutInflater = LayoutInflater.from(mContext);
    }
    /**
     * 相当于getView 创建ViewHolder部分代码
     * 创建ViewHolder
     *
     * @param parent
     * @param viewType 当前的类型
     * @return
     */
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == BANNER) {
            return new BannerViewHolder(mContext, mLayoutInflater.inflate(R.layout.banner_viewpager, null));
        }else if (viewType==CHANNEL){
            return new ChannelViewHolder(mContext,mLayoutInflater.inflate(R.layout.channel_item,null));
        }else if (viewType == ACT) {
            return new ActViewHolder(mContext, mLayoutInflater.inflate(R.layout.act_item, null));
        }else if(viewType == RECOMMEND ) {
            return new RecommendViewHolder(mContext, mLayoutInflater.inflate(R.layout.recommend_item, null));
        }else if (viewType==HOT){
            return new HotViewHolder(mContext, mLayoutInflater.inflate(R.layout.hot_item, null));
        }

        return null;
    }
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == BANNER) {
            BannerViewHolder bannerViewHolder = (BannerViewHolder) holder;
            bannerViewHolder.setData(resultBean.getBanner_info());
        }else if (getItemViewType(position)==CHANNEL){
            ChannelViewHolder channelViewHolder = (ChannelViewHolder) holder;
            channelViewHolder.setData(resultBean.getChannel_info());
        }else if (getItemViewType(position) == ACT) {
            ActViewHolder actViewHolder = (ActViewHolder) holder;
            actViewHolder.setData(resultBean.getAct_info());
        }
        else if(getItemViewType(position) == RECOMMEND) {
            RecommendViewHolder recommendViewHolder = (RecommendViewHolder) holder;
            recommendViewHolder.setData(resultBean.getRecommend_info());
        }else if (getItemViewType(position)==HOT){
            HotViewHolder hotViewHolder=(HotViewHolder) holder;
            hotViewHolder.setData(resultBean.getHot_info());
        }
    }

    class HotViewHolder extends RecyclerView.ViewHolder{

        private final Context mContext;

        private TextView tv_more_hot;
        private GridView gv_hot;

        public HotViewHolder(final Context mContext, View itemView) {
            super(itemView);
            this.mContext = mContext;
            tv_more_hot = (TextView) itemView.findViewById(R.id.tv_more_hot);
            gv_hot = (GridView) itemView.findViewById(R.id.gv_hot);

        }

        public void setData(final List<ResultBeanData.ResultBean.HotInfoBean> hot_info) {
            //1.有数据
            //2.设置GridView的适配器
            HotGridViewAdapter  adapter = new HotGridViewAdapter(mContext,hot_info);
            gv_hot.setAdapter(adapter);
            //设置item的监听
            gv_hot.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(mContext, "position=="+position, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
    class RecommendViewHolder extends RecyclerView.ViewHolder{

        private final Context mContext;
        private TextView tv_more_recommend;
        private GridView gv_recommend;
        private RecommendGridViewAdapter adapter;

        public RecommendViewHolder(final Context mContext, View itemView) {
            super(itemView);
            this.mContext = mContext;
            tv_more_recommend = (TextView) itemView.findViewById(R.id.tv_more_recommend);
            gv_recommend = (GridView) itemView.findViewById(R.id.gv_recommend);


        }

        public void setData(final List<ResultBeanData.ResultBean.RecommendInfoBean> recommend_info) {
            //1.有数据了
            //2.设置适配器
            adapter = new RecommendGridViewAdapter(mContext,recommend_info);
            gv_recommend.setAdapter(adapter);

            gv_recommend.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(mContext, "position=="+position, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }


    class ActViewHolder extends RecyclerView.ViewHolder {
        private Context mContext;
        private ViewPager act_viewpager;

        public ActViewHolder(Context mContext, View itemView) {
            super(itemView);
            this.mContext = mContext;
            act_viewpager = (ViewPager) itemView.findViewById(R.id.act_viewpager);
        }

        public void setData(final List<ResultBeanData.ResultBean.ActInfoBean> act_info) {
            act_viewpager.setPageMargin(20);
            act_viewpager.setOffscreenPageLimit(3);//>=3

            //setPageTransformer 决定动画效果
            act_viewpager.setPageTransformer(true, new ScaleInTransformer());
            //1.有数据了
            //2.设置适配器
            act_viewpager.setAdapter(new PagerAdapter() {
                @Override
                public int getCount() {
                    return act_info.size();
                }

                public boolean isViewFromObject(View view, Object object) {
                    return view == object;
                }

                /**
                 *
                 * @param container ViewPager
                 * @param position 对应页面的位置
                 * @return
                 */
                @Override
                public Object instantiateItem(ViewGroup container, final int position) {
                    ImageView imageView = new ImageView(mContext);
                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);

                    Glide.with(mContext).load(Constants.BASE_URL_IMAGE + act_info.get(position).getIcon_url()).into(imageView);
                    //添加到容器中
                    container.addView(imageView);

                    //设置点击事件
                    imageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(mContext, "position=="+position, Toast.LENGTH_SHORT).show();
                        }
                    });


                    return imageView;
                }

                @Override
                public void destroyItem(ViewGroup container, int position, Object object) {
                    container.removeView((View) object);
                }
            });
        }
    }

    class ChannelViewHolder extends RecyclerView.ViewHolder{
        private Context mContext;
        private GridView gv_channel;
        private ChannelAdapter adapter;

        public ChannelViewHolder(final Context mContext, View itemView) {
            super(itemView);
            this.mContext=mContext;
            gv_channel=(GridView) itemView.findViewById(R.id.gv_channel);

            gv_channel.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView parent, View view, int position, long id) {
                    Toast.makeText(mContext, "position" + position, Toast.LENGTH_SHORT).show();
                }
            });
        }


        public void setData(List<ResultBeanData.ResultBean.ChannelInfoBean> channel_info) {
            //得到数据了
            //设置GridView的适配器
            adapter = new ChannelAdapter(mContext, channel_info);
            gv_channel.setAdapter(adapter);
        }
    }

    class BannerViewHolder extends RecyclerView.ViewHolder{
        private Context mContext;
        private Banner banner;

        public BannerViewHolder(Context mContext, View itemView){
            super(itemView);
            this.mContext=mContext;
            this.banner=(Banner)itemView.findViewById(R.id.banner);
        }
        public void setData(List<ResultBeanData.ResultBean.BannerInfoBean> banner_info) {
            //设置Banner的数据
            //得到图片集合地址
            List<String> imagesUrl = new ArrayList<>();
            for (int i = 0; i < banner_info.size(); i++) {
                String imageUrl = banner_info.get(i).getImage();
                imagesUrl.add(imageUrl);
            }

            //设置循环指示点
            banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
            //设置手风琴效果
            banner.setBannerAnimation(Transformer.Accordion);
            banner.setImages(imagesUrl, new OnLoadImageListener() {
                @Override
                public void OnLoadImage(ImageView view, Object url) {

                    //联网请求图片-Glide
                    Glide.with(mContext).load(Constants.BASE_URL_IMAGE + url).into(view);

                }
            });

            //设置item的点击事件
            banner.setOnBannerClickListener(new OnBannerClickListener() {
                @Override
                public void OnBannerClick(int position) {
                    Toast.makeText(mContext, "position==" + position, Toast.LENGTH_SHORT).show();
                }
            });
        }

    }




    /**
     * 得到类型
     *
     * @param position
     * @return
     */

    public int getItemViewType(int position) {
        switch (position) {
            case BANNER:
                currentType = BANNER;
                break;
            case CHANNEL:
                currentType = CHANNEL;
                break;
            case ACT:
                currentType = ACT;
                break;
            case RECOMMEND:
                currentType = RECOMMEND;
                break;
            case HOT:
                currentType = HOT;
                break;
        }
        return currentType;
    }

    /**
     * 总共有多少个item
     *
     * @return
     */

    public int getItemCount() {
        //开发过程中从1-->6
        return 5;
    }
}