package edu.poo;

import java.util.List;

public interface IAcademiaDAO<T> {

	void inserir(T t);
	
    List<T> consultar(String nome);
    
}
