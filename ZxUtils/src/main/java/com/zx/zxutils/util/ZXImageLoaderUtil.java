package com.zx.zxutils.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.net.Uri;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.zx.zxutils.R;
import com.zx.zxutils.ZXApp;

import java.io.File;
import java.util.concurrent.ExecutionException;

/**
 * Create By Xiangb On 2017/6/30
 * 功能：图片加载工具类
 */
public class ZXImageLoaderUtil {

    public static Bitmap getBitmap(String url, int width, int height) {
        Bitmap bitmap = null;
        try {
            bitmap = Glide.with(ZXApp.getContext())
                    .load(url)
                    .asBitmap() //必须
                    .into(width, height)
                    .get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    /**
     * 默认500-500
     *
     * @param url
     * @return
     */
    public static Bitmap getBitmap(String url) {
        return getBitmap(url, 500, 500);
    }

    /**
     * 获取simpletarget
     *
     * @param url
     * @param simpleTarget
     */
    public static void getBitmap(String url, SimpleTarget<Bitmap> simpleTarget) {
        Glide.with(ZXApp.getContext())
                .load(url)
                .asBitmap()
                .into(simpleTarget); //方法中设置asBitmap可以设置回调类型
    }

    public static void display(ImageView imageView, String url, int placeholder, int error) {
        if (imageView == null) {
            throw new IllegalArgumentException("argument error");
        }
        Glide.with(ZXApp.getContext()).load(url).placeholder(placeholder)
                .error(error).crossFade().into(imageView);
    }

    public static void display(ImageView imageView, String url) {
        display(imageView, url, R.mipmap.ic_empty_picture);
    }

    public static void display(ImageView imageView, String url, int errorImage) {
        if (imageView == null) {
            throw new IllegalArgumentException("argument error");
        }
        Glide.with(ZXApp.getContext()).load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.mipmap.ic_image_loading)
                .error(errorImage)
                .crossFade().into(imageView);
    }

    public static void display(ImageView imageView, Uri uri) {
        display(imageView, uri, R.mipmap.ic_empty_picture);
    }

    public static void display(ImageView imageView, Uri uri, int errorImage) {
        if (imageView == null) {
            throw new IllegalArgumentException("argument error");
        }
        Glide.with(ZXApp.getContext()).load(uri)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.mipmap.ic_image_loading)
                .error(errorImage)
                .crossFade().into(imageView);
    }

    public static void display(ImageView imageView, File file) {
        display(imageView, file, R.mipmap.ic_empty_picture);
    }

    public static void display(ImageView imageView, File file, int errorImage) {
        if (imageView == null) {
            throw new IllegalArgumentException("argument error");
        }
        Glide.with(ZXApp.getContext()).load(file)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.mipmap.ic_image_loading)
                .error(errorImage)
                .crossFade().into(imageView);
    }

    public static void displaySmallPhoto(ImageView imageView, String url) {
        displaySmallPhoto(imageView, url, R.mipmap.ic_empty_picture);
    }

    public static void displaySmallPhoto(ImageView imageView, String url, int errorImage) {
        if (imageView == null) {
            throw new IllegalArgumentException("argument error");
        }
        Glide.with(ZXApp.getContext()).load(url).asBitmap()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.mipmap.ic_image_loading)
                .error(errorImage)
                .thumbnail(0.5f)
                .into(imageView);
    }

    public static void displayBigPhoto(ImageView imageView, String url) {
        displayBigPhoto(imageView, url, R.mipmap.ic_empty_picture);
    }

    public static void displayBigPhoto(ImageView imageView, String url, int errorImage) {
        if (imageView == null) {
            throw new IllegalArgumentException("argument error");
        }
        Glide.with(ZXApp.getContext()).load(url).asBitmap()
                .format(DecodeFormat.PREFER_ARGB_8888)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.mipmap.ic_image_loading)
                .error(errorImage)
                .into(imageView);
    }

    public static void display(ImageView imageView, int resourceId) {
        display(imageView, resourceId, R.mipmap.ic_empty_picture);
    }

    public static void display(ImageView imageView, int resourceId, int errorImage) {
        if (imageView == null) {
            throw new IllegalArgumentException("argument error");
        }
        Glide.with(ZXApp.getContext()).load(resourceId)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.mipmap.ic_image_loading)
                .error(errorImage)
                .crossFade().into(imageView);
    }

    public static void displaySquare(ImageView imageView, String url) {
        if (imageView == null) {
            throw new IllegalArgumentException("argument error");
        }
        Glide.with(ZXApp.getContext()).load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .crossFade()
                .into(imageView);
    }

    public static void displaySquare(ImageView imageView, int resourceId) {
        if (imageView == null) {
            throw new IllegalArgumentException("argument error");
        }
        Glide.with(ZXApp.getContext()).load(resourceId)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .crossFade()
                .into(imageView);
    }

    public static void displayRound(ImageView imageView, String url) {
        displayRound(imageView, url, R.mipmap.toux2);
    }

    public static void displayRound(ImageView imageView, String url, int errorImage) {
        if (imageView == null) {
            throw new IllegalArgumentException("argument error");
        }
        Glide.with(ZXApp.getContext()).load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .error(errorImage)
                .transform(new GlideRoundTransformUtil(ZXApp.getContext())).into(imageView);
    }

    private static class GlideRoundTransformUtil extends BitmapTransformation {
        public GlideRoundTransformUtil(Context context) {
            super(context);
        }

        @Override
        protected Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {
            return circleCrop(pool, toTransform);
        }

        private Bitmap circleCrop(BitmapPool pool, Bitmap source) {
            if (source == null) return null;

            int size = Math.min(source.getWidth(), source.getHeight());
            int x = (source.getWidth() - size) / 2;
            int y = (source.getHeight() - size) / 2;

            // TODO this could be acquired from the pool too
            Bitmap squared = Bitmap.createBitmap(source, x, y, size, size);

            Bitmap result = pool.get(size, size, Bitmap.Config.ARGB_8888);
            if (result == null) {
                result = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888);
            }

            Canvas canvas = new Canvas(result);
            Paint paint = new Paint();
            paint.setShader(new BitmapShader(squared, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP));
            paint.setAntiAlias(true);
            float r = size / 2f;
            canvas.drawCircle(r, r, r, paint);
            return result;
        }

        @Override
        public String getId() {
            return getClass().getName();
        }
    }

}
