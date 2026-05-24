package SS6.com.example.parking.controller;


import SS6.com.example.parking.dto.ApiResponse;
import SS6.com.example.parking.dto.PageResponse;
import SS6.com.example.parking.dto.VehicleCreateRequest;
import SS6.com.example.parking.dto.VehicleResponse;
import SS6.com.example.parking.service.VehicleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/vehicles")
public class VehicleController {

    private final VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    // 1. Endpoint POST /api/v1/vehicles - Thêm xe mới
    @PostMapping
    public ResponseEntity<ApiResponse<VehicleResponse>> createVehicle(@RequestBody VehicleCreateRequest request) {
        VehicleResponse data = vehicleService.createVehicle(request);
        ApiResponse<VehicleResponse> response = new ApiResponse<>(true, "Tạo mới phương tiện thành công", data);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // 2. Endpoint GET /api/v1/vehicles - Lấy danh sách xe phân trang, tìm kiếm, sắp xếp
    @GetMapping
    public ResponseEntity<ApiResponse<PageResponse<VehicleResponse>>> getVehicles(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "sortBy", required = false) String sortBy,
            @RequestParam(value = "direction", required = false) String direction,
            @RequestParam(value = "keyword", required = false) String keyword
    ) {
        PageResponse<VehicleResponse> data = vehicleService.getPagedVehicles(page, size, sortBy, direction, keyword);
        ApiResponse<PageResponse<VehicleResponse>> response = new ApiResponse<>(true, "Lấy danh sách phương tiện thành công", data);
        return ResponseEntity.ok(response);
    }
}