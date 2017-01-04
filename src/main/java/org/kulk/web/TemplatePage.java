package org.kulk.web;

import org.apache.wicket.Session;
import org.apache.wicket.markup.html.WebPage;
import org.kulk.web.panels.Footer;
import org.kulk.web.panels.Header;
import org.kulk.web.panels.MenuPanel;

/**
 * Created by gebruiker on 22-12-2015.
 */
public class TemplatePage extends WebPage {

    public TemplatePage() {
	add(new Header("headerPanel"));
	add(new MenuPanel("menuPanel"));
	add(new Footer("footerPanel"));
    }

    public Session session() {
	return Session.get();
    }
}
