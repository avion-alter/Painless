package com.ucsf.painless.model;

import java.util.List;

public class PackageModel {
    List<String> priceList;
    String Name;
    String description;
    String selectedOption;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSelectedOption() {
        return selectedOption;
    }

    public void setSelectedOption(String selectedOption) {
        this.selectedOption = selectedOption;
    }


    public PackageModel( String name,List<String> priceList, String description) {
        this.priceList = priceList;
        Name = name;
        this.description = description;
    }

    public List<String> getPriceList() {
        return priceList;
    }

    public void setPriceList(List<String> priceList) {
        this.priceList = priceList;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
