package com.mbytessolution.cocktail;

public class Cocktails {

    private String image;
    private String name;
    private String instructions;
    private String Glass;

    public Cocktails(String image, String name, String instructions, String Glass) {
        this.image = image;
        this.name = name;
        this.instructions = instructions;
        this.Glass = Glass;
    }

    public Cocktails() {
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public String getGlass() {
        return Glass;
    }

    public void setGlass(String glass) {
        Glass = glass;
    }
}
