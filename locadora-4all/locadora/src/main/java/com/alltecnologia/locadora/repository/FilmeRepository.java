package com.alltecnologia.locadora.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.alltecnologia.locadora.model.Filme;
 
 
@Repository
public interface FilmeRepository extends JpaRepository<Filme, Integer>{

	Filme findByTituloIgnoreCase(String titulo);
 
	Filme findByIdFilme(Integer idFilme);
	
	@Query(value =  "SELECT f.id_filme,       "
			+       "       f.titulo,         "
			+       "       f.diretor,        " 
			+       "       f.quantidade,     "
			+       "       f.disponivel      "
			+       "  FROM locadora.filme f  "
			+       " WHERE f.quantidade <> 0 " , nativeQuery = true)
	    public List<Filme> findFilmesDisponiveis();
	
	@Transactional
	@Modifying
	@Query(value="  UPDATE locadora.filme f           " + 
			     "    SET  f.disponivel = :disponivel " +   
			     "  WHERE  f.id_filme   = :id_filme", nativeQuery=true) 
	public void updateUnidadeDisponivel(@Param("id_filme") Integer id_filme, @Param("disponivel") Integer disponivel);
	
	@Transactional
	@Modifying
	@Query(value="  UPDATE locadora.filme f           " + 
			     "    SET  f.disponivel = :disponivel," +
			     "         f.quantidade = :quantidade " +   
			     "  WHERE  f.id_filme   = :id_filme", nativeQuery=true) 
	public void updateQuantidadeDisponivel(@Param("id_filme") Integer id_filme, @Param("disponivel") Integer disponivel, @Param("quantidade") Integer quantidade);
	
}
