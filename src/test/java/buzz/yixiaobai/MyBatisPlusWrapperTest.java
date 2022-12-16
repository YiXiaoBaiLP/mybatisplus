package buzz.yixiaobai;

import buzz.yixiaobai.mapper.UserMapper;
import buzz.yixiaobai.pojo.User;
import buzz.yixiaobai.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

/**
 * MyBatisPlus Wrapper 的使用
 * 条件构建器的使用
 */
@SpringBootTest
public class MyBatisPlusWrapperTest {

    /**
     * 用户Mapper
     */
    @Autowired
    private UserMapper userMapper;

    /**
     * 用户Service
     */
    @Autowired
    private UserService userService;

    /**
     *
     */
    @Test
    public void test(){
        //  SELECT uid AS id,user_name AS name,age,email,is_deleted FROM t_user WHERE is_deleted=0
        //  AND (user_name LIKE ? AND age BETWEEN ? AND ? AND email IS NOT NULL)
        QueryWrapper<User> wrapper = new QueryWrapper<>(new User());
        wrapper.like("user_name","张")
                .between("age", 20, 25)
                .isNotNull("email");
//        wrapper.getEntity().setName("张三");
        List<User> userList = userMapper.selectList(wrapper);
        userList.forEach(System.out::println);
    }

    @Test
    public void test02(){
        // 查询用户信息，按照年龄的降序排序，若年龄相同，则按照id升序排序
        // SELECT uid AS id,user_name AS name,age,email,is_deleted FROM t_user WHERE is_deleted=0 ORDER BY age DESC,uid ASC
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("age")
                .orderByAsc("uid");
        List<User> userList = userMapper.selectList(queryWrapper);
        userList.forEach(System.out::println);
    }

    @Test
    public void test03(){
        // 删除邮箱为空的数据
        // 添加了逻辑删除所以这里是Update
        // UPDATE t_user SET is_deleted=1 WHERE is_deleted=0 AND (email IS NULL)
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.isNull("email");
        int count = userMapper.delete(queryWrapper);
        System.out.println("受影响的行数：" + count);
    }

    /**
     * 条件优先级
     */
    @Test
    public void testUpdate(){
        // 将张三修改为李四
        // UPDATE t_user SET user_name=? WHERE is_deleted=0 AND (user_name = ?)
        // UPDATE t_user SET user_name=? WHERE is_deleted=0 AND (user_name = ? OR user_name = ?)
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .eq("user_name", "张三")
                .or()
                .eq("user_name", "张三1");
        User user = new User();
        user.setName("李四");
        // 当第一个参数为null时，会变成查询
        int row = userMapper.update(user, queryWrapper);
        System.out.println("受影响的行数：" + row);
    }

    /**
     * 条件的优先级
     */
    @Test
    public void testPriority(){
        // 将用户名中包含有a并且（年龄大于20或邮箱为null）的用户信息修改
        // UPDATE t_user SET user_name=?, email=? WHERE is_deleted=0 AND (user_name LIKE ? AND (age > ? OR email IS NULL))
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("user_name", "a")
                // lambda的条件，先执行
                // 当前条件的优先级高于外部
                // default Children and(Consumer<Param> consumer)
                .and(i -> i.gt("age", 20).or().isNull("email"));
        User user = new User();
        user.setName("小红");
        user.setEmail("test@test.com");
        userMapper.update(user, queryWrapper);
    }

    /**
     * 指定select查询的字段
     */
    @Test
    public void testSelectSon() {
        // SELECT user_name,email FROM t_user WHERE is_deleted=0
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        // 设置要查询的字段名称
        queryWrapper.select("user_name", "email");
        List<Map<String, Object>>  userList = userMapper.selectMaps(queryWrapper);
        userList.forEach(System.out::println);
    }

    /**
     * 组装一个子查询
     * 括号中带有一个查询语句
     */
    @Test
    public void testAssemble() {
        // 查询
        // id 小于等于 100 的用户信息
        // SELECT uid AS id,user_name AS name,age,email,is_deleted FROM t_user WHERE
        // is_deleted=0 AND (uid IN (select uid from t_user where uid <= 100))
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        String inSql = "select uid from t_user where uid <= 100";
        queryWrapper.inSql("uid", inSql);
        List<User> userList = userMapper.selectList(queryWrapper);
        userList.forEach(System.out::println);
    }
}
