package org.bank.bankingsystem.security;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bank.bankingsystem.entity.AccountEntity;
import org.bank.bankingsystem.entity.UserEntity;
import org.bank.bankingsystem.exception.CustomException;
import org.bank.bankingsystem.repository.UserRepository;
import org.bank.bankingsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

	@Autowired
	private BankUserDetailsService jwtUserDetailsService;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	UserService userService;

	@Autowired
	UserRepository userRepository;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {

		final String requestTokenHeader = request.getHeader("Authorization");

		String username = null;
		String jwtToken = null;
		// JWT Token is in the form "Bearer token". Remove Bearer word and get only the Token
		if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
			jwtToken = requestTokenHeader.substring(7);
			try {
				username = jwtTokenUtil.getUsernameFromToken(jwtToken);
			} catch (IllegalArgumentException e) {
				System.out.println("Unable to get JWT Token");
			} catch (ExpiredJwtException e) {
				System.out.println("JWT Token has expired");
			}
		} else {
			logger.warn("JWT Token does not begin with Bearer String");
		}

		if (username != null) {
			UserDetails getUserRole = this.jwtUserDetailsService.loadUserByUsername(username);
			Collection<? extends GrantedAuthority> authorities = getUserRole.getAuthorities();
			String userRole = authorities.toString().substring(1, authorities.toString().length() - 1);

			if (userRole.equals("ROLE_USER")) {
				String requestUri = request.getRequestURI();
				// check if token belongs to user
				if (!requestUri.equals("/users/signup") &&
						!requestUri.equals("/users/findAllLoans") &&
						requestUri.startsWith("/users/")) {
					checkUserTokenOwner(jwtToken, request);
				}
				// check if token belongs to user's account
				if (requestUri.startsWith("/accounts/transaction/") ||
						requestUri.startsWith("/accounts/deposit/") ||
						requestUri.startsWith("/accounts/withdraw/")) {
					checkTransactionAccountOwner(jwtToken, request);
				} else if (!requestUri.equals("/accounts/transactions") &&
						requestUri.startsWith("/accounts/")) {
					checkUserAccountOwner(jwtToken, request);
				}
			}
		}
		//Once we get the token validate it.
		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

			UserDetails userDetails = this.jwtUserDetailsService.loadUserByUsername(username);

			// if token is valid configure Spring Security to manually set authentication
			if (jwtTokenUtil.validateToken(jwtToken, userDetails)) {
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());
				usernamePasswordAuthenticationToken
						.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				// After setting the Authentication in the context, we specify
				// that the current user is authenticated. So it passes the Spring Security Configurations successfully.
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			}
		}
		chain.doFilter(request, response);
	}

	protected void checkUserTokenOwner(String jwtToken, HttpServletRequest request) {
		String requestUri = request.getRequestURI();
		String userIdStr = requestUri.split("/")[2];
		if (userIdStr.length() < 6) {
			Long userId = Long.valueOf(userIdStr);
			String tokenUsername = jwtTokenUtil.getUsernameFromToken(jwtToken);
			UserEntity findUserByUsername = userRepository.findByUsername(tokenUsername);
			Long findUserId = findUserByUsername.getId();

			if (userId != findUserId) {
				throw new CustomException.InvalidUserDetails("Authorization does not match");
			}
		}
	}

	protected void checkUserAccountOwner(String jwtToken, HttpServletRequest request) {
		String requestUri = request.getRequestURI();
		String userIdStr = requestUri.split("/")[2];
		Long userId = Long.valueOf(userIdStr);

		String tokenUsername = jwtTokenUtil.getUsernameFromToken(jwtToken);
		UserEntity findUserByUsername = userRepository.findByUsername(tokenUsername);
		Long findUserId = findUserByUsername.getAccount().getAccountNumber();

		if (userId != findUserId) {
			throw new CustomException.UnauthorizedTransfer("Authorization does not match");
		}
	}

	protected void checkTransactionAccountOwner(String jwtToken, HttpServletRequest request) {
		String requestURI = request.getRequestURI();
		String senderAccountStr = requestURI.split("/")[3];
		Long senderAccount = Long.valueOf(senderAccountStr);

		String tokenUsername = jwtTokenUtil.getUsernameFromToken(jwtToken);
		UserEntity findUserByUsername = userRepository.findByUsername(tokenUsername);
		AccountEntity userAccount = findUserByUsername.getAccount();

		if (userAccount.getAccountNumber() != senderAccount) {
			throw new CustomException.UnauthorizedTransfer("Authorization does not match");
		}
	}
}
