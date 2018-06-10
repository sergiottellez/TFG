/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

/**
 *
 * @author sergiottellez
 */
public enum ServiceProvider {
    ORCID("/orcid", "orcid"), 
    Facebook("/facebook", "facebook");
    
    private String redirectUrl;
    private String id;
    
    ServiceProvider(String redirectUrl, String id) {
        this.redirectUrl = redirectUrl;
        this.id = id;
    }

    public String getRedirectUrl() {
        return redirectUrl;
    }
    public String getId() {
        return id;
    }
}