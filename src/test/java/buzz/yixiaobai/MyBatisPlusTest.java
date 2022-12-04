package buzz.yixiaobai;

import buzz.yixiaobai.mapper.UserMapper;
import buzz.yixiaobai.pojo.User;
import buzz.yixiaobai.service.UserService;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
public class MyBatisPlusTest {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserService userService;

    @Test
    public void testSelectList(){
        List<User> userList = userMapper.selectList(null);
        userList.forEach(System.out::println);
    }

    @Test
    public void testInsert(){
        // 新增用户信息
        // INSERT INTO user ( id, name, age, email ) VALUES ( ?, ?, ?, ? )
        User user = new User();
        user.setAge(23);
        user.setName("张三");
        user.setEmail("zhangsan@atguigu.com");
        int result = userMapper.insert(user);
        System.out.println("result：" + result);
        System.out.println("添加的User id：" + user.getId());
    }

    @Test
    public void testDeleteById(){
        // 通过用户id删除
        // DELETE FROM user WHERE id=?
        int result = userMapper.deleteById(1599318219599040514L);
        System.out.println("删除了几条数据：" + result);
    }

    @Test
    public void testDeleteByMap(){
        // 通过name和age来删除数据
        // DELETE FROM user WHERE name = ? AND age = ?
        Map<String, Object> map = new HashMap<>(2);
        map.put("name", "张三");
        map.put("age", 23);
        int result = userMapper.deleteByMap(map);
        System.out.println("删除了几条数据：" + result);
    }

    @Test
    public void testDeleteBatchList(){
        // 通过ID来批量删除数据
        // DELETE FROM user WHERE id IN ( ? , ? , ? , ? )
        List<Long> idLists = Arrays.asList(
                1599318219599040517L,
                1599318219599040515L,
                1599318219599040516L);
        int result = userMapper.deleteBatchIds(idLists);
        System.out.println("删除了几条数据：" + result);
    }

    @Test
    public void testSelectById(){
        Map<String, Object> result = userMapper.selectById(1599318219599040514L);
        System.out.println("查询到的数据：" + result);
    }

    @Test
    public void testCount(){
        long userCount = userService.count();
        System.out.println("查询到的条数：" + userCount);
    }

}
