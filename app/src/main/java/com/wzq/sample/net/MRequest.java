package com.wzq.sample.net;

import android.annotation.SuppressLint;
import com.wzq.mvvmsmart.net.base.BaseRequest;
import com.wzq.mvvmsmart.net.base.BaseResponse;
import com.wzq.mvvmsmart.utils.constant.NetConstants;
import com.wzq.sample.bean.DemoBean;
import com.wzq.sample.bean.NewsData;
import io.reactivex.Observable;
import java.util.ArrayList;
import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * author :王志强
 * date   : 2019/11/12 11:10
 */
public class MRequest extends BaseRequest {

    private DemoApiService service;

    public static MRequest getInstance() {
        return Holder.INSTANCE;
    }

    private static class Holder {

        @SuppressLint("StaticFieldLeak")
        static MRequest INSTANCE = new MRequest();
    }

    private MRequest() {
        super();
        this.service = retrofit.create(DemoApiService.class);
    }

    // 获取列表条目
    public Observable<BaseResponse<DemoBean>> demoGet(int what, int num) {
        Observable<BaseResponse<DemoBean>> observable = service.demoGet(num);
        return observable;
    }

    // 获取新闻列表
    public Observable<BaseResponse<ArrayList<NewsData>>> doGetServerNews(int pageNum) {
        Observable<BaseResponse<ArrayList<NewsData>>> observable = service.doGetServerNews(pageNum);
        return observable;
    }

    // post请求 JSON
    public Observable<BaseResponse<ArrayList<NewsData>>> doPostServerNews(String jsonParams) {
        RequestBody requestBody = RequestBody.create(MediaType.parse(NetConstants.APPLICATION_JSON), jsonParams);
        Observable<BaseResponse<ArrayList<NewsData>>> observable = service.doPostServerNews(requestBody);
        return observable;
    }
    // post请求 返回字符串数据由用户自己解析，特定场景使用
    public Observable<String> doPostServerNewsCustom(String jsonParams) {
        RequestBody requestBody = RequestBody.create(MediaType.parse(NetConstants.APPLICATION_JSON), jsonParams);
        Observable<String> observable = service.doPostServerNewsCustom(requestBody);
        return observable;
    }
    // 动态设置baseUrl，针对baseUrl从配置文件配置的场景
    public Observable<String> doBaseUrl() {
        return service.doBaseUrl();
    }
}
