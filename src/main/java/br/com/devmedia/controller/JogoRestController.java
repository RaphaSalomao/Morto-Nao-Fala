package br.com.devmedia.controller;

import java.util.ArrayList;
import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.devmedia.model.Jogador;
import br.com.devmedia.repository.JogadorRepository;

@RestController
@RequestMapping("/cidadedorme")
public class JogoRestController {

	@Autowired
	private JogadorRepository jogadorRepository;

	@GetMapping("/jogadores")
	public List<Jogador> listaJogadores(){
		List<Jogador> listaJogadores = jogadorRepository.findAll();
		return listaJogadores;
	}
	
	@GetMapping("/novosJogadores/{jogadores}")
	public String novosJogadores(@PathVariable("jogadores") String jogadores){
		String[] listaJogadores = jogadores.split("-");
		for (String nome : listaJogadores) {
			Jogador jogador = new Jogador();
			jogador.setNome(nome);
			jogadorRepository.save(jogador);
		}
		jogadores = "{";
		for (String nome : listaJogadores) {
			jogadores += "\"nome\": \"" + nome + "\",";
		}
		jogadores += "}";
		jogadores = jogadores.replaceFirst(",}", "}");
		return jogadores;
	}
	

}