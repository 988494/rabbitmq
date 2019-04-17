package consumer.pojo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "sys_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "用户标志 - id",dataType="Integer", example = "0")
    private Integer id;
    @ApiModelProperty(value = "用户名 - username",dataType="String")
    @NotBlank
    private String username;
    @ApiModelProperty(value = "密码 - password",dataType="String")
    @NotBlank
    private String password;
    @ApiModelProperty(value = "日期 - date",dataType="Date")
    private Date date;
}
