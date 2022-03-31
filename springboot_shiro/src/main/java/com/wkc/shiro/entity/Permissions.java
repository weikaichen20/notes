package com.wkc.shiro.entity;

/**
 * Created on 2021/09/15.
 *
 * @author Weikaichen
 */
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Permissions {
    private String id;
    private String permissionsName;
}