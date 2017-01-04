package org.kulk.web.pages.search;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
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

/**
 * Created by gebruiker on 6-12-2015.
 */
public class SearchByDatePage extends TemplatePage {

    @SpringBean
    private PersonService personService;
    private List<Person> persons = new ArrayList<Person>();

    private static final List<String> MONTHS = Arrays.asList(new String[]{ "januari", "februari", "maart", "april", "mei", "juni", "juli", "augustus", "september", "oktober", "november", "december" });
    private String selectedMonth = "0";
    private DropDownChoice<String> birthMonthDropdown = new DropDownChoice<>("birthMonthDropdown", new PropertyModel<>(this, "selectedMonth"), MONTHS);
    ;

    private FeedbackPanel feedbackPanel = new FeedbackPanel("feedback");
    private String noPersonFoundFeedbackMessage = "Niemand gevonden met geboortemaand '%s'";

    private ListView<Person> searchResultList = new LinkableListView("searchResultList", persons, EditPersonPage.class, PageParameterKey.PERSON);

    public SearchByDatePage() {

	Form form = new Form<Void>("form") {
	    @Override
	    protected void onSubmit() {
		selectedMonth = birthMonthDropdown.getInput();
		int selectedIndex = 0;
		try {
		    selectedIndex = Integer.parseInt(selectedMonth);
		} catch (NumberFormatException e) {
		    e.printStackTrace();
		}
		persons = personService.getPersonsByMonthOfBirth(selectedIndex);
		if (CollectionUtils.isEmpty(persons)) {
		    searchResultList.setList(new ArrayList<>());
		    error(String.format(noPersonFoundFeedbackMessage, MONTHS.get(selectedIndex)));
		} else {
		    searchResultList.setList(persons);
		}

	    }
	};

	//add components;
	add(searchResultList);
	add(feedbackPanel);
	add(form);
	form.add(birthMonthDropdown);
    }


}
