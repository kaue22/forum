package br.com.alura.forum.controller;

import br.com.alura.forum.controller.dto.TopicoDto;
import br.com.alura.forum.modelo.Topico;
import br.com.alura.forum.repository.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TopicosController {

    @Autowired
    private TopicoRepository topicoRepository;
    @RequestMapping("/topicos")
    public List<TopicoDto> lista(String nomeCurso){
    	if(nomeCurso == null) {
        List<Topico> topics = topicoRepository.findAll();
        return TopicoDto.converter(topics);
    	}else {
    	List<Topico> topics = topicoRepository.findByCursoNome(nomeCurso);
    	return TopicoDto.converter(topics);
    	}
       
    }
}
