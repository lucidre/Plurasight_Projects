package com.oti.plurasightprojects.interfaces;

import com.oti.plurasightprojects.model.LearningLeaders;
import com.oti.plurasightprojects.model.SkillIQLeaders;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

import static com.oti.plurasightprojects.Constants.EMAIL_ENTRY_ID;
import static com.oti.plurasightprojects.Constants.GITHUB_LINK_ENTRY_ID;
import static com.oti.plurasightprojects.Constants.LAST_NAME_ENTRY_ID;
import static com.oti.plurasightprojects.Constants.LEARNING_LEADERS_GET_URL;
import static com.oti.plurasightprojects.Constants.NAME_ENTRY_ID;
import static com.oti.plurasightprojects.Constants.SKILL_IQ_GET_URL;
import static com.oti.plurasightprojects.Constants.SUBMIT_POST_URL;

public interface Api {

	@GET(LEARNING_LEADERS_GET_URL)
	Call<List<LearningLeaders>> getLearningLeaders();

	@GET(SKILL_IQ_GET_URL)
	Call<List<SkillIQLeaders>> getSkillIQLeaders();

	@POST(SUBMIT_POST_URL)
	@FormUrlEncoded
	Call<Void> submitProject(@Field(EMAIL_ENTRY_ID) String email, @Field(NAME_ENTRY_ID) String name, @Field(LAST_NAME_ENTRY_ID) String lastName, @Field(GITHUB_LINK_ENTRY_ID) String gitHubLink);


}
