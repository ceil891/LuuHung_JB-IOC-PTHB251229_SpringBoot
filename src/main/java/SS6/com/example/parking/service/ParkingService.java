package SS6.com.example.parking.service;

import SS6.com.example.parking.dto.TicketRequest;
import SS6.com.example.parking.dto.TicketResponse;
import SS6.com.example.parking.model.ParkingTicket;
import SS6.com.example.parking.model.Vehicle;
import SS6.com.example.parking.model.Zone;
import SS6.com.example.parking.repository.ParkingTicketRepository;
import SS6.com.example.parking.repository.VehicleRepository;
import SS6.com.example.parking.repository.ZoneRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class ParkingService {

    private final ParkingTicketRepository ticketRepository;
    private final VehicleRepository vehicleRepository;
    private final ZoneRepository zoneRepository;

    public ParkingService(ParkingTicketRepository ticketRepository,
                          VehicleRepository vehicleRepository,
                          ZoneRepository zoneRepository) {
        this.ticketRepository = ticketRepository;
        this.vehicleRepository = vehicleRepository;
        this.zoneRepository = zoneRepository;
    }

    @Transactional
    public TicketResponse checkIn(TicketRequest req) {
        // 1. Kiểm tra Xe có tồn tại trong hệ thống không
        Vehicle vehicle = vehicleRepository.findById(req.getVehicleId())
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy phương tiện với ID: " + req.getVehicleId()));

        // 2. Kiểm tra Khu vực đỗ xe có tồn tại không
        Zone zone = zoneRepository.findById(req.getZoneId())
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy khu vực đỗ xe với ID: " + req.getZoneId()));

        // Validation bổ sung: Kiểm tra xem xe này vốn dĩ đã ở trong bãi từ trước chưa
        if (ticketRepository.existsByVehicleIdAndCheckOutTimeIsNull(vehicle.getId())) {
            throw new IllegalStateException("Phương tiện này hiện đang ở trong bãi, không thể check-in tiếp!");
        }

        // 3. Kiểm tra xem khu vực đỗ xe còn chỗ trống không
        if (zone.getOccupiedSpots() >= zone.getCapacity()) {
            throw new IllegalStateException("Khu vực " + zone.getName() + " đã hết chỗ đỗ xe!");
        }

        // 4. Khởi tạo vé gửi xe mới (ParkingTicket)
        ParkingTicket ticket = new ParkingTicket();
        ticket.setVehicle(vehicle);
        ticket.setZone(zone);
        ticket.setCheckInTime(LocalDateTime.now()); // Lấy thời gian hiện tại lúc quét xe vào

        // 5. Cập nhật tăng số chỗ đã dùng ở Khu vực
        zone.setOccupiedSpots(zone.getOccupiedSpots() + 1);
        zoneRepository.save(zone); // Cập nhật bảng zones

        // 6. Lưu vé đỗ xe vào DB
        ParkingTicket savedTicket = ticketRepository.save(ticket);

        // Map sang dữ liệu DTO trả về cho client
        return new TicketResponse(
                savedTicket.getId(),
                vehicle.getLicensePlate(),
                zone.getName(),
                savedTicket.getCheckInTime(),
                savedTicket.getCheckOutTime()
        );
    }

    @Transactional
    public TicketResponse checkOut(Long vehicleId) {
        // 1. Tìm vé chưa check-out gần đây nhất của xe này
        ParkingTicket ticket = ticketRepository.findTopByVehicleIdAndCheckOutTimeIsNull(vehicleId)
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy lượt gửi xe hợp lệ (chưa ra bãi) cho xe này!"));

        // 2. Cập nhật thời gian xe ra khỏi bãi
        ticket.setCheckOutTime(LocalDateTime.now());

        // 3. Giảm số chỗ đỗ xe đã dùng tại khu vực mà xe đó đang đỗ
        Zone zone = ticket.getZone();
        if (zone.getOccupiedSpots() > 0) {
            zone.setOccupiedSpots(zone.getOccupiedSpots() - 1);
            zoneRepository.save(zone);
        }

        // 4. Lưu lại thông tin vé đã cập nhật thời gian ra
        ParkingTicket updatedTicket = ticketRepository.save(ticket);

        return new TicketResponse(
                updatedTicket.getId(),
                updatedTicket.getVehicle().getLicensePlate(),
                zone.getName(),
                updatedTicket.getCheckInTime(),
                updatedTicket.getCheckOutTime()
        );
    }
}
