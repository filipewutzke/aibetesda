package br.com.aibetesda.servico;

import org.hibernate.Query;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import br.com.aibetesda.dao.HibernateAibetesdaDAO;
import br.com.aibetesda.modelos.Usuario;

@Component(value="userServiceImpl")
public class UserServiceImpl extends HibernateAibetesdaDAO implements UserDetailsService {

	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException, DataAccessException {
		Query q = getSession().createQuery("from Usuario where login = ?");
		q.setParameter(0, login);
		
		Usuario u = (Usuario) q.uniqueResult();
		if(u == null)
			throw new UsernameNotFoundException("Usuário "+login+" não encontrado");
		
		return u;
	}

}
