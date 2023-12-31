package me.wll.assi.service;

import me.wll.assi.model.dto.BbooksQuery;
import me.wll.assi.model.entity.Bbooks;
import me.wll.assi.model.vo.BbooksVo;
import me.wll.common.bean.PageBean;

/**
* 
* @description 服务接口
* @author wll
* @date 2023-07-19
**/
public interface BbooksService {

	/**
	 * 查询数据分页
	 * 
	 * @param query 条件参数
	 * @return
	 *
	 * @变更记录 2023年7月20日 上午9:33:18 武林林 创建
	 *
	 */
	PageBean<BbooksVo> findBbooks(BbooksQuery query);
  
	/**
     * 根据ID查询
     * 
     * @param id
     * @return BBooksVo
     *
     * @变更记录 2023年7月20日 上午9:33:01 武林林 创建
     *
     */
    BbooksVo findBbooksById(String id);

    /**
     * 创建或者编辑书籍
     * 
     * @param resources 
     *
     * @变更记录 2023年7月20日 上午9:32:36 武林林 创建
     *
     */
    void createOrUpdateBbooks(Bbooks resources);

    /**
     * 多选删除
     * 
     * @param ids 需要删除的主键
     *
     * @变更记录 2023年7月20日 上午9:32:09 武林林 创建
     *
     */
    void deleteAll(String[] ids);

   

}