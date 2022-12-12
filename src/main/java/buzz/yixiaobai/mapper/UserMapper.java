package buzz.yixiaobai.mapper;

import buzz.yixiaobai.pojo.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.MapKey;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface UserMapper extends BaseMapper<User> {

    @MapKey("id")
    Map<String, Object> selectById(Long id);

}
