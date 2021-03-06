package controlador;

import entidade.Tema;
import java.util.List;
import util.RestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import servico.ServiceTema;
import util.ITQException;
import util.RestMessage;

@RestController
@CrossOrigin
@RequestMapping("ResourceTema")
public class ResourceTema {

    @Autowired
    private ServiceTema serviceTema;

    @RequestMapping(
            value = "/tema",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public RestResponse<RestMessage> saveTema(@RequestBody Tema tema) throws Exception {
        return new RestResponse<>(serviceTema.saveTema(tema));
    }
    
    @RequestMapping(
            value = "/professor/{matricula}/disciplina/{id}/tema",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public RestResponse<List<Tema>> listTemasByDisciplinaByProfessor(
            @PathVariable("matricula") String matricula,
            @PathVariable("id") int id) throws Exception {
        return new RestResponse<>(serviceTema.listTemasByDisciplinaByProfessor(matricula, id));
    }
    
    @RequestMapping(
            value = "/questao/{id}/tema",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public RestResponse<List<Tema>> listTemaByQuestao(@PathVariable("id") int id) throws ITQException{
        return new RestResponse<>(serviceTema.listTemaByQuestao(id));
    }
}