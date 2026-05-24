package SS6.com.example.parking.repository;

import SS6.com.example.parking.model.ParkingTicket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ParkingTicketRepository extends JpaRepository<ParkingTicket, Long> {

    // Tìm kiếm vé gần nhất của một xe mà xe đó CHƯA check-out (chưa rời bãi)
    @Query("SELECT t FROM ParkingTicket t " +
            "WHERE t.vehicle.id = :vehicleId " +
            "AND t.checkOutTime IS NULL " +
            "ORDER BY t.checkInTime DESC")
    Optional<ParkingTicket> findTopByVehicleIdAndCheckOutTimeIsNull(@Param("vehicleId") Long vehicleId);

    // Kiểm tra xem xe này hiện tại đã ở trong bãi chưa (tránh việc 1 xe check-in 2 lần liên tiếp mà chưa ra)
    boolean existsByVehicleIdAndCheckOutTimeIsNull(Long vehicleId);
}