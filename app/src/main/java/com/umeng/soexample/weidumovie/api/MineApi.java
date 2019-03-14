package com.umeng.soexample.weidumovie.api;


import okhttp3.MultipartBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface MineApi {
    /*//查询会员首页信息
    @GET("user/v1/verify/findUserHomeInfo")
    Observable<FindUserHomeInfo> findUserHomeInfo(@Header("userId") int userId, @Header("sessionId") String sessionId);

    //修改密码
    @FormUrlEncoded
    @POST("user/v1/verify/modifyUserPwd")
    Observable<ModifyUserPwd> modifyUserPwd(@Header("userId") int userId, @Header("sessionId") String sessionId, @Field("oldPwd") String oldPwd, @Field("newPwd") String newPwd, @Field("newPwd2") String newPwd2);

    //上传用户头像
    @Multipart
    @POST("user/v1/verify/uploadHeadPic")
    Observable<UploadHeadPic> uploadHeadPic(@Header("userId") int userId, @Header("sessionId") String sessionId, @Part MultipartBody.Part part);

    //修改用户信息(昵称，性别，邮箱)
    @FormUrlEncoded
    @POST("user/v1/verify/modifyUserInfo")
    Observable<ModifyUserInfo> modifyUserInfo(@Header("userId") int userId, @Header("sessionId") String sessionId, @Field("nickName") String nickName, @Field("sex") int sex, @Field("email") String email);

    //用户购票记录查询列表
    @GET("user/v1/verify/findUserBuyTicketRecordList")
    Observable<FindUserBuyTicketRecordList> findUserBuyTicketRecordList(@Header("userId") int userId, @Header("sessionId") String sessionId, @Query("page") int page, @Query("count") int count);

    //用户签到
    @GET("user/v1/verify/userSignIn")
    Observable<UserSignIn> userSignIn(@Header("userId") int userId, @Header("sessionId") String sessionId);

    //根据用户ID查询用户信息
    @GET("user/v1/verify/getUserInfoByUserId")
    Observable<GetUserInfoByUserId> getUserInfoByUserId(@Header("userId") int userId, @Header("sessionId") String sessionId);

    //意见反馈
    @FormUrlEncoded
    @POST("tool/v1/verify/recordFeedBack")
    Observable<RecordFeedBack> recordFeedBack(@Header("userId") int userId, @Header("sessionId") String sessionId, @Field("content") String content);

    //查询新版本
    @GET("tool/v1/findNewVersion")
    Observable<FindNewVersion> findNewVersion(@Header("userId") int userId, @Header("sessionId") String sessionId, @Header("ak") String ak);

    //查询系统消息列表
    @GET("tool/v1/verify/findAllSysMsgList")
    Observable<FindAllSysMsgList> findAllSysMsgList(@Header("userId") int userId, @Header("sessionId") String sessionId, @Query("page") int page, @Query("count") int count);

    //系统消息读取状态修改
    @GET("tool/v1/verify/changeSysMsgStatus")
    Observable<ChangeSysMsgStatus> changeSysMsgStatus(@Header("userId") int userId, @Header("sessionId") String sessionId, @Query("id") int id);

    //查询用户当前未读消息数量
    @GET("tool/v1/verify/findUnreadMessageCount")
    Observable<FindUnreadMessageCount> findUnreadMessageCount(@Header("userId") int userId, @Header("sessionId") String sessionId);

    //上传消息推送使用的token
    @FormUrlEncoded
    @POST("tool/v1/verify/uploadPushToken")
    Observable<UploadPushPushToken> uploadPushToken(@Header("userId") int userId, @Header("sessionId") String sessionId, @Field("token") String token, @Field("os") int os);

    //微信分享前置接口，获取分享所需参数
    @GET("tool/v1/wxShare")
    Observable<WXShare> wxShare(@Query("time") String time, @Query("sign") String sign);

    //微信登录接口
    @FormUrlEncoded
    @POST("user/v1/weChatBindingLogin")
    Observable<WeChatBindingLogin> weChatBindingLogin(@Field("code") String code);

    //绑定微信账号
    @FormUrlEncoded
    @POST("user/v1/verify/bindWeChat")
    Observable<BindWeChat> bindWeChat(@Header("userId") int userId, @Field("code") String code);

    //是否绑定微信账号
    @GET("user/v1/verify/whetherToBindWeChat")
    Observable<WhetherToBindWeChat> whetherToBindWeChat(@Header("userId") int userId);

    // 查询用户关注的电影
    @GET("movie/v1/verify/findMoviePageList")
    Observable<AttentionMovieBean> findAttentionMovie(@Query("page") int page, @Query("count") int count);

    // 查询用户关注的影院
    @GET("cinema/v1/findRecommendCinemas")
    Observable<AttentionCinemaBean> findAttentionCinema(@Query("page") int page, @Query("count") int count);*/
}
