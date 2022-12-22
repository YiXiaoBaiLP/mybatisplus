package buzz.yixiaobai.mapper;

import buzz.yixiaobai.pojo.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface UserMapper extends BaseMapper<User> {

    @MapKey("id")
    Map<String, Object> selectById(Long id);

    /**
     *  分页查询
     * @param page 这是MyBatisPlus提供的分页功能，必须放到参数的第一个位置
     * @param age
     * @return
     */
    Page<User> selectPageVo(@Param("page")IPage<User> page, @Param("age")Integer age);

}
