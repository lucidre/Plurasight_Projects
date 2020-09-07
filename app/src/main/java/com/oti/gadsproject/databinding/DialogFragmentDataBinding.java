package com.oti.gadsproject.databinding;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.oti.gadsproject.BR;

public class DialogFragmentDataBinding extends BaseObservable {


	private String dialogText;
	private int animationId;


	@Bindable
	public String getDialogText() {
		return dialogText;
	}

	public void setDialogText(String dialogText) {
		this.dialogText = dialogText;
		notifyPropertyChanged(BR.dialogText);
	}

	@Bindable
	public int getAnimationId() {
		return animationId;
	}

	public void setAnimationId(int animationId) {
		this.animationId = animationId;
		notifyPropertyChanged(BR.animationId);
	}
}
