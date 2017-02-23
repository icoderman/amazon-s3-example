package com.icoderman;

import lombok.Getter;

public enum ContentType {
    JPEG(0, "jpg", "image/jpeg"),
    PNG(1, "png", "image/png"),
    PDF(2, "pdf", "application/pdf");

    @Getter
    private final int id;

    @Getter
    private final String format;

    @Getter
    private final String mimeType;

    ContentType(int id, String format, String mimeType) {
        this.id = id;
        this.format = format;
        this.mimeType = mimeType;
    }

    public static ContentType getByFormat(String format) {
        for (ContentType type : ContentType.values()) {
            if (type.getFormat().equals(format)) {
                return type;
            }
        }
        throw new IllegalArgumentException("No matching ContentType for the format: " + format);
    }

    public static ContentType getByMimeType(String mimeType) {
        for (ContentType type : ContentType.values()) {
            if (type.getMimeType().equals(mimeType)) {
                return type;
            }
        }
        throw new IllegalArgumentException("No matching ContentType for the mimeType: " + mimeType);
    }

    public static ContentType getById(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("Id can't be null");
        }
        for (ContentType type : ContentType.values()) {
            if (type.getId() == id) {
                return type;
            }
        }
        throw new IllegalArgumentException("No matching ContentType for the Id: " + id);
    }
}