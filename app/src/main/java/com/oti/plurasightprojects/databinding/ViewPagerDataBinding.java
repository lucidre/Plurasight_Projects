package com.oti.plurasightprojects.databinding;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.oti.plurasightprojects.BR;
import com.oti.plurasightprojects.model.LearningLeaders;
import com.oti.plurasightprojects.model.SkillIQLeaders;

import java.util.List;

public class ViewPagerDataBinding extends BaseObservable {

	private List<LearningLeaders> mLearningList;
	private List<SkillIQLeaders> mSkillList;
	private int position;


	@Bindable
	public List<LearningLeaders> getLearningList() {
		return mLearningList;
	}
	public void setLearningList(List<LearningLeaders> learningList) {
		mLearningList = learningList;
		notifyPropertyChanged(BR.learningList);
	}

	@Bindable
	public List<SkillIQLeaders> getSkillList() {
		return mSkillList;
	}
	public void setSkillList(List<SkillIQLeaders> skillList) {
		mSkillList = skillList;
		notifyPropertyChanged(BR.skillList);
	}

	@Bindable
	public int getPosition() {
		return position;
	}
	public void setPosition(int position) {
		this.position = position;
		notifyPropertyChanged(BR.position);
	}
}
