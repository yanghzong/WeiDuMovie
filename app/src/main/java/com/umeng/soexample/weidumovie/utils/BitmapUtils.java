package com.umeng.soexample.weidumovie.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;


/**
 * 1.为什么要对图片进行二次采样
 * 在开发App的过程中有没有遇到过类似照片墙的功能？，在做图片墙的时候你有没有遇到过OOM异常？
 * 比如我现在有一张100M大的图片，我想把这张图片用ImageView显示出来，那么ImageView能够显示出来这张图片吗？
 * 上面我们说的这两种情况其实是涉及到图片加载时内存溢出的问题，内存溢出可能发生在加载一张大图的时候，也有可能发生在加载多张普通小图的时候
 * 如果我们不对图片做二次采样，那么OOM就是一把悬在头上的剑，随时可能会掉下
 * 所以一定要对图片进行二次采样。
 * 事实上，我在手机上显示一张分辨率特别大的图片和显示一张分辨率小的图片（不要小的太离谱即可），对用户的视觉体验来说，并不会有多大的变化
 * 但是对我们的手机内存来说，影响却是非常巨大的，总而言之，二次采样就是为了避免图片加载是的OOM异常
 *
 * 2.图片的二次采样
 * 第一次采样：获取图片的压缩比例
 * 假如说我有一张图片是200*200，那么我想把这张图片的缩略图显示在一个50*50的ImageView上
 * 那么我的压缩比例应该是4
 * 第一步:先加载图片的边界到内存中，这个加载操作并不会消费多少内存，加载到内存之后
 * 第二步：我们就可以获取这张图片的宽高参数
 * 第三步：然后根据图片的宽高，再结合控件的宽高计算出缩放比例
 *
 * 第二次采样：在第一次采样的基础上，进行第二次采样
 * 第一步：把第一次采样后算出来的结果作为一个参数传递给BitmapFactory（缩放比例）
 * 这样加载图片的时候就不会将整张图片加载进来，而是只会加载该图片的一张缩略图
 * 优点：提高加载速率、极大的节省了内存，而且对于用户来说，不会有视觉上的差异
 */
public class BitmapUtils {
    /**
     * @param filePath   要加载的图片路径
     * @param destWidth  显示图片的控件宽度
     * @param destHeight 显示图片的控件的高度
     * @return
     */
    public static Bitmap getBitmap(String filePath, int destWidth, int destHeight) {
        //第一次采样
        BitmapFactory.Options options = new BitmapFactory.Options();
        //该属性设置为true只会加载图片的边框进来，并不会加载图片具体的像素点
        options.inJustDecodeBounds = true;
        //第一次加载图片，这时只会加载图片的边框进来，并不会加载图片中的像素点
        BitmapFactory.decodeFile(filePath, options);
        //获得原图的宽和高
        int outWidth = options.outWidth;
        int outHeight = options.outHeight;
        //定义缩放比例
        int sampleSize = 1;
        while (outHeight / sampleSize > destHeight || outWidth / sampleSize > destWidth) {
            //如果宽高的任意一方的缩放比例没有达到要求，都继续增大缩放比例
            //sampleSize应该为2的n次幂，如果给sampleSize设置的数字不是2的n次幂，那么系统会就近取值
            sampleSize *= 2;
        }
        /********************************************************************************************/
        //至此，第一次采样已经结束，我们已经成功的计算出了sampleSize的大小
        /********************************************************************************************/
        //二次采样开始
        //二次采样时我需要将图片加载出来显示，不能只加载图片的框架，因此inJustDecodeBounds属性要设置为false
        options.inJustDecodeBounds = false;
        //设置缩放比例
        options.inSampleSize = sampleSize;
        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
        //加载图片并返回
        return BitmapFactory.decodeFile(filePath, options);
    }
}
