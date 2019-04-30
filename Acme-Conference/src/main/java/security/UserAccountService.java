
package security;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UserAccountService {

	@Autowired
	private UserAccountRepository userAccountRepository;


	public UserAccount create() {

		final UserAccount res = new UserAccount();

		return res;
	}

}
