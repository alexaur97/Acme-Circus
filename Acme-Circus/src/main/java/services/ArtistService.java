package services; 

import java.util.Collection; 

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Service; 
import org.springframework.transaction.annotation.Transactional; 
import org.springframework.util.Assert; 

import repositories.ArtistRepository;

import domain.Artist; 
import domain.Attendee;

@Service 
@Transactional 
public class ArtistService { 

	//Managed repository -------------------
	@Autowired
	private ArtistRepository artistRepository;


	//Supporting Services ------------------


	//COnstructors -------------------------
	public ArtistService(){
		super();
	}


	//Simple CRUD methods--------------------

	public Artist create(){
		Artist result;

		result = new Artist();

		return result;
	}

	public Collection<Artist> findAll(){
		Collection<Artist> result;

		result = artistRepository.findAll();

		return result;
	}

	public Artist findOne(int artistId){
		Artist result;

		result = artistRepository.findOne(artistId);

		return result;
	}

	public void save(Artist artist){
		Assert.notNull(artist);

		artistRepository.save(artist);
	}

	public void delete(Artist artist){
		artistRepository.delete(artist);
	}


	//Other Methods--------------------
} 
