package cn.njthl.HotelClean.app;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.previewlibrary.loader.IZoomMediaLoader;
import com.previewlibrary.loader.MySimpleTarget;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import cn.njthl.HotelClean.R;

/**
 * Created by yangc on 2017/9/4.
 * E-Mail:yangchaojiang@outlook.com
 * Deprecated:
 */

public class TestImageLoader implements IZoomMediaLoader {


    @Override
    public void displayImage(@NonNull Fragment context, @NonNull String path, ImageView imageView, @NonNull final MySimpleTarget simpleTarget) {
        Glide.with(context).load(path)
                .asBitmap()
                .error(R.drawable.ic_default_image)
              //  .placeholder(android.R.color.darker_gray)
                .fitCenter()
                .centerCrop()
                .listener(new RequestListener<String, Bitmap>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<Bitmap> target, boolean isFirstResource) {
                        simpleTarget.onLoadFailed(null);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Bitmap resource, String model, Target<Bitmap> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        simpleTarget.onResourceReady();
                        return false;
                    }
                })
                .into(imageView);
    }

    @Override
    public void displayGifImage(@NonNull Fragment context, @NonNull String path, ImageView imageView, @NonNull final MySimpleTarget simpleTarget) {
        Glide.with(context).load(path)
               .asGif()
                //可以解决gif比较几种时 ，加载过慢  //DiskCacheStrategy.NONE
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .error(R.drawable.ic_default_image)
                .dontAnimate() //去掉显示动画
                .listener(new RequestListener<String, GifDrawable>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<GifDrawable> target, boolean isFirstResource) {
                        simpleTarget.onResourceReady();
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GifDrawable resource, String model, Target<GifDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        simpleTarget.onLoadFailed(null);
                        return false;
                    }
                })
                .into(imageView);
    }
    @Override
    public void onStop(@NonNull Fragment context) {
          Glide.with(context).onStop();

    }

    @Override
    public void clearMemory(@NonNull Context c) {
             Glide.get(c).clearMemory();
    }
}
