package com.alltecnologia.locadora.repository;

import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.alltecnologia.locadora.model.Locacao;

@Repository
public interface LocacaoRepository extends JpaRepository<Locacao, Integer>{
	
	Locacao findByCodigoLocacao(Integer codigoLocacao); 
	
	Locacao findByTituloIgnoreCase(String titulo); 
	
	@Transactional
	@Modifying
	@Query(value="  UPDATE  locadora.locacao l                  " + 
			     "     SET  l.data_devolucao   = :dataDevolucao " +   
			     "   WHERE  l.codigo_locacao    = :codigoLocacao ", nativeQuery=true) 
	public void dataDevolucao(@Param("dataDevolucao") Date dataDevolucao, @Param("codigoLocacao") Integer codigoLocacao);
	
}
