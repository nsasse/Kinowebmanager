package de.cofinpro.controller.dataView;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;

@ManagedBean(name="imagesView")
public class ImagesView {

	private List<String> images;

	public void init() {
		images = new ArrayList<String>();
		for (int i = 1; i <= 3; i++) {
			images.add("slider_" + i + ".jpg");
		}
	}

	public List<String> getImages() {
		return images;
	}
}
