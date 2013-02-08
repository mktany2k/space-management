package com.osm.web.action.auth;

import com.opensymphony.xwork2.ActionSupport;
import com.osm.model.User;
import com.osm.repository.UserRepository;
import java.util.Collection;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("user")
@Scope("prototype")
public class UserAction extends ActionSupport {

    private static final long serialVersionUID = 1L;
    private UserRepository userRepository;
    private Collection<User> users;

    @Actions({
        @Action(value = "/user/list", results = {
            @Result(name = SUCCESS, location = "/WEB-INF/jsp/admin/administration.jsp")
        })
    })
    public String list() throws Exception {
        users = userRepository.findAll();
        return SUCCESS;
    }

    public Collection<User> getUsers() {
        return users;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}