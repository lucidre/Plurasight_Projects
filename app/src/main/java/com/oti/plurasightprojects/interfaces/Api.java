package com.oti.plurasightprojects.interfaces;

import com.oti.plurasightprojects.model.LearningLeaders;
import com.oti.plurasightprojects.model.SkillIQLeaders;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Api {

	@GET("api/hours")
	Call<List<LearningLeaders>> getLearningLeaders();
	@GET("api/skilliq")
	Call<List<SkillIQLeaders>> getSkillIQLeaders();

	@POST("https://docs.google.com/forms/d/e/1FAIpQLSf9d1TcNU6zc6KR8bSEM41Z1g1zl35cwZr2xyjIhaMAz8WChQ/formResponse")
	@FormUrlEncoded
	Call<Void>  submitProject(
			@Field("entry.1824927963") String email,
			@Field("entry.1877115667") String name,
			@Field("entry.2006916086") String lastName,
			@Field("entry.284483984") String gitHubLink
	);



}
