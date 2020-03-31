package com.pakistan.recyclerviewtest;

public class Contact {
    private int image;
    private String name, lastMsg;

    public Contact() {}

    public Contact(int image, String name, String lastMsg) {
        this.image = image;
        this.name = name;
        this.lastMsg = lastMsg;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastMsg() {
        return lastMsg;
    }

    public void setLastMsg(String lastMsg) {
        this.lastMsg = lastMsg;
    }
}
