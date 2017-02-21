package org.kulk.application;

import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
import org.kulk.web.HomePage;
import org.kulk.web.pages.login.Login;

public class CalendarApplication extends WebApplication {

    @Override
    public Class<HomePage> getHomePage() {

	return HomePage.class; // return default page
    }

    @Override
    protected void init() {

	super.init();
	mountPage("/login", Login.class);
	getComponentInstantiationListeners().add(new SpringComponentInjector(this));

    }

}
