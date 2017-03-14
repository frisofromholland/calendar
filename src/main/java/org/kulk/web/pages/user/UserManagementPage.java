package org.kulk.web.pages.user;

import java.util.Arrays;
import java.util.List;

import lombok.extern.log4j.Log4j;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.validation.validator.PatternValidator;
import org.kulk.db.entities.Role;
import org.kulk.db.entities.User;
import org.kulk.db.enums.RoleType;
import org.kulk.service.UserService;
import org.kulk.web.TemplatePage;

/**
 * User: frisokulk
 * Date: 1/28/17
 * Time: 3:12 PM
 */
@Log4j
public class UserManagementPage extends TemplatePage {


    @SpringBean
    private UserService userService;

    private User user = new User();

    private FeedbackPanel feedbackPanel = new FeedbackPanel("feedback");

    private Role selectedRole = new Role(RoleType.ROLE_USER);
    private List<Role> allRoles = userService.allRoles();


    private final String PASSWORD_PATTERN = ".{3,20}";

    public UserManagementPage() {

	Form form = new Form<Void>("userForm", new CompoundPropertyModel(user)) {
	    @Override
	    protected void onSubmit() {

		user.setEnabled(true);
		user.setRoles(Arrays.asList(new Role[]{ selectedRole }));

		String message = "Succesvol opgeslagen: " + user.getUserName();

		try {
		    userService.save(user);
		} catch (Throwable t) {
		    message = t.getMessage();
		}

		feedbackPanel.info(message);
		log.info(message);

	    }
	};
	add(feedbackPanel);
	add(form);
	form.add(new RequiredTextField<String>("userName"));

	final PasswordTextField password = new PasswordTextField("password");
	form.add(password);
	password.add(new PatternValidator(PASSWORD_PATTERN));

	form.add(new DropDownChoice<>("roles", new PropertyModel<Role>(this, "selectedRole"), allRoles));

    }
}
