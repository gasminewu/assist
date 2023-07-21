package me.wll.assi.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import me.wll.assi.model.entity.BBooks;

/**
* 书籍
*
* @author wll
* @date 2023-07-19
**/
public interface BBooksRepository extends JpaRepository<BBooks, String>, JpaSpecificationExecutor<BBooks> {
}