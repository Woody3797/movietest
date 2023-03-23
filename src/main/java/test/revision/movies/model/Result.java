package test.revision.movies.model;


public class Result {
    
    private String id;
    private String title;
    private Integer releaseYear;


    public Result() {
    }

    public Result(String id, String title, Integer releaseYear) {
        this.id = id;
        this.title = title;
        this.releaseYear = releaseYear;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public Integer getReleaseYear() {
        return releaseYear;
    }
    public void setReleaseYear(Integer releaseYear) {
        this.releaseYear = releaseYear;
    }


    
}
