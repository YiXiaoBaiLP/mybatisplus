package buzz.yixiaobai;

import buzz.yixiaobai.mapper.UserMapper;
import buzz.yixiaobai.pojo.User;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 测试lambdaWrapper条件构造器
 */
@SpringBootTest
public class LambdaWrapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void test01(){
        String username = "a";
        Integer ageBegin = null;
        Integer ageEnd = 30;
        // SELECT uid AS id,user_name AS name,age,email,is_deleted FROM t_user
        // WHERE is_deleted=0 AND (user_name LIKE ? AND age < ?)
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.like(StringUtils.isNotBlank(username), User::getName, username)
                .eq(ageBegin != null, User::getAge, ageBegin)
                .lt(ageEnd != null, User::getAge,ageEnd);
        userMapper.selectList(lambdaQueryWrapper);

    }

}
