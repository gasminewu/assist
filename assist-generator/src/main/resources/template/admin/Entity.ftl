package ${package}.model.entity;

import java.io.Serializable;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import javax.persistence.*;
<#if isNotNullColumns??>
import javax.validation.constraints.*;
</#if>
<#if hasPk>
import javax.persistence.Entity;
import javax.persistence.Table;
import org.hibernate.annotations.*;
</#if>
<#if hasTimestamp>
import java.sql.Timestamp;
</#if>
<#if hasBigDecimal>
import java.math.BigDecimal;
</#if>

/**
* <#--类描述，作者，和创建时间-->
* @description ${apiAlias}
* @author ${author}
* @since ${date}
**/
@Entity
@Data
@Table(name="${tableName}")
public class ${className} implements Serializable {
	private static final long serialVersionUID = 1L;
<#if columns??>
    <#list columns as column>
	<#--循环所有列-->
	<#--判断是否是主键-->
    <#if column.columnKey = 'PRI'>
    @Id
    @GenericGenerator(name = "jpa-uuid", strategy = "uuid")
	@GeneratedValue(generator = "jpa-uuid")
    </#if>
    <#if column.istNotNull && column.columnKey != 'PRI'>
        <#if column.columnType = 'String'>
    @NotBlank
        <#else>
    @NotNull
        </#if>
    </#if>  
    <#--添加@Size 注解-->
    <#if column.size!= '0'>
    @Size(max = ${column.size}, message = "${column.remark}最大长度${column.size}")
    </#if>  
    <#--添加@ApiModelProperty 注解-->
    <#if column.remark != ''>
    @ApiModelProperty(value = "${column.remark}")
    </#if>    
    private ${column.columnType} ${column.changeColumnName};
    
    </#list>
</#if>

    public void copy(${className} source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}
