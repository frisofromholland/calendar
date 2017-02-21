package org.kulk.web.pages.user;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.validation.validator.PatternValidator;
import org.kulk.db.entities.User;
import org.kulk.service.UserService;
import org.kulk.web.TemplatePage;

/**
 * User: frisokulk
 * Date: 1/28/17
 * Time: 3:12 PM
 */
public class UserManagementPage extends TemplatePage {


    @SpringBean
    private UserService userService;

    private User user = new User();

    private FeedbackPanel feedbackPanel = new FeedbackPanel("feedback");

    private final String PASSWORD_PATTERN
	= "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})";

    public UserManagementPage() {

	Form form = new Form<Void>("userForm", new CompoundPropertyModel(user)) {
	    @Override
	    protected void onSubmit() {
		userService.save(user);
		feedbackPanel.info("Succesvol opgeslagen: " + user.getUserName());
	    }
	};
	add(feedbackPanel);
	add(form);
	form.add(new RequiredTextField<String>("userName"));

	final PasswordTextField password = new PasswordTextField("password");
	form.add(password);
	password.add(new PatternValidator(PASSWORD_PATTERN));
    }
}
