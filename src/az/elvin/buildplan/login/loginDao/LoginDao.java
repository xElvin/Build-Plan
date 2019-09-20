package az.elvin.buildplan.login.loginDao;

import az.elvin.buildplan.login.loginModel.Login;

/**
 * Created by Elvin on 06.08.2019.
 */
public interface LoginDao
{
    Login login(String username, String password) throws Exception;
}
