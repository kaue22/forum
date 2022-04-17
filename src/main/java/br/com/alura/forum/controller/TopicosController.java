package br.com.alura.forum.controller;

import br.com.alura.forum.controller.dto.TopicoDto;
import br.com.alura.forum.controller.form.TopicoForm;
import br.com.alura.forum.modelo.Topico;
import br.com.alura.forum.repository.CursoRepository;
import br.com.alura.forum.repository.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/topicos")
public class TopicosController {

    @Autowired
    private TopicoRepository topicoRepository;
    

    @Autowired
    private CursoRepository cursoRepository;
   //@RequestMapping(value = "/topicos", method = RequestMethod.GET)
    @GetMapping
    public List<TopicoDto> lista(String nomeCurso){
    	if(nomeCurso == null) {
        List<Topico> topics = topicoRepository.findAll();
        return TopicoDto.converter(topics);
    	}else {
    	List<Topico> topics = topicoRepository.findByCursoNome(nomeCurso);
    	return TopicoDto.converter(topics);
    	}
    }
    
   //@RequestMapping(value = "/topicos", method = RequestMethod.POST)
    @PostMapping
    public ResponseEntity<TopicoDto> cadastrar(@RequestBody TopicoForm form, UriComponentsBuilder uriBuilder) {
    	Topico topico = form.converter(cursoRepository);
    	topicoRepository.save(topico);
    	
    	URI uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
    	return ResponseEntity.created(uri).body(new TopicoDto(topico));
    	
    }
}
