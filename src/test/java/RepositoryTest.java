

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
	Long cId;
	Long bId;
	
	@Before
	public void setup(){
		cookieRepo = new CookieRepository();
		boxRepo = new BoxRepository();

		cId = cookieRepo.save(new Cookie("Chocolate", "large"));
		bId = boxRepo.save(new Box("large", "Christmas Box"));	
	}
	
	@Test
	public void shouldLinkCookieAndBox(){
		Cookie cookie = cookieRepo.findCookieById(cId);
		cookie.setBox(boxRepo.findBoxById(bId));
		cookieRepo.update(cookie);
		assertEquals(cookieRepo.findCookieById(cId).getBox(),boxRepo.findBoxById(bId));
	}
	
	@Test
	public void shouldAddCookiesToBox(){
		Box box = boxRepo.findBoxById(bId);
		Cookie cookie= cookieRepo.findCookieById(cId);
		box.getCookies().add(cookie);
		boxRepo.save(box);
		assertTrue(boxRepo.findBoxById(bId).getCookies().contains(cookie));
	}
	
}
