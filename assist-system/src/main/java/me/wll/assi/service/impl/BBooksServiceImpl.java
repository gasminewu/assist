package me.wll.assi.service.impl;

import java.util.List;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

import me.wll.assi.dao.BBooksRepository;
import me.wll.assi.model.dto.BBooksQuery;
import me.wll.assi.model.entity.BBooks;
import me.wll.assi.model.entity.QBBooks;
import me.wll.assi.model.vo.BBooksVo;
import me.wll.assi.service.BBooksService;
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
public class BBooksServiceImpl implements BBooksService {
	Logger logger = LoggerFactory.getLogger(BBooksServiceImpl.class);
	
	@Autowired
	private JPAQueryFactory queryFactory;
	@Autowired
    private  BBooksRepository bBooksRepository;
	
	private QBBooks qBBooks=QBBooks.bBooks;
	
    @Override
    public PageBean<BBooksVo> listBBooks(BBooksQuery query){
    	//查询条件动态拼接
		BooleanBuilder builder = new BooleanBuilder();
		
		if (StringUtils.isNotBlank(query.getKeyword())) {
			builder.and(qBBooks.title.like("%" + query.getKeyword()+ "%"));
		}
		// 分页对象
		Pageable pageable = PageRequest.of(query.getCurpage() - 1, query.getPercount());
		// 查询
		JPAQuery<BBooks> jq  = this.queryFactory.selectFrom(qBBooks).where(builder).
				orderBy(qBBooks.newtime.desc()).offset(pageable.getOffset()).limit(pageable.getPageSize());
		QueryResults<BBooks> tuples = jq.fetchResults();
		List<BBooks> list=jq.fetchResults().getResults();
		
//		return new PageBean<>(bBooksMapper.toDto(list), pageable, tuples.getTotal());
		return null;
    }

    @Override
    @Transactional
    public BBooksVo findBBooksById(String id) {
        BBooks bBooks = bBooksRepository.findById(id).orElseGet(BBooks::new);
        ValidationUtil.isNull(bBooks.getId(),"BBooks","id",id);
//        return bBooksMapper.toDto(bBooks);
    	return null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createOrUpdateBBooks(BBooks resources) {
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