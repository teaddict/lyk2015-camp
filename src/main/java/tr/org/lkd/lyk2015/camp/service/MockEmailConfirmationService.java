package tr.org.lkd.lyk2015.camp.service;

import org.springframework.stereotype.Component;

@Component
public class MockEmailConfirmationService implements EmailService {

	@Override
	public boolean sendConfirmation(String to, String subject, String content) {
		if (to.equals("a@gmail.com")) {
			return false;
		} else {
			return true;
		}
	}

}
