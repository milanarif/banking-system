package org.bank.bankingsystem;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

class BankingSystemApplicationTests {

	/* private UserRepository userRepository = Mockito.mock(UserRepository.class);
	private BCryptPasswordEncoder passwordEncoder = Mockito.mock(BCryptPasswordEncoder.class);
	private AccountRepository accountRepository = Mockito.mock(AccountRepository.class);
	private RoleRepository roleRepository = Mockito.mock(RoleRepository.class);

	private UserService userService;

	@BeforeEach
	void setUp() {
		roleRepository.save(new RoleEntity("ROLE_ADMIN"));
		roleRepository.save(new RoleEntity("ROLE_USER"));
		userService = new UserService(userRepository, accountRepository, roleRepository, passwordEncoder);
	} */

	@Test
	void assertTrueIsTrue() {
		assertEquals(true, true);
	}

	/* @Test
	void assertNullNotNull() {
		assertNotNull(null);
	} */

	/* @Test
	void assertFalseIsFalse() {
		assertEquals(false, false);
	} */

}