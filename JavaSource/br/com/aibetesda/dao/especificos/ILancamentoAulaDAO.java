package br.com.aibetesda.dao.especificos;

import java.util.Date;
import java.util.List;

import br.com.aibetesda.dao.DAO;
import br.com.aibetesda.modelos.Aluno;
import br.com.aibetesda.modelos.AlunoPresenca;
import br.com.aibetesda.modelos.LancamentoAula;
import br.com.aibetesda.modelos.Turma;

public interface ILancamentoAulaDAO extends DAO<LancamentoAula>{
	public List<LancamentoAula> getLancamentoDataTurma(Date data, Turma turma);
	public List<LancamentoAula> getLancamentosData(Date data);
	public LancamentoAula getLancamentosDataAluno(Date data, Aluno aluno);
	public List<AlunoPresenca> getAlunosFaltaDia(Date dataInicial, Date dataFinal);

}
