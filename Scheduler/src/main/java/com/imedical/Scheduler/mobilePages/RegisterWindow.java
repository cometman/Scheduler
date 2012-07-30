package com.imedical.Scheduler.mobilePages;

import java.util.Vector;

import com.imedical.Scheduler.data.ProviderVO;
import com.vaadin.addon.touchkit.ui.NavigationView;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Form;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalSplitPanel;

public class RegisterWindow extends NavigationView {
	private VerticalSplitPanel verticalPanel = new VerticalSplitPanel();
	private HorizontalSplitPanel horizPanel = new HorizontalSplitPanel();
	private Form newUserForm = new Form();
	private Button submitButton = new Button("Submit");
	private Button cancelButton = new Button("Cancel");
	private Vector order = new Vector();
	private BeanItem<ProviderVO> providerBean;

	public RegisterWindow(ProviderVO providerVO) {
//		submitButton.setP
		providerBean = new BeanItem<ProviderVO>(providerVO);

		order.add("username");

		order.add("firstName");
		order.add("email");
		order.add("password");

		newUserForm.setItemDataSource(providerBean);
		newUserForm.setVisibleItemProperties(order);
		verticalPanel.setFirstComponent(newUserForm);
		verticalPanel.setSecondComponent(horizPanel);
		horizPanel.setWidth("50%");
		horizPanel.addComponent(submitButton);
		horizPanel.addComponent(cancelButton);
		setContent(verticalPanel);
		newUserForm.addField("repassword", new TextField("Re-Password"));
		// setContent(registerPanel);
	}

	public void setCancelListener(ClickListener c) {
		cancelButton.addListener(c);
	}

	public void setRegiserListener(ClickListener c) {
		submitButton.addListener(c);
	}

	public BeanItem<ProviderVO> getProviderBeanVO() {
		return providerBean;
	}

	public String getPasswordChecker() {
		return newUserForm.getItemProperty("repassword").getValue().toString();
	}

}
