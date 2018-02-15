package com.selva.androidproficiencyexercise.adaptor;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.selva.androidproficiencyexercise.R;
import com.selva.androidproficiencyexercise.model.CountryData;

import butterknife.BindView;
import butterknife.ButterKnife;


public class CountryDataAdaptor extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private CountryData countryData;
    private Context context;

    public CountryDataAdaptor(Context context, CountryData countryData) {
        this.countryData = countryData;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.layout_country_details_list, viewGroup, false);

        return new CountryDataViewHolder(v);
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof CountryDataViewHolder) {
            final CountryDataViewHolder countryDataViewHolder = (CountryDataViewHolder) holder;
            countryDataViewHolder.textViewContentTitle.setText(String.valueOf(countryData.getRows()
                    .get(position).getTitle()));
            countryDataViewHolder.textViewContentDesc.setText(String.valueOf(countryData.getRows()
                    .get(position).getDescription()));

            /**
             * Checking image Url is Null
             */
            if (TextUtils.isEmpty(countryData.getRows().get(position).getImageHref())) {
                countryDataViewHolder.contentImageview.setVisibility(View.GONE);
            } else {
                countryDataViewHolder.contentImageview.setVisibility(View.GONE);
                Glide.with(context)
                        .load(countryData.getRows().get(position).getImageHref())
                        .apply(new RequestOptions().frame(1000))
                        .thumbnail(.5f)
                        .listener(new RequestListener<Drawable>() {
                            @Override
                            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                                countryDataViewHolder.contentImageview.setVisibility(View.GONE);
                                return false;
                            }

                            @Override
                            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                                countryDataViewHolder.contentImageview.setVisibility(View.VISIBLE);
                                return false;
                            }
                        })
                        .into(countryDataViewHolder.contentImageview);
            }
        }
    }

    @Override
    public int getItemCount() {
        return countryData.getRows().size();
    }


    class CountryDataViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.card_view)
        CardView rootView;
        @BindView(R.id.content_title)
        AppCompatTextView textViewContentTitle;

        @BindView(R.id.content_desc)
        AppCompatTextView textViewContentDesc;

        @BindView(R.id.content_img)
        AppCompatImageView contentImageview;

        public CountryDataViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
