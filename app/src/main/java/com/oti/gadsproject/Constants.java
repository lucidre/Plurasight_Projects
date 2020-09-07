package com.oti.gadsproject;

import com.oti.gadsproject.model.LearningLeaders;
import com.oti.gadsproject.model.SkillIQLeaders;

public class Constants {
	public static final int SKILL_IQ_POS = 1;
	public static final int LEARNING_IQ_POS = 0;
	public static final String TYPE_SUCCESS = "success";
	public static final String TYPE_FAILURE = "failure";
	public static final String TYPE_NULL = null;
	public static final String NAME_ENTRY_ID = "entry.1877115667";
	public static final String LAST_NAME_ENTRY_ID = "entry.2006916086";
	public static final String EMAIL_ENTRY_ID = "entry.1824927963";
	public static final String GITHUB_LINK_ENTRY_ID = "entry.284483984";
	public static final String SUBMIT_POST_URL = "1FAIpQLSf9d1TcNU6zc6KR8bSEM41Z1g1zl35cwZr2xyjIhaMAz8WChQ/formResponse";
	public static final String SKILL_IQ_GET_URL = "api/skilliq";
	public static final String LEARNING_LEADERS_GET_URL = "api/hours";
	public static final String BASE_API_URL = "https://gadsapi.herokuapp.com/";

	public static final String SUBMIT_API_URL = "https://docs.google.com/forms/d/e/";
	public static final String LEARNING_TYPE = LearningLeaders.class.toString();
	public static final String SKILL_TYPE = SkillIQLeaders.class.toString();
	public static final String LEARNING_LEADERS_ARRAY_LIST = "com.oti.gadsproject.LEARNING_LEADERS_ARRAY_LIST";
	public static final String SKILL_IQ_ARRAY_LIST = "com.oti.gadsproject.SKILL_IQ_ARRAY_LIST";
	public static final String NAME = "com.oti.gadsproject.NAME";
	public static final String OTHER_NAME = "com.oti.gadsproject.OTHER_NAME";
	public static final String EMAIL = "com.oti.gadsproject.EMAIL";
	public static final String GITHUB_LINK = "com.oti.gadsproject.GITHUB_LINK";

	private Constants() {
	}
}
