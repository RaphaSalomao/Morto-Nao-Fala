package br.com.devmedia.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.devmedia.model.Jogador;
import br.com.devmedia.model.Role;
import br.com.devmedia.repository.JogadorRepository;
import net.bytebuddy.build.Plugin.Engine.Source.Empty;

@Controller
@RequestMapping("/cidadedorme")
public class JogoController {

	private JogadorRepository jogadorRepository;

	public JogoController(JogadorRepository jogadorRepository) {
		this.jogadorRepository = jogadorRepository;
	}

	@RequestMapping(value = "/novojogador/{nome}", method = RequestMethod.GET)
	public String novoJogador(@PathVariable("nome") String nome, Model model) {
		Jogador entity = new Jogador();
		entity.setNome(nome);
		jogadorRepository.save(entity);
		model.addAttribute("jogador", nome);
		return "cadastrado";
	}

	@RequestMapping(value = "/reinicia", method = RequestMethod.GET)
	public String reinicia() {
		jogadorRepository.deleteAll();
		return "resetado";
	}

	@RequestMapping(value = "/sorteio", method = RequestMethod.GET)
	public String sorteio(Model model) {
		List<Jogador> jogadores = jogadorRepository.findAll();
		if ((jogadores.size() - 3) < 1)
			return "sorteado";
		ArrayList<Role> roles = new ArrayList<Role>();
		roles.add(Role.ASSASSINO);
		roles.add(Role.MEDICO);
		roles.add(Role.DETETIVE);
		jogadores.stream().forEach(j -> {
			if (roles.size() != jogadores.size()) {
				roles.add(Role.CIDADAO);
			}
		});
		Collections.shuffle(roles);
		Collections.shuffle(jogadores);
		jogadores.forEach(j -> {
			j.setRole(roles.get(jogadores.indexOf(j)));
			jogadorRepository.save(j);
		});
		model.addAttribute("jogadores", jogadores);
		return "sorteado";
	}

	@RequestMapping(value = "/papel/{nome}")
	public String papel(@PathVariable("nome") String nome, Model model) {
		List<Jogador> entity = jogadorRepository.findByNome(nome);
		Jogador jogador = entity.get(0);
		model.addAttribute("jogador", jogador);
		return "papel";
	}

	@PostMapping("/jogador")
	public String jogador(@ModelAttribute Jogador jogador, Model model) {
		model.addAttribute("jogador", jogadorRepository.findByNome(jogador.getNome()).get(0));
		return "cadastrado";
	}

	@GetMapping
	public String createForm(Model model) {
		model.addAttribute("jogador", new Jogador());
		return "formulario";
	}

	@PostMapping
	public String jogadorForm(@ModelAttribute Jogador jogador, Model model) {
		jogadorRepository.save(jogador);
		model.addAttribute("jogador", jogador);
		return "cadastrado";
	}

	@GetMapping(value = "/remove/{nome}")
	public String remove(@PathVariable("nome") String nome, Model model) {
		List<Jogador> entity = jogadorRepository.findByNome(nome);
		Jogador jogador = entity.get(0);
		jogadorRepository.delete(jogador);
		model.addAttribute("jogador", new Jogador());
		return "formulario";
	}

}
