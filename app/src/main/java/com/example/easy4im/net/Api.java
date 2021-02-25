
package com.example.easy4im.net;

import com.example.easy4im.bean.res.Result;
import com.example.easy4im.bean.res.UserResVo;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * @Author yang.zhao
 * Date: 2021/1/29
 * Description:
 **/
public interface Api {
    static final String BASE_URL="http://victorzy.top:8081";
    @POST("/v1/api/login/byPwd")
    Observable<Result> login(@Body RequestBody requestBody);
//    @POST("api/user/registByMobile")
//    Observable<Result<User>> register(@Body RequestBody requestBody);
//    @POST("api/friend/friends")
//    Observable<Result<List<Friend>>> getFriends( );
}

