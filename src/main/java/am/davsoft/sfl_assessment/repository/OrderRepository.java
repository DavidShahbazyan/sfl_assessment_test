package am.davsoft.sfl_assessment.repository;

import am.davsoft.sfl_assessment.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author David Shahbazyan
 * @since Jul 18, 2020
 */
public interface OrderRepository extends JpaRepository<Order, Integer> {
}
