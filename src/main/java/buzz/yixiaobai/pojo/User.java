package buzz.yixiaobai.pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
//@TableName("t_user")
public class User {
    @TableId(value = "uid", type = IdType.AUTO)
    private Long id;
    // 此注解配置数据库中的字段名称
     @TableField("user_name")
    private String name;
    private Integer age;
    private String email;
    // 此注解表示软删除字段
    @TableLogic
    private Integer isDeleted;
}
