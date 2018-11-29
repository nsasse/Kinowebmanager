package de.cofinpro.controller.dataView;

import java.io.BufferedReader;

public class TableItem {
	private String fileName;
	private BufferedReader br;
	private long size;
	private String type;

	public TableItem(String fileName,BufferedReader br, long size, String type) {
		this.fileName = fileName;
		this.br = br;
		this.size = size;
		this.type = type;
	}

	public String getFileName() {
		return fileName;
	}

	public BufferedReader getBr() {
		return br;
	}

	public long getSize() {
		return size;
	}

	public String getType() {
		return type;
	}
}