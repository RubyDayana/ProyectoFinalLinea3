package ucundi.edu.co;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import ucundi.edu.co.service.IAutorService;



@SpringBootTest
class LineaIIIEntrega1ApplicationTests {

	@Test
	void contextLoads() {
	}
	@Autowired
	private BCryptPasswordEncoder bcrypt;
	
	@Autowired
	private IAutorService autorService;
	
	
	@Test
	void vaerificarClave() {
		System.out.println("Resultado:--------------------  " + bcrypt.encode("Cuenta2021"));
		assertTrue(true);
	}
}
