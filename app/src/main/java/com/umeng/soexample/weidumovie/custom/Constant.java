package com.umeng.soexample.weidumovie.custom;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.bumptech.glide.util.Util;
import com.umeng.soexample.weidumovie.application.MyApplication;

import java.io.File;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class Constant {
    public static final class WX {
        public static final String WEIXIN_APP_ID = "wxb3852e6a6b7d9516";
    }

    public static Observable addMain(Observable observable) {
        return observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

//    public static void shareBitmap(){
//        Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.send_img);
//
//        //初始化 WXImageObject 和 WXMediaMessage 对象
//        WXImageObject imgObj = new WXImageObject(bmp);
//        WXMediaMessage msg = new WXMediaMessage();
//        msg.mediaObject = imgObj;
//
//        //设置缩略图
//        Bitmap thumbBmp = Bitmap.createScaledBitmap(bmp, THUMB_SIZE, THUMB_SIZE, true);
//        bmp.recycle();
//        msg.thumbData = Util.bmpToByteArray(thumbBmp, true);
//
//        //构造一个Req
//        SendMessageToWX.Req req = new SendMessageToWX.Req();
//        req.transaction = buildTransaction("img");
//        req.message = msg;
//        req.scene = mTargetScene;
//        req.userOpenId = getOpenId();
//        //调用api接口，发送数据到微信
//        api.sendReq(req);
//    }

    public static final int IMAGE_LENGTH_LIMIT = 6291456;

//    private static ByteArrayOutputStream getWXThumb(Bitmap resource) {
//        Bitmap thumb = createScaledBitmap(resource, 100, true);
//        ByteArrayOutputStream output = new ByteArrayOutputStream();
//        int quality = 90;
//        int realLength = Util.getBitmapByteSize(resource.getWidth(), resource.getHeight(), Bitmap.Config.ARGB_8888);
//        if (realLength > IMAGE_LENGTH_LIMIT) {
//            quality = (int) (IMAGE_LENGTH_LIMIT * 1f / realLength * 100);
//        }
//        if (quality < 75) {
//            quality = 75;
//        }
//        resource.compress(Bitmap.CompressFormat.PNG, quality, output);
//        output.reset();
//        thumb.compress(Bitmap.CompressFormat.PNG, 85, output);
//        return output;
//    }

  /*  public static void shareText(String text){
        //初始化一个 WXTextObject 对象，填写分享的文本内容
        WXTextObject textObj = new WXTextObject();
        textObj.text = text;

        //用 WXTextObject 对象初始化一个 WXMediaMessage 对象
        WXMediaMessage msg = new WXMediaMessage();
        msg.mediaObject = textObj;
        msg.description = text;
        msg.title = "曲春建";

        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = String.valueOf(System.currentTimeMillis());
        req.message = msg;
        req.scene = SendMessageToWX.Req.WXSceneSession;
        //调用api接口，发送数据到微信
        MyApplication.mWxApi.sendReq(req);*/
    }

//    public static void shareWXApi(File picFile, String tag) {
//        if (!picFile.exists()) {
//            return;
//        }
//        Bitmap pic = BitmapFactory.decodeFile(picFile.toString());
//
//        WXImageObject imageObject = new WXImageObject(pic);
//        //这个构造方法中自动把传入的bitmap转化为2进制数据,或者你直接传入byte[]也行
//        //注意传入的数据不能大于10M,开发文档上写的
//
//        WXMediaMessage msg = new WXMediaMessage();  //这个对象是用来包裹发送信息的对象
//        msg.mediaObject = imageObject;
//        //msg.mediaObject实际上是个IMediaObject对象,
//        //它有很多实现类,每一种实现类对应一种发送的信息,
//        //比如WXTextObject对应发送的信息是文字,想要发送文字直接传入WXTextObject对象就行
//
//
//        Bitmap thumbBitmap = Bitmap.createScaledBitmap(pic, 150, 150, true);
//
//        msg.thumbData = bitmap2ByteArray(thumbBitmap);
//        //在这设置缩略图
//        //官方文档介绍这个bitmap不能超过32kb
//        //如果一个像素是8bit的话换算成正方形的bitmap则边长不超过181像素,边长设置成150是比较保险的
//        //或者使用msg.setThumbImage(thumbBitmap);省去自己转换二进制数据的过程
//        //如果超过32kb则抛异常
//
//        SendMessageToWX.Req req = new SendMessageToWX.Req();    //创建一个请求对象
//        req.message = msg;  //把msg放入请求对象中
//        req.scene = SendMessageToWX.Req.WXSceneTimeline;    //设置发送到朋友圈
//        //req.scene = SendMessageToWX.Req.WXSceneSession;   //设置发送给朋友
//        req.transaction = tag;  //这个tag要唯一,用于在回调中分辨是哪个分享请求
//        boolean b = MyApplication.mWxApi.sendReq(req);   //如果调用成功微信,会返回true
//    }

