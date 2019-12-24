package com.i18n.example.model;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

public class DisplayPageModel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	


	private SelectOption selectedLocale;

	public SelectOption getSelectedLocale() {
		return selectedLocale;
	}

	public void setSelectedLocale(SelectOption selectedLocale) {
		this.selectedLocale = selectedLocale;
	}
	
}
