package spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("infoPrinter")
public class MemberInfoPrinter {
	
	@Autowired
	private MemberDao memberDao;
	@Autowired
	@Qualifier("memberPrinter")
	private MemberPrinter printer;
	
	public void printMemberInfo(String email) {
		Member member = memberDao.selectByEmail(email);
		if(member == null) {
			System.out.println("데이터 없음");
			return;
		}
		
		printer.print(member);
		System.out.println();
	}


}
