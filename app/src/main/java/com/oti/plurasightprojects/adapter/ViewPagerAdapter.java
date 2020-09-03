package com.oti.plurasightprojects.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import androidx.viewpager.widget.PagerAdapter;

import com.oti.plurasightprojects.R;
import com.oti.plurasightprojects.databinding.ViewPagerDataBinding;

import com.oti.plurasightprojects.databinding.ViewpagerItemBinding;
import com.oti.plurasightprojects.model.LearningLeaders;
import com.oti.plurasightprojects.model.SkillIQLeaders;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ViewPagerAdapter extends PagerAdapter {


	private LayoutInflater layoutInflater;
	private String[] mTitles;
	private List<LearningLeaders> mLearningLeaders;
	private List<SkillIQLeaders> mSkillIQLeaders;

	public ViewPagerAdapter(Context context, String[] titles, List<LearningLeaders> learningLeaders, List<SkillIQLeaders> skillIQLeaders) {
		this.mTitles = titles;
		this.mLearningLeaders = learningLeaders;
		this.mSkillIQLeaders = skillIQLeaders;
		layoutInflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return mTitles.length;
	}

	@Override
	public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
		return view == object;
	}

	@NotNull
	public Object instantiateItem(@NonNull ViewGroup container, int position) {
		ViewpagerItemBinding itemBinding = DataBindingUtil.inflate(layoutInflater, R.layout.viewpager_item, null, false);

		ViewPagerDataBinding viewPagerDataBinding = new ViewPagerDataBinding();
		viewPagerDataBinding.setLearningList(mLearningLeaders);
		viewPagerDataBinding.setSkillList(mSkillIQLeaders);
		viewPagerDataBinding.setPosition(position);
		itemBinding.setViewPagerDataBinding(viewPagerDataBinding);

		container.addView(itemBinding.getRoot());
		return itemBinding.getRoot();
	}

	@Override
	public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
		container.removeView((View) object);
	}

	@Nullable
	@Override
	public CharSequence getPageTitle(int position) {
		return mTitles[position];
	}


	public void update(List<LearningLeaders> learningLeaders, List<SkillIQLeaders> skillIQLeaders) {
		mLearningLeaders = learningLeaders;
		mSkillIQLeaders = skillIQLeaders;
		notifyDataSetChanged();
	}
}
