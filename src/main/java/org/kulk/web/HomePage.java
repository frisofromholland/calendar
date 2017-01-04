package org.kulk.web;

import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.request.mapper.parameter.PageParameters;

public class HomePage extends TemplatePage {

    public HomePage(final PageParameters parameters) {
	add(new BookmarkablePageLink("templatePage", TemplatePage.class));
    }

}

