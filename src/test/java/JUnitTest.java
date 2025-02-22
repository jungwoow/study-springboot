import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class JUnitTest {
    @DisplayName("1 + 2는 3이다.") //테스트 이름을 명시
    @Test // 이 애너테이션으로 해당 메서드가 테스트를 수행하는 메서드가 됨
    public void jUnitTest() {
        int a = 1;
        int b = 2;
        int sum = 3;

        Assertions.assertEquals(sum, a + b); // JUnit 에서 제공하는 검증 메서드인 assertEquals() 로 값이 같은지 확인
    }
}
