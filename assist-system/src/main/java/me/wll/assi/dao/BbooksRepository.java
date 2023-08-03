package me.wll.assi.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import me.wll.assi.model.entity.Bbooks;

/**
* 书籍
*
* @author wll
* @date 2023-07-19
**/
public interface BbooksRepository extends JpaRepository<Bbooks, String>, JpaSpecificationExecutor<Bbooks> {
}