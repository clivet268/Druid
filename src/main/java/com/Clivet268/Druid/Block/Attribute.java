package com.Clivet268.Druid.Block;

public enum Attribute {
    Fire("fire"),
    Water("water"),
    Lightning("lightning"),
    Contact("contact"),
    Soft("soft"),
    Smooth("smooth"),
    Sharp("sharp"),
    Tough("tough"),
    Floating("floating"),
    Heavy("heavy"),
    Time("time"),
    Sticky("sticky"),
    Pure("pure"),
    Feeding("feeding"),
    Purging("purging");


    private final String text;

    /**
     * @param text name of attribute
     */
    Attribute(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }

}
