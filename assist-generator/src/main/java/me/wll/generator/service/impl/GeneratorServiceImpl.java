package me.wll.generator.service.impl;

import java.io.IOException;

import org.springframework.stereotype.Service;

import me.wll.generator.model.dto.GenConfig;
import me.wll.generator.service.GeneratorService;
import me.wll.generator.utils.GenUtil;

@Service
public class GeneratorServiceImpl implements GeneratorService {

	@Override
	public void generator(GenConfig config) {
		try {
			GenUtil.generatorCode(config.getColumns(), config);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
