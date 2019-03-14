package com.umeng.soexample.weidumovie.api;


import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface IFilmApi {

   /* *//**
     * 获取热门电影
     *
     * @param page
     * @param count
     * @return
     *//*
    @GET("movie/v1/findHotMovieList")
    Observable<HotMovieBean> findHotMovieList(@Query("page") int page, @Query("count") int count);

    *//**
     * 三个相同入参，并且出参也一样的
     * 获取三个电影列表，传入对应的url类型
     *
     * @param movieType
     * @param page
     * @param count
     * @return
     *//*
    @GET("movie/v1/{movieType}")
    Observable<ReleaseAndComingSoonMovieBean> findReleaseAndComingSoonMovieList(@Path("movieType") String movieType, @Query("page") int page, @Query("count") int count);

    *//**
     * 获取电影详情
     *
     * @param movieId
     * @return
     *//*
    @GET("movie/v1/findMoviesDetail")
    Observable<MovieDetailsBean> findMoviesDetail(@Query("movieId") int movieId);

    *//**
     * 取消或者关注电影
     *
     * @param url
     * @param movieId
     * @return
     *//*
    @GET("movie/v1/verify/{url}")
    Observable<FollowOrCancelMovieBean> followOrCancelFollowMovie(@Path("url") String url, @Query("movieId") int movieId);

    *//**
     * 根据电影ID查询当前排片该电影的影院列表
     *//*
    @GET("movie/v1/findCinemasListByMovieId")
    Observable<TicketPurchaseBean> ticketPurchaseMovie(@Query("movieId") int movieId);


    *//**
     * 根据电影id选择演播厅
     *//*
    @GET("movie/v1/findMovieScheduleList")
    Observable<TicketDetailsPageBean> studio(@Query("cinemasId") int cinemasId, @Query("movieId") int movieId);

    *//**
     * 查询影片评论
     *
     * @param movieId
     * @param page
     * @param count
     * @return
     *//*
    @GET("movie/v1/findAllMovieComment")
    Observable<CommentBean> findAllMovieComment(@Query("movieId") int movieId, @Query("page") int page, @Query("count") int count);

    *//**
     * 查看评论回复
     *
     * @param commentId
     * @param page
     * @param count
     * @return
     *//*
    @GET("movie/v1/findCommentReply")
    Observable<CommentReplyBean> findCommentReply(@Query("commentId") int commentId, @Query("page") int page, @Query("count") int count);

    *//**
     * 添加影片评论
     *
     * @param movieId
     * @param commentContent
     * @return
     *//*
    @FormUrlEncoded
    @POST("movie/v1/verify/movieComment")
    Observable<NoResultBean> movieComment(@Field("movieId") int movieId, @Field("commentContent") String commentContent);

    *//**
     * 添加用户对评论的回复
     *
     * @param commentId
     * @param replyContent
     * @return
     *//*
    @FormUrlEncoded
    @POST("movie/v1/verify/commentReply")
    Observable<NoResultBean> commentReply(@Field("commentId") int commentId, @Field("replyContent") String replyContent);

    *//**
     * 电影评论点赞
     *
     * @param commentId
     * @return
     *//*
    @FormUrlEncoded
    @POST("movie/v1/verify/movieCommentGreat")
    Observable<NoResultBean> movieCommentGreat(@Field("commentId") int commentId);

    //关注影院
    @GET("cinema/v1/verify/followCinema")
    Observable<FollowOrCancelMovieBean> followCinema(@Query("cinemaId") int cinemaId);

    //取消关注影院
    @GET("cinema/v1/verify/cancelFollowCinema")
    Observable<FollowOrCancelMovieBean> cancelFollowCinema(@Query("cinemaId") int cinemaId);
*/
}
