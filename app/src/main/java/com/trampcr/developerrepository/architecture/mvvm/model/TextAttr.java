package com.trampcr.developerrepository.architecture.mvvm.model;

public class TextAttr {
    private String text;
    private TextChangeListener listener;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
        if (listener != null) {
            listener.onChange(text);
        }
    }

    public void setListener(TextChangeListener listener) {
        this.listener = listener;
    }
}
