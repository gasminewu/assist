package me.wll.assi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import me.wll.assi.model.dto.BbooksQuery;
import me.wll.assi.model.entity.Bbooks;
import me.wll.assi.model.vo.BbooksVo;
import me.wll.assi.service.BbooksService;
import me.wll.common.bean.PageBean;
import me.wll.common.bean.ResultBean;

/**
* @author wll
* @date 2023-07-19
**/
@Api(tags = "书籍管理")
@RestController
@RequestMapping("/bBooks")
public class BbooksController {

	@Autowired
    private BbooksService bBooksService;

	/**
	 * @param criteria 查询参数
	 * @param pageable 分页参数
	 * @return
	 *
	 * @变更记录 2023年7月19日 wll 创建
	 *
	 */
	@PostMapping(value = "/findBbooks")
    @ApiOperation("查询书籍，分页")
    public ResultBean<PageBean<BbooksVo>> findBbooks(@RequestBody BbooksQuery criteria){
        return new ResultBean<>(this.bBooksService.findBbooks(criteria));
    }
	/**
	 * @param resources 保存对象
	 * @return
	 *
	 * @变更记录 2023年7月19日 wll 创建
	 *
	 */
    @PostMapping(value = "/findBbooksById")
    @ApiOperation("根据主键查询")
    public ResultBean<BbooksVo> findBbooksById(String id){
        return new ResultBean<>(bBooksService.findBbooksById(id));
    }
	/**
	 * @param resources 保存对象
	 * @return
	 *
	 * @变更记录 2023年7月19日 wll 创建
	 *
	 */
    @PostMapping(value = "/createOrUpdateBbooks")
    @ApiOperation("新增或者编辑书籍")
    public ResultBean<?> createOrUpdateBbooks(@RequestBody Bbooks resources){
        bBooksService.createOrUpdateBbooks(resources);
        return new ResultBean<>();
    }
    /**
	 * @param ids 需要删除的主键
	 * @return
	 *
	 * @变更记录 2023年7月19日 wll 创建
	 *
	 */
    @PostMapping(value = "/deleteBbooks")
    @ApiOperation("删除书籍")
    public ResultBean<?> deleteBbooks(@RequestBody String[] ids) {
        bBooksService.deleteAll(ids);
        return new ResultBean<>();
    }
}