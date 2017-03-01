

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import anna.model.Box;
import anna.model.Cookie;
import repo.BoxRepository;
import repo.CookieRepository;

public class RepositoryTest {
	
	CookieRepository cookieRepo;
	BoxRepository boxRepo;
	Long cookieOneId;
	Long cookieTwoId;
	Long boxId;
	
	@Before
	public void setup(){
		cookieRepo = new CookieRepository();
		boxRepo = new BoxRepository();

		cookieOneId = cookieRepo.save(new Cookie("Chocolate", "large"));
		cookieTwoId = cookieRepo.save(new Cookie("Outmeal", "small"));
		
		boxId = boxRepo.save(new Box("large", "Christmas Box"));	
	}
	
	@Test
	public void shouldLinkCookieAndBox(){
		Cookie cookie = cookieRepo.findCookieById(cookieOneId);
		cookie.setBox(boxRepo.findBoxById(boxId));
		cookieRepo.update(cookie);
		assertEquals(cookieRepo.findCookieById(cookieOneId).getBox(),boxRepo.findBoxById(boxId));
	}
	
	@Test
	public void shouldAddCookiesToBox(){
		Box box = boxRepo.findBoxById(boxId);
		
		Cookie cookie= cookieRepo.findCookieById(cookieOneId);
		// bidirectional linking
		box.getCookies().add(cookie);
		cookie.setBox(box);
		cookieRepo.update(cookie);
		
		Cookie cookie2 = cookieRepo.findCookieById(cookieTwoId);
		// bidirectional linking
		box.getCookies().add(cookie2);
		cookie2.setBox(box);
		cookieRepo.update(cookie2);
		
	//	boxRepo.update(box);
		assertTrue(boxRepo.findBoxById(boxId).getCookies().contains(cookie));
		assertTrue(boxRepo.findBoxById(boxId).getCookies().contains(cookie2));

	}
	
}
