package net.cris.app.service;

import java.util.List;

import net.cris.app.model.Banner;

public interface IBannersService {
	
	void insertar(Banner banner);
	List<Banner> buscarTodos();

}
