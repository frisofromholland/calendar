package org.kulk.web.pages.search;

/**
 * Created by gebruiker on 20-12-2015.
 */

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.kulk.db.entities.Person;
import org.kulk.service.PersonService;
import org.kulk.web.TemplatePage;
import org.kulk.web.components.LinkableListView;
import org.kulk.web.pages.edit.EditPersonPage;
import org.kulk.web.util.PageParameterKey;

public class SearchByNamePage extends TemplatePage {

    @SpringBean
    private PersonService personService;


    private List<Person> persons = new ArrayList<>();

    private Person person = new Person();

    private TextField<String> nameField;
    private FeedbackPanel feedbackPanel = new FeedbackPanel("feedback");
    private String noPersonFoundFeedbackMessage = "Niemand gevonden met naam ' %s '";

    private ListView<Person> searchResultList = new LinkableListView("searchResultList", persons, EditPersonPage.class, PageParameterKey.PERSON);

    public SearchByNamePage() {

	nameField = new TextField<>("nameField", new PropertyModel<>(person, "firstName"));

	Form form = new Form<Void>("form") {

	    @Override
	    protected void onSubmit() {
		persons = personService.getPersonByName(nameField.getInput());

		if (CollectionUtils.isEmpty(persons)) {
		    searchResultList.setList(new ArrayList<>());
		    feedbackPanel.error(String.format(noPersonFoundFeedbackMessage, nameField.getInput()));
		} else {
		    searchResultList.setList(persons);
		}
	    }

	};

	add(feedbackPanel);
	add(form);
	form.add(nameField);
	add(searchResultList);

    }

}