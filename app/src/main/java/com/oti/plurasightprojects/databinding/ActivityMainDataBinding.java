package com.oti.plurasightprojects.databinding;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.oti.plurasightprojects.BR;
import com.oti.plurasightprojects.model.LearningLeaders;
import com.oti.plurasightprojects.model.SkillIQLeaders;

import java.util.List;

public class ActivityMainDataBinding extends BaseObservable {

	private boolean downloaded;
	private boolean show;
	private List<LearningLeaders> mLearningLeaders;
	private List<SkillIQLeaders> mSkillIQLeaders;

	@Bindable
	public List<LearningLeaders> getLearningLeaders() {
		return mLearningLeaders;
	}
	public void setLearningLeaders(List<LearningLeaders> learningLeaders) {
		mLearningLeaders = learningLeaders;
		notifyPropertyChanged(BR.learningLeaders);
	}

	@Bindable
	public List<SkillIQLeaders> getSkillIQLeaders() {
		return mSkillIQLeaders;
	}
	public void setSkillIQLeaders(List<SkillIQLeaders> skillIQLeaders) {
		mSkillIQLeaders = skillIQLeaders;
		notifyPropertyChanged(BR.skillIQLeaders);
	}

	@Bindable
	public boolean isDownloaded() {
		return downloaded;
	}
	public void setDownloaded(boolean downloaded) {
		this.downloaded = downloaded;
		notifyPropertyChanged(BR.downloaded);
	}

	@Bindable
	public boolean isShow() {
		return show;
	}

	public void setShow(boolean show) {
		this.show = show;
		notifyPropertyChanged(BR.show);
	}
}
