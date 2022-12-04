package esm.dnd.DnDDAM2EnriqueSergioMangel.Configuration.JWT;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public class JwtEntryPoint implements AuthenticationEntryPoint {

	//esto se usa en desarrollo para ver de donde salen los errores
	private final static Logger logger = LoggerFactory.getLogger(JwtEntryPoint.class);
	
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,AuthenticationException authException)
			throws IOException, ServletException {
		
		logger.error("fail en el metodo commence");
		response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "no autorizado");
	}

}
