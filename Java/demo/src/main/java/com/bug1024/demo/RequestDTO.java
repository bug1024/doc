package com.bug1024.demo;

import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author wangyu
 * @date 2020-05-09
 */
@Data
public class RequestDTO {

    @NotNull(message = "id不能为空", groups = {Test.class})
    private Long id;

    @NotBlank(message = "姓名不能为空")
    private String name;

    @NotNull(message = "年龄不能为空")
    @Max(value = 100, message = "年龄不能超过100")
    private Integer age;

    @Valid
    @NotNull(message = "subRequest不能为空")
    private SubRequest subRequest;

    @Data
    public static class SubRequest {

        @NotEmpty(message = "地址不能为空")
        private String address;
    }

    public interface Test2 {}

    public interface Test {}
}
