package com.agm.issuemanagement.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    @ApiModelProperty(required = true, value = "ID")
    private Long id;
    @ApiModelProperty(required = true, value = "Name Surname")
    private String nameSurname;
    @ApiModelProperty(required = true, value = "E-Mail")
    private String email;
}
