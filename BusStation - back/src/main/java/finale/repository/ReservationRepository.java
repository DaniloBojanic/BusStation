package finale.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import finale.model.Reservation;

@Repository
public interface ReservationRepository  extends JpaRepository<Reservation, Long>{
Reservation findOneById(Long id);
	
	List<Reservation> findByIdIn(List<Long> ids);
}
