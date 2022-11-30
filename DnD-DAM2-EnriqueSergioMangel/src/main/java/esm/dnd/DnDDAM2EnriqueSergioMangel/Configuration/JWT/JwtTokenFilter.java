package esm.dnd.DnDDAM2EnriqueSergioMangel.Configuration.JWT;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.filter.OncePerRequestFilter;

//se ejecuta una vez por cada peticion y comprueba que el token valga
public class JwtTokenFilter extends OncePerRequestFilter{
	
	private final static Logger logger = LoggerFactory.getLogger(JwtTokenFilter.class);
	
	@Autowired
	JwtProvider jwtProvider;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		try {
			
			String token = this.getToken(request);
			
			if(token!= null && jwtProvider.validateToken(token))
			{
				String nickname = jwtProvider.getNicknameFromToken(token);
			}
			
		} catch (Exception e) {
			 logger.error("fallo en el doFilter");
		}
		
	}
	
	private String getToken(HttpServletRequest request) {
		
		String header = request.getHeader("Authorization");
		
		if (header != null && header.startsWith("Bearer"))
		{
			return header.replace("Bearer", "");
		}
		return null;
	}

}
