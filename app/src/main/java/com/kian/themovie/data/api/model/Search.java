package com.kian.themovie.data.api.model;

import com.google.gson.annotations.Expose;

/**
 * Created by kiumars on 15-04-19.
 */
public class Search {

    @Expose
    private String Title;
    @Expose
    private String Year;
    @Expose
    private String Rated;
    @Expose
    private String Released;
    @Expose
    private String Runtime;
    @Expose
    private String Genre;
    @Expose
    private String Director;
    @Expose
    private String Writer;
    @Expose
    private String Actors;
    @Expose
    private String Plot;
    @Expose
    private String Language;
    @Expose
    private String Country;
    @Expose
    private String Awards;
    @Expose
    private String Poster;
    @Expose
    private String Metascore;
    @Expose
    private String imdbRating;
    @Expose
    private String imdbVotes;
    @Expose
    private String imdbID;
    @Expose
    private String Type;
    @Expose
    private String Response;

    public String getRated() {
        return Rated;
    }

    public void setRated(String rated) {
        Rated = rated;
    }

    public String getReleased() {
        return Released;
    }

    public void setReleased(String released) {
        Released = released;
    }

    public String getRuntime() {
        return Runtime;
    }

    public void setRuntime(String runtime) {
        Runtime = runtime;
    }

    public String getGenre() {
        return Genre;
    }

    public void setGenre(String genre) {
        Genre = genre;
    }

    public String getDirector() {
        return Director;
    }

    public void setDirector(String director) {
        Director = director;
    }

    public String getWriter() {
        return Writer;
    }

    public void setWriter(String writer) {
        Writer = writer;
    }

    public String getActors() {
        return Actors;
    }

    public void setActors(String actors) {
        Actors = actors;
    }

    public String getPlot() {
        return Plot;
    }

    public void setPlot(String plot) {
        Plot = plot;
    }

    public String getLanguage() {
        return Language;
    }

    public void setLanguage(String language) {
        Language = language;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public String getAwards() {
        return Awards;
    }

    public void setAwards(String awards) {
        Awards = awards;
    }

    public String getPoster() {
        return Poster;
    }

    public void setPoster(String poster) {
        Poster = poster;
    }

    public String getMetascore() {
        return Metascore;
    }

    public void setMetascore(String metascore) {
        Metascore = metascore;
    }

    public String getImdbRating() {
        return imdbRating;
    }

    public void setImdbRating(String imdbRating) {
        this.imdbRating = imdbRating;
    }

    public String getImdbVotes() {
        return imdbVotes;
    }

    public void setImdbVotes(String imdbVotes) {
        this.imdbVotes = imdbVotes;
    }


    public String getResponse() {
        return Response;
    }

    public void setResponse(String response) {
        Response = response;
    }

    /**
     * @return The Title
     */
    public String getTitle() {
        return Title;
    }

    /**
     * @param Title The Title
     */
    public void setTitle(String Title) {
        this.Title = Title;
    }

    /**
     * @return The Year
     */
    public String getYear() {
        return Year;
    }

    /**
     * @param Year The Year
     */
    public void setYear(String Year) {
        this.Year = Year;
    }

    /**
     * @return The imdbID
     */
    public String getImdbID() {
        return imdbID;
    }

    /**
     * @param imdbID The imdbID
     */
    public void setImdbID(String imdbID) {
        this.imdbID = imdbID;
    }

    /**
     * @return The Type
     */
    public String getType() {
        return Type;
    }

    /**
     * @param Type The Type
     */
    public void setType(String Type) {
        this.Type = Type;
    }

}
