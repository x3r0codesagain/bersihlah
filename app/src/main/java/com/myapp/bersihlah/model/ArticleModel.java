package com.myapp.bersihlah.model;

public class ArticleModel {

    String imageUrl;
    String articleContent;
    String source;
    String articleName;

    public ArticleModel(String source, String articleName) {
        this.source = source;
        this.articleName = articleName;
    }

    public String getArticleName() {
        return articleName;
    }

    public void setArticleName(String articleName) {
        this.articleName = articleName;
    }


    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}
