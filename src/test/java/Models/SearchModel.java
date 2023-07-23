package Models;

public class SearchModel {
    private String keyword;
    private String searchResults;



    public SearchModel() {

    }

    public SearchModel(String keyword, String searchResults) {
        this.keyword = keyword;
        this.searchResults = searchResults;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getSearchResults() {
        return searchResults;
    }

    public void setSearchResults(String searchResults) {
        this.searchResults = searchResults;
    }
}

