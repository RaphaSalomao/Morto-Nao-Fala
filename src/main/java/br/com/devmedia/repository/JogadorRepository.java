package br.com.devmedia.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import br.com.devmedia.model.Jogador;

@Service
public interface JogadorRepository extends JpaRepository<Jogador, Long>{
	
	List<Jogador> findByNome(String nome);
	
}
