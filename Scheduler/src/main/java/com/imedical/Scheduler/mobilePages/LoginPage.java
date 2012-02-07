package com.imedical.Scheduler.mobilePages;

import com.imedical.Scheduler.MyVaadinApplication;
import com.imedical.Scheduler.data.ProviderVO;
import com.imedical.model.ProviderModel;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Window;

public class LoginPage extends Window implements ClickListener {

	private static final long serialVersionUID = 1L;
	private TextField username = new TextField("Username");
	private TextField password = new TextField("Password");
	private Button submitButton = new Button("Login", this);
	private ProviderVO user = new ProviderVO();
	private ProviderModel providerModel;
	private Label registerLabel = new Label(
			"Not a member? No problem.  Register free here");
	private Button registerLink = new Button("Register");

	public LoginPage() {
		setClosable(false);
		setSizeFull();
		registerLink.setStyleName("link");
		registerLink.addListener(new ClickListener() {

			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				MyVaadinApplication.get().setUser("new");

			}
		});

		addComponent(username);
		addComponent(password);
		addComponent(submitButton);
		addComponent(registerLabel);

		addComponent(registerLink);

	}

	@Override
	public void buttonClick(ClickEvent event) {
		providerModel = new ProviderModel(username.getValue().toString(),
				password.getValue().toString());

		if (providerModel.getAuthStatus() == true) {
			this.getApplication().setUser(user);
		} else {
			System.out.println("Problem logging in..");
		}
	}

	public ProviderModel getProviderModel() {
		return providerModel;
	}

}
