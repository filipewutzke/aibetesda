package br.com.aibetesda.controladores;

import java.util.Date;
import java.util.List;

import br.com.aibetesda.dao.DAOUtils;
import br.com.aibetesda.dao.especificos.ILancamentoAulaDAO;
import br.com.aibetesda.modelos.Aluno;
import br.com.aibetesda.modelos.AlunoPresenca;
import br.com.aibetesda.modelos.LancamentoAula;
import br.com.aibetesda.modelos.Turma;
import br.com.aibetesda.util.ApplicationContextUtils;

public class ControladorLancamentoAula extends Controlador<LancamentoAula> {

	@Override
	public ILancamentoAulaDAO getDAO() {
		return (ILancamentoAulaDAO) ApplicationContextUtils.getDAOBean(DAOUtils.LANCAMENTOAULA);
	}

	@Override
	public LancamentoAula getById(Long id) {
		return getDAO().read(id);
	}

	@Override
	public LancamentoAula save(LancamentoAula obj) throws Exception {
		return getDAO().create(obj);
	}

	@Override
	public boolean delete(LancamentoAula obj) throws Exception {
		return false;
	}

	@Override
	public List<LancamentoAula> loadAll() {
		return getDAO().readAll();
	}
	
	public List<LancamentoAula> getLancamentoDataTurma(Date data, Turma turma){
		return getDAO().getLancamentoDataTurma(data, turma);
	}
	
	public List<LancamentoAula> getLancamentosData(Date data){
		return getDAO().getLancamentosData(data);
	}

	public LancamentoAula getLancamentoDataAluno(Date data, Aluno aluno) {
		return getDAO().getLancamentosDataAluno(data, aluno);
	}
	
	public List<AlunoPresenca> getAlunosFaltaData(Date data, Date data2){
		return getDAO().getAlunosFaltaDia(data, data2);
	}
	
}
