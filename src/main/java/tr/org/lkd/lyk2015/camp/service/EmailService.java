package tr.org.lkd.lyk2015.camp.service;

public interface EmailService {

	public abstract boolean sendConfirmation(String to, String subject, String content);

}
