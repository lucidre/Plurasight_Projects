package com.oti.gadsproject.model;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;

import com.oti.gadsproject.R;

public class LearningLeaders implements Parcelable {

	private String name;
	private int hours;
	private String country;
	private String badgeUrl;



	public LearningLeaders(String name, int hours, String country, String badgeUrl) {
		this.name = name;
		this.hours = hours;
		this.country = country;
		this.badgeUrl = badgeUrl;
	}

	public static final Creator<LearningLeaders> CREATOR = new Creator<LearningLeaders>() {
		@Override
		public LearningLeaders createFromParcel(Parcel in) {
			return new LearningLeaders(in);
		}

		@Override
		public LearningLeaders[] newArray(int size) {
			return new LearningLeaders[size];
		}
	};

	protected LearningLeaders(Parcel in) {
		name = in.readString();
		hours = in.readInt();
		country = in.readString();
		badgeUrl = in.readString();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getHours() {
		return hours;
	}

	public void setHours(int hours) {
		this.hours = hours;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getBadgeUrl() {
		return badgeUrl;
	}

	public void setBadgeUrl(String badgeUrl) {
		this.badgeUrl = badgeUrl;
	}

	public String getDescription(Context context) {
		return getHours()+" "+context.getResources().getString(R.string.learning_hours)+", "+getCountry();
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel parcel, int i) {
		parcel.writeString(name);
		parcel.writeInt(hours);
		parcel.writeString(country);
		parcel.writeString(badgeUrl);
	}
}
