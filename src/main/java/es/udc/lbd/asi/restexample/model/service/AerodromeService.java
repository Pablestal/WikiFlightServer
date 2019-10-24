package es.udc.lbd.asi.restexample.model.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.udc.lbd.asi.restexample.model.domain.Aerodrome;
import es.udc.lbd.asi.restexample.model.repository.AerodromeDAO;
import es.udc.lbd.asi.restexample.model.service.dto.AerodromeDTO;

@Service
@Transactional(readOnly= true, rollbackFor = Exception.class)
public class AerodromeService {

	@Autowired
	private AerodromeDAO aerodromeDAO;
	
	public List<AerodromeDTO> findAll() {
		return aerodromeDAO.findAll().stream().map(aerodrome -> new AerodromeDTO(aerodrome)).collect(Collectors.toList());
	}
	
	public AerodromeDTO findByName(String name) {
		return new AerodromeDTO(aerodromeDAO.findByName(name));
	}
	
	@Transactional(readOnly = false)
	public AerodromeDTO save(Aerodrome aerodrome) {
		String iata = "ZZZ";
		String oaci = "ZZZZ";
		
		if (aerodrome.getCodIATA() != null) {
			iata = aerodrome.getCodIATA();
		} else iata = "ZZZ";
		
		if (aerodrome.getCodOACI() != null) {
			oaci = aerodrome.getCodOACI();
		} else oaci = "ZZZ";

		Aerodrome bdAerodrome= new Aerodrome(iata, oaci, aerodrome.getName(), 
				aerodrome.getCountry(), aerodrome.getCity(), aerodrome.getElevation(), aerodrome.getPosition());
		aerodromeDAO.save(bdAerodrome);
		
		return new AerodromeDTO(bdAerodrome);
	}
	
	@Transactional(readOnly = false)
	public AerodromeDTO update (Aerodrome aerodrome) {
		Aerodrome bdAerodrome = aerodromeDAO.findById(aerodrome.getId());
		bdAerodrome.setCodIATA(aerodrome.getCodIATA());
		bdAerodrome.setCodOACI(aerodrome.getCodOACI());
		bdAerodrome.setName(aerodrome.getName());
		bdAerodrome.setCountry(aerodrome.getCountry());
		bdAerodrome.setCity(aerodrome.getCity());
		bdAerodrome.setElevation(aerodrome.getElevation());
		bdAerodrome.setPosition(aerodrome.getPosition());
		System.out.println("SERVICE >>>> " + aerodrome);

		aerodromeDAO.save(bdAerodrome);
		return new AerodromeDTO(bdAerodrome);
	}
	
	  @Transactional(readOnly = false)
	    public void deleteById(Long id) {
		  aerodromeDAO.deleteById(id);
	    }

}
