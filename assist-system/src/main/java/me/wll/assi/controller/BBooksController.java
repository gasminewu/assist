package me.wll.assi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import me.wll.assi.model.dto.BBooksQuery;
import me.wll.assi.model.entity.BBooks;
import me.wll.assi.model.vo.BBooksVo;
import me.wll.assi.service.BBooksService;
import me.wll.common.bean.PageBean;
import me.wll.common.bean.ResultBean;

/**
* @author wll
* @date 2023-07-19
**/
@Api(tags = "书籍管理")
@RestController
@RequestMapping("/bBooks")
public class BBooksController {

	@Autowired
    private BBooksService bBooksService;

	/**
	 * @param criteria 查询参数
	 * @param pageable 分页参数
	 * @return
	 *
	 * @变更记录 2023年7月19日 wll 创建
	 *
	 */
	@PostMapping(value = "/listBBooks")
    @ApiOperation("查询书籍，分页")
    public ResultBean<PageBean<BBooksVo>> listBBooks(@RequestBody BBooksQuery criteria){
        return new ResultBean<>(this.bBooksService.listBBooks(criteria));
    }
	/**
	 * @param resources 保存对象
	 * @return
	 *
	 * @变更记录 2023年7月19日 wll 创建
	 *
	 */
    @PostMapping(value = "/findById")
    @ApiOperation("根据主键查询")
    public ResultBean<BBooksVo> findBBooksById(String id){
        return new ResultBean<>(bBooksService.findBBooksById(id));
    }
	/**
	 * @param resources 保存对象
	 * @return
	 *
	 * @变更记录 2023年7月19日 wll 创建
	 *
	 */
    @PostMapping(value = "/createOrUpdateBBooks")
    @ApiOperation("新增或者编辑书籍")
    public ResultBean<?> createOrUpdateBBooks(@RequestBody BBooks resources){
        bBooksService.createOrUpdateBBooks(resources);
        return new ResultBean<>();
    }
    /**
	 * @param ids 需要删除的主键
	 * @return
	 *
	 * @变更记录 2023年7月19日 wll 创建
	 *
	 */
    @PostMapping(value = "/deleteBBooks")
    @ApiOperation("删除书籍")
    public ResultBean<?> deleteBBooks(@RequestBody String[] ids) {
        bBooksService.deleteAll(ids);
        return new ResultBean<>();
    }
}