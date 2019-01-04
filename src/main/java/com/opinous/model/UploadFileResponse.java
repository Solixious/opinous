package com.opinous.model;

public class UploadFileResponse {
    private String fileName;
    private String downloadFileUri;
    private String fileType;
    private long size;

    public UploadFileResponse(String fileName, String downloadFileUri, String fileType, long size) {
        this.fileName = fileName;
        this.downloadFileUri = downloadFileUri;
        this.fileType = fileType;
        this.size = size;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getDownloadFileUri() {
        return downloadFileUri;
    }

    public void setDownloadFileUri(String downloadFileUri) {
        this.downloadFileUri = downloadFileUri;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }
}
