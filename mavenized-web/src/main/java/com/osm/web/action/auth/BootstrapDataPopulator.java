/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package com.osm.web.action.auth;


import com.google.common.collect.Sets;
import com.osm.model.Role;
import com.osm.model.User;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * A data populator that creates a set of security tables and test data that can
 * be used by the Shiro Spring sample application to demonstrate the use of the
 * {@link org.apache.shiro.realm.jdbc.JdbcRealm} The tables created by this
 * class follow the default table and column names that
 * {@link org.apache.shiro.realm.jdbc.JdbcRealm} uses.
 * 
 */
@Component
public class BootstrapDataPopulator implements InitializingBean {

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        // because we're using an in-memory hsqldb for the sample app, a new one
        // will be created each time the
        // app starts, so insert the sample admin user at startup:
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        //the whole user creation is ugly.
        //there must be a better way.
        User user = new User();
        user.setUsername("admin");
        user.setPassword(new Sha256Hash("admin").toHex());

        session.save(user);

        Role role = new Role();
        role.setName("user");
        role.setDescription("The default role given to all users.");

        session.save(role);

        role = new Role();
        role.setName("admin");
        role.setDescription("The administrator role only given to site admins");

        session.save(role);

        user.setRoles(Sets.newHashSet(role));

        session.update(user);

        role.setPermissions(Sets.newHashSet("user:*"));

        session.update(role);

        transaction.commit();

    }
}