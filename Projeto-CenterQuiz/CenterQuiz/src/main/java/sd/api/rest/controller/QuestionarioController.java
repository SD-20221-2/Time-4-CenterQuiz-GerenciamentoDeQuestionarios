package sd.api.rest.controller;

import java.util.ArrayList;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sd.api.rest.model.Questionario;
import sd.api.rest.repository.QuestionarioRepository;

@RestController
@RequestMapping(value="/api/questionario")
public class QuestionarioController {
	@Autowired // se fosse CDI seria @Inject
	private QuestionarioRepository questionarioRepository;
	
	@GetMapping(value="/todos", produces="application/json")
	public ResponseEntity<List<Questionario>> obterQuestionarios() {
		List<Questionario> questionarios = (List<Questionario>) questionarioRepository.findAll();
		
		return new ResponseEntity<List<Questionario>>(questionarios, HttpStatus.OK);
	}
	
	@GetMapping(value="/id/{id}", produces="application/json")
	public ResponseEntity<Questionario> obterQuestionarioId(@PathVariable (value = "id") Long id) {
		Optional<Questionario> questionario = questionarioRepository.findById(id);
		
		return new ResponseEntity(questionario.get(), HttpStatus.OK);
	}
	
        /*
	@PostMapping(value="", produces="application/json")
	public ResponseEntity<Questionario> cadastrarQuestionario(@RequestBody Questionario questionario) {
		
		Questionario questionarioSalvo = questionarioRepository.save(questionario);
		
		return new ResponseEntity<Questionario>(questionarioSalvo, HttpStatus.OK);
	}
        */
}
