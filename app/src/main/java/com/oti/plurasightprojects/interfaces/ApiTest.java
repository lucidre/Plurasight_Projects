package com.oti.plurasightprojects.interfaces;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiTest {


	@POST("1FAIpQLSdE288AwwRCn-guT9bPhT4AgaFonkVusMOgUrKz19a6cFXNAw/formResponse")
	@FormUrlEncoded
	Call<Void> submitTestProject(@Field("entry.2015345940") String email, @Field("entry.2045574800") String name, @Field("entry.582505969") String lastName, @Field("entry.1487607580") String gitHubLink);


}
