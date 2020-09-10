package com.oti.gadsproject;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.oti.gadsproject.databinding.ActivitySubmitBinding;
import com.oti.gadsproject.databinding.DialogFragmentDataBinding;
import com.oti.gadsproject.databinding.SubmitDialogLayoutBinding;
import com.oti.gadsproject.dialog.SubmitActivityDialogFragment;
import com.oti.gadsproject.interfaces.Api;
import com.oti.gadsproject.interfaces.SubmitDialogInterface;
import com.oti.gadsproject.interfaces.SubmitInterface;
import com.oti.gadsproject.model.ApiBuilder;
import com.oti.gadsproject.viewmodel.SubmitActivityViewModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SubmitActivity extends AppCompatActivity implements SubmitInterface, SubmitDialogInterface {

	private ActivitySubmitBinding mBinding;
	private SubmitActivityDialogFragment mDialog;
	private SubmitActivityViewModel mViewModel;
	private String TAG;

	private static void setTransparentForWindow(Activity activity) {
		Window window = activity.getWindow();
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			window.setStatusBarColor(Color.TRANSPARENT);
			window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
		} else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
			window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mBinding = DataBindingUtil.setContentView(this, R.layout.activity_submit);
		mBinding.setSubmitInterface(this);

		mBinding.setSubmitting(false);
		setTransparentForWindow(this);


		ViewModelProvider viewModelProvider = new ViewModelProvider(getViewModelStore(), ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()));
		mViewModel = viewModelProvider.get(SubmitActivityViewModel.class);

		if (!mViewModel.mIsNewlyCreated && savedInstanceState != null) {
			mViewModel.restoreState(savedInstanceState);
			restoreData();
		}
		mViewModel.mIsNewlyCreated = false;


		setSupportActionBar(mBinding.toolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);
		mBinding.toolbar.setTitle("");


	}

	private void restoreData() {
		mBinding.firstName.setText(mViewModel.name);
		mBinding.lastName.setText(mViewModel.otherName);
		mBinding.email.setText(mViewModel.email);
		mBinding.github.setText(mViewModel.githubLink);
	}

	@Override
	protected void onSaveInstanceState(@NonNull Bundle outState) {
		super.onSaveInstanceState(outState);
		if (outState != null) {
			mViewModel.saveState(outState);
		}
	}


	@Override
	public void submit() {
		submitProject();
	}

	@Override
	public boolean onOptionsItemSelected(@NonNull MenuItem item) {
		if (item.getItemId() == android.R.id.home) {
			finish();
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void back() {
		finish();
	}


	private void submitProject() {
		if (checkName(mBinding.firstName) & checkName(mBinding.lastName) & checkName(mBinding.email) & checkName(mBinding.github)) {
			mDialog = new SubmitActivityDialogFragment();
			mDialog.show(getSupportFragmentManager(), getString(R.string.dialogNull));
		}

	}

	@Override
	public void isSubmitting() {
		if (mDialog != null) mDialog.dismiss();


		mBinding.setSubmitting(true);
		mBinding.setSubmitting(false);


		String name = mBinding.firstName.getText().toString();
		String lastName = mBinding.lastName.getText().toString();
		String email = mBinding.email.getText().toString();
		String github = mBinding.github.getText().toString();


		Api api = ApiBuilder.buildSubmitService(Api.class);

		Call<Void> submitProject = api.submitProject(email, name, lastName, github);


		SubmitDialogLayoutBinding layoutBinding = SubmitDialogLayoutBinding.inflate(getLayoutInflater());
		final DialogFragmentDataBinding dataBinding = new DialogFragmentDataBinding();
		layoutBinding.setDialogFragmentDataBinding(dataBinding);


		final Toast statusToast = new Toast(this);
		statusToast.setGravity(Gravity.CENTER, 0, 0);
		statusToast.setDuration(Toast.LENGTH_SHORT);

		statusToast.setView(layoutBinding.getRoot());

		submitProject.enqueue(new Callback<Void>() {
			@Override
			public void onResponse(Call<Void> call, Response<Void> response) {
				mBinding.setSubmitting(false);


				if (response.code() != 200) {
					TAG = "SUBMIT";
					Log.e(TAG, "onResponse: " + response.code());
					return;
				}

				dataBinding.setAnimationId(R.raw.animation_sucess);
				dataBinding.setDialogText(getResources().getString(R.string.submissionSucess));
				statusToast.show();

				Log.e("SUBMIT", "onResponse: " + response.code());
			}

			@Override
			public void onFailure(Call<Void> call, Throwable t) {
				mBinding.setSubmitting(false);

				dataBinding.setAnimationId(R.raw.animation_failure);
				dataBinding.setDialogText(getResources().getString(R.string.submissionFailure));
				statusToast.show();
			}
		});
	}

	public boolean checkName(EditText text) {
		String str = text.getText().toString();
		if (str.length() == 0 || str.trim().equals("")) {
			text.setError(getString(R.string.error), getDrawable(R.drawable.ic_error));

			return false;
		} else {
			return true;
		}
	}


	@Override
	public void isCancelling() {
		if (mDialog != null) mDialog.dismiss();
		mBinding.setSubmitting(false);
	}


}
