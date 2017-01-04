package org.kulk.web.pages.edit;

import org.apache.wicket.extensions.markup.html.form.DateTextField;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.kulk.db.entities.Person;
import org.kulk.service.PersonService;
import org.kulk.web.TemplatePage;
import org.kulk.web.util.PageParameterKey;

/**
 * User: frisokulk
 * Date: 3/12/16
 * Time: 10:40 PM
 */
public class EditPersonPage extends TemplatePage {

    @SpringBean
    private PersonService personService;

    private Person person;

    private FeedbackPanel feedbackPanel = new FeedbackPanel("feedback");

    public EditPersonPage(PageParameters pageParameters) {

	String id = pageParameters.get(PageParameterKey.PERSON.name()).toString();
	person = personService.getPersonByID(id);

	if (person == null || person.getId() == null) {
	    person = new Person();
	    feedbackPanel.info("Geen bestaand persooon beschikbaar");
	}

	Form editForm = new Form("editForm", new CompoundPropertyModel(person)) {
	    @Override
	    protected void onSubmit() {
		if (person == null || person.getId() == null) {
		    feedbackPanel.info("Wijzigen niet gelukt: persoon niet bekend");
		} else {
		    personService.update(person);
		    feedbackPanel.info("Wijziging succesvol opgeslagen");
		}
	    }
	};

	add(feedbackPanel);

	add(editForm);
	editForm.add(new RequiredTextField<String>("firstName"));
	editForm.add(new RequiredTextField<String>("lastName"));
	editForm.add(new DateTextField("dateOfBirth", "dd-MM-yyyy"));


    }
}
