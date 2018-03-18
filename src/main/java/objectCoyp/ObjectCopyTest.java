package objectCoyp;

import objectCoyp.vo.BusinessObject;
import objectCoyp.vo.Partner;
import objectCoyp.vo.VariableObject;

public class ObjectCopyTest {
	public static void main(String[] args) {
		TurnTool<BusinessObject, VariableObject> tool = new TurnTool<BusinessObject, VariableObject>();

		// test main code
		BusinessObject bo = new BusinessObject();
		tool.turnToTarget(bo, generateTestData());

//		showData(bo);
	}

	/**
	 * Generate test data
	 */
	private static VariableObject generateTestData() {
		VariableObject vo = new VariableObject();
		// set variable
		vo.setAge(28);
		vo.setId(228L);
		vo.setName("unicorn");

		// test Collection whether have copyed
		vo.getWork().add("DBA");
		vo.getWork().add("SD");
		vo.getWork().add("PG");

		// test Collection<Object> whether have copyed
		Partner p1 = new Partner();
		p1.setAge(27);
		p1.setName("Ray");
		p1.getWork().add("PG");
		p1.getWork().add("SD");

		Partner p2 = new Partner();
		p2.setAge(22);
		p2.setName("Kelly");
		p2.getWork().add("PMO");
		p2.getWork().add("秘書");
		p2.getWork().add("行政");

		vo.getPartners().add(p1);
		vo.getPartners().add(p2);

		return vo;
	}

	/**
	 * Show data
	 */
	private static void showData(BusinessObject bo) {
		System.out.println("bo.getAge() = " + bo.getAge());
		System.out.println("bo.getId() = " + bo.getId());
		System.out.println("bo.getName()= " + bo.getName());
		System.out.println("bo.getWork().size() = " + bo.getWork().size());
		for (String work : bo.getWork()) {
			System.out.println("work = " + work);
		}

		System.out.println("bo.getPartners().size() = " + bo.getPartners().size());
		for (Partner partner : bo.getPartners()) {
			System.out.println("partner.getAge() = " + partner.getAge());
			System.out.println("partner.getName() = " + partner.getName());
			System.out.println("partner.getWork().size() = " + partner.getWork().size());
			for (String work : partner.getWork()) {
				System.out.println("partner.work = " + work);
			}
		}

	}

}
