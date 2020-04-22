package com.cinema.api.model;

public class PlaceModel {

    private Long idPlace;
    private int row;
    private int site;
    private String state;
    private SeanceModel seance;
    private BillingModel billing;

    public PlaceModel() {
    }

    public PlaceModel(Long idPlace, int row, int site, String state, SeanceModel seance, BillingModel billing) {
        this.idPlace = idPlace;
        this.row = row;
        this.site = site;
        this.state = state;
        this.seance = seance;
        this.billing = billing;
    }

    public Long getIdPlace() {
        return idPlace;
    }

    public void setIdPlace(Long idPlace) {
        this.idPlace = idPlace;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getSite() {
        return site;
    }

    public void setSite(int site) {
        this.site = site;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public SeanceModel getSeance() {
        return seance;
    }

    public void setSeance(SeanceModel seance) {
        this.seance = seance;
    }

    public BillingModel getBilling() {
        return billing;
    }

    public void setBilling(BillingModel billing) {
        this.billing = billing;
    }
}
