package SS6.com.example.parking.controller;

import SS6.com.example.parking.dto.ApiResponse;
import SS6.com.example.parking.dto.TicketRequest;
import SS6.com.example.parking.dto.TicketResponse;
import SS6.com.example.parking.service.ParkingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/tickets")
public class ParkingController {

    private final ParkingService parkingService;

    public ParkingController(ParkingService parkingService) {
        this.parkingService = parkingService;
    }

    @PostMapping("/check-in")
    public ResponseEntity<ApiResponse<TicketResponse>> checkIn(@RequestBody TicketRequest request) {
        try {
            TicketResponse data = parkingService.checkIn(request);
            ApiResponse<TicketResponse> response = new ApiResponse<>(true, "Xe vào bãi thành công!", data);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (IllegalArgumentException | IllegalStateException e) {
            // Xử lý khi có lỗi validate (Sai ID hoặc hết chỗ)
            ApiResponse<TicketResponse> response = new ApiResponse<>(false, e.getMessage(), null);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/check-out/{vehicleId}")
    public ResponseEntity<ApiResponse<TicketResponse>> checkOut(@PathVariable("vehicleId") Long vehicleId) {
        try {
            TicketResponse data = parkingService.checkOut(vehicleId);
            ApiResponse<TicketResponse> response = new ApiResponse<>(true, "Xe ra khỏi bãi thành công!", data);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            ApiResponse<TicketResponse> response = new ApiResponse<>(false, e.getMessage(), null);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }
}