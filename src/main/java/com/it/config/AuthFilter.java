package com.it.config;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.it.entities.User;
import com.it.repositories.UserRepo;
import com.it.services.UserService;
import com.it.utils.JWTUtils;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class AuthFilter extends OncePerRequestFilter 
{
	@Autowired
	private UserRepo userRepo;
	
	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException 
	{
		return request.getRequestURI().startsWith("/hospital");
	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, 
			HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException 
	{
		
		
		try {
			String header = request.getHeader("Authorization");
			//System.out.println(header);
			if(header!=null && !header.isEmpty())
			{
				String token = header.split(" ")[1];
		
				if(JWTUtils.isValid(token)) 
				{
					int userid = Integer.parseInt(JWTUtils.getData(token));
				
					Optional<User> op = userRepo.findById(userid);
					
					if(op.isPresent())
					{
						User user = op.get();
						UsernamePasswordAuthenticationToken authToken =
			                    new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
			            SecurityContextHolder.getContext().setAuthentication(authToken);
			            filterChain.doFilter(request, response);
					}
					else {
						response.setStatus(HttpStatus.BAD_REQUEST.value());
						PrintWriter pw = response.getWriter();
						pw.write("User Not Found !");
					}
				}else {
					response.setStatus(HttpStatus.BAD_REQUEST.value());
					PrintWriter pw = response.getWriter();
					pw.write("Token Not Valid !");
				}
			}else {
				response.setStatus(HttpStatus.BAD_REQUEST.value());
				PrintWriter pw = response.getWriter();
				pw.write("Token Not Found !");
			}
		}catch(ExpiredJwtException ex) {
			response.setStatus(HttpStatus.BAD_REQUEST.value());
			PrintWriter pw = response.getWriter();
			pw.write("Token is Expired !");
		}
	}
}
