/**
 * 
 */
package com.salesianostriana.dam.gestionhermandad.seguridad;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.salesianostriana.dam.gestionhermandad.model.Hermano;
import com.salesianostriana.dam.gestionhermandad.services.HermanoServicio;

/**
 * @author usuarioç
 *
 */
@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

	HermanoServicio hermanoServicio;

	public UserDetailsServiceImpl(HermanoServicio servicio) {
		this.hermanoServicio = servicio;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Hermano hermano = hermanoServicio.buscarPorUsuario(username);

		UserBuilder userBuilder = null;

		if (hermano != null) {
			userBuilder = User.withUsername(hermano.getUsuario());
			userBuilder.disabled(false);
			userBuilder.password(hermano.getPassword());
			if (hermano.isAdmin()) {
				// Este caso indica que un ADMIN también puede hacer todo lo que hace un USER
				userBuilder.authorities(new SimpleGrantedAuthority("ROLE_USER"),
						new SimpleGrantedAuthority("ROLE_ADMIN"));
			} else {
				userBuilder.authorities(new SimpleGrantedAuthority("ROLE_USER"));
			}
		} else {
			throw new UsernameNotFoundException("Hermano no encontrado");
		}

		return userBuilder.build();

	}
}
