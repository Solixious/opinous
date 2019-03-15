package com.opinous.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
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
}
