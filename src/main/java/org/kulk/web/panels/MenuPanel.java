package org.kulk.web.panels;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.kulk.web.HomePage;
import org.kulk.web.pages.edit.CreatePersonPage;
import org.kulk.web.pages.logout.Logout;
import org.kulk.web.pages.search.SearchByDatePage;
import org.kulk.web.pages.search.SearchByNamePage;
import org.kulk.web.pages.user.UserManagementPage;

/**
 * Created by gebruiker on 22-12-2015.
 */
public class MenuPanel extends SecuredPanel {

    public MenuPanel(String id) {

	super(id);

	final boolean adminUser = adminAccess();

	add(new BookmarkablePageLink("home", HomePage.class));
	add(new BookmarkablePageLink("searchByName", SearchByNamePage.class));
	add(new BookmarkablePageLink("searchByMonthOfBirth", SearchByDatePage.class));
	add(adminUser ? new BookmarkablePageLink("createPerson", CreatePersonPage.class) : new Label("createPerson"));
	add(adminUser ? new BookmarkablePageLink("userManagement", UserManagementPage.class) : new Label("userManagement"));
	add(new BookmarkablePageLink("logout", Logout.class));
    }


}
