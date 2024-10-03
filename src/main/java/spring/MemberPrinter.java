package spring;

import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class MemberPrinter {

	// 빈이 없으니까 Autowired 안되는게 맞다.
	private DateTimeFormatter dateTimeFormatter;

	public MemberPrinter() {
		dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일");
	}

	public void print(Member member) {
		if (dateTimeFormatter == null) {
			System.out.printf("회원 정보: 아이디=%d, 이메일=%s, 이름=%s, 등록일=%tF\n", member.getId(), member.getEmail(),
					member.getName(), member.getRegisterDateTime());
		} else {
			System.out.printf("회원 정보: 아이디=%d, 이메일=%s, 이름=%s, 등록일=%s\n", member.getId(), member.getEmail(),
					member.getName(), dateTimeFormatter.format(member.getRegisterDateTime()));
		}
	}
	 
	/*  
	 * 1. required = false : DateTimeFormatter 타입에 대한 빈이 없으므로 의존성 주입이 일어나지 않음(setter 함수 실행 x)
	 * 2. Nullable : DateTimeFormatter 타입에 대한 빈이 없지만 DateTimeFormatter의 값을 null로 설정하고 의존성 주입(setter 함수 실행)
	 *               (MemberPrinter 클래스 생성자의 호출 이후 DateTimeFormatter의 setter 함수가 실행되는데, dateTimeFormatter 값을 다시 null로 바꾸게됨.)
	 */
	@Autowired
	public void setDateTimeFormatter(@Nullable DateTimeFormatter dateTimeFormatter) {
		this.dateTimeFormatter = dateTimeFormatter;
	}
}
