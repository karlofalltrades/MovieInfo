package com.simpleapp.movieinfo.model;
import java.io.Serializable;
import java.util.Date;

import com.google.gson.annotations.SerializedName;

public class Movie implements Serializable {

    public Movie(String wrapperType, String kind, int collectionId, int trackId, String artistName, String collectionName, String trackName, String collectionCensoredName, String trackCensoredName, int collectionArtistId, String collectionArtistViewUrl, String collectionViewUrl, String trackViewUrl, String previewUrl, String artworkUrl30, String artworkUrl60, String artworkUrl100, double collectionPrice, double trackPrice, double trackRentalPrice, double collectionHdPrice, double trackHdPrice, double trackHdRentalPrice, Date releaseDate, String collectionExplicitness, String trackExplicitness, int trackCount, int trackNumber, int trackTimeMillis, String country, String currency, String primaryGenreName, String contentAdvisoryRating, String shortDescription, String longDescription, boolean hasITunesExtras) {
        this.wrapperType = wrapperType;
        this.kind = kind;
        this.collectionId = collectionId;
        this.trackId = trackId;
        this.artistName = artistName;
        this.collectionName = collectionName;
        this.trackName = trackName;
        this.collectionCensoredName = collectionCensoredName;
        this.trackCensoredName = trackCensoredName;
        this.collectionArtistId = collectionArtistId;
        this.collectionArtistViewUrl = collectionArtistViewUrl;
        this.collectionViewUrl = collectionViewUrl;
        this.trackViewUrl = trackViewUrl;
        this.previewUrl = previewUrl;
        this.artworkUrl30 = artworkUrl30;
        this.artworkUrl60 = artworkUrl60;
        this.artworkUrl100 = artworkUrl100;
        this.collectionPrice = collectionPrice;
        this.trackPrice = trackPrice;
        this.trackRentalPrice = trackRentalPrice;
        this.collectionHdPrice = collectionHdPrice;
        this.trackHdPrice = trackHdPrice;
        this.trackHdRentalPrice = trackHdRentalPrice;
        this.releaseDate = releaseDate;
        this.collectionExplicitness = collectionExplicitness;
        this.trackExplicitness = trackExplicitness;
        this.trackCount = trackCount;
        this.trackNumber = trackNumber;
        this.trackTimeMillis = trackTimeMillis;
        this.country = country;
        this.currency = currency;
        this.primaryGenreName = primaryGenreName;
        this.contentAdvisoryRating = contentAdvisoryRating;
        this.shortDescription = shortDescription;
        this.longDescription = longDescription;
        this.hasITunesExtras = hasITunesExtras;
    }

    @SerializedName("wrapperType")
    String wrapperType;

    @SerializedName("kind")
    String kind;

    @SerializedName("collectionId")
    int collectionId;

    @SerializedName("trackId")
    int trackId;

    @SerializedName("artistName")
    String artistName;

    @SerializedName("collectionName")
    String collectionName;

    @SerializedName("trackName")
    String trackName;

    @SerializedName("collectionCensoredName")
    String collectionCensoredName;

    @SerializedName("trackCensoredName")
    String trackCensoredName;

    @SerializedName("collectionArtistId")
    int collectionArtistId;

    @SerializedName("collectionArtistViewUrl")
    String collectionArtistViewUrl;

    @SerializedName("collectionViewUrl")
    String collectionViewUrl;

    @SerializedName("trackViewUrl")
    String trackViewUrl;

    @SerializedName("previewUrl")
    String previewUrl;

    @SerializedName("artworkUrl30")
    String artworkUrl30;

    @SerializedName("artworkUrl60")
    String artworkUrl60;

    @SerializedName("artworkUrl100")
    String artworkUrl100;

    @SerializedName("collectionPrice")
    double collectionPrice;

    @SerializedName("trackPrice")
    double trackPrice;

    @SerializedName("trackRentalPrice")
    double trackRentalPrice;

    @SerializedName("collectionHdPrice")
    double collectionHdPrice;

    @SerializedName("trackHdPrice")
    double trackHdPrice;

    @SerializedName("trackHdRentalPrice")
    double trackHdRentalPrice;

    @SerializedName("releaseDate")
    Date releaseDate;

    @SerializedName("collectionExplicitness")
    String collectionExplicitness;

    @SerializedName("trackExplicitness")
    String trackExplicitness;

    @SerializedName("trackCount")
    int trackCount;

    @SerializedName("trackNumber")
    int trackNumber;

    @SerializedName("trackTimeMillis")
    int trackTimeMillis;

    @SerializedName("country")
    String country;

    @SerializedName("currency")
    String currency;

    @SerializedName("primaryGenreName")
    String primaryGenreName;

    @SerializedName("contentAdvisoryRating")
    String contentAdvisoryRating;

    @SerializedName("shortDescription")
    String shortDescription;

    @SerializedName("longDescription")
    String longDescription;

    @SerializedName("hasITunesExtras")
    boolean hasITunesExtras;


    public void setWrapperType(String wrapperType) {
        this.wrapperType = wrapperType;
    }
    public String getWrapperType() {
        return wrapperType;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }
    public String getKind() {
        return kind;
    }

    public void setCollectionId(int collectionId) {
        this.collectionId = collectionId;
    }
    public int getCollectionId() {
        return collectionId;
    }

    public void setTrackId(int trackId) {
        this.trackId = trackId;
    }
    public int getTrackId() {
        return trackId;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }
    public String getArtistName() {
        return artistName;
    }

    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }
    public String getCollectionName() {
        return collectionName;
    }

    public void setTrackName(String trackName) {
        this.trackName = trackName;
    }
    public String getTrackName() {
        return trackName;
    }

    public void setCollectionCensoredName(String collectionCensoredName) {
        this.collectionCensoredName = collectionCensoredName;
    }
    public String getCollectionCensoredName() {
        return collectionCensoredName;
    }

    public void setTrackCensoredName(String trackCensoredName) {
        this.trackCensoredName = trackCensoredName;
    }
    public String getTrackCensoredName() {
        return trackCensoredName;
    }

    public void setCollectionArtistId(int collectionArtistId) {
        this.collectionArtistId = collectionArtistId;
    }
    public int getCollectionArtistId() {
        return collectionArtistId;
    }

    public void setCollectionArtistViewUrl(String collectionArtistViewUrl) {
        this.collectionArtistViewUrl = collectionArtistViewUrl;
    }
    public String getCollectionArtistViewUrl() {
        return collectionArtistViewUrl;
    }

    public void setCollectionViewUrl(String collectionViewUrl) {
        this.collectionViewUrl = collectionViewUrl;
    }
    public String getCollectionViewUrl() {
        return collectionViewUrl;
    }

    public void setTrackViewUrl(String trackViewUrl) {
        this.trackViewUrl = trackViewUrl;
    }
    public String getTrackViewUrl() {
        return trackViewUrl;
    }

    public void setPreviewUrl(String previewUrl) {
        this.previewUrl = previewUrl;
    }
    public String getPreviewUrl() {
        return previewUrl;
    }

    public void setArtworkUrl30(String artworkUrl30) {
        this.artworkUrl30 = artworkUrl30;
    }
    public String getArtworkUrl30() {
        return artworkUrl30;
    }

    public void setArtworkUrl60(String artworkUrl60) {
        this.artworkUrl60 = artworkUrl60;
    }
    public String getArtworkUrl60() {
        return artworkUrl60;
    }

    public void setArtworkUrl100(String artworkUrl100) {
        this.artworkUrl100 = artworkUrl100;
    }
    public String getArtworkUrl100() {
        return artworkUrl100;
    }

    public void setCollectionPrice(double collectionPrice) {
        this.collectionPrice = collectionPrice;
    }
    public double getCollectionPrice() {
        return collectionPrice;
    }

    public void setTrackPrice(double trackPrice) {
        this.trackPrice = trackPrice;
    }
    public double getTrackPrice() {
        return trackPrice;
    }

    public void setTrackRentalPrice(double trackRentalPrice) {
        this.trackRentalPrice = trackRentalPrice;
    }
    public double getTrackRentalPrice() {
        return trackRentalPrice;
    }

    public void setCollectionHdPrice(double collectionHdPrice) {
        this.collectionHdPrice = collectionHdPrice;
    }
    public double getCollectionHdPrice() {
        return collectionHdPrice;
    }

    public void setTrackHdPrice(double trackHdPrice) {
        this.trackHdPrice = trackHdPrice;
    }
    public double getTrackHdPrice() {
        return trackHdPrice;
    }

    public void setTrackHdRentalPrice(double trackHdRentalPrice) {
        this.trackHdRentalPrice = trackHdRentalPrice;
    }
    public double getTrackHdRentalPrice() {
        return trackHdRentalPrice;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }
    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setCollectionExplicitness(String collectionExplicitness) {
        this.collectionExplicitness = collectionExplicitness;
    }
    public String getCollectionExplicitness() {
        return collectionExplicitness;
    }

    public void setTrackExplicitness(String trackExplicitness) {
        this.trackExplicitness = trackExplicitness;
    }
    public String getTrackExplicitness() {
        return trackExplicitness;
    }

    public void setTrackCount(int trackCount) {
        this.trackCount = trackCount;
    }
    public int getTrackCount() {
        return trackCount;
    }

    public void setTrackNumber(int trackNumber) {
        this.trackNumber = trackNumber;
    }
    public int getTrackNumber() {
        return trackNumber;
    }

    public void setTrackTimeMillis(int trackTimeMillis) {
        this.trackTimeMillis = trackTimeMillis;
    }
    public int getTrackTimeMillis() {
        return trackTimeMillis;
    }

    public void setCountry(String country) {
        this.country = country;
    }
    public String getCountry() {
        return country;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
    public String getCurrency() {
        return currency;
    }

    public void setPrimaryGenreName(String primaryGenreName) {
        this.primaryGenreName = primaryGenreName;
    }
    public String getPrimaryGenreName() {
        return primaryGenreName;
    }

    public void setContentAdvisoryRating(String contentAdvisoryRating) {
        this.contentAdvisoryRating = contentAdvisoryRating;
    }
    public String getContentAdvisoryRating() {
        return contentAdvisoryRating;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }
    public String getShortDescription() {
        return shortDescription;
    }

    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
    }
    public String getLongDescription() {
        return longDescription;
    }

    public void setHasITunesExtras(boolean hasITunesExtras) {
        this.hasITunesExtras = hasITunesExtras;
    }
    public boolean getHasITunesExtras() {
        return hasITunesExtras;
    }

}