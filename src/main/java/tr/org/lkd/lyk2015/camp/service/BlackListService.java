package tr.org.lkd.lyk2015.camp.service;

public interface BlackListService {
	public abstract boolean validate(Long tckn, String email, String name, String surname);
}
