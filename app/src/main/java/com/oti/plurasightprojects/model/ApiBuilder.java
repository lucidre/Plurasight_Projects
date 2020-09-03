package com.oti.plurasightprojects.model;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiBuilder {

	private static HttpLoggingInterceptor logger = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);

	private  static OkHttpClient.Builder oBuilder = new OkHttpClient.Builder().addInterceptor(logger);

	private static final String BASE_API_URL = "https://gadsapi.herokuapp.com/";
	private static final String SUBMIT_API_URL = "https://docs.google.com/forms/d/e/1FAIpQLSf9d1TcNU6zc6KR8bSEM41Z1g1zl35cwZr2xyjIhaMAz8WChQ/formResponse";

	private static Retrofit.Builder sBuilder = new Retrofit.Builder().baseUrl(BASE_API_URL).addConverterFactory(GsonConverterFactory.create()).client(oBuilder.build());
	private static Retrofit sRetrofit = sBuilder.build() ;

	public  static <S> S buildService (Class<S> serviceType){
		return  sRetrofit.create(serviceType);
	}
}
