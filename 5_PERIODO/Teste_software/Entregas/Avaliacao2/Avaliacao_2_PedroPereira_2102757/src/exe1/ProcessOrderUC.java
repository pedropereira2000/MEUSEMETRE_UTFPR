package exe1;

public class ProcessOrderUC {
	private Validator validator;
	private TransportService service;
	private EmailSender emailSender;
	private Repository repo;

	public ProcessOrderUC(Validator validator, Repository repo) {
		this.validator = validator;
		this.repo = repo;
	}

	public void setService(TransportService service) {
		this.service = service;
	}

	public void setEmailSender(EmailSender emailSender) {
		this.emailSender = emailSender;
	}

	public int[] process(Order order) {
		/*1*/var errors = validator.validateBasicData(order);
		/*2*/if (!errors.isEmpty()) {
			/*3*/var errorMsg = String.join(",", errors);
			/*4*/throw new IllegalArgumentException(errorMsg);
		}
		/*5*/if (service.isDown() || /*6*/emailSender.isOffline()) {
			/*7*/throw new RuntimeException("Services offline. Try again later.");
		}
		/*8*/int orderedProds = 0, unorderedProds = 0;
		/*9*/for (int prodId : order.getProdIds()) {
			/*10*/var success = repo.orderProduct(prodId);
			/*11*/if (success) {
				/*12*/orderedProds++;
			} /*13*/else {
				/*13*/unorderedProds++;
			}
		}
		/*14*/var transportId = service.makeTag(order.getCode(), order.getAddress());
		/*15*/var emailId = emailSender.sendEmail(order.getEmail(), "Your order", order.getDesc());
		/*16*/int[] ret = { transportId, emailId, orderedProds, unorderedProds };
		/*17*/return ret;
	}

}
