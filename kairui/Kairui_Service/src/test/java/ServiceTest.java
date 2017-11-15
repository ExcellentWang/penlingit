/*import com.google.gson.Gson;
import com.ontheroad.api.request.UserRequest;
import com.ontheroad.api.response.Response;
import com.ontheroad.user.service.UserService;
import com.ontheroad.utils.SmsUtil;
import common.BaseJunit4Test;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

*//**
 * Created by Administrator on 2017/6/8 0008.
 *//*
public class ServiceTest extends BaseJunit4Test {

    @Autowired
    private UserService userService;

    @Autowired
    private MessageSource messageSource;

    @Before
    public void before() {
        SmsUtil.init(messageSource);
    }

    @Test
    public void test1() {
        UserRequest request = new UserRequest();
        request.setMobile("13277918809");
        try {
            Response response = userService.getVerification(request);
            System.out.println(new Gson().toJson(response));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
*/