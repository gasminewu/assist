package me.wll.assi.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

import me.wll.assi.dao.BbooksRepository;
import me.wll.assi.model.dto.BbooksQuery;
import me.wll.assi.model.entity.Bbooks;
import me.wll.assi.model.entity.QBBooks;
import me.wll.assi.model.vo.BbooksVo;
import me.wll.assi.service.BbooksService;
import me.wll.common.bean.PageBean;
import me.wll.common.utils.DateUtil;
import me.wll.common.utils.StringUtils;
import me.wll.common.utils.ValidationUtil;
/**
*
* @description 服务实现
* @author wll
* @date 2023-07-19
**/
@Service
@Transactional(rollbackFor = Exception.class)
public class BbooksServiceImpl implements BbooksService {
	Logger logger = LoggerFactory.getLogger(BbooksServiceImpl.class);
	
	@Autowired
	private JPAQueryFactory queryFactory;
	@Autowired
    private  BbooksRepository bBooksRepository;
	
	private QBBooks bBooks=QBBooks.bBooks;
	
    @Override
    public PageBean<BbooksVo> findBbooks(BbooksQuery query){
    	//查询条件动态拼接
		BooleanBuilder builder = new BooleanBuilder();
		
		if (StringUtils.isNotBlank(query.getKeyword())) {
			builder.and(bBooks.title.like("%" + query.getKeyword()+ "%"));
		}
		// 分页对象
		Pageable pageable = PageRequest.of(query.getCurpage() - 1, query.getPercount());
		// 查询
		JPAQuery<Bbooks> jq  = this.queryFactory.selectFrom(bBooks).where(builder).
				orderBy(bBooks.newtime.desc()).offset(pageable.getOffset()).limit(pageable.getPageSize());
		QueryResults<Bbooks> tuples = jq.fetchResults();
		List<Bbooks> list=jq.fetchResults().getResults();
		
		return null;
    }

    @Override
    public BbooksVo findBbooksById(String id) {
        Bbooks bBooks = bBooksRepository.findById(id).orElseGet(Bbooks::new);
        ValidationUtil.isNull(bBooks.getId(),"BBooks","id",id);
    	return null;
    }

    @Override
    public void createOrUpdateBbooks(Bbooks resources) {
    	//如果是新增
    	if(StringUtils.isBlank(resources.getId())) {
    		resources.setNewtime(DateUtil.getYMD());
    		resources.setState("0");
    	}
    	//顺序号是空，默认+1
    	if(null==resources.getSn()) {
    		//查询最大的顺序号
//    		this.bBooksRepository.find
    	}
    	//判断书籍名称和作者是否重复
    	if(null==resources.getSn()) {
    		
    	}else {
    		
    	}
        bBooksRepository.save(resources);
    }

    @Override
    public void deleteAll(String[] ids) {
        for (String id : ids) {
            bBooksRepository.deleteById(id);
        }
    }
}