package esm.dnd.DnDDAM2EnriqueSergioMangel.Configuration.JWT;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import esm.dnd.DnDDAM2EnriqueSergioMangel.Configuration.JWT.modelo.UsuarioPrincipal;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtProvider {
	
	private final static Logger logger = LoggerFactory.getLogger(JwtProvider.class);
	
	//esto me coge el valor de la variable que tengo en el application properties
	@Value("${jwt.secret}")
	private String secret;
	
	@Value("${jwt.expiration}")
	private int expiration;
	
	public String generateToken(Authentication auth) {
		
		UsuarioPrincipal usuarioPrincipal = (UsuarioPrincipal) auth.getPrincipal();
		//creo el nuevo token
		return Jwts.builder().setSubject(usuarioPrincipal.getNickname())
				.setExpiration(new Date(new Date().getTime() + expiration * 10))
				.signWith(SignatureAlgorithm.HS512, secret)
				.compact();
	}
	
	public String getNicknameFromToken(String token) {
		
		//El subject es el nickname
		return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
	}
	
	public boolean validateToken (String token) {
		
		boolean validado = false;
		try {
			
			Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
			validado=true;
			
		} catch (MalformedJwtException e) {
			logger.error("token mal formado");
		} catch (UnsupportedJwtException e) {
			logger.error("token no soportado");
		} catch (ExpiredJwtException e) {
			logger.error("token expirado");
		} catch (IllegalArgumentException e) {
			logger.error("token vacio");
		} catch (SignatureException e) {
			logger.error("fallo en la firma");
		}
		
		return validado;
	}

}
