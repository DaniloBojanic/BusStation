package finale.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import finale.dto.ReservationDTO;
import finale.model.Line;
import finale.model.Reservation;
import finale.service.LineService;
import finale.service.ReservationService;
import finale.support.ReservationToReservationDto;

@RestController
@RequestMapping(value = "/api/reservations", produces = MediaType.APPLICATION_JSON_VALUE)
public class ReservationController {
	@Autowired
	private LineService lineService;
	
	@Autowired
	private ReservationService reservationService;
	
	@Autowired
	private ReservationToReservationDto toReservationDto;
	
	//@PreAuthorize("hasAnyRole('KORISNIK', 'ADMIN')")
	@GetMapping
	public ResponseEntity<ReservationDTO> create(@RequestParam(required=false) Long id,
			@RequestParam(required=false) int numberOfSeats){
		
		Line line = lineService.findOne(id);
		int nOfSets = numberOfSeats;
		
		if (line.getAvailableSeats() >= nOfSets) {
			line.setAvailableSeats(line.getAvailableSeats() - nOfSets);
			lineService.update(line);
			
			Reservation reservation = new Reservation();
			reservation.setLine(line);
			reservationService.save(reservation);
			
			return new ResponseEntity<>(toReservationDto.convert(reservation), HttpStatus.CREATED);
		}  else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
}
