package com.framed.imageselector.models;

public class ImagemDto {

    private String imagemUrl;

    private String[] tagsToRemove;

    private String[] tagsToAdd;

    public ImagemDto (String url, String[] tagsToRemove, String[] tagsToAdd) {
        this.imagemUrl = url;
        this.tagsToRemove = tagsToRemove;
        this.tagsToAdd = tagsToAdd;
    }

    public String getImagemUrl() {
        return imagemUrl;
    }

    public void setImagemUrl(String imagemUrl) {
        this.imagemUrl = imagemUrl;
    }

    public String[] getTagsToRemove() {
        return tagsToRemove;
    }

    public void setTagsToRemove(String[] tagsToRemove) {
        this.tagsToRemove = tagsToRemove;
    }

    public String[] getTagsToAdd() {
        return tagsToAdd;
    }

    public void setTagsToAdd(String[] tagsToAdd) {
        this.tagsToAdd = tagsToAdd;
    }

    
    
}
