package org.kulk.web.pages.login;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.kulk.web.panels.Header;

/**
 * User: frisokulk
 * Date: 2/12/17
 * Time: 11:02 PM
 */
public class Login extends WebPage {

    public Login(final PageParameters parameters) {
	success(parameters);
	add(new Header("headerPanel"));
    }

}
