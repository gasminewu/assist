package me.wll.assi.model.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import me.wll.common.model.entity.IdEntity;

/**
* 
* @description 书籍
* @author wll
* @since 2023-07-19
**/
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name="b_books")
public class BBooks extends IdEntity {
    
    private static final long serialVersionUID = -7353513661319929347L;

	@Size(max = 100, message = "书籍名称最大长度100")
    @ApiModelProperty(value = "书籍名称")
    private String title;

    @NotBlank
    @Size(max = 50, message = "作者最大长度50")
    @ApiModelProperty(value = "作者")
    private String autor;
    
    @NotNull
    @ApiModelProperty(value = "顺序号")
    private Integer sn;
    
    @NotBlank
    @Size(max = 2, message = "")
    @ApiModelProperty(value = "状态(0初始，5已归档)")
    private String state;
    
    @NotBlank
    @Size(max = 20, message = "新增时间最大长度20")
    @ApiModelProperty(value = "新增时间")
    private String newtime;
    
    @Size(max = 20, message = "")
    @ApiModelProperty(value = "归档时间")
    private String gdtime;
    
    @Size(max = 50, message = "")
    @ApiModelProperty(value = "国家,国内是0，英国")
    private String nation;
    
    @Size(max = 10, message = "")
    @ApiModelProperty(value = "分类")
    private String classi;
    
    @Size(max = 1, message = "")
    @ApiModelProperty(value = "是否有拼音 0否 ")
    private String pinyin;
    
    @Size(max = 1, message = "")
    @ApiModelProperty(value = "喜爱等级 ,0-5（5是最喜欢）  ")
    private String lovel;
    
    @Size(max = 1, message = "")
    @ApiModelProperty(value = "年龄 1（1-3），3（3-6），6(6-)")
    private String agerange;
    
    @Size(max = 1, message = "")
    @ApiModelProperty(value = "是否入手  0否，1是")
    private String buy;
    
    @Size(max = 1000, message = "")
    @ApiModelProperty(value = "备注")
    private String remark;
    

    public void copy(BBooks source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}
