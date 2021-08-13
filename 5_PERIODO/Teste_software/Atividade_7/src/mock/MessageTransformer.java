package mock;

public class MessageTransformer {
	private Logger logger;
	private Helper helper;
	
	public MessageTransformer(Logger logger, Helper helper) {
		this.logger = logger;
		this.helper = helper;
	}
	
	public String trasform(String message) {
		String originalMessage = message;
		for(String censoredWord : helper.getCensoredWords()) {
			message = message.replaceAll(censoredWord, "???");
		}
		
		if(!message.equals(originalMessage)) {
			if(!logger.logTransformation(originalMessage, message))
				throw new RuntimeException("No connection to database");
		}
		
		return message;
	}
}
