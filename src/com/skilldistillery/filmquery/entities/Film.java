package com.skilldistillery.filmquery.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Film {
	private int filmId;
	private String title;
	private String desc;
	private short releaseYear;
	private int langId;
	private String language;
	private int rentDur;
	private double rate;
	private int length;
	private double repCost;
	private String rating;
	private String features;
	private String category;
	List<Actor> cast;
	
	public Film() {}
	
	public Film(int filmId, String title, String desc, short releaseYear, int langId, String language, int rentDur, double rate, int length, double repCost, String rating, String features, String category) {
		this.filmId = filmId;
		this.title = title;
		this.desc = desc;
		this.releaseYear = releaseYear;
		this.langId = langId;
		this.language = language;
		this.rentDur = rentDur;
		this.rate = rate;
		this.length = length;
		this.repCost = repCost;
		this.rating = rating;
		this.features = features;
		this.category = category;
	}
	
	public String displayCast() {
		String actors;
		if (cast.size() > 0) {
			actors = "Cast: ";
			for (Actor actor : cast) {
				actors += actor.displayNameOnly();
				if (cast.indexOf(actor) < cast.size() - 1) {
					actors += ", ";
				}
			}
		}
		else {
			actors = "This film has no actors.";
		}
		return actors;
	}
	
	public String displayFullDetails() {
		return "\nTitle: " + title + "\nCategory: " + category + "\nDescription: " + desc + "\nRelease Year: " + releaseYear 
				+ "\nRating: " + rating + "\nLanguage: " + language + "\nLength: " + length + " minutes"
				+ "\nFilm ID: " + filmId + "\nRental Duration: " + rentDur + " days" + "\nRental Rate: $" + rate 
				+ "\nReplacement Cost: $" + repCost	+ "\nFeatures: " + features
				+ "\n" + displayCast();
	}

	public int getFilmId() {
		return filmId;
	}

	public void setFilmId(int filmId) {
		this.filmId = filmId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public short getReleaseYear() {
		return releaseYear;
	}

	public void setReleaseYear(short releaseYear) {
		this.releaseYear = releaseYear;
	}

	public int getLangId() {
		return langId;
	}

	public void setLangId(int langId) {
		this.langId = langId;
	}

	public int getRentDur() {
		return rentDur;
	}

	public void setRentDur(int rentDur) {
		this.rentDur = rentDur;
	}

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public double getRepCost() {
		return repCost;
	}

	public void setRepCost(double repCost) {
		this.repCost = repCost;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public String getFeatures() {
		return features;
	}

	public void setFeatures(String features) {
		this.features = features;
	}

	public List<Actor> getCast() {
		List<Actor> castCopy = new ArrayList<>(cast);
		return castCopy;
	}

	public void setCast(List<Actor> cast) {
		this.cast = cast;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cast, desc, features, filmId, langId, length, rate, rating, releaseYear, rentDur, repCost,
				title);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Film other = (Film) obj;
		return Objects.equals(cast, other.cast) && Objects.equals(desc, other.desc)
				&& Objects.equals(features, other.features) && filmId == other.filmId && langId == other.langId
				&& length == other.length && Double.doubleToLongBits(rate) == Double.doubleToLongBits(other.rate)
				&& Objects.equals(rating, other.rating) && releaseYear == other.releaseYear && rentDur == other.rentDur
				&& Double.doubleToLongBits(repCost) == Double.doubleToLongBits(other.repCost)
				&& Objects.equals(title, other.title);
	}

	@Override
	public String toString() {
		return "\nTitle: " + title + ", Release Year: " + releaseYear 
				+ ", Rating: " + rating + ", Language: " + language + "\nDescription: " + desc + "\n" + displayCast();
	}
	
}
