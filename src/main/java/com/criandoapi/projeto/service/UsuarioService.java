package com.criandoapi.projeto.service;

import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.criandoapi.projeto.model.Usuario;
import com.criandoapi.projeto.repository.IUsuario;

@Service
public class UsuarioService {

	private IUsuario repository;
	private PasswordEncoder passwordEncoder;

	public UsuarioService(IUsuario repository) {
		this.repository = repository;
		this.passwordEncoder = new BCryptPasswordEncoder();
	}

	public List<Usuario> listarUsuario() {
		List<Usuario> lista = repository.findAll();
		return lista;
	}

	public Usuario criarUsuario(Usuario usuario) {
		String encoder = passwordEncoder.encode(usuario.getSenha());
		usuario.setSenha(encoder);
		Usuario novoUsuario = repository.save(usuario);
		return novoUsuario;
	}

	public Usuario editarUsuario(Usuario usuario) {
		String encoder = passwordEncoder.encode(usuario.getSenha());
		usuario.setSenha(encoder);
		Usuario novoUsuario = repository.save(usuario);
		return novoUsuario;
	}

	public Boolean excluirUsuario(Integer id) {
		repository.deleteById(id);
		return true;
	}

	public Boolean validarSenha(Usuario usuario) {
		String senha = null;
		Optional<Usuario> usuarioOptional = repository.findById(usuario.getId());
		if (usuarioOptional.isPresent()) {
			senha = usuarioOptional.get().getSenha();
		}
		Boolean valid = (senha != null) && passwordEncoder.matches(usuario.getSenha(), senha);
		return valid;
	}

}
