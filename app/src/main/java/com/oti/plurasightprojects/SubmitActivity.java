package com.oti.plurasightprojects;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.oti.plurasightprojects.databinding.ActivitySubmitBinding;
import com.oti.plurasightprojects.dialog.SubmitActivityDialogFragment;
import com.oti.plurasightprojects.interfaces.ApiTest;
import com.oti.plurasightprojects.interfaces.SubmitDialogInterface;
import com.oti.plurasightprojects.interfaces.SubmitInterface;
import com.oti.plurasightprojects.model.ApiBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SubmitActivity extends AppCompatActivity implements SubmitInterface, SubmitDialogInterface {

	private ActivitySubmitBinding mBinding;
	private SubmitActivityDialogFragment mDialog;

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

		setSupportActionBar(mBinding.toolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);
		mBinding.toolbar.setTitle("");

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
		if (checkName(mBinding.firstName) && checkName(mBinding.lastName) && checkName(mBinding.email) && checkName(mBinding.github)) {
			mDialog = new SubmitActivityDialogFragment(Constants.TYPE_NULL);
			mDialog.show(getSupportFragmentManager(), getString(R.string.dialogNull));
		}

	}

	@Override
	public void isSubmitting() {
		if (mDialog != null) mDialog.dismiss();


		mBinding.setSubmitting(true);
		mBinding.setSubmitting(false);
		mDialog = new SubmitActivityDialogFragment(Constants.TYPE_SUCCESS);
		mDialog.show(getSupportFragmentManager(), getString(R.string.dialogSuccess));
		String name = mBinding.firstName.getText().toString();
		String lastName = mBinding.lastName.getText().toString();
		String email = mBinding.email.getText().toString();
		String github = mBinding.github.getText().toString();

//okhttp
		ApiTest api = ApiBuilder.buildSubmitService(ApiTest.class);

		Call<Void> submitProject = api.submitTestProject(email, name, lastName, github);

		submitProject.enqueue(new Callback<Void>() {
			@Override
			public void onResponse(Call<Void> call, Response<Void> response) {
				mBinding.setSubmitting(false);
				if (mDialog != null) mDialog.dismiss();
				mDialog = new SubmitActivityDialogFragment(Constants.TYPE_SUCCESS);
				mDialog.show(getSupportFragmentManager(), getString(R.string.dialogSuccess));

				Log.e("SUBMIT", "onResponse: " + response.code());
			}

			@Override
			public void onFailure(Call<Void> call, Throwable t) {
				mBinding.setSubmitting(false);
				if (mDialog != null) mDialog.dismiss();
				mDialog = new SubmitActivityDialogFragment(Constants.TYPE_FAILURE);
				mDialog.show(getSupportFragmentManager(), getString(R.string.dialogFailure));
			}
		});
	}

	public boolean checkName(EditText text) {
		String str = text.getText().toString();
		if (str.length() == 0 || str.trim().equals("")) {
			text.setError(getString(R.string.error),getDrawable(R.drawable.ic_error));

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
