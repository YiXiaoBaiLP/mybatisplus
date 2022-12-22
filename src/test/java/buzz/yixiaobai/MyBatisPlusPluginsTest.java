package buzz.yixiaobai;

import buzz.yixiaobai.mapper.UserMapper;
import buzz.yixiaobai.pojo.User;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * MyBatisPlus 分页插件测试
 */
@SpringBootTest
public class MyBatisPlusPluginsTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void pageTest01(){
        // 带有分页功能的查询语句
        // SELECT uid AS id,user_name AS name,age,email,is_deleted FROM
        // t_user WHERE is_deleted=0 LIMIT ?,?
        IPage<User> page = new Page<>(2, 1);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        userMapper.selectPage(page, queryWrapper);
        System.out.println("打印分页信息：" + page);
    }

}
