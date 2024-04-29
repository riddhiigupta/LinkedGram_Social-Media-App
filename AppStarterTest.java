import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * A program Team Project
 *
 * <p>Purdue University -- CS18000 -- Spring 2024 -- Team Project-- </p>
 *
 * @author Purdue CS
 * @version Mar 31, 2024
 */
public class AppStarterTest {

    @Test
    public void testMain() {
        // 这是一个简单的测试，我们只是调用main方法来看看是否会抛出异常
        // 在实际的测试中，你可能需要使用更复杂的设置来验证你的代码的行为
        assertDoesNotThrow(() -> AppStarter.main(new String[]{}));
    }
}