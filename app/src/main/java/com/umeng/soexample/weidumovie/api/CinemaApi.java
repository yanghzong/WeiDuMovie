package com.umeng.soexample.weidumovie.api;


import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;



public interface CinemaApi {
   /* //获取推荐影院
    @GET("cinema/v1/findRecommendCinemas")
    Observable<CinemaSearchBean> getCinemaSearch(@Header("userId") int userId, @Header("sessionId") String sessionId, @Query("longitude") String longitude, @Query("latitude") String latitude, @Query("page") int page, @Query("count") int count);

    //查询电影信息明细
    @GET("cinema/v1/findCinemaInfo")
    Observable<CinemaMessageBean> getCinemaMessage(@Query("cinemaId") int cinemaId);

    //查询所有电影院
    @GET("cinema/v1/findAllCinemas")
    Observable<AllCinemaBean> findAllCinemas(@Query("page") int page, @Query("count") int count, @Query("cinemaName") String cinemaName);

    @GET("cinema/v1/findAllCinemas")
    Observable<AllCinemaBean> findAllCinemas(@Query("page") int page, @Query("count") int count);

    //根据影院ID查询该影院当前排期的电影列表
    @GET("movie/v1/findMovieListByCinemaId")
    Observable<CinemaMovieBean> getCinemaMovie(@Query("cinemaId") int cinemaId);

    //.根据电影ID和影院ID查询电影排期列表
    @GET("movie/v1/findMovieScheduleList")
    Observable<MoviePlayBean> getMoviePlay(@Query("cinemasId") int cinemasId, @Query("movieId") int movieId);

    //查询影院用户评论列表
    @GET("cinema/v1/findAllCinemaComment")
    Observable<CinemaCommentBean> getCinemaComment(@Header("userId") int userId, @Header("sessionId") String sessionId, @Query("cinemaId") int cinemaId, @Query("page") int page, @Query("count") int count);

    //影院评论
    @POST("cinema/v1/verify/cinemaComment")
    @FormUrlEncoded
    Observable<FollowOrCancelMovieBean> writeComment(@Header("userId") int userId, @Header("sessionId") String sessionId, @Field("cinemaId") int cinemaId, @Field("commentContent") String commentContent);

    //影院评论点赞
    @POST("cinema/v1/verify/cinemaCommentGreat")
    @FormUrlEncoded
    Observable<FollowOrCancelMovieBean> setCinemaCommentGreat(@Header("userId") int userId, @Header("sessionId") String sessionId, @Field("commentId") int commentId);

    //关注影院
    @GET("cinema/v1/verify/followCinema")
    Observable<FollowOrCancelMovieBean> followCinema(@Header("userId") int userId, @Header("sessionId") String sessionId, @Query("cinemaId") int cinemaId);

    //取消关注影院
    @GET("cinema/v1/verify/cancelFollowCinema")
    Observable<FollowOrCancelMovieBean> cancelFollowCinema(@Header("userId") int userId, @Header("sessionId") String sessionId, @Query("cinemaId") int cinemaId);

    //购票下单
    @POST("movie/v1/verify/buyMovieTicket")
    @FormUrlEncoded
    Observable<OrderBean> buyMovieTicket(@Header("userId") int userId, @Header("sessionId") String sessionId, @Field("scheduleId") int scheduleId, @Field("amount") int amount, @Field("sign") String sign);

    //微信支付
    @POST("movie/v1/verify/pay")
    @FormUrlEncoded
    Observable<PayWeiBean> payWei(@Header("userId") int userId, @Header("sessionId") String sessionId, @Field("payType") int payType, @Field("orderId") String orderId);

    //支付宝支付
    @POST("movie/v1/verify/pay")
    @FormUrlEncoded
    Observable<ResponseBody> payZhi(@Header("userId") int userId, @Header("sessionId") String sessionId, @Field("payType") int payType, @Field("orderId") String orderId);

    //信鸽推送
    @POST("tool/v1/verify/uploadPushToken")
    @FormUrlEncoded
    Observable<FollowOrCancelMovieBean> uploadPushToken(@Field("token") String token, @Field("os") int os);
*/
}
