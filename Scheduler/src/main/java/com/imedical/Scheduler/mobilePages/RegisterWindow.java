package com.imedical.Scheduler.mobilePages;

import java.util.Vector;

import com.imedical.Scheduler.data.ProviderVO;
import com.vaadin.addon.touchkit.ui.NavigationView;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Button;
import com.vaadin.ui.Form;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.VerticalSplitPanel;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

public class RegisterWindow extends NavigationView {
	private VerticalSplitPanel verticalPanel = new VerticalSplitPanel();
	private HorizontalSplitPanel horizPanel = new HorizontalSplitPanel();
	private Form newUserForm = new Form();
	private Button submitButton = new Button("Submit");
	private Button cancelButton = new Button("Cancel");
	private Vector order = new Vector();

	public RegisterWindow(ProviderVO providerVO) {

		BeanItem<ProviderVO> providerBean = new BeanItem<ProviderVO>(providerVO);

		order.add("username");
		order.add("password");
		order.add("firstName");
		order.add("email");

		newUserForm.setItemDataSource(providerBean);
		newUserForm.setVisibleItemProperties(order);
		verticalPanel.setFirstComponent(newUserForm);
		verticalPanel.setSecondComponent(horizPanel);
		horizPanel.setWidth("50%");
		horizPanel.addComponent(submitButton);
		horizPanel.addComponent(cancelButton);

		setContent(verticalPanel);

		// setContent(registerPanel);
	}

	public void setCancelListener(ClickListener c) {
		cancelButton.addListener(c);
	}
}
