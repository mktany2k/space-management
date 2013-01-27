package com.osm.auth.shiro;

import com.osm.model.Role;
import com.osm.model.User;
import com.osm.repository.UserRepository;
import javax.annotation.Resource;
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

@Component
public class OsmRealm extends AuthorizingRealm {

    private UserRepository userRepository;

    @Autowired()
    @Resource(name = "userRepository")
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public OsmRealm() {
        setName("OsmRealm"); //This name must match the name in the User class's getPrincipals() method
        setCredentialsMatcher(new HashedCredentialsMatcher(Sha256Hash.ALGORITHM_NAME));
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
        User user = userRepository.findByUsername(token.getUsername());
        if (user == null) {
            return null;
        }

        return new SimpleAuthenticationInfo(user, user.getPassword(), getName());
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        User userId = (User) principals.fromRealm(getName()).iterator().next();
        User user = userRepository.findOne(userId.getId());

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