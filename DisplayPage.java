package com.i18n.example.pages;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.apache.wicket.Session;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

import com.i18n.example.model.DisplayPageModel;
import com.i18n.example.model.SelectOption;

public class DisplayPage extends Panel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Form<?> form = null;
	DisplayPageModel displayPageModel;

	public DisplayPage(String id, DisplayPageModel displayPageModel) {
		super(id);
		this.displayPageModel = displayPageModel;
	}

	@Override
	protected void onInitialize() {
		// TODO Auto-generated method stub
		super.onInitialize();

		final DropDownChoice<SelectOption> localeChoice = createDropDown();
		form = new Form<Object>("form");
		AbstractReadOnlyModel<String> model = new AbstractReadOnlyModel<String>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public String getObject() {
				return getString("greetingMessage");
			}
		};
		final Label label = new Label("label", model);
		
		label.setOutputMarkupId(true);

		localeChoice.setModelObject(new SelectOption("en", "English"));
		Session.get().setLocale(new Locale("en"));
		form.add(localeChoice);
		form.add(label);

		add(form);

	}

	private ArrayList<SelectOption> getDisplayValues() {
		Map<String, String> localeMapTemp = new HashMap<String, String>();

		localeMapTemp.put(" ", "General");
		localeMapTemp.put("en", "UK");
		localeMapTemp.put("de", "Germany");
		localeMapTemp.put("nl", "Netherlands");
		localeMapTemp.put("zh_CN", "China");
		ArrayList<SelectOption> list = new ArrayList<SelectOption>();
		for (Map.Entry<String, String> entry : localeMapTemp.entrySet()) {
			list.add(new SelectOption(entry.getKey(), entry.getValue()));
		}

		return list;
	}

	private DropDownChoice<SelectOption> createDropDown() {

		DropDownChoice<SelectOption> localeChoice = new DropDownChoice<SelectOption>(
				"dropDwonChoice", new PropertyModel<SelectOption>(
						displayPageModel, "selectedLocale"),
				getDisplayValues(), new ChoiceRenderer<SelectOption>("value",
						"key")) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			protected boolean wantOnSelectionChangedNotifications() {

				return true;
			}

			@Override
			protected void onSelectionChanged(SelectOption newSelection) {
				Session.get().setLocale(
						new Locale(((SelectOption) getModelObject()).getKey()));
			}
		};

		return localeChoice;

	}

}
