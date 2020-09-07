package com.oti.gadsproject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.oti.gadsproject.databinding.ActivityMainBinding;
import com.oti.gadsproject.databinding.ActivityMainDataBinding;
import com.oti.gadsproject.interfaces.Api;
import com.oti.gadsproject.interfaces.UpdateUserInterface;
import com.oti.gadsproject.model.ApiBuilder;
import com.oti.gadsproject.model.LearningLeaders;
import com.oti.gadsproject.model.SkillIQLeaders;
import com.oti.gadsproject.viewmodel.MainActivityViewModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements UpdateUserInterface {


	boolean mSkillFetched;
	boolean mLearningLeadersFetched;
	long CURRENT_TIME = 0;
	private List<LearningLeaders> mLearningLeaders;
	private List<SkillIQLeaders> mSkillIQLeaders;
	private ActivityMainBinding mBinding;
	private ActivityMainDataBinding mActivityMainDataBinding;
	private MainActivityViewModel mViewModel;
	private String TAG;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

		setTransparentBackground(this);


		mBinding.setUpdateui(this);
		mSkillFetched = false;
		mLearningLeadersFetched = false;
		mActivityMainDataBinding = new ActivityMainDataBinding();
		mBinding.setDataview(mActivityMainDataBinding);


		ViewModelProvider viewModelProvider = new ViewModelProvider(getViewModelStore(), ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()));
		mViewModel = viewModelProvider.get(MainActivityViewModel.class);

		if (!mViewModel.mIsNewlyCreated && savedInstanceState != null) {
			mViewModel.restoreState(savedInstanceState);
			restoreData();
		} else {
			getData(this);
		}
		mViewModel.mIsNewlyCreated = false;

	}

	private void restoreData() {
		mLearningLeaders = mViewModel.mLearningLeaders;
		mSkillIQLeaders = mViewModel.mSkillIQLeaders;
		mSkillFetched = true;
		mLearningLeadersFetched = true;
		updateUi();
	}

	@Override
	protected void onSaveInstanceState(@NonNull Bundle outState) {
		super.onSaveInstanceState(outState);
		if (outState != null) {
			mViewModel.saveState(outState);
		}
	}

	private void setTransparentBackground(Activity activity) {
		Window window = activity.getWindow();
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			window.setStatusBarColor(Color.TRANSPARENT);
			window.setNavigationBarColor(Color.TRANSPARENT);
			window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
		} else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
			window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
		}
	}


	public void updateUi() {
		if (mSkillFetched && mLearningLeadersFetched) {
			mActivityMainDataBinding.setDownloaded(true);
			mActivityMainDataBinding.setShow(true);
			mActivityMainDataBinding.setLearningLeaders(mLearningLeaders);
			mActivityMainDataBinding.setSkillIQLeaders(mSkillIQLeaders);
			mBinding.tab.setupWithViewPager(mBinding.viewpager);
		}
	}

	private boolean isConnected(Context context) {
		ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

		NetworkInfo wifiNetwork = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
		NetworkInfo mobileNetwork = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

		return (wifiNetwork != null && wifiNetwork.isConnected()) || (mobileNetwork != null && mobileNetwork.isConnected());


	}

	/**
	 * @param context check internet connecting if not availiable disable the viewpager, progressbar and display group
	 */
	public void getData(Context context) {

		if (isConnected(context)) {
			Api api = ApiBuilder.buildService(Api.class);
			Call<List<LearningLeaders>> learningLeadersCall = api.getLearningLeaders();
			Call<List<SkillIQLeaders>> skillIQCall = api.getSkillIQLeaders();
			learningLeadersCall.enqueue(new Callback<List<LearningLeaders>>() {
				@Override
				public void onResponse(Call<List<LearningLeaders>> call, Response<List<LearningLeaders>> response) {

					if (response.code() != 200) {
						TAG = "LEARNING LEADERS";
						Log.e(TAG, "onResponse: " + response.code());

						return;
					}
					mLearningLeaders = response.body();
					mViewModel.mLearningLeaders = (ArrayList<LearningLeaders>) mLearningLeaders;
					mLearningLeadersFetched = true;
					updateUi();
				}


				@Override
				public void onFailure(Call<List<LearningLeaders>> call, Throwable t) {
					mActivityMainDataBinding.setDownloaded(false);
					mActivityMainDataBinding.setShow(true);
					mBinding.txtError.setText(R.string.oops_developer);
					Toast.makeText(MainActivity.this, "error on fetching learning leaders", Toast.LENGTH_SHORT).show();
				}
			});
			skillIQCall.enqueue(new Callback<List<SkillIQLeaders>>() {
				@Override
				public void onResponse(Call<List<SkillIQLeaders>> call, Response<List<SkillIQLeaders>> response) {

					if (response.code() != 200) {
						TAG = "SKILL IQ";
						Log.e(TAG, "onResponse: " + response.code());
						return;
					}

					mSkillIQLeaders = response.body();
					mViewModel.mSkillIQLeaders = (ArrayList<SkillIQLeaders>) mSkillIQLeaders;
					mSkillFetched = true;
					updateUi();
				}

				@Override
				public void onFailure(Call<List<SkillIQLeaders>> call, Throwable t) {
					mActivityMainDataBinding.setDownloaded(false);
					mActivityMainDataBinding.setShow(true);
					mBinding.txtError.setText(R.string.oops_developer);
					Toast.makeText(MainActivity.this, "error on fetching skill iq", Toast.LENGTH_SHORT).show();
				}
			});
		} else {
			mActivityMainDataBinding.setDownloaded(false);
			mActivityMainDataBinding.setShow(true);
			mBinding.txtError.setText(R.string.network_error);
		}
	}

	@Override
	public void submit() {
		Intent intent = new Intent(MainActivity.this, SubmitActivity.class);
		startActivity(intent);
	}

	@Override
	public void refresh() {
		mActivityMainDataBinding.setShow(false);
		mActivityMainDataBinding.setDownloaded(false);

		getData(MainActivity.this);
	}

	@Override
	public void onBackPressed() {
		if (CURRENT_TIME == 0) {
			CURRENT_TIME = System.currentTimeMillis();
			Toast.makeText(this, R.string.exit, Toast.LENGTH_SHORT).show();
		} else if (SystemClock.elapsedRealtime() <= CURRENT_TIME + 4000) {
			CURRENT_TIME = 0;
			super.onBackPressed();
		}
	}


}
