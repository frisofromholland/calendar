package org.kulk.web.panels;

import org.apache.commons.lang3.StringUtils;
import org.apache.wicket.markup.html.panel.Panel;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * User: frisokulk
 * Date: 3/14/17
 * Time: 12:29 PM
 */
public class SecuredPanel extends Panel {

    public SecuredPanel(final String id) {
	super(id);
    }


    protected boolean prinicpalInRole(final String... roles) {
	return SecurityContextHolder.getContext().getAuthentication().getAuthorities()
	    .stream()
	    .filter(a -> StringUtils.containsAny(a.getAuthority(), roles))
	    .findAny()
	    .isPresent();
    }

    protected boolean adminAccess() {
	return prinicpalInRole("ROLE_ADMIN");
    }

}
