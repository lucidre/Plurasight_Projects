package com.oti.gadsproject.databinding;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.oti.gadsproject.BR;
import com.oti.gadsproject.model.LearningLeaders;
import com.oti.gadsproject.model.SkillIQLeaders;


public class ItemDataBinding extends BaseObservable {

	private LearningLeaders mItemLearning;
	private SkillIQLeaders mItemSkill;
	private String classType;

	@Bindable
	public LearningLeaders getItemLearning() {
		return mItemLearning;
	}
	public void setItemLearning(LearningLeaders itemLearning) {
		mItemLearning = itemLearning;
		notifyPropertyChanged(BR.itemLearning);
	}

	@Bindable
	public SkillIQLeaders getItemSkill() {
		return mItemSkill;
	}
	public void setItemSkill(SkillIQLeaders itemSkill) {
		mItemSkill = itemSkill;
		notifyPropertyChanged(BR.itemSkill);
	}

	@Bindable
	public String getClassType() {
		return classType;
	}
	public void setClassType(String classType) {
		this.classType = classType;
		notifyPropertyChanged(BR.classType);
	}
}
