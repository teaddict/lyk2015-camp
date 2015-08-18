package tr.org.lkd.lyk2015.camp.service;

import org.springframework.stereotype.Component;

@Component
public class MockTcknValidationService implements TcknValidationService {

	@Override
	public boolean validate(String name, String surname, Integer birthDate, Long tckn) {
		if (tckn.equals(1L)) {
			return false;
		}

		return true;
	}

}
