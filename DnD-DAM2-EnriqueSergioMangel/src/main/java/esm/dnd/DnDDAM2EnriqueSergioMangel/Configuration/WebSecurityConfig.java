package esm.dnd.DnDDAM2EnriqueSergioMangel.Configuration;



import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig implements WebMvcConfigurer {

    @Bean
    public MongoCustomConversions customConversions(){
        List<Converter<?,?>> converterList = new ArrayList<Converter<?,?>>();
        converterList.add(new StringToEnumConverterCatEquipo());
        converterList.add(new StringToEnumConverterPropiedadEquipo());
        converterList.add(new StringToEnumConverterTipoEquipo());
        converterList.add(new StringToEnumConverterAlineamiento());
        converterList.add(new StringToEnumConverterClase());
        converterList.add(new StringToEnumConverterRaza());
        return new MongoCustomConversions(converterList);
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{

        http.authorizeRequests((requests)->requests.antMatchers("/","/**").permitAll());
        http.csrf().disable().authorizeRequests().anyRequest().authenticated().and().httpBasic();
        http.cors().disable();
        return http.build();
    }
    

    /*
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("GET","POST","PUT","DELETE","OPTIONS"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    @Override
    public void addCorsMappings(CorsRegistry registry){
        registry.addMapping("/**")
        .allowedMethods("GET","POST","PUT","DELETE")
        .allowedOrigins("*");
    }

    @Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.cors().disable();
		return http.build();
	}

     */
}