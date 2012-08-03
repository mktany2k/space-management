package com.scwcd.process.login.core;


import com.scwcd.enterprise.sql.dao.DAOUser;
import com.scwcd.enterprise.sql.dao.UserParameter;
import com.scwcd.enterprise.sql.hbm.User;
import com.scwcd.framework.business.core.AbstractBusinessService;
import com.scwcd.framework.sql.core.DAOFactory;


class ServiceLogin extends AbstractBusinessService<User> {

	private String username;

	private char[] password;

	@Override
	public void perform() {
		DAOFactory daoFactory = DAOFactory.getInstance();
		DAOUser daoUser = (DAOUser) daoFactory.getInstance(DAOUser.class);
		User user = daoUser.doSelect(new UserParameter(username));

		// verify user
		if (user == null) {
			setRcCode(2000);
			return;
		}

		// verify password
		char[] _password = user.getPassword();
		if (password.length != _password.length) {
			setRcCode(2001);
			return;
		} else {
			for (int i = 0; i < password.length; i++) {
				if (password[i] != _password[i]) {
					setRcCode(2001);
					return;
				}
			}
		}

		// no error
		setRcCode(0);
		setOutput(user);
	}

	public void setUsername(final String username) {
		this.username = username;
	}

	public void setPassword(final char[] password) {
		this.password = password;
	}
}