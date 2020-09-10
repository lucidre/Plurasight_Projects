package com.oti.gadsproject.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.databinding.DataBindingUtil;

import com.oti.gadsproject.R;
import com.oti.gadsproject.databinding.SubmitConfirmationLayoutBinding;
import com.oti.gadsproject.interfaces.SubmitDialogInterface;

import java.util.Objects;

public class SubmitActivityDialogFragment extends AppCompatDialogFragment {


	private SubmitDialogInterface mListener;

	public SubmitActivityDialogFragment() {

	}

	@NonNull
	@Override
	public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
		AlertDialog.Builder builder = new AlertDialog.Builder(Objects.requireNonNull(getActivity()));
		LayoutInflater layoutInflater = getActivity().getLayoutInflater();

			SubmitConfirmationLayoutBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.submit_confirmation_layout, null, false);
			binding.setSubmittingDialogInterface(mListener);
			binding.setConfirmationText(getResources().getString(R.string.submissionConfirmation));
			View view = binding.getRoot();
			builder.setView(view);
			builder.setCancelable(false);

		return builder.create();
	}

	@Override
	public void onAttach(@NonNull Context context) {
		super.onAttach(context);
		try {
			mListener = (SubmitDialogInterface) context;
		} catch (Exception e) {
			throw new ClassCastException(context.toString() + " must implement SubmitDialog Listener");
		}
	}
}

