package com.osm.auth.shiro;

import com.osm.model.Role;
import com.osm.model.User;
import com.osm.repository.UserDAO;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * The Spring/Hibernate sample application's one and only configured Apache Shiro Realm.
 *
 * <p>Because a Realm is really just a security-specific DAO, we could have just made Hibernate calls directly in the implementation
 * and named it a 'HibernateRealm' or something similar.</p>
 *
 * <p>But we've decided to make the calls to the database using a UserDAO, since a DAO would be used in other areas of a 'real'
 * application in addition to here. We felt it better to use that same DAO to show code re-use.</p>
 */
@Component
public class SampleRealm extends AuthorizingRealm {

    protected UserDAO userDAO = null;

    public SampleRealm() {
        setName("SampleRealm"); //This name must match the name in the User class's getPrincipals() method
        setCredentialsMatcher(new HashedCredentialsMatcher(Sha256Hash.ALGORITHM_NAME));
    }

    @Autowired
    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
        User user = userDAO.findUser(token.getUsername());
        if (user == null) {
            return null;
        }
        
        return new SimpleAuthenticationInfo(user, user.getPassword(), getName());
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        User userId = (User) principals.fromRealm(getName()).iterator().next();
        User user = userDAO.getUser(userId.getId());
        
        if (user == null) {
            return null;
        }
        
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        for (Role role : user.getRoles()) {
            info.addRole(role.getName());
            info.addStringPermissions(role.getPermissions());
        }
        
        return info;
    }
}