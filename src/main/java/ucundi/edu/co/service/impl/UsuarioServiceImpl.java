package ucundi.edu.co.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ucundi.edu.co.entity.Usuario;
import ucundi.edu.co.exception.ArgumentRequiredException;
import ucundi.edu.co.exception.ConflictException;
import ucundi.edu.co.exception.ModelNotFoundException;
import ucundi.edu.co.repository.IUsuarioRepo;
import ucundi.edu.co.service.IUsuarioService;

import org.springframework.security.core.GrantedAuthority;

@Service
public class UsuarioServiceImpl implements IUsuarioService, UserDetailsService {
	
	@Autowired
	private IUsuarioRepo repo;

	

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.print("Entro logueo");
	
		Usuario usuario = repo.findOneByNick(username);		
		if(usuario == null)
			new UsernameNotFoundException("----Usuario no encontrado");
		
		List<GrantedAuthority> roles = new ArrayList<>();
		roles.add(new SimpleGrantedAuthority(usuario.getRol().getNombre()));
		
		UserDetails ud = new User(usuario.getNick(), usuario.getClave(), roles);
		return ud;
	}



	@Override
	public Page<Usuario> retornarPaginado(int page, int size) {
		return repo.findAll(PageRequest.of(page, size));
	}



	@Override
	public Page<Usuario> retornarPaginado(Pageable page) {
		return repo.findAll(page);
	}



	@Override
	public Usuario retonarPorId(Integer idUser) throws ModelNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public void guardar(Usuario user) throws ConflictException {
		
		repo.save(user);
		
	}



	@Override
	public void editar(Usuario user) throws ArgumentRequiredException, ModelNotFoundException, ConflictException {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void eliminar(int idUser) throws ModelNotFoundException {
		// TODO Auto-generated method stub
		
	}


}


