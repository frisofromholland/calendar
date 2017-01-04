package org.kulk.web.pages.edit;

import org.apache.wicket.extensions.markup.html.form.DateTextField;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.kulk.db.entities.Person;
import org.kulk.service.PersonService;
import org.kulk.web.TemplatePage;

/**
 * User: frisokulk
 * Date: 3/8/16
 * Time: 10:37 AM
 */
public class CreatePersonPage extends TemplatePage {

    @SpringBean
    private PersonService personService;

    private Person person = new Person();

    private FeedbackPanel feedbackPanel = new FeedbackPanel("feedback");

    public CreatePersonPage() {

	Form form = new Form("form", new CompoundPropertyModel(person)) {
	    @Override
	    protected void onSubmit() {
		personService.save(person);
		feedbackPanel.info("Succesvol opgeslagen");
	    }
	};

	add(feedbackPanel);
	add(form);
	form.add(new RequiredTextField<String>("firstName"));
	form.add(new RequiredTextField<String>("lastName"));
	form.add(new DateTextField("dateOfBirth", "dd-MM-yyyy"));

    }
}
