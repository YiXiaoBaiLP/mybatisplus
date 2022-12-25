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

    /**
     * 分页信息中的所有参数
     */
    @Test
    public void pageTest02(){
        Page<User> page = new Page<>(2, 2);
        userMapper.selectPage(page, null);
        // 获取记录信息
        System.out.println(page.getRecords());
        // 获取页数
        System.out.println(page.getPages());
        // 获取总数
        System.out.println(page.getTotal());
        // 获取又没有下一页
        System.out.println(page.hasNext());
        // 获取有没有上一页
        System.out.println(page.hasPrevious());
    }

    /**
     * 自定义的分页插件来使用查询功能
     */
    @Test
    public void pageTest03(){
        Page<User> page = new Page<>(2, 1);
        Page<User> userMap = userMapper.selectPageVo(page, 20);
        System.out.println(userMap);
    }
}
