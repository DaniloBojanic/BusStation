package finale.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import finale.model.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long>{

	Company findOneById(Long id);

	List<Company> findByIdIn(List<Long> ids);

}
