package buzz.yixiaobai;

import buzz.yixiaobai.mapper.UserMapper;
import buzz.yixiaobai.pojo.User;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 更新条件构造器
 */
@SpringBootTest
public class UpdateWrapperTest {

    @Autowired
    private UserMapper userMapper;

    /**
     *
     */
    @Test
    public void updateWrapperTest01(){
        // 将用户名称中含有a，并且（年龄大于20或邮箱为null）的用户信息修改
        // UPDATE t_user SET user_name=?,email=? WHERE is_deleted=0 AND (user_name LIKE ? AND (age > ? OR email IS NULL))
        UpdateWrapper<User> userUpdateWrapper = new UpdateWrapper<>();
        userUpdateWrapper.like("user_name", "1")
                .and(i -> i.gt("age", 20)
                        .or()
                        .isNull("email"));
        userUpdateWrapper.set("user_name", "小黑")
                .set("email", "test@atguigu.com");
        int result = userMapper.update(null, userUpdateWrapper);
        System.out.println("result：" + result);
    }
}
