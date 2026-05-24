package SS6.com.example.parking.service;


import SS6.com.example.parking.dto.PageResponse;
import SS6.com.example.parking.dto.VehicleCreateRequest;
import SS6.com.example.parking.dto.VehicleResponse;
import SS6.com.example.parking.model.Vehicle;
import SS6.com.example.parking.repository.VehicleRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class VehicleService {

    private final VehicleRepository vehicleRepository;

    public VehicleService(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    @Transactional
    public VehicleResponse createVehicle(VehicleCreateRequest request) {
        Vehicle vehicle = new Vehicle();
        vehicle.setLicensePlate(request.getLicensePlate());
        vehicle.setColor(request.getColor());
        vehicle.setType(request.getType());

        Vehicle savedVehicle = vehicleRepository.save(vehicle);
        return new VehicleResponse(savedVehicle.getId(), savedVehicle.getLicensePlate(), savedVehicle.getColor(), savedVehicle.getType());
    }

    // Logic xử lý Phân trang, Tìm kiếm & Sắp xếp chính
    public PageResponse<VehicleResponse> getPagedVehicles(int page, int size, String sortBy, String direction, String keyword) {
        // Xử lý an toàn: Nếu page nhỏ hơn 0 thì mặc định về 0
        if (page < 0) {
            page = 0;
        }

        // Xử lý đối tượng Sắp xếp (Sort)
        Sort sort = Sort.unsorted();
        if (sortBy != null && !sortBy.trim().isEmpty()) {
            if (direction != null && direction.equalsIgnoreCase("DESC")) {
                sort = Sort.by(sortBy).descending();
            } else {
                sort = Sort.by(sortBy).ascending(); // Mặc định là ASC nếu truyền sortBy nhưng direction null hoặc là ASC
            }
        }

        // Tạo đối tượng Pageable
        Pageable pageable = PageRequest.of(page, size, sort);

        // Chuẩn hóa từ khóa tìm kiếm (Nếu rỗng hoặc chỉ có khoảng trắng thì đưa về null)
        String searchKeyword = (keyword != null && !keyword.trim().isEmpty()) ? keyword.trim() : null;

        // Gọi DB thông qua Repository
        Page<VehicleResponse> vehiclePage = vehicleRepository.findAllByKeyword(searchKeyword, pageable);

        // Đóng gói kết quả trả về theo cấu trúc PageResponse yêu cầu
        return new PageResponse<>(
                vehiclePage.getContent(),
                vehiclePage.getNumber(),
                vehiclePage.getSize(),
                vehiclePage.getTotalElements(),
                vehiclePage.getTotalPages(),
                vehiclePage.isLast()
        );
    }
}