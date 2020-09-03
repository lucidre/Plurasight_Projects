package com.oti.plurasightprojects.databinding;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.widget.ImageView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.airbnb.lottie.LottieAnimationView;


import com.oti.plurasightprojects.R;
import com.oti.plurasightprojects.adapter.RecyclerViewAdapter;
import com.oti.plurasightprojects.adapter.ViewPagerAdapter;
import com.oti.plurasightprojects.model.LearningLeaders;
import com.oti.plurasightprojects.model.SkillIQLeaders;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ProjectBindingMethods {

	/**
	 *
	 * @param recyclerView
	 * @param learningLeaders
	 * @param skillIQLeaders
	 *
	 * set the data based on which one is empty . at psotion 0 skill iq is empty
	 */
	@BindingAdapter({"learningList", "skillIQList"})
	public static void setlearnigleaderslist(RecyclerView recyclerView, List<LearningLeaders> learningLeaders, List<SkillIQLeaders> skillIQLeaders) {
Context context = recyclerView.getContext();
		if (learningLeaders == null && skillIQLeaders == null) return;

		RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
		if (layoutManager == null) {
			recyclerView.setLayoutManager(new LinearLayoutManager(context));
		}
		RecyclerViewAdapter recyclerViewAdapter = (RecyclerViewAdapter) recyclerView.getAdapter();
		if (recyclerViewAdapter == null) {
			if (skillIQLeaders == null) {
				recyclerViewAdapter = new RecyclerViewAdapter(learningLeaders);
			}
			if (learningLeaders == null) {
				recyclerViewAdapter = new RecyclerViewAdapter(skillIQLeaders);
			}
			recyclerView.setAdapter(recyclerViewAdapter);
		}

	}


	/**
	 *
	 * @param viewPager
	 * @param learningLeaders
	 * @param skillIQLeaders
	 * use to set the current item for the view pager and the adapter . passing on the nessary layouts from the main activity
	 */
	@BindingAdapter({"viewPagerLearningLeaders", "viewPagerSkillIQLeaders"})
	public static void setViewPagerAdapter(ViewPager viewPager, List<LearningLeaders> learningLeaders, List<SkillIQLeaders> skillIQLeaders) {
		Context  context = viewPager.getContext();
		if (learningLeaders == null && skillIQLeaders == null) return;
		ViewPagerAdapter viewPagerAdapter = (ViewPagerAdapter) viewPager.getAdapter();
		if (viewPagerAdapter == null) {
			String[] titles = context.getResources().getStringArray(R.array.viewpagerTitles);
			viewPagerAdapter = new ViewPagerAdapter(context, titles, learningLeaders, skillIQLeaders);
			viewPager.setAdapter(viewPagerAdapter);
			viewPager.setCurrentItem(0);
		} else {
			viewPagerAdapter.update(learningLeaders, skillIQLeaders);
		}
	}

	@BindingAdapter("imageUrl")
	public static void imageUrl(ImageView imageView, String url) {
		Context context = imageView.getContext();

		Picasso.with(context).load(url).error(R.drawable.logo).into(imageView);

	}

	/**
	 *
	 * @param layout
	 * @param isSubmitting
	 *
	 * if is submittiong set the background of the constraint layout to semi transparent
	 */
	@BindingAdapter("foreGroundImage")
	public static void foreGroundImage(ConstraintLayout layout, boolean isSubmitting) {
		Context context = layout.getContext();
		if (isSubmitting)
			layout.setForeground(new ColorDrawable(context.getColor(R.color.semiTransparent)));
		else
			layout.setForeground(new ColorDrawable(context.getColor(android.R.color.transparent)));
	}


	@BindingAdapter("setLottieAnimation")
	public static void setLottieAnimation(LottieAnimationView lottieAnimation, int resId) {
		lottieAnimation.setAnimation(resId);
	}


}
