package persistencia;

import entidade.Questao;
import entidade.Questionario;
import java.util.List;
import org.hibernate.Query;
import util.ITQException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public class GatewayQuestionario {

    @Autowired(required = false)
    private SessionFactory sessionFactory;
    private Session session;

    public List<Questionario> listQuestionarioByDisciplinaByProfessor(String matricula_professor, Integer disciplina_id) throws ITQException {
        session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM Questionario q WHERE q.professor.matricula = :professor AND q.disciplina.id = :disciplina");
        query.setParameter("professor", matricula_professor);
        query.setParameter("disciplina", disciplina_id);
        List<Questionario> questionarios = query.list();
        return questionarios;
    }

    public List<Questao> listQuestoesByQuestionario(Integer questionario_id) throws ITQException {
        session = sessionFactory.getCurrentSession();
        Questionario questionario = session.get(Questionario.class, questionario_id);
        questionario.getQuestoes().size();
        return questionario.getQuestoes();
    }

    @Transactional
    public boolean saveQuestionario(Questionario q) throws ITQException {
        try {
            session = sessionFactory.getCurrentSession();
            Integer questionario_id = (Integer) session.save(q);
            return questionario_id != null;
        } catch (Exception e) {
            throw new ITQException(e.getMessage());
        }
    }
    
    @Transactional
    public boolean updateQuestionario(Questionario q) throws ITQException {
        try {
            session = sessionFactory.getCurrentSession();
            session.update(q);
            return true;
        } catch (Exception e) {
            throw new ITQException(e.getMessage());
        }
    }
}
