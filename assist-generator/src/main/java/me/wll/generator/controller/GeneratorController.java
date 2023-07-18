package me.wll.generator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import me.wll.generator.model.dto.GenConfig;
import me.wll.generator.service.GeneratorService;

/**
 * 代码生成管理
 *
 * @since  2023年7月18日
 * @author 武林林
 *
 */
@RestController
@RequestMapping("/api/generator")
public class GeneratorController {
	
	@Autowired
	private GeneratorService generatorService;
	
    @PostMapping(value = "/generatorCode")
    public ResponseEntity<Object> generatorCode(@RequestBody GenConfig config){
	   generatorService.generator(config);
       return new ResponseEntity<>(HttpStatus.OK);
    }
}
