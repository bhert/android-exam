package com.entrance.upraxismobile;
import com.entrance.upraxismobile.main.model.UsersWebService;

import java.io.File;
import java.util.concurrent.TimeUnit;
import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static final String CACHE_CONTROL = "Cache-Control";
    private static final String PRAGMA = "Pragma";
    private static String CACHE_NAME = "cache";
    private static final long cacheSize = 5 * 1024 * 1024; // 5 MB

    private static ApiClient instance;

    public static ApiClient getInstance() {
        if (instance == null) {
            instance = new ApiClient();
        }
        return instance;
    }

    public static UsersWebService getUserApi() {
        return retrofit().create(UsersWebService.class);
    }

    private static Retrofit retrofit() {
        return new Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .client(okHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    private static OkHttpClient okHttpClient() {
        return new OkHttpClient.Builder()
                .cache(cache())
                .addNetworkInterceptor(provideCacheInterceptor())
                .build();
    }

    private static Cache cache() {
        return new Cache(new File(MyApplication.getInstance().getCacheDir(), CACHE_NAME), cacheSize);
    }

    public static Interceptor provideCacheInterceptor() {
        return chain -> {
            Response response = chain.proceed(chain.request());

            CacheControl cacheControl = new CacheControl.Builder()
                    .maxAge(10, TimeUnit.MINUTES)
                    .build();

            return response.newBuilder()
                    .removeHeader(PRAGMA)
                    .removeHeader(CACHE_CONTROL)
                    .header(CACHE_CONTROL, cacheControl.toString())
                    .build();
        };
    }
}
