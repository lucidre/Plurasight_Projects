package com.oti.plurasightprojects.model;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;

import com.oti.plurasightprojects.R;

public class SkillIQLeaders implements Parcelable {

	private String name;
	private int score;
	private String country;
	private String badgeUrl;

	public SkillIQLeaders(String name, int score, String country, String badgeUrl) {
		this.name = name;
		this.score = score;
		this.country = country;
		this.badgeUrl = badgeUrl;
	}

	public static final Creator<SkillIQLeaders> CREATOR = new Creator<SkillIQLeaders>() {
		@Override
		public SkillIQLeaders createFromParcel(Parcel in) {
			return new SkillIQLeaders(in);
		}

		@Override
		public SkillIQLeaders[] newArray(int size) {
			return new SkillIQLeaders[size];
		}
	};

	protected SkillIQLeaders(Parcel in) {
		name = in.readString();
		score = in.readInt();
		country = in.readString();
		badgeUrl = in.readString();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
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
		return getScore()+" "+context.getResources().getString(R.string.skill_iq)+", "+getCountry();
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel parcel, int i) {
		parcel.writeString(name);
		parcel.writeInt(score);
		parcel.writeString(country);
		parcel.writeString(badgeUrl);
	}
}
