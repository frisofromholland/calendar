package org.kulk.web.components;

import java.util.List;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.kulk.web.util.PageParameterKey;

/**
 * User: frisokulk
 * Date: 3/7/16
 * Time: 9:26 PM
 */
public class LinkableListView<T extends Viewable> extends ListView<T> {

    private final Class responsePageClass;

    private final PageParameterKey pageParameterKey;


    public LinkableListView(String id, List<T> list, Class responsePageClass, PageParameterKey pageParameterKey) {
	super(id, list);
	this.responsePageClass = responsePageClass;
	this.pageParameterKey = pageParameterKey;
    }

    @Override
    protected void populateItem(final ListItem item) {
	final Viewable viewable = (Viewable) item.getModel().getObject();

	Link link = new Link("link") {

	    @Override
	    public void onClick() {
		final PageParameters pageParameters = new PageParameters();
		pageParameters.add(pageParameterKey.name(), viewable.getIdentification());
		setResponsePage(responsePageClass, pageParameters);

		//http://stackoverflow.com/questions/11058099/making-list-view-items-clickable
	    }
	};

	item.add(link);
	link.add(new Label("label", viewable.viewText()));
    }
}
