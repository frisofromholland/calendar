package org.kulk.web.panels;

import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.panel.Panel;
import org.kulk.web.HomePage;
import org.kulk.web.pages.edit.CreatePersonPage;
import org.kulk.web.pages.search.SearchByDatePage;
import org.kulk.web.pages.search.SearchByNamePage;

/**
 * Created by gebruiker on 22-12-2015.
 */
public class MenuPanel extends Panel {

    public MenuPanel(String id) {

	super(id);

	add(new BookmarkablePageLink("home", HomePage.class));
	add(new BookmarkablePageLink("searchByName", SearchByNamePage.class));
	add(new BookmarkablePageLink("searchByMonthOfBirth", SearchByDatePage.class));
	add(new BookmarkablePageLink("createPerson", CreatePersonPage.class));

    }

}
