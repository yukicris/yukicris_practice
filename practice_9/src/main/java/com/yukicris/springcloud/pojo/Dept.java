package com.yukicris.springcloud.pojo;


import com.oracle.webservices.internal.api.databinding.DatabindingMode;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data //注在类上,提供get,set,equal,hashCode,canEqual,toString 方法
@NoArgsConstructor //注在类上,提供无参构造
@Accessors(chain = true) //链式写法
public class Dept implements Serializable { //Dept 实体类  ,orm msql --dept  类表关系映射
    /**
     * lombok 相关注解
     * @Data
     * @AllArgsConstructor
     * @Setter
     * @Getter
     * @EqualsAndHashCode
     *
     */



    private Long deptno;

    private String dname;

    // 判断这个数据存在哪个数据库的字段_ 微服务 ,一个服务对应一个数据库, 同一个信息可能存在不同的数据库
    private String db_source;

    public Dept(String dname) {
        this.dname = dname;
    }


    /**
     * 链式写法
     *
     * Dept dept = new Dept（）；
     *
     * // 说白了就是一次性塞多个参数,常见于配置文件,如java_config
     * dept.setDeptNo(11).setDname('ssss').setDb_source('001');
     */
}
